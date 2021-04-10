package p007io.reactivex.internal.subscribers;

import com.own.bless.soy.Disposable;
import com.own.bless.soy.Subscriber;
import com.own.bless.soy.Subscription;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.internal.disposables.DisposableHelper;
import p007io.reactivex.internal.subscriptions.SubscriptionHelper;

/* renamed from: io.reactivex.internal.subscribers.SubscriberResourceWrapper */
public final class SubscriberResourceWrapper extends AtomicReference implements Subscriber, Disposable, Subscription {
    private static final long serialVersionUID = -8612022020200669122L;
    final Subscriber downstream;
    final AtomicReference upstream = new AtomicReference();

    public SubscriberResourceWrapper(Subscriber v2Var) {
        this.downstream = v2Var;
    }

    @Override // com.own.bless.soy.Subscriber
    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.setOnce(this.upstream, s)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // com.own.bless.soy.Subscriber
    public void onNext(Object obj) {
        this.downstream.onNext(obj);
    }

    @Override // com.own.bless.soy.Subscriber
    public void onError(Throwable t) {
        DisposableHelper.dispose(this);
        this.downstream.onError(t);
    }

    @Override // com.own.bless.soy.Subscriber
    public void onComplete() {
        DisposableHelper.dispose(this);
        this.downstream.onComplete();
    }

    @Override // com.own.bless.soy.Subscription
    public void request(long n) {
        if (SubscriptionHelper.validate(n)) {
            ((Subscription) this.upstream.get()).request(n);
        }
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        SubscriptionHelper.cancel(this.upstream);
        DisposableHelper.dispose(this);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this.upstream.get() == SubscriptionHelper.CANCELLED;
    }

    @Override // com.own.bless.soy.Subscription
    public void cancel() {
        dispose();
    }

    public void setResource(Disposable resource) {
        DisposableHelper.set(this, resource);
    }
}
