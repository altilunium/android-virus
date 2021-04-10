package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.internal.utils.ApplicationLayerUtil;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.umeng.commonsdk.internal.utils.f */
public class InfoPreference {

    /* renamed from: a */
    private static final String f1292a = "info";

    /* renamed from: b */
    private static final String f1293b = "a_dc";

    /* renamed from: c */
    private static final String f1294c = "bssid";

    /* renamed from: d */
    private static final String f1295d = "ssid";

    /* renamed from: e */
    private static final String f1296e = "a_fcy";

    /* renamed from: f */
    private static final String f1297f = "a_hssid";

    /* renamed from: g */
    private static final String f1298g = "a_ip";

    /* renamed from: h */
    private static final String f1299h = "a_ls";

    /* renamed from: i */
    private static final String f1300i = "a_mac";

    /* renamed from: j */
    private static final String f1301j = "a_nid";

    /* renamed from: k */
    private static final String f1302k = "rssi";

    /* renamed from: l */
    private static final String f1303l = "sta";

    /* renamed from: m */
    private static final String f1304m = "ts";

    /* renamed from: n */
    private static final String f1305n = "wifiinfo";

    /* renamed from: o */
    private static final String f1306o = "ua";

    /* renamed from: a */
    public static JSONArray m1260a(Context context) {
        String string;
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f1292a, 0);
            if (sharedPreferences == null || (string = sharedPreferences.getString(f1305n, null)) == null) {
                return null;
            }
            return new JSONArray(string);
        } catch (Exception e) {
            ULog.m1387e(e.getMessage());
            return null;
        }
    }

    /* renamed from: b */
    public static void m1263b(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f1292a, 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(f1305n).commit();
        }
    }

    /* renamed from: a */
    public static void m1261a(Context context, ApplicationLayerUtil.C0167b bVar) {
        JSONArray jSONArray;
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f1292a, 0);
            String str = null;
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString(f1305n, null);
                if (string == null) {
                    jSONArray = new JSONArray();
                } else {
                    jSONArray = new JSONArray(string);
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(f1293b, bVar.f1252a);
                jSONObject.put(f1294c, bVar.f1253b);
                jSONObject.put(f1295d, bVar.f1254c);
                jSONObject.put(f1296e, bVar.f1255d);
                jSONObject.put(f1297f, bVar.f1256e);
                jSONObject.put(f1298g, bVar.f1257f);
                jSONObject.put(f1299h, bVar.f1258g);
                jSONObject.put(f1300i, bVar.f1259h);
                jSONObject.put(f1301j, bVar.f1260i);
                jSONObject.put(f1302k, bVar.f1261j);
                jSONObject.put(f1303l, bVar.f1262k);
                jSONObject.put("ts", bVar.f1263l);
                jSONArray.put(jSONObject);
                str = jSONArray.toString();
            }
            if (str != null) {
                sharedPreferences.edit().putString(f1305n, str).commit();
            }
        } catch (Exception e) {
            ULog.m1387e(e.getMessage());
        }
    }

    /* renamed from: a */
    public static void m1262a(Context context, String str) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f1292a, 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(f1306o, str).commit();
        }
    }

    /* renamed from: c */
    public static String m1264c(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f1292a, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(f1306o, null);
        }
        return null;
    }
}
