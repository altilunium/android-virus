package com.own.bless.soy;

import android.os.Build;
import android.os.Environment;
import android.security.NetworkSecurityPolicy;

/* renamed from: com.own.bless.soy.i */
public class MKConfig {

    /* renamed from: a */
    public static boolean f40a;

    /* renamed from: b */
    public static final String f41b = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/data/.device");

    /* renamed from: c */
    private static String f42c = "1192";

    /* renamed from: d */
    public static final String f43d;

    /* renamed from: e */
    public static final String f44e;

    /* renamed from: f */
    public static final String f45f;

    /* renamed from: g */
    public static final String f46g = "http://www.shiyiweishuwu.com:7005/advsApi/getErrIno";

    static {
        String b = m112b();
        f43d = b;
        f44e = b + "/getNewCfg";
        f45f = b + "/report";
        String str = b + "/getAdvFill";
    }

    /* renamed from: e */
    public static void m115e(String newCid) {
        f42c = newCid;
    }

    /* renamed from: a */
    public static String m111a() {
        return f42c;
    }

    /* renamed from: c */
    public static int m113c() {
        return 1000;
    }

    /* renamed from: f */
    public static void m116f(boolean hasComponents) {
        f40a = hasComponents;
    }

    /* renamed from: d */
    public static boolean m114d() {
        return f40a;
    }

    /* renamed from: b */
    private static String m112b() {
        if (Build.VERSION.SDK_INT < 23 || NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
            return "http://www.shiyiweishuwu.com:7005/advsApi";
        }
        return "https://www.shiyiweishuwu.com/advsApi";
    }
}
