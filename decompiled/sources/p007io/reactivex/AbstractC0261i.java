package p007io.reactivex;

import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import p007io.reactivex.internal.disposables.EmptyDisposable;
import p007io.reactivex.internal.disposables.SequentialDisposable;

/* renamed from: io.reactivex.i */
/* compiled from: Scheduler */
public abstract class AbstractC0261i implements Disposable {
    /* renamed from: b */
    public abstract Disposable mo1092b(Runnable runnable, long j, TimeUnit timeUnit);

    /* renamed from: c */
    public Disposable mo1093c(Runnable run, long initialDelay, long period, TimeUnit unit) {
        SequentialDisposable first = new SequentialDisposable();
        SequentialDisposable sd = new SequentialDisposable(first);
        Runnable decoratedRun = RxJavaPlugins.m168m(run);
        long periodInNanoseconds = unit.toNanos(period);
        long firstNowNanoseconds = mo1091a(TimeUnit.NANOSECONDS);
        Disposable d = mo1092b(new RunnableC0260h(this, firstNowNanoseconds + unit.toNanos(initialDelay), decoratedRun, firstNowNanoseconds, sd, periodInNanoseconds), initialDelay, unit);
        if (d == EmptyDisposable.INSTANCE) {
            return d;
        }
        first.replace(d);
        return sd;
    }

    /* renamed from: a */
    public long mo1091a(TimeUnit unit) {
        return AbstractC0283j.m1946a(unit);
    }
}
