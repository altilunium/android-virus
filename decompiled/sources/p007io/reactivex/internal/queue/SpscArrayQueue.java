package p007io.reactivex.internal.queue;

import com.own.bless.soy.SimpleQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p007io.reactivex.internal.util.Pow2;

/* renamed from: io.reactivex.internal.queue.SpscArrayQueue */
public final class SpscArrayQueue extends AtomicReferenceArray implements SimpleQueue {
    private static final Integer MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    private static final long serialVersionUID = -1296597691183856449L;
    final AtomicLong consumerIndex = new AtomicLong();
    final int lookAheadStep;
    final int mask = (length() - 1);
    final AtomicLong producerIndex = new AtomicLong();
    long producerLookAhead;

    public SpscArrayQueue(int capacity) {
        super(Pow2.m1943a(capacity));
        this.lookAheadStep = Math.min(capacity / 4, MAX_LOOK_AHEAD_STEP.intValue());
    }

    public boolean offer(Object obj) {
        if (obj != null) {
            int mask2 = this.mask;
            long index = this.producerIndex.get();
            int offset = calcElementOffset(index, mask2);
            if (index >= this.producerLookAhead) {
                int step = this.lookAheadStep;
                if (lvElement(calcElementOffset(((long) step) + index, mask2)) == null) {
                    this.producerLookAhead = ((long) step) + index;
                } else if (lvElement(offset) != null) {
                    return false;
                }
            }
            soElement(offset, obj);
            soProducerIndex(1 + index);
            return true;
        }
        throw new NullPointerException("Null is not a valid element");
    }

    public boolean offer(Object obj, Object obj2) {
        return offer(obj) && offer(obj2);
    }

    public Object poll() {
        long index = this.consumerIndex.get();
        int offset = calcElementOffset(index);
        Object lvElement = lvElement(offset);
        if (lvElement == null) {
            return null;
        }
        soConsumerIndex(1 + index);
        soElement(offset, null);
        return lvElement;
    }

    public boolean isEmpty() {
        return this.producerIndex.get() == this.consumerIndex.get();
    }

    /* access modifiers changed from: package-private */
    public void soProducerIndex(long newIndex) {
        this.producerIndex.lazySet(newIndex);
    }

    /* access modifiers changed from: package-private */
    public void soConsumerIndex(long newIndex) {
        this.consumerIndex.lazySet(newIndex);
    }

    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int calcElementOffset(long index, int mask2) {
        return ((int) index) & mask2;
    }

    /* access modifiers changed from: package-private */
    public int calcElementOffset(long index) {
        return ((int) index) & this.mask;
    }

    /* access modifiers changed from: package-private */
    public void soElement(int offset, Object obj) {
        lazySet(offset, obj);
    }

    /* access modifiers changed from: package-private */
    public Object lvElement(int offset) {
        return get(offset);
    }
}
