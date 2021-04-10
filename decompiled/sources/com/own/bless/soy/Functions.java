package com.own.bless.soy;

import p007io.reactivex.exceptions.OnErrorNotImplementedException;

/* renamed from: com.own.bless.soy.a2 */
final class Functions implements Consumer {
    Functions() {
    }

    /* renamed from: a */
    public void accept(Throwable error) {
        RxJavaPlugins.m167l(new OnErrorNotImplementedException(error));
    }
}
