package p007io.reactivex.internal.operators.observable;

import java.util.concurrent.TimeUnit;
import p007io.reactivex.AbstractC0261i;
import p007io.reactivex.AbstractC0283j;
import p007io.reactivex.Observable;
import p007io.reactivex.Observer;
import p007io.reactivex.internal.schedulers.C0278w;

/* renamed from: io.reactivex.internal.operators.observable.d */
/* compiled from: ObservableInterval */
public final class C0264d extends Observable {

    /* renamed from: a */
    final AbstractC0283j f1741a;

    /* renamed from: b */
    final long f1742b;

    /* renamed from: c */
    final long f1743c;

    /* renamed from: d */
    final TimeUnit f1744d;

    public C0264d(long initialDelay, long period, TimeUnit unit, AbstractC0283j scheduler) {
        this.f1742b = initialDelay;
        this.f1743c = period;
        this.f1744d = unit;
        this.f1741a = scheduler;
    }

    @Override // p007io.reactivex.Observable
    /* renamed from: f */
    public void mo1074f(Observer fVar) {
        ObservableInterval is = new ObservableInterval(fVar);
        fVar.onSubscribe(is);
        AbstractC0283j sch = this.f1741a;
        if (sch instanceof C0278w) {
            AbstractC0261i worker = sch.mo1198b();
            is.mo1170a(worker);
            worker.mo1093c(is, this.f1742b, this.f1743c, this.f1744d);
            return;
        }
        is.mo1170a(sch.mo1199c(is, this.f1742b, this.f1743c, this.f1744d));
    }
}
