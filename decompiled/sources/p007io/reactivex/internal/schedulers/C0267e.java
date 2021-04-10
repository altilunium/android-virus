package p007io.reactivex.internal.schedulers;

import com.own.bless.soy.Disposable;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.AbstractC0261i;
import p007io.reactivex.AbstractC0283j;

/* renamed from: io.reactivex.internal.schedulers.e */
/* compiled from: ComputationScheduler */
public final class C0267e extends AbstractC0283j {

    /* renamed from: e */
    static final C0265c f1763e;

    /* renamed from: f */
    static final RxThreadFactory f1764f;

    /* renamed from: g */
    static final int f1765g = m1887d(Runtime.getRuntime().availableProcessors(), Integer.getInteger("rx2.computation-threads", 0).intValue());

    /* renamed from: h */
    static final C0266d f1766h;

    /* renamed from: c */
    final ThreadFactory f1767c;

    /* renamed from: d */
    final AtomicReference f1768d;

    static {
        C0266d dVar = new C0266d(new RxThreadFactory("RxComputationShutdown"));
        f1766h = dVar;
        dVar.dispose();
        RxThreadFactory rxThreadFactory = new RxThreadFactory("RxComputationThreadPool", Math.max(1, Math.min(10, Integer.getInteger("rx2.computation-priority", 5).intValue())), true);
        f1764f = rxThreadFactory;
        C0265c cVar = new C0265c(0, rxThreadFactory);
        f1763e = cVar;
        cVar.mo1197b();
    }

    /* renamed from: d */
    static int m1887d(int cpuCount, int paramThreads) {
        return (paramThreads <= 0 || paramThreads > cpuCount) ? cpuCount : paramThreads;
    }

    public C0267e() {
        this(f1764f);
    }

    public C0267e(ThreadFactory threadFactory) {
        this.f1767c = threadFactory;
        this.f1768d = new AtomicReference(f1763e);
        mo1200e();
    }

    @Override // p007io.reactivex.AbstractC0283j
    /* renamed from: b */
    public AbstractC0261i mo1198b() {
        return new ComputationScheduler(((C0265c) this.f1768d.get()).mo1196a());
    }

    @Override // p007io.reactivex.AbstractC0283j
    /* renamed from: c */
    public Disposable mo1199c(Runnable run, long initialDelay, long period, TimeUnit unit) {
        return ((C0265c) this.f1768d.get()).mo1196a().mo1216e(run, initialDelay, period, unit);
    }

    /* renamed from: e */
    public void mo1200e() {
        C0265c update = new C0265c(f1765g, this.f1767c);
        if (!this.f1768d.compareAndSet(f1763e, update)) {
            update.mo1197b();
        }
    }
}
