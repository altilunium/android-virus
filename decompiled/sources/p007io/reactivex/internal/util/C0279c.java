package p007io.reactivex.internal.util;

import java.util.concurrent.atomic.AtomicReference;
import p007io.reactivex.exceptions.CompositeException;

/* renamed from: io.reactivex.internal.util.c */
/* compiled from: ExceptionHelper */
public final class C0279c {

    /* renamed from: a */
    public static final Throwable f1823a = new ExceptionHelper();

    /* renamed from: c */
    public static RuntimeException m1933c(Throwable error) {
        if (error instanceof Error) {
            throw ((Error) error);
        } else if (error instanceof RuntimeException) {
            return (RuntimeException) error;
        } else {
            return new RuntimeException(error);
        }
    }

    /* renamed from: a */
    public static boolean m1931a(AtomicReference atomicReference, Throwable exception) {
        Throwable current;
        Throwable update;
        do {
            current = (Throwable) atomicReference.get();
            if (current == f1823a) {
                return false;
            }
            if (current == null) {
                update = exception;
            } else {
                update = new CompositeException(current, exception);
            }
        } while (!atomicReference.compareAndSet(current, update));
        return true;
    }

    /* renamed from: b */
    public static Throwable m1932b(AtomicReference atomicReference) {
        Throwable current = (Throwable) atomicReference.get();
        Throwable th = f1823a;
        if (current != th) {
            return (Throwable) atomicReference.getAndSet(th);
        }
        return current;
    }
}
