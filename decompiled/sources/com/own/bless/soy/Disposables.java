package com.own.bless.soy;

/* renamed from: com.own.bless.soy.f1 */
public final class Disposables {
    /* renamed from: a */
    public static Disposable m83a(Runnable run) {
        C0013e2.m76d(run, "run is null");
        return new RunnableDisposable(run);
    }
}
