package p007io.reactivex.internal.subscriptions;

import com.own.bless.soy.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: io.reactivex.internal.subscriptions.BooleanSubscription */
public final class BooleanSubscription extends AtomicBoolean implements Subscription {
    private static final long serialVersionUID = -8127758972444290902L;

    @Override // com.own.bless.soy.Subscription
    public void request(long n) {
        SubscriptionHelper.validate(n);
    }

    @Override // com.own.bless.soy.Subscription
    public void cancel() {
        lazySet(true);
    }

    public boolean isCancelled() {
        return get();
    }

    public String toString() {
        return "BooleanSubscription(cancelled=" + get() + ")";
    }
}
