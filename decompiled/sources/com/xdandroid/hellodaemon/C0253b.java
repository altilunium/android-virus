package com.xdandroid.hellodaemon;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xdandroid.hellodaemon.b */
/* compiled from: DaemonEnv */
public final class C0253b {

    /* renamed from: a */
    static Context f1697a;

    /* renamed from: b */
    static Class f1698b;

    /* renamed from: c */
    private static int f1699c = 360000;

    /* renamed from: d */
    static boolean f1700d;

    /* renamed from: e */
    static final Map f1701e = new HashMap();

    /* renamed from: b */
    public static void m1816b(Context app, Class cls, Integer wakeUpInterval) {
        f1697a = app;
        f1698b = cls;
        if (wakeUpInterval != null) {
            f1699c = wakeUpInterval.intValue();
        }
        f1700d = true;
    }

    /* renamed from: c */
    public static void m1817c(Class cls) {
        if (f1700d) {
            Intent i = new Intent(f1697a, cls);
            m1818d(i);
            if (((ServiceConnection) f1701e.get(cls)) == null) {
                f1697a.bindService(i, new DaemonEnv(cls, i), 1);
            }
        }
    }

    /* renamed from: d */
    static void m1818d(Intent i) {
        if (f1700d) {
            try {
                f1697a.startService(i);
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    static int m1815a() {
        return Math.max(f1699c, 180000);
    }
}
