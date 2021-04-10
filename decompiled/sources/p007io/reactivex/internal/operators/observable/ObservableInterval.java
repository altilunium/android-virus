package p007io.reactivex.internal.operators.observable;

import com.own.bless.soy.Disposable;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.Observer;
import p007io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.operators.observable.c */
final class ObservableInterval extends AtomicReference implements Disposable, Runnable {

    /* renamed from: a */
    final Observer f1739a;

    /* renamed from: b */
    long f1740b;

    ObservableInterval(Observer fVar) {
        this.f1739a = fVar;
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    public void run() {
        if (get() != DisposableHelper.DISPOSED) {
            Observer fVar = this.f1739a;
            long j = this.f1740b;
            this.f1740b = 1 + j;
            fVar.onNext(Long.valueOf(j));
        }
    }

    /* renamed from: a */
    public void mo1170a(Disposable d) {
        DisposableHelper.setOnce(this, d);
    }
}
