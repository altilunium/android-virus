package p007io.reactivex.internal.operators.maybe;

import com.own.bless.soy.Action;
import com.own.bless.soy.C0010c2;
import com.own.bless.soy.Consumer;
import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.MaybeObserver;
import p007io.reactivex.exceptions.CompositeException;
import p007io.reactivex.exceptions.Exceptions;
import p007io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeCallbackObserver */
public final class MaybeCallbackObserver extends AtomicReference implements MaybeObserver, Disposable {
    private static final long serialVersionUID = -6076952298809384986L;
    final Action onComplete;
    final Consumer onError;
    final Consumer onSuccess;

    public MaybeCallbackObserver(Consumer n1Var, Consumer n1Var2, Action onComplete2) {
        this.onSuccess = n1Var;
        this.onError = n1Var2;
        this.onComplete = onComplete2;
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed((Disposable) get());
    }

    @Override // p007io.reactivex.MaybeObserver
    public void onSubscribe(Disposable d) {
        DisposableHelper.setOnce(this, d);
    }

    public void onSuccess(Object obj) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onSuccess.accept(obj);
        } catch (Throwable ex) {
            Exceptions.m1830a(ex);
            RxJavaPlugins.m167l(ex);
        }
    }

    @Override // p007io.reactivex.MaybeObserver
    public void onError(Throwable e) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onError.accept(e);
        } catch (Throwable ex) {
            Exceptions.m1830a(ex);
            RxJavaPlugins.m167l(new CompositeException(e, ex));
        }
    }

    @Override // p007io.reactivex.MaybeObserver
    public void onComplete() {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onComplete.run();
        } catch (Throwable ex) {
            Exceptions.m1830a(ex);
            RxJavaPlugins.m167l(ex);
        }
    }

    public boolean hasCustomOnError() {
        return this.onError != C0010c2.f25d;
    }
}
