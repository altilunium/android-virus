package p007io.reactivex.internal.observers;

import com.own.bless.soy.Disposable;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.Observer;
import p007io.reactivex.internal.disposables.DisposableHelper;
import p007io.reactivex.internal.util.NotificationLite;

/* renamed from: io.reactivex.internal.observers.BlockingObserver */
public final class BlockingObserver extends AtomicReference implements Observer, Disposable {
    public static final Object TERMINATED = new Object();
    private static final long serialVersionUID = -4875965440900746268L;
    final Queue queue;

    public BlockingObserver(Queue queue2) {
        this.queue = queue2;
    }

    @Override // p007io.reactivex.Observer
    public void onSubscribe(Disposable d) {
        DisposableHelper.setOnce(this, d);
    }

    @Override // p007io.reactivex.Observer
    public void onNext(Object obj) {
        this.queue.offer(NotificationLite.next(obj));
    }

    @Override // p007io.reactivex.Observer
    public void onError(Throwable t) {
        this.queue.offer(NotificationLite.error(t));
    }

    @Override // p007io.reactivex.Observer
    public void onComplete() {
        this.queue.offer(NotificationLite.complete());
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        if (DisposableHelper.dispose(this)) {
            this.queue.offer(TERMINATED);
        }
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }
}
