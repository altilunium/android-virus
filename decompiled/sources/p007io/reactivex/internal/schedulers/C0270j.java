package p007io.reactivex.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.AbstractC0261i;
import p007io.reactivex.AbstractC0283j;

/* renamed from: io.reactivex.internal.schedulers.j */
/* compiled from: IoScheduler */
public final class C0270j extends AbstractC0283j {

    /* renamed from: e */
    static final RxThreadFactory f1786e;

    /* renamed from: f */
    static final RxThreadFactory f1787f;

    /* renamed from: g */
    private static final long f1788g = Long.getLong("rx2.io-keep-alive-time", 60).longValue();

    /* renamed from: h */
    private static final TimeUnit f1789h = TimeUnit.SECONDS;

    /* renamed from: i */
    static final C0269i f1790i;

    /* renamed from: j */
    static boolean f1791j = Boolean.getBoolean("rx2.io-scheduled-release");

    /* renamed from: k */
    static final IoScheduler f1792k;

    /* renamed from: c */
    final ThreadFactory f1793c;

    /* renamed from: d */
    final AtomicReference f1794d;

    static {
        C0269i iVar = new C0269i(new RxThreadFactory("RxCachedThreadSchedulerShutdown"));
        f1790i = iVar;
        iVar.dispose();
        int priority = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        RxThreadFactory rxThreadFactory = new RxThreadFactory("RxCachedThreadScheduler", priority);
        f1786e = rxThreadFactory;
        f1787f = new RxThreadFactory("RxCachedWorkerPoolEvictor", priority);
        IoScheduler gVar = new IoScheduler(0, null, rxThreadFactory);
        f1792k = gVar;
        gVar.mo1209e();
    }

    public C0270j() {
        this(f1786e);
    }

    public C0270j(ThreadFactory threadFactory) {
        this.f1793c = threadFactory;
        this.f1794d = new AtomicReference(f1792k);
        mo1214d();
    }

    /* renamed from: d */
    public void mo1214d() {
        IoScheduler update = new IoScheduler(f1788g, f1789h, this.f1793c);
        if (!this.f1794d.compareAndSet(f1792k, update)) {
            update.mo1209e();
        }
    }

    @Override // p007io.reactivex.AbstractC0283j
    /* renamed from: b */
    public AbstractC0261i mo1198b() {
        return new RunnableC0268h((IoScheduler) this.f1794d.get());
    }
}
