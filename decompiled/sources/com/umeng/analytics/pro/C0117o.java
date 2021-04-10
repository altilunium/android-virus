package com.umeng.analytics.pro;

import com.umeng.analytics.AnalyticsConfig;
import java.lang.Thread;

/* renamed from: com.umeng.analytics.pro.o */
/* compiled from: CrashHandler */
public class C0117o implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private Thread.UncaughtExceptionHandler f935a;

    /* renamed from: b */
    private OnAppCrashHandler f936b;

    public C0117o() {
        if (Thread.getDefaultUncaughtExceptionHandler() != this) {
            this.f935a = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    /* renamed from: a */
    public void mo582a(OnAppCrashHandler sVar) {
        this.f936b = sVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        m981a(th);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f935a;
        if (uncaughtExceptionHandler != null && uncaughtExceptionHandler != Thread.getDefaultUncaughtExceptionHandler()) {
            this.f935a.uncaughtException(thread, th);
        }
    }

    /* renamed from: a */
    private void m981a(Throwable th) {
        if (AnalyticsConfig.CATCH_EXCEPTION) {
            this.f936b.mo230a(th);
        } else {
            this.f936b.mo230a(null);
        }
    }
}
