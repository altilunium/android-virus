package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.umeng.analytics.pro.TSerializer;
import com.umeng.analytics.pro.UContent;
import com.umeng.analytics.pro.UMCommonContent;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.internal.UMInternalConfig;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.stateless.UMSLConfig;
import com.umeng.commonsdk.stateless.UMSLNetWorkSender;
import com.umeng.commonsdk.stateless.UMSLUtils;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.idtracking.Envelope;
import com.umeng.commonsdk.statistics.idtracking.IdTracker;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMConstant;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.umeng.commonsdk.statistics.b */
public class EnvelopeManager {

    /* renamed from: a */
    public static String f1359a = null;

    /* renamed from: b */
    public static String f1360b = "";

    /* renamed from: c */
    private static final String f1361c = "EnvelopeManager";

    /* renamed from: d */
    private static final String f1362d = "debug.umeng.umTaskId";

    /* renamed from: e */
    private static final String f1363e = "debug.umeng.umCaseId";

    /* renamed from: f */
    private static final String f1364f = "empty";

    /* renamed from: g */
    private static String f1365g = "";

    /* renamed from: h */
    private static String f1366h = "";

    /* renamed from: i */
    private static String f1367i = null;

    /* renamed from: k */
    private static boolean f1368k;

    /* renamed from: j */
    private int f1369j = 0;

    /* renamed from: a */
    public static void m1341a() {
        if (f1367i != null) {
            f1367i = null;
            IdTracker.m1481a();
        }
    }

    /* renamed from: a */
    public static long m1337a(Context context) {
        long j = DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX - DataHelper.ENVELOPE_EXTRA_LENGTH;
        if (ULog.DEBUG) {
            Log.i(f1361c, "free size is " + j);
        }
        return j;
    }

