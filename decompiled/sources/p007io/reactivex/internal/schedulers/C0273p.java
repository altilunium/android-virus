package p007io.reactivex.internal.schedulers;

import com.own.bless.soy.Function;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: io.reactivex.internal.schedulers.p */
/* compiled from: SchedulerPoolFactory */
public final class C0273p {

    /* renamed from: a */
    public static final boolean f1799a;

    /* renamed from: b */
    public static final int f1800b;

    /* renamed from: c */
    static final AtomicReference f1801c = new AtomicReference();

    /* renamed from: d */
    static final Map f1802d = new ConcurrentHashMap();

    static {
        C0272o propertyAccessor = new C0272o();
        boolean b = m1910b(true, "rx2.purge-enabled", true, true, propertyAccessor);
        f1799a = b;
        f1800b = m1911c(b, "rx2.purge-period-seconds", 1, 1, propertyAccessor);
        m1912d();
    }

    /* renamed from: d */
    public static void m1912d() {
        m1914f(f1799a);
    }

    /* renamed from: f */
    static void m1914f(boolean purgeEnabled) {
        if (purgeEnabled) {
            while (true) {
                AtomicReference atomicReference = f1801c;
                ScheduledExecutorService curr = (ScheduledExecutorService) atomicReference.get();
                if (curr == null) {
                    ScheduledExecutorService next = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge"));
                    if (atomicReference.compareAndSet(curr, next)) {
                        SchedulerPoolFactory nVar = new SchedulerPoolFactory();
                        int i = f1800b;
                        next.scheduleAtFixedRate(nVar, (long) i, (long) i, TimeUnit.SECONDS);
                        return;
                    }
                    next.shutdownNow();
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: c */
    static int m1911c(boolean enabled, String key, int defaultNotFound, int defaultNotEnabled, Function o1Var) {
        if (!enabled) {
            return defaultNotEnabled;
        }
        try {
            String value = (String) o1Var.apply(key);
            if (value == null) {
                return defaultNotFound;
            }
            return Integer.parseInt(value);
        } catch (Throwable th) {
            return defaultNotFound;
        }
    }

    /* renamed from: b */
    static boolean m1910b(boolean enabled, String key, boolean defaultNotFound, boolean defaultNotEnabled, Function o1Var) {
        if (!enabled) {
            return defaultNotEnabled;
        }
        try {
            String value = (String) o1Var.apply(key);
            if (value == null) {
                return defaultNotFound;
            }
            return "true".equals(value);
        } catch (Throwable th) {
            return defaultNotFound;
        }
    }

    /* renamed from: a */
    public static ScheduledExecutorService m1909a(ThreadFactory factory) {
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1, factory);
        m1913e(f1799a, exec);
        return exec;
    }

    /* renamed from: e */
    static void m1913e(boolean purgeEnabled, ScheduledExecutorService exec) {
        if (purgeEnabled && (exec instanceof ScheduledThreadPoolExecutor)) {
            f1802d.put((ScheduledThreadPoolExecutor) exec, exec);
        }
    }
}
