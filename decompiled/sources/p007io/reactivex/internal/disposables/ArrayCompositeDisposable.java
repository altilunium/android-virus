package p007io.reactivex.internal.disposables;

import com.own.bless.soy.Disposable;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: io.reactivex.internal.disposables.ArrayCompositeDisposable */
public final class ArrayCompositeDisposable extends AtomicReferenceArray implements Disposable {
    private static final long serialVersionUID = 2746389416410565408L;

    public ArrayCompositeDisposable(int capacity) {
        super(capacity);
    }

    public boolean setResource(int index, Disposable resource) {
        Disposable o;
        do {
            o = (Disposable) get(index);
            if (o == DisposableHelper.DISPOSED) {
                resource.dispose();
                return false;
            }
        } while (!compareAndSet(index, o, resource));
        if (o == null) {
            return true;
        }
        o.dispose();
        return true;
    }

    public Disposable replaceResource(int index, Disposable resource) {
        Disposable o;
        do {
            o = (Disposable) get(index);
            if (o == DisposableHelper.DISPOSED) {
                resource.dispose();
                return null;
            }
        } while (!compareAndSet(index, o, resource));
        return o;
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
        Disposable o;
        if (get(0) != DisposableHelper.DISPOSED) {
            int s = length();
            for (int i = 0; i < s; i++) {
                Disposable o2 = (Disposable) get(i);
                DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
                if (!(o2 == disposableHelper || (o = (Disposable) getAndSet(i, disposableHelper)) == disposableHelper || o == null)) {
                    o.dispose();
                }
            }
        }
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return get(0) == DisposableHelper.DISPOSED;
    }
}
