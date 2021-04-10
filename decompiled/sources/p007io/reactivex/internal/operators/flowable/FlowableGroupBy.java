package p007io.reactivex.internal.operators.flowable;

import com.own.bless.soy.GroupedFlowable;

/* renamed from: io.reactivex.internal.operators.flowable.a */
final class FlowableGroupBy extends GroupedFlowable {

    /* renamed from: b */
    final C0262b f1716b;

    /* renamed from: a */
    public static FlowableGroupBy m1845a(Object obj, int bufferSize, FlowableGroupBy$GroupBySubscriber flowableGroupBy$GroupBySubscriber, boolean delayError) {
        return new FlowableGroupBy(obj, new C0262b(bufferSize, flowableGroupBy$GroupBySubscriber, obj, delayError));
    }

    protected FlowableGroupBy(Object obj, C0262b bVar) {
        super(obj);
        this.f1716b = bVar;
    }

    /* renamed from: d */
    public void mo1141d(Object obj) {
        this.f1716b.onNext(obj);
    }

    /* renamed from: c */
    public void mo1140c(Throwable e) {
        this.f1716b.onError(e);
    }

    /* renamed from: b */
    public void mo1139b() {
        this.f1716b.onComplete();
    }
}
