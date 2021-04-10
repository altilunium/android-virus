package p007io.reactivex.internal.schedulers;

import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import p007io.reactivex.AbstractC0261i;
import p007io.reactivex.internal.disposables.DisposableContainer;
import p007io.reactivex.internal.disposables.EmptyDisposable;

/* renamed from: io.reactivex.internal.schedulers.l */
public class NewThreadWorker extends AbstractC0261i implements Disposable {

    /* renamed from: a */
    private final ScheduledExecutorService f1797a;

    /* renamed from: b */
    volatile boolean f1798b;

    public NewThreadWorker(ThreadFactory threadFactory) {
        this.f1797a = C0273p.m1909a(threadFactory);
    }

    @Override // p007io.reactivex.AbstractC0261i
    /* renamed from: b */
    public Disposable mo1092b(Runnable action, long delayTime, TimeUnit unit) {
        if (this.f1798b) {
            return EmptyDisposable.INSTANCE;
        }
        return mo1215d(action, delayTime, unit, null);
    }

    /* renamed from: e */
    public Disposable mo1216e(Runnable run, long initialDelay, long period, TimeUnit unit) {
        Future<?> f;
        Runnable decoratedRun = RxJavaPlugins.m168m(run);
        if (period <= 0) {
            InstantPeriodicTask periodicWrapper = new InstantPeriodicTask(decoratedRun, this.f1797a);
            if (initialDelay <= 0) {
                try {
                    f = this.f1797a.submit(periodicWrapper);
                } catch (RejectedExecutionException ex) {
                    RxJavaPlugins.m167l(ex);
                    return EmptyDisposable.INSTANCE;
                }
            } else {
                f = this.f1797a.schedule(periodicWrapper, initialDelay, unit);
            }
            periodicWrapper.mo1202b(f);
            return periodicWrapper;
        }
        ScheduledDirectPeriodicTask task = new ScheduledDirectPeriodicTask(decoratedRun);
        try {
            task.setFuture(this.f1797a.scheduleAtFixedRate(task, initialDelay, period, unit));
            return task;
        } catch (RejectedExecutionException ex2) {
            RxJavaPlugins.m167l(ex2);
            return EmptyDisposable.INSTANCE;
        }
    }

    /* renamed from: d */
    public ScheduledRunnable mo1215d(Runnable run, long delayTime, TimeUnit unit, DisposableContainer parent) {
        Future<?> f;
        ScheduledRunnable sr = new ScheduledRunnable(RxJavaPlugins.m168m(run), parent);
        if (parent != null && !parent.mo39a(sr)) {
            return sr;
        }
        if (delayTime <= 0) {
            try {
                f = this.f1797a.submit((Callable) sr);
            } catch (RejectedExecutionException ex) {
                if (parent != null) {
                    parent.mo40b(sr);
                }
                RxJavaPlugins.m167l(ex);
            }
        } else {
            f = this.f1797a.schedule((Callable) sr, delayTime, unit);
        }
        sr.setFuture(f);
        return sr;
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        if (!this.f1798b) {
            this.f1798b = true;
            this.f1797a.shutdownNow();
        }
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this.f1798b;
    }
}
