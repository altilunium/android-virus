package p007io.reactivex;

import com.own.bless.soy.Disposable;

/* renamed from: io.reactivex.c */
public interface MaybeObserver {
    void onComplete();

    void onError(Throwable th);

    void onSubscribe(Disposable e1Var);
}
