package p007io.reactivex.internal.subscribers;

import com.own.bless.soy.QueueSubscription;
import com.own.bless.soy.SimpleQueue;
import com.own.bless.soy.Subscriber;
import com.own.bless.soy.Subscription;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.internal.subscriptions.SubscriptionHelper;
import p007io.reactivex.internal.util.QueueDrainHelper;

/* renamed from: io.reactivex.internal.subscribers.InnerQueuedSubscriber */
public final class InnerQueuedSubscriber extends AtomicReference implements Subscriber, Subscription {
    private static final long serialVersionUID = 22876611072430776L;
    volatile boolean done;
    int fusionMode;
    final int limit;
    final InnerQueuedSubscriberSupport parent;
    final int prefetch;
    long produced;
    volatile SimpleQueue queue;

    public InnerQueuedSubscriber(InnerQueuedSubscriberSupport aVar, int prefetch2) {
        this.parent = aVar;
        this.prefetch = prefetch2;
        this.limit = prefetch2 - (prefetch2 >> 2);
    }

    @Override // com.own.bless.soy.Subscriber
    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.setOnce(this, s)) {
            if (s instanceof QueueSubscription) {
                io.reactivex.internal.fuseable.QueueSubscription<T> qs = (QueueSubscription) s;
                int m = qs.requestFusion(3);
                if (m == 1) {
                    this.fusionMode = m;
                    this.queue = qs;
                    this.done = true;
                    this.parent.mo1235b(this);
                    return;
                } else if (m == 2) {
                    this.fusionMode = m;
                    this.queue = qs;
                    QueueDrainHelper.m1945b(s, this.prefetch);
                    return;
                }
            }
            this.queue = QueueDrainHelper.m1944a(this.prefetch);
            QueueDrainHelper.m1945b(s, this.prefetch);
        }
    }

    @Override // com.own.bless.soy.Subscriber
    public void onNext(Object obj) {
        if (this.fusionMode == 0) {
            this.parent.mo1236c(this, obj);
        } else {
            this.parent.mo1234a();
        }
    }

    @Override // com.own.bless.soy.Subscriber
    public void onError(Throwable t) {
        this.parent.mo1237d(this, t);
    }

    @Override // com.own.bless.soy.Subscriber
    public void onComplete() {
        this.parent.mo1235b(this);
    }

    @Override // com.own.bless.soy.Subscription
    public void request(long n) {
        if (this.fusionMode != 1) {
            long p = this.produced + n;
            if (p >= ((long) this.limit)) {
                this.produced = 0;
                ((Subscription) get()).request(p);
                return;
            }
            this.produced = p;
        }
    }

    public void requestOne() {
        if (this.fusionMode != 1) {
            long p = this.produced + 1;
            if (p == ((long) this.limit)) {
                this.produced = 0;
                ((Subscription) get()).request(p);
                return;
            }
            this.produced = p;
        }
    }

    @Override // com.own.bless.soy.Subscription
    public void cancel() {
        SubscriptionHelper.cancel(this);
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
}
