package p007io.reactivex.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: io.reactivex.internal.schedulers.RxThreadFactory */
public final class RxThreadFactory extends AtomicLong implements ThreadFactory {
    private static final long serialVersionUID = -7789753024099756196L;
    final boolean nonBlocking;
    final String prefix;
    final int priority;

    public RxThreadFactory(String prefix2) {
        this(prefix2, 5, false);
    }

    public RxThreadFactory(String prefix2, int priority2) {
        this(prefix2, priority2, false);
    }

    public RxThreadFactory(String prefix2, int priority2, boolean nonBlocking2) {
        this.prefix = prefix2;
        this.priority = priority2;
        this.nonBlocking = nonBlocking2;
    }

    public Thread newThread(Runnable r) {
        StringBuilder sb = new StringBuilder(this.prefix);
        sb.append('-');
        String name = sb.append(incrementAndGet()).toString();
        Thread t = this.nonBlocking ? new C0271m(r, name) : new Thread(r, name);
        t.setPriority(this.priority);
        t.setDaemon(true);
        return t;
    }

    public String toString() {
        return "RxThreadFactory[" + this.prefix + "]";
    }
}
