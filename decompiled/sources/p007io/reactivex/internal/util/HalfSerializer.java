package p007io.reactivex.internal.util;

import com.own.bless.soy.RxJavaPlugins;
import com.own.bless.soy.Subscriber;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: io.reactivex.internal.util.d */
public final class HalfSerializer {
    /* renamed from: c */
    public static void m1936c(Subscriber v2Var, Object obj, AtomicInteger wip, AtomicThrowable error) {
        if (wip.get() == 0 && wip.compareAndSet(0, 1)) {
            v2Var.onNext(obj);
            if (wip.decrementAndGet() != 0) {
                Throwable ex = error.terminate();
                if (ex != null) {
                    v2Var.onError(ex);
                } else {
                    v2Var.onComplete();
                }
            }
        }
    }

    /* renamed from: b */
    public static void m1935b(Subscriber v2Var, Throwable ex, AtomicInteger wip, AtomicThrowable error) {
        if (!error.addThrowable(ex)) {
            RxJavaPlugins.m167l(ex);
        } else if (wip.getAndIncrement() == 0) {
            v2Var.onError(error.terminate());
        }
    }

    /* renamed from: a */
    public static void m1934a(Subscriber v2Var, AtomicInteger wip, AtomicThrowable error) {
        if (wip.getAndIncrement() == 0) {
            Throwable ex = error.terminate();
            if (ex != null) {
                v2Var.onError(ex);
            } else {
                v2Var.onComplete();
            }
        }
    }
}
