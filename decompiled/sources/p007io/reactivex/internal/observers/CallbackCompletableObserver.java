package p007io.reactivex.internal.observers;

import com.own.bless.soy.Action;
import com.own.bless.soy.Consumer;
import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.CompletableObserver;
import p007io.reactivex.exceptions.Exceptions;
import p007io.reactivex.exceptions.OnErrorNotImplementedException;
import p007io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.observers.CallbackCompletableObserver */
public final class CallbackCompletableObserver extends AtomicReference implements CompletableObserver, Disposable, Consumer {
    private static final long serialVersionUID = -4361286194466301354L;
    final Action onComplete;
    final Consumer onError;

    public CallbackCompletableObserver(Action onComplete2) {
        this.onError = this;
        this.onComplete = onComplete2;
    }

    public CallbackCompletableObserver(Consumer n1Var, Action onComplete2) {
        this.onError = n1Var;
        this.onComplete = onComplete2;
    }

    public void accept(Throwable e) {
        RxJavaPlugins.m167l(new OnErrorNotImplementedException(e));
    }

    @Override // p007io.reactivex.CompletableObserver
    public void onComplete() {
        try {
            this.onComplete.run();
        } catch (Throwable ex) {
            Exceptions.m1830a(ex);
            RxJavaPlugins.m167l(ex);
        }
        lazySet(DisposableHelper.DISPOSED);
    }

    @Override // p007io.reactivex.CompletableObserver
    public void onError(Throwable e) {
        try {
            this.onError.accept(e);
        } catch (Throwable ex) {
            Exceptions.m1830a(ex);
            RxJavaPlugins.m167l(ex);
        }
        lazySet(DisposableHelper.DISPOSED);
    }

    @Override // p007io.reactivex.CompletableObserver
    public void onSubscribe(Disposable d) {
        DisposableHelper.setOnce(this, d);
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    public boolean hasCustomOnError() {
        return this.onError != this;
    }
}
