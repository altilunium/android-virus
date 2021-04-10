package p007io.reactivex;

import java.util.concurrent.TimeUnit;
import p007io.reactivex.internal.disposables.SequentialDisposable;

/* access modifiers changed from: package-private */
/* renamed from: io.reactivex.h */
/* compiled from: Scheduler */
public final class RunnableC0260h implements Runnable {

    /* renamed from: a */
    final Runnable f1707a;

    /* renamed from: b */
    final SequentialDisposable f1708b;

    /* renamed from: c */
    final long f1709c;

    /* renamed from: d */
    long f1710d;

    /* renamed from: e */
    long f1711e;

    /* renamed from: f */
    long f1712f;

    /* renamed from: g */
    final /* synthetic */ AbstractC0261i f1713g;

    RunnableC0260h(AbstractC0261i this$0, long firstStartInNanoseconds, Runnable decoratedRun, long firstNowNanoseconds, SequentialDisposable sd, long periodInNanoseconds) {
        this.f1713g = this$0;
        this.f1707a = decoratedRun;
        this.f1708b = sd;
        this.f1709c = periodInNanoseconds;
        this.f1711e = firstNowNanoseconds;
        this.f1712f = firstStartInNanoseconds;
    }

    public void run() {
        long nextTick;
        this.f1707a.run();
        if (!this.f1708b.isDisposed()) {
            AbstractC0261i iVar = this.f1713g;
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            long nowNanoseconds = iVar.mo1091a(timeUnit);
            long j = AbstractC0283j.f1832b;
            long j2 = this.f1711e;
            if (nowNanoseconds + j >= j2) {
                long j3 = this.f1709c;
                if (nowNanoseconds < j2 + j3 + j) {
                    long j4 = this.f1712f;
                    long j5 = this.f1710d + 1;
                    this.f1710d = j5;
                    nextTick = j4 + (j5 * j3);
                    this.f1711e = nowNanoseconds;
                    this.f1708b.replace(this.f1713g.mo1092b(this, nextTick - nowNanoseconds, timeUnit));
                }
            }
            long nextTick2 = this.f1709c;
            long nextTick3 = nowNanoseconds + nextTick2;
            long j6 = this.f1710d + 1;
            this.f1710d = j6;
            this.f1712f = nextTick3 - (nextTick2 * j6);
            nextTick = nextTick3;
            this.f1711e = nowNanoseconds;
            this.f1708b.replace(this.f1713g.mo1092b(this, nextTick - nowNanoseconds, timeUnit));
        }
    }
}
