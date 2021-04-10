package p007io.reactivex.internal.observers;

import com.own.bless.soy.RxJavaPlugins;
import p007io.reactivex.Observer;

/* renamed from: io.reactivex.internal.observers.DeferredScalarDisposable */
public class DeferredScalarDisposable extends BasicIntQueueDisposable {
    static final int DISPOSED = 4;
    static final int FUSED_CONSUMED = 32;
    static final int FUSED_EMPTY = 8;
    static final int FUSED_READY = 16;
    static final int TERMINATED = 2;
    private static final long serialVersionUID = -5502432239815349361L;
    protected final Observer downstream;
    protected Object value;

    public DeferredScalarDisposable(Observer fVar) {
        this.downstream = fVar;
    }

    @Override // p007io.reactivex.internal.observers.BasicIntQueueDisposable, com.own.bless.soy.QueueFuseable
    public final int requestFusion(int mode) {
        if ((mode & 2) == 0) {
            return 0;
        }
        lazySet(8);
        return 2;
    }

    public final void complete(Object obj) {
        int state = get();
        if ((state & 54) == 0) {
            io.reactivex.Observer<? super T> a = this.downstream;
            if (state == 8) {
                this.value = obj;
                lazySet(FUSED_READY);
                a.onNext(null);
            } else {
                lazySet(2);
                a.onNext(obj);
            }
            if (get() != 4) {
                a.onComplete();
            }
        }
    }

    public final void error(Throwable t) {
        if ((get() & 54) != 0) {
            RxJavaPlugins.m167l(t);
            return;
        }
        lazySet(2);
        this.downstream.onError(t);
    }

    public final void complete() {
        if ((get() & 54) == 0) {
            lazySet(2);
            this.downstream.onComplete();
        }
    }

    @Override // p007io.reactivex.internal.observers.BasicIntQueueDisposable
    public final Object poll() {
        if (get() != FUSED_READY) {
            return null;
        }
        Object obj = this.value;
        this.value = null;
        lazySet(32);
        return obj;
    }

    @Override // p007io.reactivex.internal.observers.BasicIntQueueDisposable
    public final boolean isEmpty() {
        return get() != FUSED_READY;
    }

    @Override // p007io.reactivex.internal.observers.BasicIntQueueDisposable
    public final void clear() {
        lazySet(32);
        this.value = null;
    }

    @Override // p007io.reactivex.internal.observers.BasicIntQueueDisposable, com.own.bless.soy.Disposable
    public void dispose() {
        set(4);
        this.value = null;
    }

    public final boolean tryDispose() {
        return getAndSet(4) != 4;
    }

    @Override // p007io.reactivex.internal.observers.BasicIntQueueDisposable, com.own.bless.soy.Disposable
    public final boolean isDisposed() {
        return get() == 4;
    }
}
