package p007io.reactivex.internal.operators.observable;

import com.own.bless.soy.QueueDisposable;
import java.util.concurrent.atomic.AtomicInteger;
import p007io.reactivex.Observer;

/* renamed from: io.reactivex.internal.operators.observable.ObservableScalarXMap$ScalarDisposable */
public final class ObservableScalarXMap$ScalarDisposable extends AtomicInteger implements QueueDisposable, Runnable {
    static final int FUSED = 1;
    static final int ON_COMPLETE = 3;
    static final int ON_NEXT = 2;
    static final int START = 0;
    private static final long serialVersionUID = 3880992722410194083L;
    final Observer observer;
    final Object value;

    public ObservableScalarXMap$ScalarDisposable(Observer fVar, Object obj) {
        this.observer = fVar;
        this.value = obj;
    }

    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public Object poll() {
        if (get() != 1) {
            return null;
        }
        lazySet(3);
        return this.value;
    }

    public boolean isEmpty() {
        return get() != 1;
    }

    public void clear() {
        lazySet(3);
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        set(3);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return get() == 3;
    }

    @Override // com.own.bless.soy.QueueFuseable
    public int requestFusion(int mode) {
        if ((mode & 1) == 0) {
            return 0;
        }
        lazySet(1);
        return 1;
    }

    public void run() {
        if (get() == 0 && compareAndSet(0, 2)) {
            this.observer.onNext(this.value);
            if (get() == 2) {
                lazySet(3);
                this.observer.onComplete();
            }
        }
    }
}
