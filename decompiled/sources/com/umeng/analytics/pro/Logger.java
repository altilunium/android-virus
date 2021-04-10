package com.umeng.analytics.pro;

import android.util.Log;

/* renamed from: com.umeng.analytics.pro.ah */
public class Logger {

    /* renamed from: a */
    private static final String f262a = "OpenId";

    /* renamed from: b */
    private static boolean f263b = false;

    /* renamed from: a */
    public static void m415a(boolean z) {
        Log.d(f262a, "setDebug:" + z);
        f263b = z;
    }

    /* renamed from: a */
    public static void m414a(String str, Object... objArr) {
        if (f263b) {
            Log.d(f262a, m419e(str, objArr));
        }
    }

    /* renamed from: b */
    public static void m416b(String str, Object... objArr) {
        if (f263b) {
            Log.i(f262a, m419e(str, objArr));
        }
    }

    /* renamed from: c */
    public static void m417c(String str, Object... objArr) {
        if (f263b) {
            Log.w(f262a, m419e(str, objArr));
        }
    }

    /* renamed from: d */
    public static void m418d(String str, Object... objArr) {
        if (f263b) {
            Log.e(f262a, m419e(str, objArr));
        }
    }

    /* renamed from: e */
    private static String m419e(String str, Object... objArr) {
        if (str == null && objArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Object[] objArr2 = new Object[1];
        if (str == null) {
            str = "-";
        }
        int i = 0;
        objArr2[0] = str;
        sb.append(String.format("[%s] ", objArr2));
        if (objArr != null) {
            int length = objArr.length;
            while (true) {
                int i2 = i + 1;
                if (i2 >= objArr.length) {
                    break;
                }
                sb.append(m413a(objArr[i], objArr[i2]));
                if (i2 < length - 1) {
                    sb.append(",");
                }
                i = i2 + 1;
            }
            if (i == objArr.length - 1) {
                sb.append(objArr[i]);
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static String m413a(Object obj, Object obj2) {
        Object[] objArr = new Object[2];
        if (obj == null) {
            obj = "";
        }
        objArr[0] = obj;
        if (obj2 == null) {
            obj2 = "";
        }
        objArr[1] = obj2;
        return String.format("%s:%s", objArr);
    }
}
