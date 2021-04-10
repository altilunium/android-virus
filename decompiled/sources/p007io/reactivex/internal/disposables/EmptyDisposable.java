package p007io.reactivex.internal.disposables;

import com.own.bless.soy.QueueDisposable;
import p007io.reactivex.CompletableObserver;
import p007io.reactivex.MaybeObserver;
import p007io.reactivex.Observer;
import p007io.reactivex.SingleObserver;

/* renamed from: io.reactivex.internal.disposables.EmptyDisposable */
public enum EmptyDisposable implements QueueDisposable {
    INSTANCE,
    NEVER;

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return this == INSTANCE;
    }

    public static void complete(Observer fVar) {
        fVar.onSubscribe(INSTANCE);
        fVar.onComplete();
    }

    public static void complete(MaybeObserver cVar) {
        cVar.onSubscribe(INSTANCE);
        cVar.onComplete();
    }

    public static void error(Throwable e, Observer fVar) {
        fVar.onSubscribe(INSTANCE);
        fVar.onError(e);
    }

    public static void complete(CompletableObserver observer) {
        observer.onSubscribe(INSTANCE);
        observer.onComplete();
    }

    public static void error(Throwable e, CompletableObserver observer) {
        observer.onSubscribe(INSTANCE);
        observer.onError(e);
    }

    public static void error(Throwable e, SingleObserver kVar) {
        kVar.onSubscribe(INSTANCE);
        kVar.onError(e);
    }

    public static void error(Throwable e, MaybeObserver cVar) {
        cVar.onSubscribe(INSTANCE);
        cVar.onError(e);
    }

    public boolean offer(Object value) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public boolean offer(Object v1, Object v2) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public Object poll() {
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

    public void clear() {
    }

    @Override // com.own.bless.soy.QueueFuseable
    public int requestFusion(int mode) {
        return mode & 2;
    }
}
