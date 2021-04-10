package p007io.reactivex.internal.schedulers;

import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.AbstractC0261i;
import p007io.reactivex.AbstractC0283j;
import p007io.reactivex.internal.disposables.EmptyDisposable;

/* renamed from: io.reactivex.internal.schedulers.r */
/* compiled from: SingleScheduler */
public final class C0274r extends AbstractC0283j {

    /* renamed from: d */
    static final RxThreadFactory f1806d = new RxThreadFactory("RxSingleScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.single-priority", 5).intValue())), true);

    /* renamed from: e */
    static final ScheduledExecutorService f1807e;

    /* renamed from: c */
    final AtomicReference f1808c;

    static {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(0);
        f1807e = newScheduledThreadPool;
        newScheduledThreadPool.shutdown();
    }

    public C0274r() {
        this(f1806d);
    }

    public C0274r(ThreadFactory threadFactory) {
        AtomicReference atomicReference = new AtomicReference();
        this.f1808c = atomicReference;
        atomicReference.lazySet(m1916d(threadFactory));
    }

    /* renamed from: d */
    static ScheduledExecutorService m1916d(ThreadFactory threadFactory) {
        return C0273p.m1909a(threadFactory);
    }

    @Override // p007io.reactivex.AbstractC0283j
    /* renamed from: b */
    public AbstractC0261i mo1198b() {
        return new SingleScheduler((ScheduledExecutorService) this.f1808c.get());
    }

    @Override // p007io.reactivex.AbstractC0283j
    /* renamed from: c */
    public Disposable mo1199c(Runnable run, long initialDelay, long period, TimeUnit unit) {
        Future<?> f;
        Runnable decoratedRun = RxJavaPlugins.m168m(run);
        if (period <= 0) {
            ScheduledExecutorService exec = (ScheduledExecutorService) this.f1808c.get();
            InstantPeriodicTask periodicWrapper = new InstantPeriodicTask(decoratedRun, exec);
            if (initialDelay <= 0) {
                try {
                    f = exec.submit(periodicWrapper);
                } catch (RejectedExecutionException ex) {
                    RxJavaPlugins.m167l(ex);
                    return EmptyDisposable.INSTANCE;
                }
            } else {
                f = exec.schedule(periodicWrapper, initialDelay, unit);
            }
            periodicWrapper.mo1202b(f);
            return periodicWrapper;
        }
        ScheduledDirectPeriodicTask task = new ScheduledDirectPeriodicTask(decoratedRun);
        try {
            task.setFuture(((ScheduledExecutorService) this.f1808c.get()).scheduleAtFixedRate(task, initialDelay, period, unit));
            return task;
        } catch (RejectedExecutionException ex2) {
            RxJavaPlugins.m167l(ex2);
            return EmptyDisposable.INSTANCE;
        }
    }
}
