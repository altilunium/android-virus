package p007io.reactivex.internal.operators.observable;

import com.own.bless.soy.C0013e2;
import com.own.bless.soy.Disposable;
import com.own.bless.soy.Function;
import io.reactivex.internal.operators.observable.ObservableGroupBy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import p007io.reactivex.Observer;
import p007io.reactivex.exceptions.Exceptions;
import p007io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.operators.observable.ObservableGroupBy$GroupByObserver */
public final class ObservableGroupBy$GroupByObserver extends AtomicInteger implements Observer, Disposable {
    static final Object NULL_KEY = new Object();
    private static final long serialVersionUID = -3688291656102519502L;
    final int bufferSize;
    final AtomicBoolean cancelled = new AtomicBoolean();
    final boolean delayError;
    final Observer downstream;
    final Map groups;
    final Function keySelector;
    Disposable upstream;
    final Function valueSelector;

    public ObservableGroupBy$GroupByObserver(Observer fVar, Function o1Var, Function o1Var2, int bufferSize2, boolean delayError2) {
        this.downstream = fVar;
        this.keySelector = o1Var;
        this.valueSelector = o1Var2;
        this.bufferSize = bufferSize2;
        this.delayError = delayError2;
        this.groups = new ConcurrentHashMap();
        lazySet(1);
    }

    @Override // p007io.reactivex.Observer
    public void onSubscribe(Disposable d) {
        if (DisposableHelper.validate(this.upstream, d)) {
            this.upstream = d;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // p007io.reactivex.Observer
    public void onNext(Object obj) {
        try {
            Object apply = this.keySelector.apply(obj);
            Object mapKey = apply != null ? apply : NULL_KEY;
            ObservableGroupBy.GroupedUnicast<K, V> group = (ObservableGroupBy) this.groups.get(mapKey);
            if (group == null) {
                if (!this.cancelled.get()) {
                    group = ObservableGroupBy.m1851g(apply, this.bufferSize, this, this.delayError);
                    this.groups.put(mapKey, group);
                    getAndIncrement();
                    this.downstream.onNext(group);
                } else {
                    return;
                }
            }
            try {
                Object apply2 = this.valueSelector.apply(obj);
                C0013e2.m76d(apply2, "The value supplied is null");
                group.mo1163j(apply2);
            } catch (Throwable e) {
                Exceptions.m1830a(e);
                this.upstream.dispose();
                onError(e);
            }
        } catch (Throwable e2) {
            Exceptions.m1830a(e2);
            this.upstream.dispose();
            onError(e2);
        }
    }

    @Override // p007io.reactivex.Observer
    public void onError(Throwable t) {
        List<ObservableGroupBy.GroupedUnicast<K, V>> list = new ArrayList<>(this.groups.values());
        this.groups.clear();
        for (ObservableGroupBy.GroupedUnicast<K, V> e : list) {
            e.mo1162i(t);
        }
        this.downstream.onError(t);
    }

    @Override // p007io.reactivex.Observer
    public void onComplete() {
        List<ObservableGroupBy.GroupedUnicast<K, V>> list = new ArrayList<>(this.groups.values());
        this.groups.clear();
        for (ObservableGroupBy.GroupedUnicast<K, V> e : list) {
            e.mo1161h();
        }
        this.downstream.onComplete();
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        if (this.cancelled.compareAndSet(false, true) && decrementAndGet() == 0) {
            this.upstream.dispose();
        }
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this.cancelled.get();
    }

    public void cancel(Object obj) {
        this.groups.remove(obj != null ? obj : NULL_KEY);
        if (decrementAndGet() == 0) {
            this.upstream.dispose();
        }
    }
}
