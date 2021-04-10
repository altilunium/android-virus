package p007io.reactivex.internal.schedulers;

import com.own.bless.soy.CompositeDisposable;
import com.own.bless.soy.Disposable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import p007io.reactivex.AbstractC0261i;
import p007io.reactivex.internal.disposables.EmptyDisposable;

/* renamed from: io.reactivex.internal.schedulers.h */
/* compiled from: IoScheduler */
final class RunnableC0268h extends AbstractC0261i implements Runnable {

    /* renamed from: a */
    private final CompositeDisposable f1781a;

    /* renamed from: b */
    private final IoScheduler f1782b;

    /* renamed from: c */
    private final C0269i f1783c;

    /* renamed from: d */
    final AtomicBoolean f1784d = new AtomicBoolean();

    RunnableC0268h(IoScheduler pool) {
        this.f1782b = pool;
        this.f1781a = new CompositeDisposable();
        this.f1783c = pool.mo1206b();
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        if (this.f1784d.compareAndSet(false, true)) {
            this.f1781a.dispose();
            if (C0270j.f1791j) {
                this.f1783c.mo1215d(this, 0, TimeUnit.NANOSECONDS, null);
            } else {
                this.f1782b.mo1208d(this.f1783c);
            }
        }
    }

    public void run() {
        this.f1782b.mo1208d(this.f1783c);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this.f1784d.get();
    }

    @Override // p007io.reactivex.AbstractC0261i
    /* renamed from: b */
    public Disposable mo1092b(Runnable action, long delayTime, TimeUnit unit) {
        if (this.f1781a.isDisposed()) {
            return EmptyDisposable.INSTANCE;
        }
        return this.f1783c.mo1215d(action, delayTime, unit, this.f1781a);
    }
}
