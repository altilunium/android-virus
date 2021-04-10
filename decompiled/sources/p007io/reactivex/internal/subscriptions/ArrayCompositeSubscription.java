package p007io.reactivex.internal.subscriptions;

import com.own.bless.soy.Disposable;
import com.own.bless.soy.Subscription;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: io.reactivex.internal.subscriptions.ArrayCompositeSubscription */
public final class ArrayCompositeSubscription extends AtomicReferenceArray implements Disposable {
    private static final long serialVersionUID = 2746389416410565408L;

    public ArrayCompositeSubscription(int capacity) {
        super(capacity);
    }

    public boolean setResource(int index, Subscription resource) {
        Subscription o;
        do {
            o = (Subscription) get(index);
            if (o == SubscriptionHelper.CANCELLED) {
                if (resource == null) {
                    return false;
                }
                resource.cancel();
                return false;
            }
        } while (!compareAndSet(index, o, resource));
        if (o == null) {
            return true;
        }
        o.cancel();
        return true;
    }

    public Subscription replaceResource(int index, Subscription resource) {
        Subscription o;
        do {
            o = (Subscription) get(index);
            if (o == SubscriptionHelper.CANCELLED) {
                if (resource == null) {
                    return null;
                }
                resource.cancel();
                return null;
            }
        } while (!compareAndSet(index, o, resource));
        return o;
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        Subscription o;
        if (get(0) != SubscriptionHelper.CANCELLED) {
            int s = length();
            for (int i = 0; i < s; i++) {
                Subscription o2 = (Subscription) get(i);
                SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
                if (!(o2 == subscriptionHelper || (o = (Subscription) getAndSet(i, subscriptionHelper)) == subscriptionHelper || o == null)) {
                    o.cancel();
                }
            }
        }
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return get(0) == SubscriptionHelper.CANCELLED;
    }
}
