package com.own.bless.soy;

import io.reactivex.functions.Consumer;
import java.util.concurrent.Callable;
import p007io.reactivex.AbstractC0283j;
import p007io.reactivex.Observable;
import p007io.reactivex.Observer;
import p007io.reactivex.exceptions.CompositeException;
import p007io.reactivex.exceptions.MissingBackpressureException;
import p007io.reactivex.exceptions.OnErrorNotImplementedException;
import p007io.reactivex.exceptions.UndeliverableException;
import p007io.reactivex.internal.util.C0279c;

/* renamed from: com.own.bless.soy.k2 */
public final class RxJavaPlugins {
    /* renamed from: e */
    public static AbstractC0283j m160e(Callable callable) {
        C0013e2.m76d(callable, "Scheduler Callable can't be null");
        if (0 == 0) {
            return m159d(callable);
        }
        return m158c(null, callable);
    }

    /* renamed from: f */
    public static AbstractC0283j m161f(Callable callable) {
        C0013e2.m76d(callable, "Scheduler Callable can't be null");
        if (0 == 0) {
            return m159d(callable);
        }
        return m158c(null, callable);
    }

    /* renamed from: g */
    public static AbstractC0283j m162g(Callable callable) {
        C0013e2.m76d(callable, "Scheduler Callable can't be null");
        if (0 == 0) {
            return m159d(callable);
        }
        return m158c(null, callable);
    }

    /* renamed from: h */
    public static AbstractC0283j m163h(Callable callable) {
        C0013e2.m76d(callable, "Scheduler Callable can't be null");
        if (0 == 0) {
            return m159d(callable);
        }
        return m158c(null, callable);
    }

    /* renamed from: k */
    public static AbstractC0283j m166k(AbstractC0283j defaultScheduler) {
        if (0 == 0) {
            return defaultScheduler;
        }
        return (AbstractC0283j) m157b(null, defaultScheduler);
    }

    /* renamed from: l */
    public static void m167l(Throwable error) {
        Consumer<? super Throwable> f = null;
        if (error == null) {
            error = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else if (!m164i(error)) {
            error = new UndeliverableException(error);
        }
        if (0 != 0) {
            try {
                f.accept(error);
                return;
            } catch (Throwable e) {
                e.printStackTrace();
                m170o(e);
            }
        }
        error.printStackTrace();
        m170o(error);
    }

    /* renamed from: i */
    static boolean m164i(Throwable error) {
        if (!(error instanceof OnErrorNotImplementedException) && !(error instanceof MissingBackpressureException) && !(error instanceof IllegalStateException) && !(error instanceof NullPointerException) && !(error instanceof IllegalArgumentException) && !(error instanceof CompositeException)) {
            return false;
        }
        return true;
    }

    /* renamed from: o */
    static void m170o(Throwable error) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, error);
    }

    /* renamed from: m */
    public static Runnable m168m(Runnable run) {
        C0013e2.m76d(run, "run is null");
        if (0 == 0) {
            return run;
        }
        return (Runnable) m157b(null, run);
    }

    /* renamed from: n */
    public static Observer m169n(Observable eVar, Observer fVar) {
        if (0 != 0) {
            return (Observer) m156a(null, eVar, fVar);
        }
        return fVar;
    }

    /* renamed from: j */
    public static Observable m165j(Observable eVar) {
        if (0 != 0) {
            return (Observable) m157b(null, eVar);
        }
        return eVar;
    }

    /* renamed from: b */
    static Object m157b(Function o1Var, Object obj) {
        try {
            return o1Var.apply(obj);
        } catch (Throwable ex) {
            throw C0279c.m1933c(ex);
        }
    }

    /* renamed from: a */
    static Object m156a(BiFunction l1Var, Object obj, Object obj2) {
        try {
            return l1Var.apply(obj, obj2);
        } catch (Throwable ex) {
            throw C0279c.m1933c(ex);
        }
    }

    /* renamed from: d */
    static AbstractC0283j m159d(Callable callable) {
        try {
            Object call = callable.call();
            C0013e2.m76d(call, "Scheduler Callable result can't be null");
            return (AbstractC0283j) call;
        } catch (Throwable ex) {
            throw C0279c.m1933c(ex);
        }
    }

    /* renamed from: c */
    static AbstractC0283j m158c(Function o1Var, Callable callable) {
        Object b = m157b(o1Var, callable);
        C0013e2.m76d(b, "Scheduler Callable result can't be null");
        return (AbstractC0283j) b;
    }
}
