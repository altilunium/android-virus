package p007io.reactivex.internal.schedulers;

import com.own.bless.soy.CompositeDisposable;
import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import p007io.reactivex.AbstractC0261i;
import p007io.reactivex.internal.disposables.EmptyDisposable;

/* renamed from: io.reactivex.internal.schedulers.q */
final class SingleScheduler extends AbstractC0261i {

    /* renamed from: a */
    final ScheduledExecutorService f1803a;

    /* renamed from: b */
    final CompositeDisposable f1804b = new CompositeDisposable();

    /* renamed from: c */
    volatile boolean f1805c;

    SingleScheduler(ScheduledExecutorService executor) {
        this.f1803a = executor;
    }

    @Override // p007io.reactivex.AbstractC0261i
    /* renamed from: b */
    public Disposable mo1092b(Runnable run, long delay, TimeUnit unit) {
        Future<?> f;
        if (this.f1805c) {
            return EmptyDisposable.INSTANCE;
        }
        ScheduledRunnable sr = new ScheduledRunnable(RxJavaPlugins.m168m(run), this.f1804b);
        this.f1804b.mo39a(sr);
        if (delay <= 0) {
            try {
                f = this.f1803a.submit((Callable) sr);
            } catch (RejectedExecutionException ex) {
                dispose();
                RxJavaPlugins.m167l(ex);
                return EmptyDisposable.INSTANCE;
            }
        } else {
            f = this.f1803a.schedule((Callable) sr, delay, unit);
        }
        sr.setFuture(f);
        return sr;
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        if (!this.f1805c) {
            this.f1805c = true;
            this.f1804b.dispose();
        }
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this.f1805c;
    }
}
