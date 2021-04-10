package com.own.bless.soy;

/* renamed from: com.own.bless.soy.k */
public abstract class ThreadTask implements Runnable {

    /* renamed from: a */
    Object[] f66a;

    /* renamed from: a */
    public abstract void mo46a(Object... objArr);

    public ThreadTask(Object... objects) {
        this.f66a = objects;
    }

    public void run() {
        try {
            mo46a(this.f66a);
        } catch (Throwable throwable) {
            MyLog.m48b("线程处理失败", throwable);
        }
    }
}
