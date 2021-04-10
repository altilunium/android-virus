package com.own.bless.soy;

/* renamed from: com.own.bless.soy.h1 */
final class RunnableDisposable extends ReferenceDisposable {
    RunnableDisposable(Runnable value) {
        super(value);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo47a(Runnable value) {
        value.run();
    }

    public String toString() {
        return "RunnableDisposable(disposed=" + isDisposed() + ", " + get() + ")";
    }
}
