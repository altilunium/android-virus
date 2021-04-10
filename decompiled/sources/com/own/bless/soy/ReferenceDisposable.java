package com.own.bless.soy;

import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
/* renamed from: com.own.bless.soy.g1 */
public abstract class ReferenceDisposable extends AtomicReference implements Disposable {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo47a(Object obj);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ReferenceDisposable(Object obj) {
        super(obj);
        C0013e2.m76d(obj, "value is null");
    }

    @Override // com.own.bless.soy.Disposable
    public final void dispose() {
        Object andSet;
        if (get() != null && (andSet = getAndSet(null)) != null) {
            mo47a(andSet);
        }
    }

    @Override // com.own.bless.soy.Disposable
    public final boolean isDisposed() {
        return get() == null;
    }
}
