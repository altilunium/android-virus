package p007io.reactivex.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import p007io.reactivex.AbstractC0261i;
import p007io.reactivex.AbstractC0283j;

/* renamed from: io.reactivex.internal.schedulers.k */
public final class NewThreadScheduler extends AbstractC0283j {

    /* renamed from: d */
    private static final RxThreadFactory f1795d = new RxThreadFactory("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.newthread-priority", 5).intValue())));

    /* renamed from: c */
    final ThreadFactory f1796c;

    public NewThreadScheduler() {
        this(f1795d);
    }

    public NewThreadScheduler(ThreadFactory threadFactory) {
        this.f1796c = threadFactory;
    }

    @Override // p007io.reactivex.AbstractC0283j
    /* renamed from: b */
    public AbstractC0261i mo1198b() {
        return new NewThreadWorker(this.f1796c);
    }
}
