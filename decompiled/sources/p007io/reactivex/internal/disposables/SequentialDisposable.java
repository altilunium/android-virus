package p007io.reactivex.internal.disposables;

import com.own.bless.soy.Disposable;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: io.reactivex.internal.disposables.SequentialDisposable */
public final class SequentialDisposable extends AtomicReference implements Disposable {
    private static final long serialVersionUID = -754898800686245608L;

    public SequentialDisposable() {
    }

    public SequentialDisposable(Disposable initial) {
        lazySet(initial);
    }

    public boolean update(Disposable next) {
        return DisposableHelper.set(this, next);
    }

    public boolean replace(Disposable next) {
        return DisposableHelper.replace(this, next);
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
