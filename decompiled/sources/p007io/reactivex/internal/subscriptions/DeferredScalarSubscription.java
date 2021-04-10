package p007io.reactivex.internal.subscriptions;

import com.own.bless.soy.Subscriber;

/* renamed from: io.reactivex.internal.subscriptions.DeferredScalarSubscription */
public class DeferredScalarSubscription extends BasicIntQueueSubscription {
    static final int CANCELLED = 4;
    static final int FUSED_CONSUMED = 32;
    static final int FUSED_EMPTY = 8;
    static final int FUSED_READY = 16;
    static final int HAS_REQUEST_HAS_VALUE = 3;
    static final int HAS_REQUEST_NO_VALUE = 2;
    static final int NO_REQUEST_HAS_VALUE = 1;
    static final int NO_REQUEST_NO_VALUE = 0;
    private static final long serialVersionUID = -2151279923272604993L;
    protected final Subscriber downstream;
    protected Object value;

    public DeferredScalarSubscription(Subscriber v2Var) {
        this.downstream = v2Var;
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription, com.own.bless.soy.Subscription
    public final void request(long n) {
        Object obj;
        if (SubscriptionHelper.validate(n)) {
            do {
                int state = get();
                if ((state & -2) == 0) {
                    if (state == 1) {
                        if (compareAndSet(1, 3) && (obj = this.value) != null) {
                            this.value = null;
                            org.reactivestreams.Subscriber<? super T> a = this.downstream;
                            a.onNext(obj);
                            if (get() != 4) {
                                a.onComplete();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                } else {
                    return;
                }
            } while (!compareAndSet(0, 2));
        }
    }

    public final void complete(Object obj) {
        int state = get();
        while (state != 8) {
            if ((state & -3) == 0) {
                if (state == 2) {
                    lazySet(3);
                    org.reactivestreams.Subscriber<? super T> a = this.downstream;
                    a.onNext(obj);
                    if (get() != 4) {
                        a.onComplete();
                        return;
                    }
                    return;
                }
                this.value = obj;
                if (!compareAndSet(0, 1)) {
                    state = get();
                    if (state == 4) {
                        this.value = null;
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        this.value = obj;
        lazySet(FUSED_READY);
        org.reactivestreams.Subscriber<? super T> a2 = this.downstream;
        a2.onNext(obj);
        if (get() != 4) {
            a2.onComplete();
        }
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription, com.own.bless.soy.QueueFuseable
    public final int requestFusion(int mode) {
        if ((mode & 2) == 0) {
            return 0;
        }
        lazySet(8);
        return 2;
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription
    public final Object poll() {
        if (get() != FUSED_READY) {
            return null;
        }
        lazySet(32);
        Object obj = this.value;
        this.value = null;
        return obj;
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription
    public final boolean isEmpty() {
        return get() != FUSED_READY;
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription
    public final void clear() {
        lazySet(32);
        this.value = null;
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription, com.own.bless.soy.Subscription
    public void cancel() {
        set(4);
        this.value = null;
    }

    public final boolean isCancelled() {
        return get() == 4;
    }

    public final boolean tryCancel() {
        return getAndSet(4) != 4;
    }
}
