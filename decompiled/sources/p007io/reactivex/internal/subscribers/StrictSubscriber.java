package p007io.reactivex.internal.subscribers;

import com.own.bless.soy.Subscriber;
import com.own.bless.soy.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.internal.subscriptions.SubscriptionHelper;
import p007io.reactivex.internal.util.AtomicThrowable;
import p007io.reactivex.internal.util.HalfSerializer;

/* renamed from: io.reactivex.internal.subscribers.StrictSubscriber */
public class StrictSubscriber extends AtomicInteger implements Subscriber, Subscription {
    private static final long serialVersionUID = -4945028590049415624L;
    volatile boolean done;
    final Subscriber downstream;
    final AtomicThrowable error = new AtomicThrowable();
    final AtomicBoolean once = new AtomicBoolean();
    final AtomicLong requested = new AtomicLong();
    final AtomicReference upstream = new AtomicReference();

    public StrictSubscriber(Subscriber v2Var) {
        this.downstream = v2Var;
    }

    @Override // com.own.bless.soy.Subscription
    public void request(long n) {
        if (n <= 0) {
            cancel();
            onError(new IllegalArgumentException("§3.9 violated: positive request amount required but it was " + n));
            return;
        }
        SubscriptionHelper.deferredRequest(this.upstream, this.requested, n);
    }

    @Override // com.own.bless.soy.Subscription
    public void cancel() {
        if (!this.done) {
            SubscriptionHelper.cancel(this.upstream);
        }
    }

    @Override // com.own.bless.soy.Subscriber
    public void onSubscribe(Subscription s) {
        if (this.once.compareAndSet(false, true)) {
            this.downstream.onSubscribe(this);
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, s);
            return;
        }
        s.cancel();
        cancel();
        onError(new IllegalStateException("§2.12 violated: onSubscribe must be called at most once"));
    }

    @Override // com.own.bless.soy.Subscriber
    public void onNext(Object obj) {
        HalfSerializer.m1936c(this.downstream, obj, this, this.error);
    }

    @Override // com.own.bless.soy.Subscriber
    public void onError(Throwable t) {
        this.done = true;
        HalfSerializer.m1935b(this.downstream, t, this, this.error);
    }

    @Override // com.own.bless.soy.Subscriber
    public void onComplete() {
        this.done = true;
        HalfSerializer.m1934a(this.downstream, this, this.error);
    }
}
