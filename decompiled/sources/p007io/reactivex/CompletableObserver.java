package p007io.reactivex;

import com.own.bless.soy.Disposable;

/* renamed from: io.reactivex.a */
public interface CompletableObserver {
    void onComplete();

    void onError(Throwable th);

    void onSubscribe(Disposable e1Var);
}
