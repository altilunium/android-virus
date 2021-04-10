package com.umeng.umzid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.lang.reflect.Method;

/* renamed from: com.umeng.umzid.c */
public class C0250c {
    /* renamed from: a */
    public static String m1791a(Context context) {
        SharedPreferences a;
        if (context == null || (a = C0246a.m1787a(context)) == null) {
            return null;
        }
        return a.getString("aaid", null);
    }

    /* renamed from: a */
    public static void m1792a(Context context, String str) {
        SharedPreferences a;
        SharedPreferences.Editor edit;
        if (context != null && str != null && !TextUtils.isEmpty(str) && (a = C0246a.m1787a(context)) != null && (edit = a.edit()) != null) {
            edit.putString("mac", str).commit();
        }
    }

    /* renamed from: b */
    public static String m1793b(Context context) {
        SharedPreferences a;
        return (context == null || (a = C0246a.m1787a(context)) == null) ? "" : a.getString("zdata", null);
    }

    /* renamed from: b */
    public static void m1794b(Context context, String str) {
        SharedPreferences a;
        SharedPreferences.Editor edit;
        if (context != null && str != null && !TextUtils.isEmpty(str) && (a = C0246a.m1787a(context)) != null && (edit = a.edit()) != null) {
            edit.putString("oaid", str).commit();
        }
    }

    /* renamed from: c */
    public static String m1795c(Context context) {
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.statistics.common.DeviceConfig");
            Method declaredMethod = cls.getDeclaredMethod("getMac", Context.class);
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(cls, context);
                if (invoke != null && (invoke instanceof String)) {
                    return (String) invoke;
                }
            }
        } catch (Throwable th) {
        }
        return "";
    }

    /* renamed from: c */
    public static void m1796c(Context context, String str) {
        SharedPreferences a;
        SharedPreferences.Editor edit;
        if (context != null && !TextUtils.isEmpty(str) && (a = C0246a.m1787a(context)) != null && (edit = a.edit()) != null) {
            edit.putString("resetToken", str).commit();
        }
    }

    /* renamed from: d */
    public static String m1797d(Context context) {
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.statistics.common.DeviceConfig");
            Method declaredMethod = cls.getDeclaredMethod("getOaid", Context.class);
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(cls, context);
                if (invoke != null && (invoke instanceof String)) {
                    return (String) invoke;
                }
            }
        } catch (Throwable th) {
        }
        return "";
    }

    /* renamed from: d */
    public static void m1798d(Context context, String str) {
        SharedPreferences a;
        SharedPreferences.Editor edit;
        if (context != null && !TextUtils.isEmpty(str) && (a = C0246a.m1787a(context)) != null && (edit = a.edit()) != null) {
            edit.putString("uabc", str).commit();
        }
    }

    /* renamed from: e */
    public static void m1799e(Context context, String str) {
        SharedPreferences a;
        SharedPreferences.Editor edit;
        if (context != null && str != null && !TextUtils.isEmpty(str) && (a = C0246a.m1787a(context)) != null && (edit = a.edit()) != null) {
            edit.putString("aaid", str).commit();
        }
    }

    /* renamed from: f */
    public static void m1800f(Context context, String str) {
        SharedPreferences a;
        SharedPreferences.Editor edit;
        if (context != null && str != null && !TextUtils.isEmpty(str) && (a = C0246a.m1787a(context)) != null && (edit = a.edit()) != null) {
            edit.putString("zdata", str).commit();
        }
    }
}
