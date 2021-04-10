package p007io.reactivex.internal.operators.flowable;

import com.own.bless.soy.Publisher;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import p007io.reactivex.internal.queue.SpscLinkedArrayQueue;
import p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import p007io.reactivex.internal.subscriptions.SubscriptionHelper;
import p007io.reactivex.internal.util.BackpressureHelper;

/* access modifiers changed from: package-private */
/* renamed from: io.reactivex.internal.operators.flowable.b */
/* compiled from: FlowableGroupBy */
public final class C0262b extends BasicIntQueueSubscription implements Publisher {

    /* renamed from: a */
    final Object f1717a;

    /* renamed from: b */
    final SpscLinkedArrayQueue f1718b;

    /* renamed from: c */
    final FlowableGroupBy$GroupBySubscriber f1719c;

    /* renamed from: d */
    final boolean f1720d;

    /* renamed from: e */
    final AtomicLong f1721e = new AtomicLong();

    /* renamed from: f */
    volatile boolean f1722f;

    /* renamed from: g */
    Throwable f1723g;

    /* renamed from: h */
    final AtomicBoolean f1724h = new AtomicBoolean();

    /* renamed from: i */
    final AtomicReference f1725i = new AtomicReference();

    /* renamed from: j */
    boolean f1726j;

    /* renamed from: k */
    int f1727k;

    C0262b(int bufferSize, FlowableGroupBy$GroupBySubscriber flowableGroupBy$GroupBySubscriber, Object obj, boolean delayError) {
        new AtomicBoolean();
        this.f1718b = new SpscLinkedArrayQueue(bufferSize);
        this.f1719c = flowableGroupBy$GroupBySubscriber;
        this.f1717a = obj;
        this.f1720d = delayError;
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription, com.own.bless.soy.Subscription
    public void request(long n) {
        if (SubscriptionHelper.validate(n)) {
            BackpressureHelper.m1928a(this.f1721e, n);
            drain();
        }
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription, com.own.bless.soy.Subscription
    public void cancel() {
        if (this.f1724h.compareAndSet(false, true)) {
            this.f1719c.cancel(this.f1717a);
            drain();
        }
    }

    public void onNext(Object obj) {
        this.f1718b.mo1185m(obj);
        drain();
    }

    public void onError(Throwable e) {
        this.f1723g = e;
        this.f1722f = true;
        drain();
    }

    public void onComplete() {
        this.f1722f = true;
        drain();
    }

    /* access modifiers changed from: package-private */
    public void drain() {
        if (getAndIncrement() == 0) {
            if (this.f1726j) {
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
        io.reactivex.internal.queue.SpscLinkedArrayQueue<T> q = this.f1718b;
        Subscriber<? super T> a = (com.own.bless.soy.Subscriber) this.f1725i.get();
        while (true) {
            if (a != null) {
                if (!this.f1724h.get()) {
                    boolean d = this.f1722f;
                    if (!d || this.f1720d || (ex = this.f1723g) == null) {
                        a.onNext(null);
                        if (d) {
                            Throwable ex2 = this.f1723g;
                            if (ex2 != null) {
                                a.onError(ex2);
                                return;
                            } else {
                                a.onComplete();
                                return;
                            }
                        }
                    } else {
                        q.mo1183d();
                        a.onError(ex);
                        return;
                    }
                } else {
                    return;
                }
            }
            missed = addAndGet(-missed);
            if (missed != 0) {
                if (a == null) {
                    a = (com.own.bless.soy.Subscriber) this.f1725i.get();
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void drainNormal() {
        int missed;
        io.reactivex.internal.queue.SpscLinkedArrayQueue<T> q = this.f1718b;
        boolean delayError = this.f1720d;
        int missed2 = 1;
        Subscriber<? super T> a = (com.own.bless.soy.Subscriber) this.f1725i.get();
        while (true) {
            if (a != null) {
                long r = this.f1721e.get();
                long e = 0;
                while (true) {
                    if (e == r) {
                        missed = missed2;
                        break;
                    }
                    boolean d = this.f1722f;
                    Object n = q.mo1186n();
                    boolean empty = n == null;
                    missed = missed2;
                    if (!mo1142a(d, empty, a, delayError, e)) {
                        if (empty) {
                            break;
                        }
                        a.onNext(n);
                        e++;
                        missed2 = missed;
                    } else {
                        return;
                    }
                }
                if (e == r && mo1142a(this.f1722f, q.mo1184e(), a, delayError, e)) {
                    return;
                }
                if (e != 0) {
                    if (r != Long.MAX_VALUE) {
                        this.f1721e.addAndGet(-e);
                    }
                    this.f1719c.upstream.request(e);
                }
            } else {
                missed = missed2;
            }
            missed2 = addAndGet(-missed);
            if (missed2 != 0) {
                if (a == null) {
                    a = (com.own.bless.soy.Subscriber) this.f1725i.get();
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo1142a(boolean d, boolean empty, com.own.bless.soy.Subscriber v2Var, boolean delayError, long emitted) {
        if (this.f1724h.get()) {
            while (this.f1718b.mo1186n() != null) {
                emitted++;
            }
            if (emitted != 0) {
                this.f1719c.upstream.request(emitted);
            }
            return true;
        } else if (!d) {
            return false;
        } else {
            if (!delayError) {
                Throwable e = this.f1723g;
                if (e != null) {
                    this.f1718b.mo1183d();
                    v2Var.onError(e);
                    return true;
                } else if (!empty) {
                    return false;
                } else {
                    v2Var.onComplete();
                    return true;
                }
            } else if (!empty) {
                return false;
            } else {
                Throwable e2 = this.f1723g;
                if (e2 != null) {
                    v2Var.onError(e2);
                } else {
                    v2Var.onComplete();
                }
                return true;
            }
        }
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription, com.own.bless.soy.QueueFuseable
    public int requestFusion(int mode) {
        if ((mode & 2) == 0) {
            return 0;
        }
        this.f1726j = true;
        return 2;
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription
    public Object poll() {
        Object n = this.f1718b.mo1186n();
        if (n != null) {
            this.f1727k++;
            return n;
        }
        mo1143b();
        return null;
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription
    public boolean isEmpty() {
        if (!this.f1718b.mo1184e()) {
            return false;
        }
        mo1143b();
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo1143b() {
        int p = this.f1727k;
        if (p != 0) {
            this.f1727k = 0;
            this.f1719c.upstream.request((long) p);
        }
    }

    @Override // p007io.reactivex.internal.subscriptions.BasicIntQueueSubscription
    public void clear() {
        io.reactivex.internal.queue.SpscLinkedArrayQueue<T> q = this.f1718b;
        while (q.mo1186n() != null) {
            this.f1727k++;
        }
        mo1143b();
    }
}
