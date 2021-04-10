package p007io.reactivex.internal.operators.observable;

import com.own.bless.soy.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.Observer;
import p007io.reactivex.internal.disposables.EmptyDisposable;
import p007io.reactivex.internal.queue.SpscLinkedArrayQueue;

/* access modifiers changed from: package-private */
/* renamed from: io.reactivex.internal.operators.observable.b */
/* compiled from: ObservableGroupBy */
public final class C0263b extends AtomicInteger implements Disposable {

    /* renamed from: a */
    final Object f1730a;

    /* renamed from: b */
    final SpscLinkedArrayQueue f1731b;

    /* renamed from: c */
    final ObservableGroupBy$GroupByObserver f1732c;

    /* renamed from: d */
    final boolean f1733d;

    /* renamed from: e */
    volatile boolean f1734e;

    /* renamed from: f */
    Throwable f1735f;

    /* renamed from: g */
    final AtomicBoolean f1736g = new AtomicBoolean();

    /* renamed from: h */
    final AtomicBoolean f1737h = new AtomicBoolean();

    /* renamed from: i */
    final AtomicReference f1738i = new AtomicReference();

    C0263b(int bufferSize, ObservableGroupBy$GroupByObserver observableGroupBy$GroupByObserver, Object obj, boolean delayError) {
        this.f1731b = new SpscLinkedArrayQueue(bufferSize);
        this.f1732c = observableGroupBy$GroupByObserver;
        this.f1730a = obj;
        this.f1733d = delayError;
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        if (this.f1736g.compareAndSet(false, true) && getAndIncrement() == 0) {
            this.f1738i.lazySet(null);
            this.f1732c.cancel(this.f1730a);
        }
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this.f1736g.get();
    }

    /* renamed from: f */
    public void mo1169f(Observer fVar) {
        if (this.f1737h.compareAndSet(false, true)) {
            fVar.onSubscribe(this);
            this.f1738i.lazySet(fVar);
            if (this.f1736g.get()) {
                this.f1738i.lazySet(null);
            } else {
                mo1165b();
            }
        } else {
            EmptyDisposable.error(new IllegalStateException("Only one Observer allowed!"), fVar);
        }
    }

    /* renamed from: e */
    public void mo1168e(Object obj) {
        this.f1731b.mo1185m(obj);
        mo1165b();
    }

    /* renamed from: d */
    public void mo1167d(Throwable e) {
        this.f1735f = e;
        this.f1734e = true;
        mo1165b();
    }

    /* renamed from: c */
    public void mo1166c() {
        this.f1734e = true;
        mo1165b();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo1165b() {
        if (getAndIncrement() == 0) {
            int missed = 1;
            io.reactivex.internal.queue.SpscLinkedArrayQueue<T> q = this.f1731b;
            boolean delayError = this.f1733d;
            io.reactivex.Observer<? super T> a = (Observer) this.f1738i.get();
            while (true) {
                if (a != null) {
                    while (true) {
                        boolean d = this.f1734e;
                        Object n = q.mo1186n();
                        boolean empty = n == null;
                        if (!mo1164a(d, empty, a, delayError)) {
                            if (empty) {
                                break;
                            }
                            a.onNext(n);
                        } else {
                            return;
                        }
                    }
                }
                missed = addAndGet(-missed);
                if (missed != 0) {
                    if (a == null) {
                        a = (Observer) this.f1738i.get();
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo1164a(boolean d, boolean empty, Observer fVar, boolean delayError) {
        if (this.f1736g.get()) {
            this.f1731b.mo1183d();
            this.f1732c.cancel(this.f1730a);
            this.f1738i.lazySet(null);
            return true;
        } else if (!d) {
            return false;
        } else {
            if (!delayError) {
                Throwable e = this.f1735f;
                if (e != null) {
                    this.f1731b.mo1183d();
                    this.f1738i.lazySet(null);
                    fVar.onError(e);
                    return true;
                } else if (!empty) {
                    return false;
                } else {
                    this.f1738i.lazySet(null);
                    fVar.onComplete();
                    return true;
                }
            } else if (!empty) {
                return false;
            } else {
                Throwable e2 = this.f1735f;
                this.f1738i.lazySet(null);
                if (e2 != null) {
                    fVar.onError(e2);
                } else {
                    fVar.onComplete();
                }
                return true;
            }
        }
    }
}
