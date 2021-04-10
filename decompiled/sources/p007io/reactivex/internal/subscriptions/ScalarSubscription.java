package p007io.reactivex.internal.subscriptions;

import com.own.bless.soy.QueueSubscription;
import com.own.bless.soy.Subscriber;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: io.reactivex.internal.subscriptions.ScalarSubscription */
public final class ScalarSubscription extends AtomicInteger implements QueueSubscription {
    static final int CANCELLED = 2;
    static final int NO_REQUEST = 0;
    static final int REQUESTED = 1;
    private static final long serialVersionUID = -3830916580126663321L;
    final Subscriber subscriber;
    final Object value;

    public ScalarSubscription(Subscriber v2Var, Object obj) {
        this.subscriber = v2Var;
        this.value = obj;
    }

    @Override // com.own.bless.soy.Subscription
    public void request(long n) {
        if (SubscriptionHelper.validate(n) && compareAndSet(0, 1)) {
            org.reactivestreams.Subscriber<? super T> s = this.subscriber;
            s.onNext(this.value);
            if (get() != 2) {
                s.onComplete();
            }
        }
    }

    @Override // com.own.bless.soy.Subscription
    public void cancel() {
        lazySet(2);
    }

    public boolean isCancelled() {
        return get() == 2;
    }

    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public Object poll() {
        if (get() != 0) {
            return null;
        }
        lazySet(1);
        return this.value;
    }

    public boolean isEmpty() {
        return get() != 0;
    }

    public void clear() {
        lazySet(1);
    }

    @Override // com.own.bless.soy.QueueFuseable
    public int requestFusion(int mode) {
        return mode & 1;
    }
}
