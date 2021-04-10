package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* renamed from: com.umeng.commonsdk.internal.utils.j */
public class UMInternalUtils {

    /* renamed from: a */
    private static final String f1310a = "um_pri";

    /* renamed from: b */
    private static final String f1311b = "um_common_strength";

    /* renamed from: c */
    private static final String f1312c = "um_common_battery";

    /* renamed from: a */
    public static String m1275a(Context context) {
        SharedPreferences sharedPreferences;
        if (context == null || (sharedPreferences = context.getApplicationContext().getSharedPreferences(f1310a, 0)) == null) {
            return null;
        }
        return sharedPreferences.getString(f1312c, null);
    }

    /* renamed from: a */
    public static void m1276a(Context context, String str) {
        SharedPreferences sharedPreferences;
        if (context != null && !TextUtils.isEmpty(str) && (sharedPreferences = context.getApplicationContext().getSharedPreferences(f1310a, 0)) != null) {
            sharedPreferences.edit().putString(f1312c, str).commit();
        }
    }
}
