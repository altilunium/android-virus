package p007io.reactivex.internal.subscriptions;

import com.own.bless.soy.Disposable;
import com.own.bless.soy.Subscription;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.subscriptions.AsyncSubscription */
public final class AsyncSubscription extends AtomicLong implements Subscription, Disposable {
    private static final long serialVersionUID = 7028635084060361255L;
    final AtomicReference actual;
    final AtomicReference resource;

    public AsyncSubscription() {
        this.resource = new AtomicReference();
        this.actual = new AtomicReference();
    }

    public AsyncSubscription(Disposable resource2) {
        this();
        this.resource.lazySet(resource2);
    }

    @Override // com.own.bless.soy.Subscription
    public void request(long n) {
        SubscriptionHelper.deferredRequest(this.actual, this, n);
    }

    @Override // com.own.bless.soy.Subscription
    public void cancel() {
        dispose();
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        SubscriptionHelper.cancel(this.actual);
        DisposableHelper.dispose(this.resource);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this.actual.get() == SubscriptionHelper.CANCELLED;
    }

    public boolean setResource(Disposable r) {
        return DisposableHelper.set(this.resource, r);
    }

    public boolean replaceResource(Disposable r) {
        return DisposableHelper.replace(this.resource, r);
    }

    public void setSubscription(Subscription s) {
        SubscriptionHelper.deferredSetOnce(this.actual, this, s);
    }
}
