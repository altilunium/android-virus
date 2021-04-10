package p007io.reactivex.internal.observers;

import com.own.bless.soy.C0010c2;
import com.own.bless.soy.Consumer;
import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.SingleObserver;
import p007io.reactivex.exceptions.CompositeException;
import p007io.reactivex.exceptions.Exceptions;
import p007io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.observers.ConsumerSingleObserver */
public final class ConsumerSingleObserver extends AtomicReference implements SingleObserver, Disposable {
    private static final long serialVersionUID = -7012088219455310787L;
    final Consumer onError;
    final Consumer onSuccess;

    public ConsumerSingleObserver(Consumer n1Var, Consumer n1Var2) {
        this.onSuccess = n1Var;
        this.onError = n1Var2;
    }

    @Override // p007io.reactivex.SingleObserver
    public void onError(Throwable e) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onError.accept(e);
        } catch (Throwable ex) {
            Exceptions.m1830a(ex);
            RxJavaPlugins.m167l(new CompositeException(e, ex));
        }
    }

    @Override // p007io.reactivex.SingleObserver
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
