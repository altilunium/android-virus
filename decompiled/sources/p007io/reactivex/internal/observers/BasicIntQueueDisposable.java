package p007io.reactivex.internal.observers;

import com.own.bless.soy.QueueDisposable;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: io.reactivex.internal.observers.BasicIntQueueDisposable */
public abstract class BasicIntQueueDisposable extends AtomicInteger implements QueueDisposable {
    private static final long serialVersionUID = -1001730202384742097L;

    public abstract /* synthetic */ void clear();

    @Override // com.own.bless.soy.Disposable
    public abstract /* synthetic */ void dispose();

    @Override // com.own.bless.soy.Disposable
    public abstract /* synthetic */ boolean isDisposed();

    public abstract /* synthetic */ boolean isEmpty();

    public abstract /* synthetic */ Object poll();

    @Override // com.own.bless.soy.QueueFuseable
    public abstract /* synthetic */ int requestFusion(int i);

    public final boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called");
    }

    public final boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
