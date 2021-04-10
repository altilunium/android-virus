package p007io.reactivex.internal.schedulers;

import com.own.bless.soy.C0010c2;
import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
/* renamed from: io.reactivex.internal.schedulers.f */
public final class InstantPeriodicTask implements Callable, Disposable {

    /* renamed from: f */
    static final FutureTask f1769f = new FutureTask(C0010c2.f22a, null);

    /* renamed from: a */
    final Runnable f1770a;

    /* renamed from: b */
    final AtomicReference f1771b = new AtomicReference();

    /* renamed from: c */
    final AtomicReference f1772c = new AtomicReference();

    /* renamed from: d */
    final ExecutorService f1773d;

    /* renamed from: e */
    Thread f1774e;

    @Override // java.util.concurrent.Callable
    public /* bridge */ /* synthetic */ Object call() {
        mo1201a();
        return null;
    }

    InstantPeriodicTask(Runnable task, ExecutorService executor) {
        this.f1770a = task;
        this.f1773d = executor;
    }

    /* renamed from: a */
    public Void mo1201a() {
        this.f1774e = Thread.currentThread();
        try {
            this.f1770a.run();
            mo1203c(this.f1773d.submit(this));
            this.f1774e = null;
        } catch (Throwable ex) {
            this.f1774e = null;
            RxJavaPlugins.m167l(ex);
        }
        return null;
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        AtomicReference atomicReference = this.f1772c;
        FutureTask futureTask = f1769f;
        Future<?> current = (Future) atomicReference.getAndSet(futureTask);
        boolean z = true;
        if (!(current == null || current == futureTask)) {
            current.cancel(this.f1774e != Thread.currentThread());
        }
        Future<?> current2 = (Future) this.f1771b.getAndSet(futureTask);
        if (current2 != null && current2 != futureTask) {
            if (this.f1774e == Thread.currentThread()) {
                z = false;
            }
            current2.cancel(z);
        }
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this.f1772c.get() == f1769f;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo1202b(Future future) {
        Future<?> current;
        do {
            current = (Future) this.f1772c.get();
            if (current == f1769f) {
                future.cancel(this.f1774e != Thread.currentThread());
                return;
            }
        } while (!this.f1772c.compareAndSet(current, future));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo1203c(Future future) {
        Future<?> current;
        do {
            current = (Future) this.f1771b.get();
            if (current == f1769f) {
                future.cancel(this.f1774e != Thread.currentThread());
                return;
            }
        } while (!this.f1771b.compareAndSet(current, future));
    }
}
