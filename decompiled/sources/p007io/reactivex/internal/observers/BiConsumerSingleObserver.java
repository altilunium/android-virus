package p007io.reactivex.internal.observers;

import com.own.bless.soy.BiConsumer;
import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.SingleObserver;
import p007io.reactivex.exceptions.CompositeException;
import p007io.reactivex.exceptions.Exceptions;
import p007io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.observers.BiConsumerSingleObserver */
public final class BiConsumerSingleObserver extends AtomicReference implements SingleObserver, Disposable {
    private static final long serialVersionUID = 4943102778943297569L;
    final BiConsumer onCallback;

    public BiConsumerSingleObserver(BiConsumer k1Var) {
        this.onCallback = k1Var;
    }

    @Override // p007io.reactivex.SingleObserver
    public void onError(Throwable e) {
        try {
            lazySet(DisposableHelper.DISPOSED);
            this.onCallback.mo70a(null, e);
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
        try {
            lazySet(DisposableHelper.DISPOSED);
            this.onCallback.mo70a(obj, null);
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
}
