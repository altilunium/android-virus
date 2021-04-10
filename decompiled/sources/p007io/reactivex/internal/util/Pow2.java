package p007io.reactivex.internal.util;

/* renamed from: io.reactivex.internal.util.i */
public final class Pow2 {
    /* renamed from: a */
    public static int m1943a(int value) {
        return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
    }
}
