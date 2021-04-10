package p007io.reactivex;

import com.own.bless.soy.Action;
import com.own.bless.soy.C0010c2;
import com.own.bless.soy.C0013e2;
import com.own.bless.soy.C0032t2;
import com.own.bless.soy.Consumer;
import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import p007io.reactivex.exceptions.Exceptions;
import p007io.reactivex.internal.observers.LambdaObserver;
import p007io.reactivex.internal.operators.observable.C0264d;

/* renamed from: io.reactivex.e */
public abstract class Observable {
    /* access modifiers changed from: protected */
    /* renamed from: f */
    public abstract void mo1074f(Observer fVar);

    /* renamed from: a */
    public static Observable m1821a(long initialDelay, long period, TimeUnit unit, AbstractC0283j scheduler) {
        C0013e2.m76d(unit, "unit is null");
        C0013e2.m76d(scheduler, "scheduler is null");
        return RxJavaPlugins.m165j(new C0264d(Math.max(0L, initialDelay), Math.max(0L, period), unit, scheduler));
    }

    /* renamed from: b */
    public static Observable m1822b(long period, TimeUnit unit) {
        return m1821a(period, period, unit, C0032t2.m223a());
    }

    /* renamed from: c */
    public final Disposable mo1071c(Consumer n1Var, Consumer n1Var2) {
        return mo1072d(n1Var, n1Var2, C0010c2.f23b, C0010c2.m56a());
    }

    /* renamed from: d */
    public final Disposable mo1072d(Consumer n1Var, Consumer n1Var2, Action onComplete, Consumer n1Var3) {
        C0013e2.m76d(n1Var, "onNext is null");
        C0013e2.m76d(n1Var2, "onError is null");
        C0013e2.m76d(onComplete, "onComplete is null");
        C0013e2.m76d(n1Var3, "onSubscribe is null");
        LambdaObserver<T> ls = new LambdaObserver(n1Var, n1Var2, onComplete, n1Var3);
        mo1073e(ls);
        return ls;
    }

    /* renamed from: e */
    public final void mo1073e(Observer fVar) {
        C0013e2.m76d(fVar, "observer is null");
        try {
            Observer<? super T> observer = RxJavaPlugins.m169n(this, fVar);
            C0013e2.m76d(observer, "The RxJavaPlugins.onSubscribe hook returned a null Observer. Please change the handler provided to RxJavaPlugins.setOnObservableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            mo1074f(observer);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable e2) {
            Exceptions.m1830a(e2);
            RxJavaPlugins.m167l(e2);
            NullPointerException npe = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            npe.initCause(e2);
            throw npe;
        }
    }
}
