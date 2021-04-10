package p007io.reactivex.internal.operators.observable;

import com.own.bless.soy.Disposable;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.Observer;
import p007io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.operators.observable.ObserverResourceWrapper */
public final class ObserverResourceWrapper extends AtomicReference implements Observer, Disposable {
    private static final long serialVersionUID = -8612022020200669122L;
    final Observer downstream;
    final AtomicReference upstream = new AtomicReference();

    public ObserverResourceWrapper(Observer fVar) {
        this.downstream = fVar;
    }

    @Override // p007io.reactivex.Observer
    public void onSubscribe(Disposable d) {
        if (DisposableHelper.setOnce(this.upstream, d)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // p007io.reactivex.Observer
    public void onNext(Object obj) {
        this.downstream.onNext(obj);
    }

    @Override // p007io.reactivex.Observer
    public void onError(Throwable t) {
        dispose();
        this.downstream.onError(t);
    }

    @Override // p007io.reactivex.Observer
    public void onComplete() {
        dispose();
        this.downstream.onComplete();
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        DisposableHelper.dispose(this.upstream);
        DisposableHelper.dispose(this);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this.upstream.get() == DisposableHelper.DISPOSED;
    }

    public void setResource(Disposable resource) {
        DisposableHelper.set(this, resource);
    }
}
