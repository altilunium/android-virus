package p007io.reactivex.internal.schedulers;

import com.own.bless.soy.C0013e2;

/* access modifiers changed from: package-private */
/* renamed from: io.reactivex.internal.schedulers.t */
/* compiled from: TrampolineScheduler */
public final class C0275t implements Comparable {

    /* renamed from: a */
    final Runnable f1812a;

    /* renamed from: b */
    final long f1813b;

    /* renamed from: c */
    final int f1814c;

    /* renamed from: d */
    volatile boolean f1815d;

    C0275t(Runnable run, Long execTime, int count) {
        this.f1812a = run;
        this.f1813b = execTime.longValue();
        this.f1814c = count;
    }

    /* renamed from: a */
    public int compareTo(C0275t that) {
        int result = C0013e2.m74b(this.f1813b, that.f1813b);
        if (result == 0) {
            return C0013e2.m73a(this.f1814c, that.f1814c);
        }
        return result;
    }
}
