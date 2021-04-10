package p007io.reactivex.internal.schedulers;

import com.own.bless.soy.Disposable;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p007io.reactivex.internal.disposables.DisposableContainer;

/* renamed from: io.reactivex.internal.schedulers.ScheduledRunnable */
public final class ScheduledRunnable extends AtomicReferenceArray implements Runnable, Callable, Disposable {
    static final Object ASYNC_DISPOSED = new Object();
    static final Object DONE = new Object();
    static final int FUTURE_INDEX = 1;
    static final Object PARENT_DISPOSED = new Object();
    static final int PARENT_INDEX = 0;
    static final Object SYNC_DISPOSED = new Object();
    static final int THREAD_INDEX = 2;
    private static final long serialVersionUID = -6120223772001106981L;
    final Runnable actual;

    public ScheduledRunnable(Runnable actual2, DisposableContainer parent) {
        super(3);
        this.actual = actual2;
        lazySet(0, parent);
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        run();
        return null;
    }

    public void run() {
        Object o;
        Object o2;
        lazySet(2, Thread.currentThread());
        try {
            this.actual.run();
        } catch (Throwable th) {
            lazySet(2, null);
            Object o3 = get(0);
            if (!(o3 == PARENT_DISPOSED || !compareAndSet(0, o3, DONE) || o3 == null)) {
                ((DisposableContainer) o3).mo41c(this);
            }
            do {
                o2 = get(1);
                if (o2 == SYNC_DISPOSED || o2 == ASYNC_DISPOSED) {
                    throw th;
                }
            } while (!compareAndSet(1, o2, DONE));
            throw th;
        }
        lazySet(2, null);
        Object o4 = get(0);
        if (!(o4 == PARENT_DISPOSED || !compareAndSet(0, o4, DONE) || o4 == null)) {
            ((DisposableContainer) o4).mo41c(this);
        }
        do {
            o = get(1);
            if (o == SYNC_DISPOSED || o == ASYNC_DISPOSED) {
                return;
            }
        } while (!compareAndSet(1, o, DONE));
    }

    public void setFuture(Future future) {
        Object o;
        do {
            o = get(1);
            if (o != DONE) {
                if (o == SYNC_DISPOSED) {
                    future.cancel(false);
                    return;
                } else if (o == ASYNC_DISPOSED) {
                    future.cancel(true);
                    return;
                }
            } else {
                return;
            }
        } while (!compareAndSet(1, o, future));
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        Object o;
        Object obj;
        Object obj2;
        Object obj3;
        while (true) {
            Object o2 = get(1);
            if (o2 == DONE || o2 == (obj2 = SYNC_DISPOSED) || o2 == (obj3 = ASYNC_DISPOSED)) {
                break;
            }
            boolean async = get(2) != Thread.currentThread();
            if (async) {
                obj2 = obj3;
            }
            if (compareAndSet(1, o2, obj2)) {
                if (o2 != null) {
                    ((Future) o2).cancel(async);
                }
            }
        }
        do {
            o = get(0);
            if (o == DONE || o == (obj = PARENT_DISPOSED) || o == null) {
                return;
            }
        } while (!compareAndSet(0, o, obj));
        ((DisposableContainer) o).mo41c(this);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        Object o = get(0);
        if (o == PARENT_DISPOSED || o == DONE) {
            return true;
        }
        return false;
    }
}
