package p007io.reactivex.internal.operators.flowable;

import com.own.bless.soy.C0013e2;
import com.own.bless.soy.Function;
import com.own.bless.soy.RxJavaPlugins;
import com.own.bless.soy.Subscriber;
import com.own.bless.soy.Subscription;
import io.reactivex.flowables.GroupedFlowable;
import io.reactivex.internal.operators.flowable.FlowableGroupBy;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import p007io.reactivex.exceptions.Exceptions;
import p007io.reactivex.internal.queue.SpscLinkedArrayQueue;
import p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import p007io.reactivex.internal.subscriptions.SubscriptionHelper;
import p007io.reactivex.internal.util.BackpressureHelper;

/* renamed from: io.reactivex.internal.operators.flowable.FlowableGroupBy$GroupBySubscriber */
public final class FlowableGroupBy$GroupBySubscriber extends BasicIntQueueSubscription implements Subscriber {
    static final Object NULL_KEY = new Object();
    private static final long serialVersionUID = -3688291656102519502L;
    final int bufferSize;
    final AtomicBoolean cancelled = new AtomicBoolean();
    final boolean delayError;
    boolean done;
    final Subscriber downstream;
    Throwable error;
    final Queue evictedGroups;
    volatile boolean finished;
    final AtomicInteger groupCount = new AtomicInteger(1);
    final Map groups;
    final Function keySelector;
    boolean outputFused;
    final SpscLinkedArrayQueue queue;
    final AtomicLong requested = new AtomicLong();
    Subscription upstream;
    final Function valueSelector;

    public FlowableGroupBy$GroupBySubscriber(Subscriber v2Var, Function o1Var, Function o1Var2, int bufferSize2, boolean delayError2, Map map, Queue queue2) {
        this.downstream = v2Var;
        this.keySelector = o1Var;
        this.valueSelector = o1Var2;
        this.bufferSize = bufferSize2;
        this.delayError = delayError2;
        this.groups = map;
        this.evictedGroups = queue2;
        this.queue = new SpscLinkedArrayQueue(bufferSize2);
    }