    /* renamed from: a */
    private JSONObject m1339a(int i, JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                jSONObject.put("exception", i);
            } catch (Exception e) {
            }
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("exception", i);
        } catch (Exception e2) {
        }
        return jSONObject2;
    }

    /* renamed from: b */
    private static boolean m1344b() {
        boolean z;
        boolean z2;
        f1365g = UMUtils.getSystemProperty(f1362d, "");
        f1366h = UMUtils.getSystemProperty(f1363e, "");
        if (TextUtils.isEmpty(f1365g) || f1364f.equals(f1365g)) {
            z = false;
        } else {
            z = true;
        }
        if (TextUtils.isEmpty(f1366h) || f1364f.equals(f1366h)) {
            z2 = false;
        } else {
            z2 = true;
        }
        return z && z2;
    }

    /* renamed from: a */
    public JSONObject mo718a(Context context, JSONObject jSONObject, JSONObject jSONObject2, String str, String str2, String str3) {
        JSONObject jSONObject3;
        Exception e;
        JSONObject jSONObject4;
        String str4;
        Envelope envelope;
        String str5;
        if (!(!ULog.DEBUG || jSONObject == null || jSONObject2 == null)) {
            Log.i(f1361c, "headerJSONObject size is " + jSONObject.toString().getBytes().length);
            Log.i(f1361c, "bodyJSONObject size is " + jSONObject2.toString().getBytes().length);
        }
        if (context == null || jSONObject2 == null) {
            return m1339a(UMErrorCode.E_UM_BE_JSON_FAILED, (JSONObject) null);
        }
        try {
            JSONObject b = m1343b(context);
            if (b == null || jSONObject == null) {
                jSONObject4 = b;
            } else {
                jSONObject4 = m1340a(b, jSONObject);
            }
            if (jSONObject4 != null) {
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (next != null && (next instanceof String)) {
                        String str6 = next;
                        if (jSONObject2.opt(str6) != null) {
                            try {
                                jSONObject4.put(str6, jSONObject2.opt(str6));
                            } catch (Exception e2) {
                            }
                        }
                    }
                }
            }
            String str7 = UMCommonContent.f295aE;
            String str8 = "1.0.0";
            if (!TextUtils.isEmpty(str2)) {
                str7 = str2;
            }
            if (!TextUtils.isEmpty(str3)) {
                str8 = str3;
            }
            if (jSONObject4 != null) {
                String str9 = str7 + "==" + str8 + "&=";
                if (TextUtils.isEmpty(str9)) {
                    return m1339a(UMErrorCode.E_UM_BE_SAVE_FAILED, jSONObject4);
                }
                if (str9.endsWith("&=")) {
                    str4 = str9.substring(0, str9.length() - 2);
                } else {
                    str4 = str9;
                }
            } else {
                str4 = null;
            }
            if (jSONObject4 != null) {
                try {
                    IdTracker a = IdTracker.m1480a(context);
                    if (a != null) {
                        a.mo790b();
                        String encodeToString = Base64.encodeToString(new TSerializer().mo398a(a.mo791c()), 0);
                        if (!TextUtils.isEmpty(encodeToString)) {
                            JSONObject jSONObject5 = jSONObject4.getJSONObject("header");
                            jSONObject5.put(UMCommonContent.f288Y, encodeToString);
                            jSONObject4.put("header", jSONObject5);
                        }
                    }
                } catch (Exception e3) {
                }
            }
            if (jSONObject4 == null || !DataHelper.largeThanMaxSize((long) jSONObject4.toString().getBytes().length, DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX)) {
                if (jSONObject4 != null) {
                    Envelope a2 = m1338a(context, jSONObject4.toString().getBytes());
                    if (a2 == null) {
                        return m1339a(UMErrorCode.E_UM_BE_CREATE_FAILED, jSONObject4);
                    }
                    envelope = a2;
                } else {
                    envelope = null;
                }
                if (envelope != null && DataHelper.largeThanMaxSize((long) envelope.toBinary().length, DataHelper.ENVELOPE_LENGTH_MAX)) {
                    return m1339a(UMErrorCode.E_UM_BE_FILE_OVERSIZE, jSONObject4);
                }
                if (jSONObject4 != null) {
                    str5 = jSONObject4.optJSONObject("header").optString("app_version");
                } else {
                    str5 = null;
                }
                int a3 = m1336a(context, envelope, str4, str5, str);
                if (a3 != 0) {
                    return m1339a(a3, jSONObject4);
                }
                if (ULog.DEBUG) {
                    Log.i(f1361c, "constructHeader size is " + jSONObject4.toString().getBytes().length);
                }
                if (!str4.startsWith(UMCommonContent.f292aB) && !str4.startsWith(UMCommonContent.f291aA) && !str4.startsWith(UMCommonContent.f296aF) && !str4.startsWith(UMCommonContent.f336at) && !UMSLNetWorkSender.m1289a()) {
                    new UMSLNetWorkSender(context);
                    UMSLNetWorkSender.m1291b();
                }
                return jSONObject4;
            }
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (sharedPreferences != null) {
                sharedPreferences.edit().putInt("serial", sharedPreferences.getInt("serial", 1) + 1).commit();
            }
            return m1339a(UMErrorCode.E_UM_BE_RAW_OVERSIZE, jSONObject4);
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
            if (jSONObject != null) {
                try {
                    JSONObject jSONObject6 = new JSONObject();
                    try {
                        jSONObject6.put("header", jSONObject);
                    } catch (JSONException e4) {
                    } catch (Exception e5) {
                        e = e5;
                        jSONObject3 = jSONObject6;
                        UMCrashManager.reportCrash(context, e);
                        return m1339a(UMErrorCode.E_UM_BE_JSON_FAILED, jSONObject3);
                    }
                    jSONObject3 = jSONObject6;
                } catch (Exception e6) {
                    e = e6;
                    jSONObject3 = null;
                    UMCrashManager.reportCrash(context, e);
                    return m1339a(UMErrorCode.E_UM_BE_JSON_FAILED, jSONObject3);
                }
            } else {
                jSONObject3 = null;
            }
            if (jSONObject3 == null) {
                try {
                    jSONObject3 = new JSONObject();
                } catch (Exception e7) {
                    e = e7;
                    UMCrashManager.reportCrash(context, e);
                    return m1339a(UMErrorCode.E_UM_BE_JSON_FAILED, jSONObject3);
                }
            }
            Iterator<String> keys2 = jSONObject2.keys();
            while (keys2.hasNext()) {
                String next2 = keys2.next();
                if (next2 != null && (next2 instanceof String)) {
                    String str10 = next2;
                    if (jSONObject2.opt(str10) != null) {
                        try {
                            jSONObject3.put(str10, jSONObject2.opt(str10));
                        } catch (Exception e8) {
                        }
                    }
                }
            }
            return m1339a(UMErrorCode.E_UM_BE_JSON_FAILED, jSONObject3);
        }
    }

    /* renamed from: a */
    public JSONObject mo717a(Context context, JSONObject jSONObject, JSONObject jSONObject2, String str) {
        Envelope envelope;
        String str2;
        try {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("header", new JSONObject());
            try {
                if (m1344b()) {
                    jSONObject.put("umTaskId", f1365g);
                    jSONObject.put("umCaseId", f1366h);
                }
            } catch (Throwable th) {
            }
            if (jSONObject != null) {
                jSONObject3 = m1340a(jSONObject3, jSONObject);
            }
            if (!(jSONObject3 == null || jSONObject2 == null)) {
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (next != null && (next instanceof String)) {
                        String str3 = next;
                        if (jSONObject2.opt(str3) != null) {
                            try {
                                jSONObject3.put(str3, jSONObject2.opt(str3));
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
            if (jSONObject3 == null || !DataHelper.largeThanMaxSize((long) jSONObject3.toString().getBytes().length, DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX)) {
                if (jSONObject3 != null) {
                    Envelope a = m1338a(context, jSONObject3.toString().getBytes());
                    if (a == null) {
                        return m1339a(UMErrorCode.E_UM_BE_CREATE_FAILED, jSONObject3);
                    }
                    envelope = a;
                } else {
                    envelope = null;
                }
                if (envelope != null && DataHelper.largeThanMaxSize((long) envelope.toBinary().length, DataHelper.ENVELOPE_LENGTH_MAX)) {
                    return m1339a(UMErrorCode.E_UM_BE_FILE_OVERSIZE, jSONObject3);
                }
                if (jSONObject3 != null) {
                    str2 = jSONObject3.optJSONObject("header").optString("app_version");
                } else {
                    str2 = null;
                }
                int a2 = m1336a(context, envelope, "z==1.2.0", str2, str);
                if (a2 != 0) {
                    return m1339a(a2, jSONObject3);
                }
                if (ULog.DEBUG) {
                    Log.i(f1361c, "constructHeader size is " + jSONObject3.toString().getBytes().length);
                }
                return jSONObject3;
            }
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (sharedPreferences != null) {
                sharedPreferences.edit().putInt("serial", sharedPreferences.getInt("serial", 1) + 1).commit();
            }
            return m1339a(UMErrorCode.E_UM_BE_RAW_OVERSIZE, jSONObject3);
        } catch (Throwable th2) {
            UMCrashManager.reportCrash(context, th2);
            return m1339a(UMErrorCode.E_UM_BE_JSON_FAILED, new JSONObject());
        }
    }

    /* renamed from: b */
    private static JSONObject m1343b(Context context) {
        JSONObject jSONObject;
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (!TextUtils.isEmpty(f1367i)) {
                try {
                    jSONObject = new JSONObject(f1367i);
                } catch (Exception e) {
                    jSONObject = null;
                }
            } else {
                UMUtils.saveSDKComponent();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(UMCommonContent.f367p, DeviceConfig.getAppMD5Signature(context));
                jSONObject2.put(UMCommonContent.f368q, DeviceConfig.getAppSHA1Key(context));
                jSONObject2.put(UMCommonContent.f369r, DeviceConfig.getAppHashKey(context));
                jSONObject2.put("app_version", DeviceConfig.getAppVersionName(context));
                jSONObject2.put("version_code", Integer.parseInt(DeviceConfig.getAppVersionCode(context)));
                jSONObject2.put(UMCommonContent.f373v, DeviceConfig.getDeviceIdUmengMD5(context));
                jSONObject2.put(UMCommonContent.f374w, DeviceConfig.getCPU());
                String mccmnc = DeviceConfig.getMCCMNC(context);
                if (!TextUtils.isEmpty(mccmnc)) {
                    jSONObject2.put(UMCommonContent.f265B, mccmnc);
                    f1360b = mccmnc;
                } else {
                    jSONObject2.put(UMCommonContent.f265B, "");
                }
                String subOSName = DeviceConfig.getSubOSName(context);
                if (!TextUtils.isEmpty(subOSName)) {
                    jSONObject2.put(UMCommonContent.f274K, subOSName);
                }
                String subOSVersion = DeviceConfig.getSubOSVersion(context);
                if (!TextUtils.isEmpty(subOSVersion)) {
                    jSONObject2.put(UMCommonContent.f275L, subOSVersion);
                }
                String deviceType = DeviceConfig.getDeviceType(context);
                if (!TextUtils.isEmpty(deviceType)) {
                    jSONObject2.put(UMCommonContent.f325ai, deviceType);
                }
                jSONObject2.put(UMCommonContent.f366o, DeviceConfig.getPackageName(context));
                jSONObject2.put(UMCommonContent.f372u, "Android");
                jSONObject2.put("device_id", DeviceConfig.getDeviceId(context));
                jSONObject2.put("device_model", Build.MODEL);
                jSONObject2.put(UMCommonContent.f268E, Build.BOARD);
                jSONObject2.put(UMCommonContent.f269F, Build.BRAND);
                jSONObject2.put(UMCommonContent.f270G, Build.TIME);
                jSONObject2.put(UMCommonContent.f271H, Build.MANUFACTURER);
                jSONObject2.put(UMCommonContent.f272I, Build.ID);
                jSONObject2.put(UMCommonContent.f273J, Build.DEVICE);
                jSONObject2.put(UMCommonContent.f376y, Build.VERSION.RELEASE);
                jSONObject2.put(UMCommonContent.f375x, "Android");
                int[] resolutionArray = DeviceConfig.getResolutionArray(context);
                if (resolutionArray != null) {
                    jSONObject2.put(UMCommonContent.f377z, resolutionArray[1] + "*" + resolutionArray[0]);
                }
                jSONObject2.put(UMCommonContent.f264A, DeviceConfig.getMac(context));
                jSONObject2.put(UMCommonContent.f276M, DeviceConfig.getTimeZone(context));
                String[] localeInfo = DeviceConfig.getLocaleInfo(context);
                jSONObject2.put(UMCommonContent.f278O, localeInfo[0]);
                jSONObject2.put(UMCommonContent.f277N, localeInfo[1]);
                jSONObject2.put(UMCommonContent.f279P, DeviceConfig.getNetworkOperatorName(context));
                jSONObject2.put(UMCommonContent.f370s, DeviceConfig.getAppName(context));
                String[] networkAccessMode = DeviceConfig.getNetworkAccessMode(context);
                if ("Wi-Fi".equals(networkAccessMode[0])) {
                    jSONObject2.put(UMCommonContent.f280Q, "wifi");
                } else if ("2G/3G".equals(networkAccessMode[0])) {
                    jSONObject2.put(UMCommonContent.f280Q, "2G/3G");
                } else {
                    jSONObject2.put(UMCommonContent.f280Q, "unknow");
                }
                if (!"".equals(networkAccessMode[1])) {
                    jSONObject2.put(UMCommonContent.f281R, networkAccessMode[1]);
                }
                if (FieldManager.allow(UMConstant.f1603H)) {
                    jSONObject2.put(UMCommonContent.f282S, DeviceConfig.getIPAddress(context));
                }
                jSONObject2.put(UMCommonContent.f283T, DeviceConfig.getNetworkType(context));
                jSONObject2.put(UMCommonContent.f343b, "9.3.7");
                jSONObject2.put(UMCommonContent.f354c, SdkVersion.SDK_TYPE);
                jSONObject2.put(UMCommonContent.f355d, SdkVersion.MINI_VERSION);
                if (!TextUtils.isEmpty(f1359a)) {
                    jSONObject2.put(UMCommonContent.f356e, f1359a);
                }
                jSONObject2.put(UMCommonContent.f326aj, Build.VERSION.SDK_INT);
                if (!TextUtils.isEmpty(UMUtils.VALUE_REC_VERSION_NAME)) {
                    jSONObject2.put(UMCommonContent.f322af, UMUtils.VALUE_REC_VERSION_NAME);
                }
                try {
                    String uUIDForZid = UMUtils.getUUIDForZid(context);
                    if (TextUtils.isEmpty(uUIDForZid)) {
                        UMUtils.setUUIDForZid(context);
                        uUIDForZid = UMUtils.getUUIDForZid(context);
                    }
                    jSONObject2.put("session_id", uUIDForZid);
                } catch (Throwable th) {
                }
                f1367i = jSONObject2.toString();
                jSONObject = jSONObject2;
            }
            if (jSONObject == null) {
                return null;
            }
            try {
                jSONObject.put(UMCommonContent.f327ak, UMUtils.getOaidRequiredTime(context));
            } catch (Exception e2) {
            }
            try {
                jSONObject.put(UMCommonContent.f284U, sharedPreferences.getInt("successful_request", 0));
                jSONObject.put(UMCommonContent.f285V, sharedPreferences.getInt(UMCommonContent.f285V, 0));
                jSONObject.put(UMCommonContent.f286W, sharedPreferences.getInt("last_request_spent_ms", 0));
                String zid = UMUtils.getZid(context);
                if (!TextUtils.isEmpty(zid)) {
                    jSONObject.put(UMCommonContent.f328al, zid);
                }
                if (!TextUtils.isEmpty(UMUtils.VALUE_ASMS_VERSION)) {
                    jSONObject.put(UMCommonContent.f329am, UMUtils.VALUE_ASMS_VERSION);
                }
            } catch (Exception e3) {
            }
            jSONObject.put("channel", UMUtils.getChannel(context));
            jSONObject.put("appkey", UMUtils.getAppkey(context));
            try {
                String deviceToken = UMUtils.getDeviceToken(context);
                if (!TextUtils.isEmpty(deviceToken)) {
                    jSONObject.put(UMCommonContent.f290a, deviceToken);
                }
            } catch (Exception e4) {
                UMCrashManager.reportCrash(context, e4);
            }
            try {
                String imprintProperty = UMEnvelopeBuild.imprintProperty(context, UMCommonContent.f358g, null);
                if (!TextUtils.isEmpty(imprintProperty)) {
                    jSONObject.put(UMCommonContent.f358g, imprintProperty);
                }
            } catch (Exception e5) {
                UMCrashManager.reportCrash(context, e5);
            }
            try {
                jSONObject.put("wrapper_type", BusinessWrapperConfig.f1356a);
                jSONObject.put("wrapper_version", BusinessWrapperConfig.f1357b);
            } catch (Exception e6) {
            }
            try {
                int targetSdkVersion = UMUtils.getTargetSdkVersion(context);
                boolean checkPermission = UMUtils.checkPermission(context, "android.permission.READ_PHONE_STATE");
                jSONObject.put(UMCommonContent.f309aS, targetSdkVersion);
                if (checkPermission) {
                    jSONObject.put(UMCommonContent.f310aT, "yes");
                } else {
                    jSONObject.put(UMCommonContent.f310aT, "no");
                }
            } catch (Throwable th2) {
            }
            try {
                if (m1344b()) {
                    jSONObject.put("umTaskId", f1365g);
                    jSONObject.put("umCaseId", f1366h);
                }
            } catch (Throwable th3) {
            }
            try {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(UMCommonContent.f314aX, UMInternalConfig.f1210e);
                if (!TextUtils.isEmpty(UMUtils.VALUE_ANALYTICS_VERSION)) {
                    jSONObject3.put(UMCommonContent.f315aY, UMUtils.VALUE_ANALYTICS_VERSION);
                }
                if (!TextUtils.isEmpty(UMUtils.VALUE_GAME_VERSION)) {
                    jSONObject3.put(UMCommonContent.f316aZ, UMUtils.VALUE_GAME_VERSION);
                }
                if (!TextUtils.isEmpty(UMUtils.VALUE_PUSH_VERSION)) {
                    jSONObject3.put(UMCommonContent.f344ba, UMUtils.VALUE_PUSH_VERSION);
                }
                if (!TextUtils.isEmpty(UMUtils.VALUE_SHARE_VERSION)) {
                    jSONObject3.put(UMCommonContent.f345bb, UMUtils.VALUE_SHARE_VERSION);
                }
                if (!TextUtils.isEmpty(UMUtils.VALUE_APM_VERSION)) {
                    jSONObject3.put(UMCommonContent.f346bc, UMUtils.VALUE_APM_VERSION);
                }
                if (!TextUtils.isEmpty(UMUtils.VALUE_VERIFY_VERSION)) {
                    jSONObject3.put(UMCommonContent.f347bd, UMUtils.VALUE_VERIFY_VERSION);
                }
                if (!TextUtils.isEmpty(UMUtils.VALUE_SMS_VERSION)) {
                    jSONObject3.put(UMCommonContent.f348be, UMUtils.VALUE_SMS_VERSION);
                }
                if (!TextUtils.isEmpty(UMUtils.VALUE_REC_VERSION_NAME)) {
                    jSONObject3.put(UMCommonContent.f349bf, UMUtils.VALUE_REC_VERSION_NAME);
                }
                if (!TextUtils.isEmpty(UMUtils.VALUE_VISUAL_VERSION)) {
                    jSONObject3.put(UMCommonContent.f350bg, UMUtils.VALUE_VISUAL_VERSION);
                }
                if (!TextUtils.isEmpty(UMUtils.VALUE_ASMS_VERSION)) {
                    jSONObject3.put(UMCommonContent.f351bh, UMUtils.VALUE_ASMS_VERSION);
                }
                if (!TextUtils.isEmpty(UMUtils.VALUE_LINK_VERSION)) {
                    jSONObject3.put(UMCommonContent.f352bi, UMUtils.VALUE_LINK_VERSION);
                }
                if (!TextUtils.isEmpty(UMUtils.VALUE_ABTEST_VERSION)) {
                    jSONObject3.put(UMCommonContent.f353bj, UMUtils.VALUE_ABTEST_VERSION);
                }
                jSONObject.put(UMCommonContent.f313aW, jSONObject3);
            } catch (Throwable th4) {
            }
            byte[] a = ImprintHandler.getImprintService(context).mo767a();
            if (a != null && a.length > 0) {
                try {
                    jSONObject.put(UMCommonContent.f287X, Base64.encodeToString(a, 0));
                } catch (JSONException e7) {
                    UMCrashManager.reportCrash(context, e7);
                }
            }
            if (jSONObject.length() > 0) {
                return new JSONObject().put("header", jSONObject);
            }
            return null;
        } catch (Throwable th5) {
            UMCrashManager.reportCrash(context, th5);
        }
    }

    /* renamed from: a */
    private JSONObject m1340a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (!(jSONObject == null || jSONObject2 == null || jSONObject.opt("header") == null || !(jSONObject.opt("header") instanceof JSONObject))) {
            JSONObject jSONObject3 = (JSONObject) jSONObject.opt("header");
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next != null && (next instanceof String)) {
                    String str = next;
                    if (jSONObject2.opt(str) != null) {
                        try {
                            jSONObject3.put(str, jSONObject2.opt(str));
                            if (str.equals(UContent.f647i) && (jSONObject2.opt(str) instanceof Integer)) {
                                this.f1369j = ((Integer) jSONObject2.opt(str)).intValue();
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
        return jSONObject;
    }

    /* renamed from: a */
    private Envelope m1338a(Context context, byte[] bArr) {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(context, "codex", null);
        int i = -1;
        try {
            if (!TextUtils.isEmpty(imprintProperty)) {
                i = Integer.valueOf(imprintProperty).intValue();
            }
        } catch (NumberFormatException e) {
            UMCrashManager.reportCrash(context, e);
        }
        if (i == 0) {
            return Envelope.genEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        if (i == 1) {
            return Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        if (f1368k) {
            return Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        return Envelope.genEnvelope(context, UMUtils.getAppkey(context), bArr);
    }

    /* renamed from: a */
    private int m1336a(Context context, Envelope envelope, String str, String str2, String str3) {
        if (context == null || envelope == null || TextUtils.isEmpty(str)) {
            return UMErrorCode.E_UM_BE_SAVE_FAILED;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = DeviceConfig.getAppVersionName(context);
        }
        String b = UMSLUtils.m1324b(str3);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("&&");
        sb.append(str2);
        sb.append("_");
        sb.append(System.currentTimeMillis());
        sb.append("_");
        sb.append(b);
        sb.append(".log");
        byte[] binary = envelope.toBinary();
        if (str.startsWith(UMCommonContent.f292aB) || str.startsWith(UMCommonContent.f291aA) || str.startsWith(UMCommonContent.f336at) || str.startsWith(UMCommonContent.f296aF)) {
            return UMFrUtils.saveEnvelopeFile(context, sb.toString(), binary);
        }
        return UMSLUtils.m1313a(context, UMSLConfig.f1328f, sb.toString(), binary);
    }

    /* renamed from: a */
    public static void m1342a(boolean z) {
        f1368k = z;
    }
}
