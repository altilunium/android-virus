package p007io.reactivex.internal.observers;

import com.own.bless.soy.Action;
import com.own.bless.soy.Consumer;
import com.own.bless.soy.Disposable;
import com.own.bless.soy.Predicate;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.Observer;
import p007io.reactivex.exceptions.CompositeException;
import p007io.reactivex.exceptions.Exceptions;
import p007io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.observers.ForEachWhileObserver */
public final class ForEachWhileObserver extends AtomicReference implements Observer, Disposable {
    private static final long serialVersionUID = -4403180040475402120L;
    boolean done;
    final Action onComplete;
    final Consumer onError;
    final Predicate onNext;

    public ForEachWhileObserver(Predicate p1Var, Consumer n1Var, Action onComplete2) {
        this.onNext = p1Var;
        this.onError = n1Var;
        this.onComplete = onComplete2;
    }

    @Override // p007io.reactivex.Observer
    public void onSubscribe(Disposable d) {
        DisposableHelper.setOnce(this, d);
    }

    @Override // p007io.reactivex.Observer
    public void onNext(Object obj) {
        if (!this.done) {
            try {
                if (!this.onNext.mo34a(obj)) {
                    dispose();
                    onComplete();
                }
            } catch (Throwable ex) {
                Exceptions.m1830a(ex);
                dispose();
                onError(ex);
            }
        }
    }

    @Override // p007io.reactivex.Observer
    public void onError(Throwable t) {
        if (this.done) {
            RxJavaPlugins.m167l(t);
            return;
        }
        this.done = true;
        try {
            this.onError.accept(t);
        } catch (Throwable ex) {
            Exceptions.m1830a(ex);
            RxJavaPlugins.m167l(new CompositeException(t, ex));
        }
    }

    @Override // p007io.reactivex.Observer
    public void onComplete() {
        if (!this.done) {
            this.done = true;
            try {
                this.onComplete.run();
            } catch (Throwable ex) {
                Exceptions.m1830a(ex);
                RxJavaPlugins.m167l(ex);
            }
        }
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed((Disposable) get());
    }
}
