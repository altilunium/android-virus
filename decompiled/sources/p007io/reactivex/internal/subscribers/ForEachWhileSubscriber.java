package p007io.reactivex.internal.subscribers;

import com.own.bless.soy.Action;
import com.own.bless.soy.Consumer;
import com.own.bless.soy.Disposable;
import com.own.bless.soy.Predicate;
import com.own.bless.soy.RxJavaPlugins;
import com.own.bless.soy.Subscriber;
import com.own.bless.soy.Subscription;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.exceptions.CompositeException;
import p007io.reactivex.exceptions.Exceptions;
import p007io.reactivex.internal.subscriptions.SubscriptionHelper;

/* renamed from: io.reactivex.internal.subscribers.ForEachWhileSubscriber */
public final class ForEachWhileSubscriber extends AtomicReference implements Subscriber, Disposable {
    private static final long serialVersionUID = -4403180040475402120L;
    boolean done;
    final Action onComplete;
    final Consumer onError;
    final Predicate onNext;

    public ForEachWhileSubscriber(Predicate p1Var, Consumer n1Var, Action onComplete2) {
        this.onNext = p1Var;
        this.onError = n1Var;
        this.onComplete = onComplete2;
    }

    @Override // com.own.bless.soy.Subscriber
    public void onSubscribe(Subscription s) {
        SubscriptionHelper.setOnce(this, s, Long.MAX_VALUE);
    }

    @Override // com.own.bless.soy.Subscriber
    public void onNext(Object obj) {
        if (!this.done) {
            try {
                if (!this.onNext.mo34a(obj)) {
                    dispose();
                    onComplete();
                }
            } catch (Throwable ex) {
                Exceptions.m1830a(ex);
                dispose();
                onError(ex);
            }
        }
    }

    @Override // com.own.bless.soy.Subscriber
    public void onError(Throwable t) {
        if (this.done) {
            RxJavaPlugins.m167l(t);
            return;
        }
        this.done = true;
        try {
            this.onError.accept(t);
        } catch (Throwable ex) {
            Exceptions.m1830a(ex);
            RxJavaPlugins.m167l(new CompositeException(t, ex));
        }
    }

    @Override // com.own.bless.soy.Subscriber
    public void onComplete() {
        if (!this.done) {
            this.done = true;
            try {
                this.onComplete.run();
            } catch (Throwable ex) {
                Exceptions.m1830a(ex);
                RxJavaPlugins.m167l(ex);
            }
        }
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        SubscriptionHelper.cancel(this);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return get() == SubscriptionHelper.CANCELLED;
    }
}
