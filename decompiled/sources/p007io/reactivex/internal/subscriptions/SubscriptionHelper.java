package p007io.reactivex.internal.subscriptions;

import com.own.bless.soy.C0013e2;
import com.own.bless.soy.RxJavaPlugins;
import com.own.bless.soy.Subscription;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.exceptions.ProtocolViolationException;
import p007io.reactivex.internal.util.BackpressureHelper;

/* renamed from: io.reactivex.internal.subscriptions.SubscriptionHelper */
public enum SubscriptionHelper implements Subscription {
    CANCELLED;

    @Override // com.own.bless.soy.Subscription
    public void request(long n) {
    }

    @Override // com.own.bless.soy.Subscription
    public void cancel() {
    }

    public static boolean validate(Subscription current, Subscription next) {
        if (next == null) {
            RxJavaPlugins.m167l(new NullPointerException("next is null"));
            return false;
        } else if (current == null) {
            return true;
        } else {
            next.cancel();
            reportSubscriptionSet();
            return false;
        }
    }

    public static void reportSubscriptionSet() {
        RxJavaPlugins.m167l(new ProtocolViolationException("Subscription already set!"));
    }

    public static boolean validate(long n) {
        if (n > 0) {
            return true;
        }
        RxJavaPlugins.m167l(new IllegalArgumentException("n > 0 required but it was " + n));
        return false;
    }

    public static void reportMoreProduced(long n) {
        RxJavaPlugins.m167l(new ProtocolViolationException("More produced than requested: " + n));
    }

    public static boolean set(AtomicReference atomicReference, Subscription s) {
        Subscription current;
        do {
            current = (Subscription) atomicReference.get();
            if (current == CANCELLED) {
                if (s == null) {
                    return false;
                }
                s.cancel();
                return false;
            }
        } while (!atomicReference.compareAndSet(current, s));
        if (current == null) {
            return true;
        }
        current.cancel();
        return true;
    }

    public static boolean setOnce(AtomicReference atomicReference, Subscription s) {
        C0013e2.m76d(s, "s is null");
        if (atomicReference.compareAndSet(null, s)) {
            return true;
        }
        s.cancel();
        if (atomicReference.get() == CANCELLED) {
            return false;
        }
        reportSubscriptionSet();
        return false;
    }

    public static boolean replace(AtomicReference atomicReference, Subscription s) {
        Subscription current;
        do {
            current = (Subscription) atomicReference.get();
            if (current == CANCELLED) {
                if (s == null) {
                    return false;
                }
                s.cancel();
                return false;
            }
        } while (!atomicReference.compareAndSet(current, s));
        return true;
    }

    public static boolean cancel(AtomicReference atomicReference) {
        Subscription current;
        Subscription current2 = (Subscription) atomicReference.get();
        SubscriptionHelper subscriptionHelper = CANCELLED;
        if (current2 == subscriptionHelper || (current = (Subscription) atomicReference.getAndSet(subscriptionHelper)) == subscriptionHelper) {
            return false;
        }
        if (current == null) {
            return true;
        }
        current.cancel();
        return true;
    }

    public static boolean deferredSetOnce(AtomicReference atomicReference, AtomicLong requested, Subscription s) {
        if (!setOnce(atomicReference, s)) {
            return false;
        }
        long r = requested.getAndSet(0);
        if (r == 0) {
            return true;
        }
        s.request(r);
        return true;
    }

    public static void deferredRequest(AtomicReference atomicReference, AtomicLong requested, long n) {
        Subscription s = (Subscription) atomicReference.get();
        if (s != null) {
            s.request(n);
        } else if (validate(n)) {
            BackpressureHelper.m1928a(requested, n);
            Subscription s2 = (Subscription) atomicReference.get();
            if (s2 != null) {
                long r = requested.getAndSet(0);
                if (r != 0) {
                    s2.request(r);
                }
            }
        }
    }

    public static boolean setOnce(AtomicReference atomicReference, Subscription s, long request) {
        if (!setOnce(atomicReference, s)) {
            return false;
        }
        s.request(request);
        return true;
    }
}
