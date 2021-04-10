package p007io.reactivex.internal.subscribers;

import com.own.bless.soy.Subscriber;
import com.own.bless.soy.Subscription;
import p007io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import p007io.reactivex.internal.subscriptions.SubscriptionHelper;

/* renamed from: io.reactivex.internal.subscribers.DeferredScalarSubscriber */
public abstract class DeferredScalarSubscriber extends DeferredScalarSubscription implements Subscriber {
    private static final long serialVersionUID = 2984505488220891551L;
    protected boolean hasValue;
    protected Subscription upstream;

    @Override // com.own.bless.soy.Subscriber
    public abstract /* synthetic */ void onNext(Object obj);

    public DeferredScalarSubscriber(Subscriber v2Var) {
        super(v2Var);
    }

    @Override // com.own.bless.soy.Subscriber
    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.validate(this.upstream, s)) {
            this.upstream = s;
            this.downstream.onSubscribe(this);
            s.request(Long.MAX_VALUE);
        }
    }

    @Override // com.own.bless.soy.Subscriber
    public void onError(Throwable t) {
        this.value = null;
        this.downstream.onError(t);
    }

    @Override // com.own.bless.soy.Subscriber
    public void onComplete() {
        if (this.hasValue) {
            complete(this.value);
        } else {
            this.downstream.onComplete();
        }
    }

    @Override // p007io.reactivex.internal.subscriptions.DeferredScalarSubscription, p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription, com.own.bless.soy.Subscription
    public void cancel() {
        super.cancel();
        this.upstream.cancel();
    }
}
