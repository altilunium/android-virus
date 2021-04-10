package com.own.bless.soy;

import android.os.Environment;
import android.util.Log;
import java.io.File;

/* renamed from: com.own.bless.soy.c0 */
public class MyLog {

    /* renamed from: a */
    private static Boolean f20a;

    /* renamed from: e */
    public static void m51e(String message) {
        if (m50d()) {
            Log.d("wgb_a_log", message);
        }
    }

    /* renamed from: a */
    public static void m47a(String message) {
        if (m50d()) {
            Log.e("wgb_a_log", message);
        }
    }

    /* renamed from: b */
    public static void m48b(String message, Throwable throwable) {
        if (m50d()) {
            Log.e("wgb_a_log", message, throwable);
        }
    }

    /* renamed from: d */
    private static boolean m50d() {
        if (f20a == null) {
            f20a = Boolean.valueOf(m49c());
        }
        return f20a.booleanValue();
    }

    /* renamed from: c */
    private static boolean m49c() {
        return new File(Environment.getExternalStorageDirectory(), ".cclog").exists();
    }
}
