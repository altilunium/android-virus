package p007io.reactivex.internal.util;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: io.reactivex.internal.util.AtomicThrowable */
public final class AtomicThrowable extends AtomicReference {
    private static final long serialVersionUID = 3949248817947090603L;

    public boolean addThrowable(Throwable t) {
        return C0279c.m1931a(this, t);
    }

    public Throwable terminate() {
        return C0279c.m1932b(this);
    }

    public boolean isTerminated() {
        return get() == C0279c.f1823a;
    }
}
