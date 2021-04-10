package p007io.reactivex.internal.schedulers;

import com.own.bless.soy.CompositeDisposable;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* access modifiers changed from: package-private */
/* renamed from: io.reactivex.internal.schedulers.g */
public final class IoScheduler implements Runnable {

    /* renamed from: a */
    private final long f1775a;

    /* renamed from: b */
    private final ConcurrentLinkedQueue f1776b;

    /* renamed from: c */
    final CompositeDisposable f1777c;

    /* renamed from: d */
    private final ScheduledExecutorService f1778d;

    /* renamed from: e */
    private final Future f1779e;

    /* renamed from: f */
    private final ThreadFactory f1780f;

    IoScheduler(long keepAliveTime, TimeUnit unit, ThreadFactory threadFactory) {
        long nanos = unit != null ? unit.toNanos(keepAliveTime) : 0;
        this.f1775a = nanos;
        this.f1776b = new ConcurrentLinkedQueue();
        this.f1777c = new CompositeDisposable();
        this.f1780f = threadFactory;
        ScheduledExecutorService evictor = null;
        Future<?> task = null;
        if (unit != null) {
            evictor = Executors.newScheduledThreadPool(1, C0270j.f1787f);
            task = evictor.scheduleWithFixedDelay(this, nanos, nanos, TimeUnit.NANOSECONDS);
        }
        this.f1778d = evictor;
        this.f1779e = task;
    }

    public void run() {
        mo1205a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public C0269i mo1206b() {
        if (this.f1777c.isDisposed()) {
            return C0270j.f1790i;
        }
        while (!this.f1776b.isEmpty()) {
            C0269i threadWorker = (C0269i) this.f1776b.poll();
            if (threadWorker != null) {
                return threadWorker;
            }
        }
        C0269i w = new C0269i(this.f1780f);
        this.f1777c.mo39a(w);
        return w;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo1208d(C0269i threadWorker) {
        threadWorker.mo1213g(mo1207c() + this.f1775a);
        this.f1776b.offer(threadWorker);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1205a() {
        if (!this.f1776b.isEmpty()) {
            long currentTimestamp = mo1207c();
            Iterator it = this.f1776b.iterator();
            while (it.hasNext()) {
                C0269i threadWorker = (C0269i) it.next();
                if (threadWorker.mo1212f() > currentTimestamp) {
                    return;
                }
                if (this.f1776b.remove(threadWorker)) {
                    this.f1777c.mo40b(threadWorker);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public long mo1207c() {
        return System.nanoTime();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo1209e() {
        this.f1777c.dispose();
        Future future = this.f1779e;
        if (future != null) {
            future.cancel(true);
        }
        ScheduledExecutorService scheduledExecutorService = this.f1778d;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
    }
}
