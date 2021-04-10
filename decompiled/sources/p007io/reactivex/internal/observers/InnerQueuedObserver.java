package p007io.reactivex.internal.observers;

import com.own.bless.soy.Disposable;
import com.own.bless.soy.QueueDisposable;
import com.own.bless.soy.SimpleQueue;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.Observer;
import p007io.reactivex.internal.disposables.DisposableHelper;
import p007io.reactivex.internal.util.QueueDrainHelper;

/* renamed from: io.reactivex.internal.observers.InnerQueuedObserver */
public final class InnerQueuedObserver extends AtomicReference implements Observer, Disposable {
    private static final long serialVersionUID = -5417183359794346637L;
    volatile boolean done;
    int fusionMode;
    final InnerQueuedObserverSupport parent;
    final int prefetch;
    SimpleQueue queue;

    public InnerQueuedObserver(InnerQueuedObserverSupport aVar, int prefetch2) {
        this.parent = aVar;
        this.prefetch = prefetch2;
    }

    @Override // p007io.reactivex.Observer
    public void onSubscribe(Disposable d) {
        if (DisposableHelper.setOnce(this, d)) {
            if (d instanceof QueueDisposable) {
                io.reactivex.internal.fuseable.QueueDisposable<T> qd = (QueueDisposable) d;
                int m = qd.requestFusion(3);
                if (m == 1) {
                    this.fusionMode = m;
                    this.queue = qd;
                    this.done = true;
                    this.parent.mo1127b(this);
                    return;
                } else if (m == 2) {
                    this.fusionMode = m;
                    this.queue = qd;
                    return;
                }
            }
            this.queue = QueueDrainHelper.m1944a(-this.prefetch);
        }
    }

    @Override // p007io.reactivex.Observer
    public void onNext(Object obj) {
        if (this.fusionMode == 0) {
            this.parent.mo1129d(this, obj);
        } else {
            this.parent.mo1126a();
        }
    }

    @Override // p007io.reactivex.Observer
    public void onError(Throwable t) {
        this.parent.mo1128c(this, t);
    }

    @Override // p007io.reactivex.Observer
    public void onComplete() {
        this.parent.mo1127b(this);
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed((Disposable) get());
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public SimpleQueue queue() {
        return this.queue;
    }

    public int fusionMode() {
        return this.fusionMode;
    }
}
