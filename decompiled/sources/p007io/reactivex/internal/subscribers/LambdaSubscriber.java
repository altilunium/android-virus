package p007io.reactivex.internal.subscribers;

import com.own.bless.soy.Action;
import com.own.bless.soy.C0010c2;
import com.own.bless.soy.Consumer;
import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import com.own.bless.soy.Subscriber;
import com.own.bless.soy.Subscription;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.exceptions.CompositeException;
import p007io.reactivex.exceptions.Exceptions;
import p007io.reactivex.internal.subscriptions.SubscriptionHelper;

/* renamed from: io.reactivex.internal.subscribers.LambdaSubscriber */
public final class LambdaSubscriber extends AtomicReference implements Subscriber, Subscription, Disposable {
    private static final long serialVersionUID = -7251123623727029452L;
    final Action onComplete;
    final Consumer onError;
    final Consumer onNext;
    final Consumer onSubscribe;

    public LambdaSubscriber(Consumer n1Var, Consumer n1Var2, Action onComplete2, Consumer n1Var3) {
        this.onNext = n1Var;
        this.onError = n1Var2;
        this.onComplete = onComplete2;
        this.onSubscribe = n1Var3;
    }

    @Override // com.own.bless.soy.Subscriber
    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.setOnce(this, s)) {
            try {
                this.onSubscribe.accept(this);
            } catch (Throwable ex) {
                Exceptions.m1830a(ex);
                s.cancel();
                onError(ex);
            }
        }
    }

    @Override // com.own.bless.soy.Subscriber
    public void onNext(Object obj) {
        if (!isDisposed()) {
            try {
                this.onNext.accept(obj);
            } catch (Throwable e) {
                Exceptions.m1830a(e);
                ((Subscription) get()).cancel();
                onError(e);
            }
        }
    }

    @Override // com.own.bless.soy.Subscriber
    public void onError(Throwable t) {
        Object obj = get();
        SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
        if (obj != subscriptionHelper) {
            lazySet(subscriptionHelper);
            try {
                this.onError.accept(t);
            } catch (Throwable e) {
                Exceptions.m1830a(e);
                RxJavaPlugins.m167l(new CompositeException(t, e));
            }
        } else {
            RxJavaPlugins.m167l(t);
        }
    }

    @Override // com.own.bless.soy.Subscriber
    public void onComplete() {
        Object obj = get();
        SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
        if (obj != subscriptionHelper) {
            lazySet(subscriptionHelper);
            try {
                this.onComplete.run();
            } catch (Throwable e) {
                Exceptions.m1830a(e);
                RxJavaPlugins.m167l(e);
            }
        }
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        cancel();
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return get() == SubscriptionHelper.CANCELLED;
    }

    @Override // com.own.bless.soy.Subscription
    public void request(long n) {
        ((Subscription) get()).request(n);
    }

    @Override // com.own.bless.soy.Subscription
    public void cancel() {
        SubscriptionHelper.cancel(this);
    }

    public boolean hasCustomOnError() {
        return this.onError != C0010c2.f25d;
    }
}
