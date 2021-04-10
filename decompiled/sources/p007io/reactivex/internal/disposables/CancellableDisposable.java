package p007io.reactivex.internal.disposables;

import com.own.bless.soy.Cancellable;
import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.exceptions.Exceptions;

/* renamed from: io.reactivex.internal.disposables.CancellableDisposable */
public final class CancellableDisposable extends AtomicReference implements Disposable {
    private static final long serialVersionUID = 5718521705281392066L;

    public CancellableDisposable(Cancellable cancellable) {
        super(cancellable);
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return get() == null;
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        Cancellable c;
        if (get() != null && (c = (Cancellable) getAndSet(null)) != null) {
            try {
                c.cancel();
            } catch (Exception ex) {
                Exceptions.m1830a(ex);
                RxJavaPlugins.m167l(ex);
            }
        }
    }
}
