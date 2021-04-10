package p007io.reactivex.internal.util;

/* renamed from: io.reactivex.internal.util.b */
final class ExceptionHelper extends Throwable {
    ExceptionHelper() {
        super("No further exceptions");
    }

    public Throwable fillInStackTrace() {
        return this;
    }
}
