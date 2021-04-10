package p007io.reactivex.internal.subscribers;

import com.own.bless.soy.Subscriber;
import com.own.bless.soy.Subscription;
import java.util.concurrent.atomic.AtomicLong;
import p007io.reactivex.internal.subscriptions.SubscriptionHelper;
import p007io.reactivex.internal.util.BackpressureHelper;

/* renamed from: io.reactivex.internal.subscribers.SinglePostCompleteSubscriber */
public abstract class SinglePostCompleteSubscriber extends AtomicLong implements Subscriber, Subscription {
    static final long COMPLETE_MASK = Long.MIN_VALUE;
    static final long REQUEST_MASK = Long.MAX_VALUE;
    private static final long serialVersionUID = 7917814472626990048L;
    protected final Subscriber downstream;
    protected long produced;
    protected Subscription upstream;
    protected Object value;

    @Override // com.own.bless.soy.Subscriber
    public abstract /* synthetic */ void onComplete();

    @Override // com.own.bless.soy.Subscriber
    public abstract /* synthetic */ void onError(Throwable th);

    @Override // com.own.bless.soy.Subscriber
    public abstract /* synthetic */ void onNext(Object obj);

    public SinglePostCompleteSubscriber(Subscriber v2Var) {
        this.downstream = v2Var;
    }

    @Override // com.own.bless.soy.Subscriber
    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.validate(this.upstream, s)) {
            this.upstream = s;
            this.downstream.onSubscribe(this);
        }
    }

    /* access modifiers changed from: protected */
    public final void complete(Object obj) {
        long p = this.produced;
        if (p != 0) {
            BackpressureHelper.m1930c(this, p);
        }
        while (true) {
            long r = get();
            if ((r & COMPLETE_MASK) != 0) {
                onDrop(obj);
                return;
            } else if ((REQUEST_MASK & r) != 0) {
                lazySet(-9223372036854775807L);
                this.downstream.onNext(obj);
                this.downstream.onComplete();
                return;
            } else {
                this.value = obj;
                if (!compareAndSet(0, COMPLETE_MASK)) {
                    this.value = null;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDrop(Object obj) {
    }

    @Override // com.own.bless.soy.Subscription
    public final void request(long n) {
        long r;
        if (SubscriptionHelper.validate(n)) {
            do {
                r = get();
                if ((r & COMPLETE_MASK) != 0) {
                    if (compareAndSet(COMPLETE_MASK, -9223372036854775807L)) {
                        this.downstream.onNext(this.value);
                        this.downstream.onComplete();
                        return;
                    }
                    return;
                }
            } while (!compareAndSet(r, BackpressureHelper.m1929b(r, n)));
            this.upstream.request(n);
        }
    }

    @Override // com.own.bless.soy.Subscription
    public void cancel() {
        this.upstream.cancel();
    }
}