    @Override // com.own.bless.soy.Subscriber
    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.validate(this.upstream, s)) {
            this.upstream = s;
            this.downstream.onSubscribe(this);
            s.request((long) this.bufferSize);
        }
    }

    @Override // com.own.bless.soy.Subscriber
    public void onNext(Object obj) {
        if (!this.done) {
            io.reactivex.internal.queue.SpscLinkedArrayQueue<GroupedFlowable<K, V>> q = this.queue;
            try {
                Object apply = this.keySelector.apply(obj);
                boolean newGroup = false;
                Object mapKey = apply != null ? apply : NULL_KEY;
                FlowableGroupBy.GroupedUnicast<K, V> group = (FlowableGroupBy) this.groups.get(mapKey);
                if (group == null) {
                    if (!this.cancelled.get()) {
                        group = FlowableGroupBy.m1845a(apply, this.bufferSize, this, this.delayError);
                        this.groups.put(mapKey, group);
                        this.groupCount.getAndIncrement();
                        newGroup = true;
                    } else {
                        return;
                    }
                }
                try {
                    Object apply2 = this.valueSelector.apply(obj);
                    C0013e2.m76d(apply2, "The valueSelector returned null");
                    group.mo1141d(apply2);
                    completeEvictions();
                    if (newGroup) {
                        q.mo1185m(group);
                        drain();
                    }
                } catch (Throwable ex) {
                    Exceptions.m1830a(ex);
                    this.upstream.cancel();
                    onError(ex);
                }
            } catch (Throwable ex2) {
                Exceptions.m1830a(ex2);
                this.upstream.cancel();
                onError(ex2);
            }
        }
    }

    @Override // com.own.bless.soy.Subscriber
    public void onError(Throwable t) {
        if (this.done) {
            RxJavaPlugins.m167l(t);
            return;
        }
        this.done = true;
        for (FlowableGroupBy.GroupedUnicast<K, V> g : this.groups.values()) {
            g.mo1140c(t);
        }
        this.groups.clear();
        Queue queue2 = this.evictedGroups;
        if (queue2 != null) {
            queue2.clear();
        }
        this.error = t;
        this.finished = true;
        drain();
    }

    @Override // com.own.bless.soy.Subscriber
    public void onComplete() {
        if (!this.done) {
            for (FlowableGroupBy.GroupedUnicast<K, V> g : this.groups.values()) {
                g.mo1139b();
            }
            this.groups.clear();
            Queue queue2 = this.evictedGroups;
            if (queue2 != null) {
                queue2.clear();
            }
            this.done = true;
            this.finished = true;
            drain();
        }
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription, com.own.bless.soy.Subscription
    public void request(long n) {
        if (SubscriptionHelper.validate(n)) {
            BackpressureHelper.m1928a(this.requested, n);
            drain();
        }
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription, com.own.bless.soy.Subscription
    public void cancel() {
        if (this.cancelled.compareAndSet(false, true)) {
            completeEvictions();
            if (this.groupCount.decrementAndGet() == 0) {
                this.upstream.cancel();
            }
        }
    }

    private void completeEvictions() {
        if (this.evictedGroups != null) {
            int count = 0;
            while (true) {
                FlowableGroupBy.GroupedUnicast<K, V> evictedGroup = (FlowableGroupBy) this.evictedGroups.poll();
                if (evictedGroup == null) {
                    break;
                }
                evictedGroup.mo1139b();
                count++;
            }
            if (count != 0) {
                this.groupCount.addAndGet(-count);
            }
        }
    }

    public void cancel(Object obj) {
        this.groups.remove(obj != null ? obj : NULL_KEY);
        if (this.groupCount.decrementAndGet() == 0) {
            this.upstream.cancel();
            if (!this.outputFused && getAndIncrement() == 0) {
                this.queue.mo1183d();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void drain() {
        if (getAndIncrement() == 0) {
            if (this.outputFused) {
                drainFused();
            } else {
                drainNormal();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void drainFused() {
        Throwable ex;
        int missed = 1;
        io.reactivex.internal.queue.SpscLinkedArrayQueue<GroupedFlowable<K, V>> q = this.queue;
        org.reactivestreams.Subscriber<? super GroupedFlowable<K, V>> a = this.downstream;
        while (!this.cancelled.get()) {
            boolean d = this.finished;
            if (!d || this.delayError || (ex = this.error) == null) {
                a.onNext(null);
                if (d) {
                    Throwable ex2 = this.error;
                    if (ex2 != null) {
                        a.onError(ex2);
                        return;
                    } else {
                        a.onComplete();
                        return;
                    }
                } else {
                    missed = addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                }
            } else {
                q.mo1183d();
                a.onError(ex);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void drainNormal() {
        int missed = 1;
        io.reactivex.internal.queue.SpscLinkedArrayQueue<GroupedFlowable<K, V>> q = this.queue;
        org.reactivestreams.Subscriber<? super GroupedFlowable<K, V>> a = this.downstream;
        do {
            long r = this.requested.get();
            long e = 0;
            while (e != r) {
                boolean d = this.finished;
                GroupedFlowable<K, V> t = (com.own.bless.soy.GroupedFlowable) q.mo1186n();
                boolean empty = t == null;
                if (!checkTerminated(d, empty, a, q)) {
                    if (empty) {
                        break;
                    }
                    a.onNext(t);
                    e++;
                } else {
                    return;
                }
            }
            if (e != r || !checkTerminated(this.finished, q.mo1184e(), a, q)) {
                if (e != 0) {
                    if (r != Long.MAX_VALUE) {
                        this.requested.addAndGet(-e);
                    }
                    this.upstream.request(e);
                }
                missed = addAndGet(-missed);
            } else {
                return;
            }
        } while (missed != 0);
    }

    /* access modifiers changed from: package-private */
    public boolean checkTerminated(boolean d, boolean empty, Subscriber v2Var, SpscLinkedArrayQueue aVar) {
        if (this.cancelled.get()) {
            aVar.mo1183d();
            return true;
        } else if (this.delayError) {
            if (!d || !empty) {
                return false;
            }
            Throwable ex = this.error;
            if (ex != null) {
                v2Var.onError(ex);
            } else {
                v2Var.onComplete();
            }
            return true;
        } else if (!d) {
            return false;
        } else {
            Throwable ex2 = this.error;
            if (ex2 != null) {
                aVar.mo1183d();
                v2Var.onError(ex2);
                return true;
            } else if (!empty) {
                return false;
            } else {
                v2Var.onComplete();
                return true;
            }
        }
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription, com.own.bless.soy.QueueFuseable
    public int requestFusion(int mode) {
        if ((mode & 2) == 0) {
            return 0;
        }
        this.outputFused = true;
        return 2;
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription
    public com.own.bless.soy.GroupedFlowable poll() {
        return (com.own.bless.soy.GroupedFlowable) this.queue.mo1186n();
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription
    public void clear() {
        this.queue.mo1183d();
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription
    public boolean isEmpty() {
        return this.queue.mo1184e();
    }
}
