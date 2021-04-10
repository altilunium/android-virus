package p007io.reactivex.internal.schedulers;

import java.util.concurrent.ThreadFactory;

/* renamed from: io.reactivex.internal.schedulers.i */
/* compiled from: IoScheduler */
final class C0269i extends NewThreadWorker {

    /* renamed from: c */
    private long f1785c = 0;

    C0269i(ThreadFactory threadFactory) {
        super(threadFactory);
    }

    /* renamed from: f */
    public long mo1212f() {
        return this.f1785c;
    }

    /* renamed from: g */
    public void mo1213g(long expirationTime) {
        this.f1785c = expirationTime;
    }
}
