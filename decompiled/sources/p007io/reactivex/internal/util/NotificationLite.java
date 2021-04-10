package p007io.reactivex.internal.util;

import com.own.bless.soy.Disposable;
import com.own.bless.soy.Subscriber;
import com.own.bless.soy.Subscription;
import p007io.reactivex.Observer;

/* renamed from: io.reactivex.internal.util.NotificationLite */
public enum NotificationLite {
    COMPLETE;

    public static Object next(Object obj) {
        return obj;
    }

    public static Object complete() {
        return COMPLETE;
    }

    public static Object error(Throwable e) {
        return new C0281f(e);
    }

    public static Object subscription(Subscription s) {
        return new C0282g(s);
    }

    public static Object disposable(Disposable d) {
        return new C0280e(d);
    }

    public static boolean isComplete(Object o) {
        return o == COMPLETE;
    }

    public static boolean isError(Object o) {
        return o instanceof C0281f;
    }

    public static boolean isSubscription(Object o) {
        return o instanceof C0282g;
    }

    public static boolean isDisposable(Object o) {
        return o instanceof C0280e;
    }

    public static Object getValue(Object o) {
        return o;
    }

    public static Throwable getError(Object o) {
        return ((C0281f) o).f1825a;
    }

    public static Subscription getSubscription(Object o) {
        return ((C0282g) o).f1826a;
    }

    public static Disposable getDisposable(Object o) {
        return ((C0280e) o).f1824a;
    }

    public static boolean accept(Object o, Subscriber v2Var) {
        if (o == COMPLETE) {
            v2Var.onComplete();
            return true;
        } else if (o instanceof C0281f) {
            v2Var.onError(((C0281f) o).f1825a);
            return true;
        } else {
            v2Var.onNext(o);
            return false;
        }
    }

    public static boolean accept(Object o, Observer fVar) {
        if (o == COMPLETE) {
            fVar.onComplete();
            return true;
        } else if (o instanceof C0281f) {
            fVar.onError(((C0281f) o).f1825a);
            return true;
        } else {
            fVar.onNext(o);
            return false;
        }
    }

    public static boolean acceptFull(Object o, Subscriber v2Var) {
        if (o == COMPLETE) {
            v2Var.onComplete();
            return true;
        } else if (o instanceof C0281f) {
            v2Var.onError(((C0281f) o).f1825a);
            return true;
        } else if (o instanceof C0282g) {
            v2Var.onSubscribe(((C0282g) o).f1826a);
            return false;
        } else {
            v2Var.onNext(o);
            return false;
        }
    }

    public static boolean acceptFull(Object o, Observer fVar) {
        if (o == COMPLETE) {
            fVar.onComplete();
            return true;
        } else if (o instanceof C0281f) {
            fVar.onError(((C0281f) o).f1825a);
            return true;
        } else if (o instanceof C0280e) {
            fVar.onSubscribe(((C0280e) o).f1824a);
            return false;
        } else {
            fVar.onNext(o);
            return false;
        }
    }

    public String toString() {
        return "NotificationLite.Complete";
    }
}
