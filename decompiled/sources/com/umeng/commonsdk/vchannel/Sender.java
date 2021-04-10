package com.umeng.commonsdk.vchannel;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.UMCommonContent;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.UMInternalConfig;
import com.umeng.commonsdk.internal.UMInternalData;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import java.util.Map;
import org.json.JSONObject;

public class Sender {
    private static long INTERVAL_THRESHOLD = 500;
    public static final String VCHANNEL_VERSION = "1.0.0";
    private static Map customHeader = null;
    private static long lastTriggerTime = 0;

    public static void setCustomHeader(Map map) {
        if (map != null) {
            customHeader = map;
        }
    }

    public static void onEvent(Context context, String str, Map map) {
        if (context == null) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> Sender: onEvent: context is null.");
        } else if (TextUtils.isEmpty(str)) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> Sender: onEvent: eventID is null or an empty string.");
        } else if (map == null) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> Sender: onEvent: map is null.");
        } else if (!UMFrUtils.isOnline(context)) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> Sender: onEvent: Network unavailable.");
        } else if (System.currentTimeMillis() - lastTriggerTime < INTERVAL_THRESHOLD) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> Sender: onEvent: The interval between events is less than 500 milliseconds.");
        } else {
            Event bVar = new Event(context);
            bVar.mo1018a(str);
            bVar.mo1017a(System.currentTimeMillis());
            bVar.mo1019a(map);
            try {
                UMWorkDispatch.sendEvent(context, UMInternalConfig.f1220o, UMInternalData.m1179a(context).mo677a(), bVar);
            } catch (Throwable th) {
            }
            lastTriggerTime = System.currentTimeMillis();
        }
    }

    public static void handleEvent(Context context, Event bVar) {
        if (context == null) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> Sender:handleEvent: context is null.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(UMCommonContent.f303aM, "1.0.0");
                Map map = customHeader;
                if (map != null && map.size() > 0) {
                    for (String str : customHeader.keySet()) {
                        jSONObject.put(str, customHeader.get(str));
                    }
                }
            } catch (Throwable th) {
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("analytics", bVar.mo1022d());
            UMEnvelopeBuild.buildEnvelopeWithExtHeader(context, jSONObject, jSONObject2, Constant.f1663c, UMCommonContent.f293aC, "1.0.0");
        } catch (Throwable th2) {
            UMCrashManager.reportCrash(context, th2);
        }
    }
}
