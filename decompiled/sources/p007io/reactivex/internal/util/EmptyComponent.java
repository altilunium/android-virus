package p007io.reactivex.internal.util;

import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import com.own.bless.soy.Subscriber;
import com.own.bless.soy.Subscription;
import p007io.reactivex.CompletableObserver;
import p007io.reactivex.MaybeObserver;
import p007io.reactivex.Observer;
import p007io.reactivex.SingleObserver;

/* renamed from: io.reactivex.internal.util.EmptyComponent */
public enum EmptyComponent implements Subscriber, Observer, MaybeObserver, SingleObserver, CompletableObserver, Subscription, Disposable {
    INSTANCE;

    public static Subscriber asSubscriber() {
        return INSTANCE;
    }

    public static Observer asObserver() {
        return INSTANCE;
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return true;
    }

    @Override // com.own.bless.soy.Subscription
    public void request(long n) {
    }

    @Override // com.own.bless.soy.Subscription
    public void cancel() {
    }

    @Override // p007io.reactivex.SingleObserver, p007io.reactivex.MaybeObserver, p007io.reactivex.Observer, p007io.reactivex.CompletableObserver
    public void onSubscribe(Disposable d) {
        d.dispose();
    }

    @Override // com.own.bless.soy.Subscriber
    public void onSubscribe(Subscription s) {
        s.cancel();
    }

    @Override // p007io.reactivex.Observer, com.own.bless.soy.Subscriber
    public void onNext(Object t) {
    }

    @Override // p007io.reactivex.SingleObserver, p007io.reactivex.MaybeObserver, p007io.reactivex.Observer, com.own.bless.soy.Subscriber, p007io.reactivex.CompletableObserver
    public void onError(Throwable t) {
        RxJavaPlugins.m167l(t);
    }

    @Override // p007io.reactivex.MaybeObserver, p007io.reactivex.Observer, com.own.bless.soy.Subscriber, p007io.reactivex.CompletableObserver
    public void onComplete() {
    }

    public void onSuccess(Object value) {
    }
}
