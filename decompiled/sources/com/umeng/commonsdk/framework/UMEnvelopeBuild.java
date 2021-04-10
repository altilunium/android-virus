package com.umeng.commonsdk.framework;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.CacheDBHelper;
import com.umeng.analytics.pro.Constants;
import com.umeng.analytics.pro.UMCommonContent;
import com.umeng.analytics.pro.UMStoreManager;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.internal.UMInternalConfig;
import com.umeng.commonsdk.internal.UMInternalData;
import com.umeng.commonsdk.statistics.EnvelopeManager;
import com.umeng.commonsdk.statistics.UMErrorCode;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class UMEnvelopeBuild {
    public static boolean transmissionSendFlag = false;

    public static boolean isOnline(Context context) {
        return UMFrUtils.isOnline(context) && (UMConfigure.needSendZcfgEnv(context) ^ true);
    }

    public static long getLastSuccessfulBuildTime(Context context) {
        if (context == null) {
            return 0;
        }
        return UMFrUtils.getLastSuccessfulBuildTime(context.getApplicationContext());
    }

    public static long getLastInstantBuildTime(Context context) {
        if (context == null) {
            return 0;
        }
        return UMFrUtils.getLastInstantBuildTime(context.getApplicationContext());
    }

    public static boolean isReadyBuildNew(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        if (!getTransmissionSendFlag()) {
            return false;
        }
        return isRet(context, uMBusinessType, false);
    }

    public static boolean isReadyBuild(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        return isRet(context, uMBusinessType, false);
    }

    public static boolean isReadyBuildStateless() {
        if (!getTransmissionSendFlag()) {
            return false;
        }
        return true;
    }

    private static boolean isRet(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType, boolean z) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            boolean isOnline = UMFrUtils.isOnline(applicationContext);
            int envelopeFileNumber = UMFrUtils.envelopeFileNumber(applicationContext);
            if (isOnline) {
                if (uMBusinessType == UMLogDataProtocol.UMBusinessType.U_INTERNAL) {
                    if (UMFrUtils.hasEnvelopeFile(applicationContext, uMBusinessType)) {
                        z = false;
                    } else {
                        z = true;
                    }
                } else if (UMNetWorkSender.m1158a()) {
                    UMWorkDispatch.sendDelayProcessMsg((long) UMNetWorkSender.m1160b());
                    z = false;
                } else if (UMFrUtils.hasEnvelopeFile(applicationContext, uMBusinessType) || UMConfigure.needSendZcfgEnv(context)) {
                    z = false;
                } else {
                    z = true;
                }
            }
            if (isOnline && envelopeFileNumber > 0) {
                UMNetWorkSender.m1164d();
            }
        }
        return z;
    }

    private static JSONObject add2CacheTable(Context context, JSONObject jSONObject, JSONObject jSONObject2, String str, String str2, String str3) {
        if (jSONObject == null || jSONObject2 == null) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]构建信封传入 header 或 body 字段为空，直接返回");
            return null;
        }
        UMStoreManager a = UMStoreManager.m853a(context);
        long currentTimeMillis = System.currentTimeMillis();
        UUID randomUUID = UUID.randomUUID();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.f438e, str2);
        contentValues.put(Constants.f439f, a.mo524c(jSONObject.toString()));
        contentValues.put(Constants.f440g, a.mo524c(jSONObject2.toString()));
        contentValues.put(Constants.f441h, String.valueOf(currentTimeMillis));
        contentValues.put(Constants.f442i, randomUUID.toString());
        contentValues.put(Constants.f443j, str);
        contentValues.put(Constants.f444k, str3);
        CacheDBHelper.m506a(context).mo365a(Constants.f436c, contentValues);
        if (UMCommonContent.f291aA.equalsIgnoreCase(str2)) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]inner业务，返回空 JSONObject。");
        } else if (UMCommonContent.f342az.equalsIgnoreCase(str2)) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]分享业务 返回body。");
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("header", new JSONObject());
                jSONObject3.put("share", jSONObject2.getJSONObject("share"));
                return jSONObject3;
            } catch (JSONException e) {
            }
        } else if (!UMCommonContent.f338av.equalsIgnoreCase(str2)) {
            if ("st".equalsIgnoreCase(str2)) {
                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]统计业务 半开报文，返回body。");
                JSONObject jSONObject4 = new JSONObject();
                try {
                    jSONObject4.put("header", new JSONObject());
                    jSONObject4.put("analytics", jSONObject2.getJSONObject("analytics"));
                    return jSONObject4;
                } catch (JSONException e2) {
                    return jSONObject2;
                }
            } else {
                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]统计业务 闭合报文，返回body。");
                JSONObject jSONObject5 = new JSONObject();
                try {
                    jSONObject5.put("header", new JSONObject());
                    jSONObject5.put("analytics", jSONObject2.getJSONObject("analytics"));
                    return jSONObject5;
                } catch (JSONException e3) {
                    return jSONObject2;
                }
            }
        }
        return new JSONObject();
    }

    public static JSONObject buildEnvelopeWithExtHeader(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        return buildEnvelopeWithExtHeader(context, jSONObject, jSONObject2, UMServerURL.PATH_ANALYTICS, jSONObject.has("st") ? UMCommonContent.f296aF : jSONObject2.has(UMCommonContent.f335as) ? UMCommonContent.f291aA : UMCommonContent.f336at, "9.3.7");
    }

    public static JSONObject buildEnvelopeWithExtHeader(Context context, JSONObject jSONObject, JSONObject jSONObject2, String str, String str2, String str3) {
        UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]业务发起构建普通有状态信封请求。");
        JSONObject jSONObject3 = null;
        if (TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject4 = new JSONObject();
                try {
                    jSONObject4.put("exception", UMErrorCode.E_UM_BE_EMPTY_URL_PATH);
                    return jSONObject4;
                } catch (JSONException e) {
                    jSONObject3 = jSONObject4;
                }
            } catch (JSONException e2) {
                return jSONObject3;
            }
        } else if (!UMUtils.isMainProgress(context)) {
            try {
                JSONObject jSONObject5 = new JSONObject();
                try {
                    jSONObject5.put("exception", UMErrorCode.E_UM_BE_NOT_MAINPROCESS);
                    return jSONObject5;
                } catch (JSONException e3) {
                    jSONObject3 = jSONObject5;
                }
            } catch (JSONException e4) {
                return jSONObject3;
            }
        } else if (UMConfigure.needSendZcfgEnv(context)) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]零号报文应答数据 未获取到，写入二级缓存");
            return add2CacheTable(context, jSONObject, jSONObject2, str, str2, str3);
        } else {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]零号报文应答数据 已获取到，判断二级缓存是否为空");
            if (CacheDBHelper.m506a(context).mo370c()) {
                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]二级缓存为空，直接打信封");
                return new EnvelopeManager().mo718a(context.getApplicationContext(), jSONObject, jSONObject2, str, str2, str3);
            }
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]二级缓存不为空，写入二级缓存");
            JSONObject add2CacheTable = add2CacheTable(context, jSONObject, jSONObject2, str, str2, str3);
            if (!UMWorkDispatch.eventHasExist(UMInternalConfig.f1225t)) {
                UMWorkDispatch.sendEvent(context, UMInternalConfig.f1225t, UMInternalData.m1179a(context).mo677a(), null);
            }
            return add2CacheTable;
        }
    }

    public static JSONObject buildZeroEnvelopeWithExtHeader(Context context, JSONObject jSONObject, JSONObject jSONObject2, String str) {
        if (UMUtils.isMainProgress(context)) {
            return new EnvelopeManager().mo717a(context.getApplicationContext(), jSONObject, jSONObject2, str);
        }
        JSONObject jSONObject3 = null;
        try {
            JSONObject jSONObject4 = new JSONObject();
            try {
                jSONObject4.put("exception", UMErrorCode.E_UM_BE_NOT_MAINPROCESS);
                return jSONObject4;
            } catch (JSONException e) {
                jSONObject3 = jSONObject4;
            }
        } catch (JSONException e2) {
            return jSONObject3;
        }
    }

    public static String imprintProperty(Context context, String str, String str2) {
        if (context == null) {
            return str2;
        }
        return ImprintHandler.getImprintService(context.getApplicationContext()).mo771c().mo776a(str, str2);
    }

    public static long maxDataSpace(Context context) {
        if (context == null) {
            return 0;
        }
        return EnvelopeManager.m1337a(context.getApplicationContext());
    }

    public static synchronized boolean getTransmissionSendFlag() {
        boolean z;
        synchronized (UMEnvelopeBuild.class) {
            z = transmissionSendFlag;
        }
        return z;
    }

    public static synchronized void setTransmissionSendFlag(boolean z) {
        synchronized (UMEnvelopeBuild.class) {
            transmissionSendFlag = z;
        }
    }
}
