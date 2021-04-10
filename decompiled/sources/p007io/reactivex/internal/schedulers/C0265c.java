package p007io.reactivex.internal.schedulers;

import java.util.concurrent.ThreadFactory;

/* access modifiers changed from: package-private */
/* renamed from: io.reactivex.internal.schedulers.c */
/* compiled from: ComputationScheduler */
public final class C0265c {

    /* renamed from: a */
    final int f1760a;

    /* renamed from: b */
    final C0266d[] f1761b;

    /* renamed from: c */
    long f1762c;

    C0265c(int maxThreads, ThreadFactory threadFactory) {
        this.f1760a = maxThreads;
        this.f1761b = new C0266d[maxThreads];
        for (int i = 0; i < maxThreads; i++) {
            this.f1761b[i] = new C0266d(threadFactory);
        }
    }

    /* renamed from: a */
    public C0266d mo1196a() {
        int c = this.f1760a;
        if (c == 0) {
            return C0267e.f1766h;
        }
        C0266d[] dVarArr = this.f1761b;
        long j = this.f1762c;
        this.f1762c = 1 + j;
        return dVarArr[(int) (j % ((long) c))];
    }

    /* renamed from: b */
    public void mo1197b() {
        for (C0266d w : this.f1761b) {
            w.dispose();
        }
    }
}
