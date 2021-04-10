package p007io.reactivex;

import com.own.bless.soy.Disposable;

/* renamed from: io.reactivex.f */
public interface Observer {
    void onComplete();

    void onError(Throwable th);

    void onNext(Object obj);

    void onSubscribe(Disposable e1Var);
}
