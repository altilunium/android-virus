package p007io.reactivex.internal.util;

import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: io.reactivex.internal.util.a */
public final class BackpressureHelper {
    /* renamed from: b */
    public static long m1929b(long a, long b) {
        long u = a + b;
        if (u < 0) {
            return Long.MAX_VALUE;
        }
        return u;
    }

    /* renamed from: a */
    public static long m1928a(AtomicLong requested, long n) {
        long r;
        do {
            r = requested.get();
            if (r == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
        } while (!requested.compareAndSet(r, m1929b(r, n)));
        return r;
    }

    /* renamed from: c */
    public static long m1930c(AtomicLong requested, long n) {
        long current;
        long update;
        do {
            current = requested.get();
            if (current == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            update = current - n;
            if (update < 0) {
                RxJavaPlugins.m167l(new IllegalStateException("More produced than requested: " + update));
                update = 0;
            }
        } while (!requested.compareAndSet(current, update));
        return update;
    }
}
