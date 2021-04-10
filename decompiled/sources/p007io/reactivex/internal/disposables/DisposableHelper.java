package p007io.reactivex.internal.disposables;

import com.own.bless.soy.C0013e2;
import com.own.bless.soy.Disposable;
import com.own.bless.soy.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.exceptions.ProtocolViolationException;

/* renamed from: io.reactivex.internal.disposables.DisposableHelper */
public enum DisposableHelper implements Disposable {
    DISPOSED;

    public static boolean isDisposed(Disposable d) {
        return d == DISPOSED;
    }

    public static boolean set(AtomicReference atomicReference, Disposable d) {
        Disposable current;
        do {
            current = (Disposable) atomicReference.get();
            if (current == DISPOSED) {
                if (d == null) {
                    return false;
                }
                d.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(current, d));
        if (current == null) {
            return true;
        }
        current.dispose();
        return true;
    }

    public static boolean setOnce(AtomicReference atomicReference, Disposable d) {
        C0013e2.m76d(d, "d is null");
        if (atomicReference.compareAndSet(null, d)) {
            return true;
        }
        d.dispose();
        if (atomicReference.get() == DISPOSED) {
            return false;
        }
        reportDisposableSet();
        return false;
    }

    public static boolean replace(AtomicReference atomicReference, Disposable d) {
        Disposable current;
        do {
            current = (Disposable) atomicReference.get();
            if (current == DISPOSED) {
                if (d == null) {
                    return false;
                }
                d.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(current, d));
        return true;
    }

    public static boolean dispose(AtomicReference atomicReference) {
        Disposable current;
        Disposable current2 = (Disposable) atomicReference.get();
        DisposableHelper disposableHelper = DISPOSED;
        if (current2 == disposableHelper || (current = (Disposable) atomicReference.getAndSet(disposableHelper)) == disposableHelper) {
            return false;
        }
        if (current == null) {
            return true;
        }
        current.dispose();
        return true;
    }

    public static boolean validate(Disposable current, Disposable next) {
        if (next == null) {
            RxJavaPlugins.m167l(new NullPointerException("next is null"));
            return false;
        } else if (current == null) {
            return true;
        } else {
            next.dispose();
            reportDisposableSet();
            return false;
        }
    }

    public static void reportDisposableSet() {
        RxJavaPlugins.m167l(new ProtocolViolationException("Disposable already set!"));
    }

    public static boolean trySet(AtomicReference atomicReference, Disposable d) {
        if (atomicReference.compareAndSet(null, d)) {
            return true;
        }
        if (atomicReference.get() != DISPOSED) {
            return false;
        }
        d.dispose();
        return false;
    }

    @Override // com.own.bless.soy.Disposable
    public void dispose() {
    }

    @Override // com.own.bless.soy.Disposable
    public boolean isDisposed() {
        return true;
    }
}
