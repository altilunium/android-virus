package p007io.reactivex.internal.observers;

import com.own.bless.soy.Disposable;
import p007io.reactivex.Observer;
import p007io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.observers.DeferredScalarObserver */
public abstract class DeferredScalarObserver extends DeferredScalarDisposable implements Observer {
    private static final long serialVersionUID = -266195175408988651L;
    protected Disposable upstream;

    @Override // p007io.reactivex.Observer
    public abstract /* synthetic */ void onNext(Object obj);

    public DeferredScalarObserver(Observer fVar) {
        super(fVar);
    }

    @Override // p007io.reactivex.Observer
    public void onSubscribe(Disposable d) {
        if (DisposableHelper.validate(this.upstream, d)) {
            this.upstream = d;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // p007io.reactivex.Observer
    public void onError(Throwable t) {
        this.value = null;
        error(t);
    }

    @Override // p007io.reactivex.Observer
    public void onComplete() {
        Object obj = this.value;
        if (obj != null) {
            this.value = null;
            complete(obj);
            return;
        }
        complete();
    }

    @Override // p007io.reactivex.internal.observers.DeferredScalarDisposable, p007io.reactivex.internal.observers.BasicIntQueueDisposable, com.own.bless.soy.Disposable
    public void dispose() {
        super.dispose();
        this.upstream.dispose();
    }
}
