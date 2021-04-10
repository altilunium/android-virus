package p007io.reactivex.internal.subscriptions;

import com.own.bless.soy.QueueSubscription;
import com.own.bless.soy.Subscriber;

/* renamed from: io.reactivex.internal.subscriptions.EmptySubscription */
public enum EmptySubscription implements QueueSubscription {
    INSTANCE;

    @Override // com.own.bless.soy.Subscription
    public void request(long n) {
        SubscriptionHelper.validate(n);
    }

    @Override // com.own.bless.soy.Subscription
    public void cancel() {
    }

    public String toString() {
        return "EmptySubscription";
    }

    public static void error(Throwable e, Subscriber v2Var) {
        v2Var.onSubscribe(INSTANCE);
        v2Var.onError(e);
    }

    public static void complete(Subscriber v2Var) {
        v2Var.onSubscribe(INSTANCE);
        v2Var.onComplete();
    }

    public Object poll() {
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

    public void clear() {
    }

    @Override // com.own.bless.soy.QueueFuseable
    public int requestFusion(int mode) {
        return mode & 2;
    }

    public boolean offer(Object value) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public boolean offer(Object v1, Object v2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
