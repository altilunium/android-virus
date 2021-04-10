package p007io.reactivex.internal.schedulers;

import com.own.bless.soy.Disposable;
import com.own.bless.soy.Disposables;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import p007io.reactivex.AbstractC0261i;
import p007io.reactivex.internal.disposables.EmptyDisposable;

/* renamed from: io.reactivex.internal.schedulers.v */
/* compiled from: TrampolineScheduler */
final class C0277v extends AbstractC0261i implements Disposable {

    /* renamed from: a */
    final PriorityBlockingQueue f1818a = new PriorityBlockingQueue();

    /* renamed from: b */
    private final AtomicInteger f1819b = new AtomicInteger();

    /* renamed from: c */
    final AtomicInteger f1820c = new AtomicInteger();

    /* renamed from: d */
    volatile boolean f1821d;

    C0277v() {
    }

    @Override // p007io.reactivex.AbstractC0261i
    /* renamed from: b */
    public Disposable mo1092b(Runnable action, long delayTime, TimeUnit unit) {
        long execTime = mo1091a(TimeUnit.MILLISECONDS) + unit.toMillis(delayTime);
        return mo1223d(new TrampolineScheduler(action, this, execTime), execTime);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public Disposable mo1223d(Runnable action, long execTime) {
        if (this.f1821d) {
            return EmptyDisposable.INSTANCE;
        }
        C0275t timedRunnable = new C0275t(action, Long.valueOf(execTime), this.f1820c.incrementAndGet());
        this.f1818a.add(timedRunnable);
        if (this.f1819b.getAndIncrement() != 0) {
            return Disposables.m83a(new RunnableC0276u(this, timedRunnable));
        }
        int missed = 1;
        while (!this.f1821d) {
            C0275t polled = (C0275t) this.f1818a.poll();
            if (polled == null) {
                missed = this.f1819b.addAndGet(-missed);
                if (missed == 0) {
                    return EmptyDisposable.INSTANCE;
                }
            } else if (!polled.f1815d) {
                polled.f1812a.run();
            }
        }
        this.f1818a.clear();
        return EmptyDisposable.INSTANCE;
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        this.f1821d = true;
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this.f1821d;
    }
}
