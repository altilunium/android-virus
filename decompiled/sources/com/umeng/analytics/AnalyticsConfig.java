package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.UMLogAnalytics;
import com.umeng.common.SPHelper;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.utils.UMUtils;

public class AnalyticsConfig {
    public static boolean CATCH_EXCEPTION = false;
    public static boolean CHANGE_CATCH_EXCEPTION_NOTALLOW = true;
    public static boolean CLEAR_EKV_BL = false;
    public static boolean CLEAR_EKV_WL = false;
    public static String GPU_RENDERER = "";
    public static String GPU_VENDER = "";

    /* renamed from: a */
    static double[] f182a = null;

    /* renamed from: b */
    private static String f183b = null;

    /* renamed from: c */
    private static String f184c = null;

    /* renamed from: d */
    private static String f185d = null;

    /* renamed from: e */
    private static int f186e = 0;
    public static boolean enable = true;
    public static long kContinueSessionMillis = 30000;
    public static String mWrapperType = null;
    public static String mWrapperVersion = null;

    /* renamed from: a */
    static void m326a(String str) {
        f184c = str;
    }

    public static String getAppkey(Context context) {
        return UMUtils.getAppkey(context);
    }

    public static String getChannel(Context context) {
        return UMUtils.getChannel(context);
    }

    public static double[] getLocation() {
        return f182a;
    }

    /* renamed from: a */
    static void m325a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            f185d = str;
            SPHelper.m1089a(context).mo649a(f185d);
            return;
        }
        UMLog.m1136aq(UMLogAnalytics.f772A, 0, "\\|");
    }

    public static String getSecretKey(Context context) {
        if (TextUtils.isEmpty(f185d)) {
            f185d = SPHelper.m1089a(context).mo653c();
        }
        return f185d;
    }

    /* renamed from: a */
    static void m324a(Context context, int i) {
        f186e = i;
        SPHelper.m1089a(context).mo648a(f186e);
    }

    public static int getVerticalType(Context context) {
        if (f186e == 0) {
            f186e = SPHelper.m1089a(context).mo654d();
        }
        return f186e;
    }

    public static String getGameSdkVersion(Context context) {
        try {
            Class<?> cls = Class.forName("com.umeng.analytics.game.GameSdkVersion");
            return (String) cls.getDeclaredField("SDK_VERSION").get(cls);
        } catch (Throwable th) {
            return null;
        }
    }
}
