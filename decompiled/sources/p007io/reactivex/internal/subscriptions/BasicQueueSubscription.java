package p007io.reactivex.internal.subscriptions;

import com.own.bless.soy.QueueSubscription;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: io.reactivex.internal.subscriptions.BasicQueueSubscription */
public abstract class BasicQueueSubscription extends AtomicLong implements QueueSubscription {
    private static final long serialVersionUID = -6671519529404341862L;

    @Override // com.own.bless.soy.Subscription
    public abstract /* synthetic */ void cancel();

    public abstract /* synthetic */ void clear();

    public abstract /* synthetic */ boolean isEmpty();

    public abstract /* synthetic */ Object poll();

    @Override // com.own.bless.soy.Subscription
    public abstract /* synthetic */ void request(long j);

    @Override // com.own.bless.soy.QueueFuseable
    public abstract /* synthetic */ int requestFusion(int i);

    public final boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public final boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
