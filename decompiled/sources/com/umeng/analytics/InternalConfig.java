package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.common.SPHelper;

/* renamed from: com.umeng.analytics.c */
public class InternalConfig {

    /* renamed from: a */
    private static String[] f233a = new String[2];

    /* renamed from: a */
    public static void m384a(Context context, String str, String str2) {
        String[] strArr = f233a;
        strArr[0] = str;
        strArr[1] = str2;
        if (context != null) {
            SPHelper.m1089a(context).mo650a(str, str2);
        }
    }

    /* renamed from: a */
    public static String[] m385a(Context context) {
        String[] a;
        if (!TextUtils.isEmpty(f233a[0]) && !TextUtils.isEmpty(f233a[1])) {
            return f233a;
        }
        if (context == null || (a = SPHelper.m1089a(context).mo651a()) == null) {
            return null;
        }
        String[] strArr = f233a;
        strArr[0] = a[0];
        strArr[1] = a[1];
        return strArr;
    }

    /* renamed from: b */
    public static void m386b(Context context) {
        String[] strArr = f233a;
        strArr[0] = null;
        strArr[1] = null;
        if (context != null) {
            SPHelper.m1089a(context).mo652b();
        }
    }
}
