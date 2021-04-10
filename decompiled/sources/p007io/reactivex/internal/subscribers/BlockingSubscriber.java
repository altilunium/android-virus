package p007io.reactivex.internal.subscribers;

import com.own.bless.soy.Subscriber;
import com.own.bless.soy.Subscription;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.internal.subscriptions.SubscriptionHelper;
import p007io.reactivex.internal.util.NotificationLite;

/* renamed from: io.reactivex.internal.subscribers.BlockingSubscriber */
public final class BlockingSubscriber extends AtomicReference implements Subscriber, Subscription {
    public static final Object TERMINATED = new Object();
    private static final long serialVersionUID = -4875965440900746268L;
    final Queue queue;

    public BlockingSubscriber(Queue queue2) {
        this.queue = queue2;
    }

    @Override // com.own.bless.soy.Subscriber
    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.setOnce(this, s)) {
            this.queue.offer(NotificationLite.subscription(this));
        }
    }

    @Override // com.own.bless.soy.Subscriber
    public void onNext(Object obj) {
        this.queue.offer(NotificationLite.next(obj));
    }

    @Override // com.own.bless.soy.Subscriber
    public void onError(Throwable t) {
        this.queue.offer(NotificationLite.error(t));
    }

    @Override // com.own.bless.soy.Subscriber
    public void onComplete() {
        this.queue.offer(NotificationLite.complete());
    }

    @Override // com.own.bless.soy.Subscription
    public void request(long n) {
        ((Subscription) get()).request(n);
    }

    @Override // com.own.bless.soy.Subscription
    public void cancel() {
        if (SubscriptionHelper.cancel(this)) {
            this.queue.offer(TERMINATED);
        }
    }

    public boolean isCancelled() {
        return get() == SubscriptionHelper.CANCELLED;
    }
}
