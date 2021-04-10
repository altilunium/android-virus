package p007io.reactivex.internal.observers;

import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.CompletableObserver;
import p007io.reactivex.exceptions.OnErrorNotImplementedException;
import p007io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.observers.EmptyCompletableObserver */
public final class EmptyCompletableObserver extends AtomicReference implements CompletableObserver, Disposable {
    private static final long serialVersionUID = -7545121636549663526L;

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    @Override // p007io.reactivex.CompletableObserver
    public void onComplete() {
        lazySet(DisposableHelper.DISPOSED);
    }

    @Override // p007io.reactivex.CompletableObserver
    public void onError(Throwable e) {
        lazySet(DisposableHelper.DISPOSED);
        RxJavaPlugins.m167l(new OnErrorNotImplementedException(e));
    }

    @Override // p007io.reactivex.CompletableObserver
    public void onSubscribe(Disposable d) {
        DisposableHelper.setOnce(this, d);
    }

    public boolean hasCustomOnError() {
        return false;
    }
}
