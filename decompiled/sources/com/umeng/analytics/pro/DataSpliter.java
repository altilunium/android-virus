package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.commonsdk.debug.UMRTLog;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.umeng.analytics.pro.p */
public class DataSpliter {
    /* renamed from: a */
    public static JSONObject m985a(Context context, long j, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (!jSONObject.has("content")) {
                return jSONObject2;
            }
            JSONObject jSONObject3 = jSONObject.getJSONObject("content");
            if (jSONObject3.has("analytics")) {
                JSONObject jSONObject4 = jSONObject3.getJSONObject("analytics");
                if (jSONObject4.has("ekv")) {
                    jSONObject4.remove("ekv");
                }
                if (jSONObject4.has(UContent.f598T)) {
                    jSONObject4.remove(UContent.f598T);
                }
                if (jSONObject4.has(UContent.f593O)) {
                    jSONObject4.remove(UContent.f593O);
                }
                jSONObject3.put("analytics", jSONObject4);
            }
            jSONObject2.put("content", jSONObject3);
            if (jSONObject.has("header")) {
                jSONObject2.put("header", jSONObject.getJSONObject("header"));
            }
            if (m984a(jSONObject2) <= j) {
                return jSONObject2;
            }
            UMStoreManager.m853a(context).mo532i();
            UMStoreManager.m853a(context).mo531h();
            UMStoreManager.m853a(context).mo523b(true, false);
            UMStoreManager.m853a(context).mo514a();
            UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> u-app packet overload !!! ");
            return null;
        } catch (Throwable th) {
            return jSONObject2;
        }
    }

    /* renamed from: a */
    public static long m984a(JSONObject jSONObject) {
        return (long) jSONObject.toString().getBytes().length;
    }

    /* renamed from: a */
    public static long m983a(JSONArray jSONArray) {
        return (long) jSONArray.toString().getBytes().length;
    }
}
