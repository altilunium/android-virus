package p007io.reactivex.internal.schedulers;

import java.util.concurrent.Callable;

/* renamed from: io.reactivex.internal.schedulers.ScheduledDirectTask */
public final class ScheduledDirectTask extends AbstractDirectTask implements Callable {
    private static final long serialVersionUID = 1811839108042568751L;

    @Override // p007io.reactivex.internal.schedulers.AbstractDirectTask
    public /* bridge */ /* synthetic */ Runnable getWrappedRunnable() {
        return super.getWrappedRunnable();
    }

    public ScheduledDirectTask(Runnable runnable) {
        super(runnable);
    }

    @Override // java.util.concurrent.Callable
    public Void call() {
        this.runner = Thread.currentThread();
        try {
            this.runnable.run();
            return null;
        } finally {
            lazySet(AbstractDirectTask.FINISHED);
            this.runner = null;
        }
    }
}
