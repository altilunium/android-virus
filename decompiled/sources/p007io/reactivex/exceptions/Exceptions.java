package p007io.reactivex.exceptions;

/* renamed from: io.reactivex.exceptions.e */
public final class Exceptions {
    /* renamed from: a */
    public static void m1830a(Throwable t) {
        if (t instanceof VirtualMachineError) {
            throw ((VirtualMachineError) t);
        } else if (t instanceof ThreadDeath) {
            throw ((ThreadDeath) t);
        } else if (t instanceof LinkageError) {
            throw ((LinkageError) t);
        }
    }
}
