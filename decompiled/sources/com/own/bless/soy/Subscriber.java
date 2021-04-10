package com.own.bless.soy;

/* renamed from: com.own.bless.soy.v2 */
public interface Subscriber {
    void onComplete();

    void onError(Throwable th);

    void onNext(Object obj);

    void onSubscribe(Subscription w2Var);
}
