package p007io.reactivex.internal.schedulers;

import com.own.bless.soy.C0010c2;
import com.own.bless.soy.Disposable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* access modifiers changed from: package-private */
/* renamed from: io.reactivex.internal.schedulers.a */
public abstract class AbstractDirectTask extends AtomicReference implements Disposable {
    protected static final FutureTask DISPOSED;
    protected static final FutureTask FINISHED;
    private static final long serialVersionUID = 1811839108042568751L;
    protected final Runnable runnable;
    protected Thread runner;

    static {
        Runnable runnable2 = C0010c2.f22a;
        FINISHED = new FutureTask(runnable2, null);
        DISPOSED = new FutureTask(runnable2, null);
    }

    AbstractDirectTask(Runnable runnable2) {
        this.runnable = runnable2;
    }

    @Override // com.own.bless.soy.Disposable
    public final void dispose() {
        FutureTask futureTask;
        Future<?> f = (Future) get();
        if (f != FINISHED && f != (futureTask = DISPOSED) && compareAndSet(f, futureTask) && f != null) {
            f.cancel(this.runner != Thread.currentThread());
        }
    }

    @Override // com.own.bless.soy.Disposable
    public final boolean isDisposed() {
        Future<?> f = (Future) get();
        return f == FINISHED || f == DISPOSED;
    }

    public final void setFuture(Future future) {
        Future<?> f;
        do {
            f = (Future) get();
            if (f != FINISHED) {
                if (f == DISPOSED) {
                    future.cancel(this.runner != Thread.currentThread());
                    return;
                }
            } else {
                return;
            }
        } while (!compareAndSet(f, future));
    }

    public Runnable getWrappedRunnable() {
        return this.runnable;
    }
}
