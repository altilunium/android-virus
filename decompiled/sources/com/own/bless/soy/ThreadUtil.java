package com.own.bless.soy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* renamed from: com.own.bless.soy.m0 */
public class ThreadUtil {

    /* renamed from: a */
    private static ExecutorService f78a;

    /* renamed from: b */
    private static ScheduledExecutorService f79b;

    /* renamed from: a */
    public static void m177a(ThreadTask threadTask) {
        if (f78a == null) {
            f78a = Executors.newCachedThreadPool();
        }
        f78a.submit(threadTask);
    }

    /* renamed from: b */
    public static void m178b(ThreadTask threadTask, long delay, TimeUnit unit) {
        if (f79b == null) {
            f79b = Executors.newScheduledThreadPool(1);
        }
        f79b.schedule(threadTask, delay, unit);
    }
}
