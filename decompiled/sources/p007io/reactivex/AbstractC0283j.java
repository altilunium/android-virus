package p007io.reactivex;

import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import p007io.reactivex.internal.disposables.EmptyDisposable;

/* renamed from: io.reactivex.j */
/* compiled from: Scheduler */
public abstract class AbstractC0283j {

    /* renamed from: a */
    static boolean f1831a = Boolean.getBoolean("rx2.scheduler.use-nanotime");

    /* renamed from: b */
    static final long f1832b = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    /* renamed from: b */
    public abstract AbstractC0261i mo1198b();

    /* renamed from: a */
    static long m1946a(TimeUnit unit) {
        if (!f1831a) {
            return unit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
        return unit.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    /* renamed from: c */
    public Disposable mo1199c(Runnable run, long initialDelay, long period, TimeUnit unit) {
        AbstractC0261i w = mo1198b();
        Scheduler periodicTask = new Scheduler(RxJavaPlugins.m168m(run), w);
        Disposable d = w.mo1093c(periodicTask, initialDelay, period, unit);
        if (d == EmptyDisposable.INSTANCE) {
            return d;
        }
        return periodicTask;
    }
}
