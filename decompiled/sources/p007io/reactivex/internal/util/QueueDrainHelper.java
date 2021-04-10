package p007io.reactivex.internal.util;

import com.own.bless.soy.SimpleQueue;
import com.own.bless.soy.Subscription;
import p007io.reactivex.internal.queue.SpscArrayQueue;
import p007io.reactivex.internal.queue.SpscLinkedArrayQueue;

/* renamed from: io.reactivex.internal.util.j */
public final class QueueDrainHelper {
    /* renamed from: a */
    public static SimpleQueue m1944a(int capacityHint) {
        if (capacityHint < 0) {
            return new SpscLinkedArrayQueue(-capacityHint);
        }
        return new SpscArrayQueue(capacityHint);
    }

    /* renamed from: b */
    public static void m1945b(Subscription s, int prefetch) {
        s.request(prefetch < 0 ? Long.MAX_VALUE : (long) prefetch);
    }
}
