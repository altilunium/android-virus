package com.umeng.umzid;

import android.util.Log;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.umeng.umzid.b */
public class C0248b {

    /* renamed from: a */
    public static volatile ScheduledThreadPoolExecutor f1689a;

    /* renamed from: b */
    public static ThreadFactory f1690b = new ThreadFactoryC0249a();

    /* renamed from: com.umeng.umzid.b$a */
    public final class ThreadFactoryC0249a implements ThreadFactory {

        /* renamed from: a */
        public AtomicInteger f1691a = new AtomicInteger(0);

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("ZIDThreadPoolExecutor" + this.f1691a.addAndGet(1));
            return thread;
        }
    }

    /* renamed from: a */
    public static ScheduledThreadPoolExecutor m1789a() {
        if (f1689a == null) {
            synchronized (C0248b.class) {
                if (f1689a == null) {
                    f1689a = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 4, f1690b);
                }
            }
        }
        return f1689a;
    }

    /* renamed from: a */
    public static void m1790a(Runnable runnable) {
        try {
            m1789a().execute(runnable);
        } catch (Throwable th) {
            Log.e("com.umeng.umzid.b", "UmengThreadPoolExecutorFactory execute exception");
        }
    }
}
