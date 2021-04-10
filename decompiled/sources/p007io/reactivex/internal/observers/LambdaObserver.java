package p007io.reactivex.internal.observers;

import com.own.bless.soy.Action;
import com.own.bless.soy.C0010c2;
import com.own.bless.soy.Consumer;
import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.Observer;
import p007io.reactivex.exceptions.CompositeException;
import p007io.reactivex.exceptions.Exceptions;
import p007io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.observers.LambdaObserver */
public final class LambdaObserver extends AtomicReference implements Observer, Disposable {
    private static final long serialVersionUID = -7251123623727029452L;
    final Action onComplete;
    final Consumer onError;
    final Consumer onNext;
    final Consumer onSubscribe;

    public LambdaObserver(Consumer n1Var, Consumer n1Var2, Action onComplete2, Consumer n1Var3) {
        this.onNext = n1Var;
        this.onError = n1Var2;
        this.onComplete = onComplete2;
        this.onSubscribe = n1Var3;
    }

    @Override // p007io.reactivex.Observer
    public void onSubscribe(Disposable d) {
        if (DisposableHelper.setOnce(this, d)) {
            try {
                this.onSubscribe.accept(this);
            } catch (Throwable ex) {
                Exceptions.m1830a(ex);
                d.dispose();
                onError(ex);
            }
        }
    }

    @Override // p007io.reactivex.Observer
    public void onNext(Object obj) {
        if (!isDisposed()) {
            try {
                this.onNext.accept(obj);
            } catch (Throwable e) {
                Exceptions.m1830a(e);
                ((Disposable) get()).dispose();
                onError(e);
            }
        }
    }

    @Override // p007io.reactivex.Observer
    public void onError(Throwable t) {
        if (!isDisposed()) {
            lazySet(DisposableHelper.DISPOSED);
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

    @Override // p007io.reactivex.Observer
    public void onComplete() {
        if (!isDisposed()) {
            lazySet(DisposableHelper.DISPOSED);
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
        DisposableHelper.dispose(this);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    public boolean hasCustomOnError() {
        return this.onError != C0010c2.f25d;
    }
}
