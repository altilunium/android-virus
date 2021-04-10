package com.own.bless.soy;

import android.content.Context;
import java.lang.Thread;

/* renamed from: com.own.bless.soy.w */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    /* renamed from: b */
    private static CrashHandler f118b = new CrashHandler();

    /* renamed from: a */
    private Context f119a;

    private CrashHandler() {
    }

    /* renamed from: a */
    public static CrashHandler m240a() {
        return f118b;
    }

    /* renamed from: b */
    public synchronized void mo116b(Context context) {
        if (this.f119a == null) {
            this.f119a = context;
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public void uncaughtException(Thread t, Throwable e) {
        MyLog.m48b("[crash]", e);
        StatsUtils.m154b(this.f119a, "CRASH", "[crash]", e);
        Runtime.getRuntime().exit(1);
    }
}
