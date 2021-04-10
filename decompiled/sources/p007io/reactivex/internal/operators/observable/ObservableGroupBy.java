package p007io.reactivex.internal.operators.observable;

import com.own.bless.soy.GroupedObservable;
import p007io.reactivex.Observer;

/* renamed from: io.reactivex.internal.operators.observable.a */
final class ObservableGroupBy extends GroupedObservable {

    /* renamed from: b */
    final C0263b f1729b;

    /* renamed from: g */
    public static ObservableGroupBy m1851g(Object obj, int bufferSize, ObservableGroupBy$GroupByObserver observableGroupBy$GroupByObserver, boolean delayError) {
        return new ObservableGroupBy(obj, new C0263b(bufferSize, observableGroupBy$GroupByObserver, obj, delayError));
    }

    protected ObservableGroupBy(Object obj, C0263b bVar) {
        super(obj);
        this.f1729b = bVar;
    }

    /* access modifiers changed from: protected */
    @Override // p007io.reactivex.Observable
    /* renamed from: f */
    public void mo1074f(Observer fVar) {
        this.f1729b.mo1169f(fVar);
    }

    /* renamed from: j */
    public void mo1163j(Object obj) {
        this.f1729b.mo1168e(obj);
    }

    /* renamed from: i */
    public void mo1162i(Throwable e) {
        this.f1729b.mo1167d(e);
    }

    /* renamed from: h */
    public void mo1161h() {
        this.f1729b.mo1166c();
    }
}
