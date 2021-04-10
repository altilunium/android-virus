package p007io.reactivex;

import com.own.bless.soy.Disposable;

/* renamed from: io.reactivex.k */
public interface SingleObserver {
    void onError(Throwable th);

    void onSubscribe(Disposable e1Var);
}
