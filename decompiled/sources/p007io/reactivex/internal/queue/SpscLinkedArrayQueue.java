package p007io.reactivex.internal.queue;

import com.own.bless.soy.SimpleQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p007io.reactivex.internal.util.Pow2;

/* renamed from: io.reactivex.internal.queue.a */
public final class SpscLinkedArrayQueue implements SimpleQueue {

    /* renamed from: i */
    static final int f1745i = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();

    /* renamed from: j */
    private static final Object f1746j = new Object();

    /* renamed from: a */
    final AtomicLong f1747a = new AtomicLong();

    /* renamed from: b */
    int f1748b;

    /* renamed from: c */
    long f1749c;

    /* renamed from: d */
    final int f1750d;

    /* renamed from: e */
    AtomicReferenceArray f1751e;

    /* renamed from: f */
    final int f1752f;

    /* renamed from: g */
    AtomicReferenceArray f1753g;

    /* renamed from: h */
    final AtomicLong f1754h = new AtomicLong();

    public SpscLinkedArrayQueue(int bufferSize) {
        int p2capacity = Pow2.m1943a(Math.max(8, bufferSize));
        int mask = p2capacity - 1;
        AtomicReferenceArray<Object> buffer = new AtomicReferenceArray<>(p2capacity + 1);
        this.f1751e = buffer;
        this.f1750d = mask;
        m1864a(p2capacity);
        this.f1753g = buffer;
        this.f1752f = mask;
        this.f1749c = (long) (mask - 1);
        m1878s(0);
    }

    /* renamed from: m */
    public boolean mo1185m(Object obj) {
        if (obj != null) {
            AtomicReferenceArray<Object> buffer = this.f1751e;
            long index = m1868g();
            int mask = this.f1750d;
            int offset = m1866c(index, mask);
            if (index < this.f1749c) {
                m1879t(buffer, obj, index, offset);
                return true;
            }
            int lookAheadStep = this.f1748b;
            if (m1870i(buffer, m1866c(((long) lookAheadStep) + index, mask)) == null) {
                this.f1749c = (((long) lookAheadStep) + index) - 1;
                m1879t(buffer, obj, index, offset);
                return true;
            } else if (m1870i(buffer, m1866c(1 + index, mask)) == null) {
                m1879t(buffer, obj, index, offset);
                return true;
            } else {
                m1874o(buffer, index, offset, obj, (long) mask);
                return true;
            }
        } else {
            throw new NullPointerException("Null is not a valid element");
        }
    }

    /* renamed from: t */
    private boolean m1879t(AtomicReferenceArray atomicReferenceArray, Object obj, long index, int offset) {
        m1876q(atomicReferenceArray, offset, obj);
        m1878s(1 + index);
        return true;
    }

    /* renamed from: o */
    private void m1874o(AtomicReferenceArray atomicReferenceArray, long currIndex, int offset, Object obj, long mask) {
        AtomicReferenceArray<Object> newBuffer = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.f1751e = newBuffer;
        this.f1749c = (currIndex + mask) - 1;
        m1876q(newBuffer, offset, obj);
        m1877r(atomicReferenceArray, newBuffer);
        m1876q(atomicReferenceArray, offset, f1746j);
        m1878s(1 + currIndex);
    }

    /* renamed from: r */
    private void m1877r(AtomicReferenceArray atomicReferenceArray, AtomicReferenceArray atomicReferenceArray2) {
        int length = atomicReferenceArray.length() - 1;
        m1865b(length);
        m1876q(atomicReferenceArray, length, atomicReferenceArray2);
    }

    /* renamed from: j */
    private AtomicReferenceArray m1871j(AtomicReferenceArray atomicReferenceArray, int nextIndex) {
        int nextOffset = m1865b(nextIndex);
        AtomicReferenceArray<Object> nextBuffer = (AtomicReferenceArray) m1870i(atomicReferenceArray, nextOffset);
        m1876q(atomicReferenceArray, nextOffset, null);
        return nextBuffer;
    }

    /* renamed from: n */
    public Object mo1186n() {
        AtomicReferenceArray<Object> buffer = this.f1753g;
        long index = m1867f();
        int mask = this.f1752f;
        int offset = m1866c(index, mask);
        Object e = m1870i(buffer, offset);
        boolean isNextBuffer = e == f1746j;
        if (e != null && !isNextBuffer) {
            m1876q(buffer, offset, null);
            m1875p(1 + index);
            return e;
        } else if (isNextBuffer) {
            return m1873l(m1871j(buffer, mask + 1), index, mask);
        } else {
            return null;
        }
    }

    /* renamed from: l */
    private Object m1873l(AtomicReferenceArray atomicReferenceArray, long index, int mask) {
        this.f1753g = atomicReferenceArray;
        int offsetInNew = m1866c(index, mask);
        Object i = m1870i(atomicReferenceArray, offsetInNew);
        if (i != null) {
            m1876q(atomicReferenceArray, offsetInNew, null);
            m1875p(1 + index);
        }
        return i;
    }

    /* renamed from: d */
    public void mo1183d() {
        while (true) {
            if (mo1186n() == null && mo1184e()) {
                return;
            }
        }
    }

    /* renamed from: e */
    public boolean mo1184e() {
        return m1872k() == m1869h();
    }

    /* renamed from: a */
    private void m1864a(int capacity) {
        this.f1748b = Math.min(capacity / 4, f1745i);
    }

    /* renamed from: k */
    private long m1872k() {
        return this.f1747a.get();
    }

    /* renamed from: h */
    private long m1869h() {
        return this.f1754h.get();
    }

    /* renamed from: g */
    private long m1868g() {
        return this.f1747a.get();
    }

    /* renamed from: f */
    private long m1867f() {
        return this.f1754h.get();
    }

    /* renamed from: s */
    private void m1878s(long v) {
        this.f1747a.lazySet(v);
    }

    /* renamed from: p */
    private void m1875p(long v) {
        this.f1754h.lazySet(v);
    }

    /* renamed from: c */
    private static int m1866c(long index, int mask) {
        int i = ((int) index) & mask;
        m1865b(i);
        return i;
    }

    /* renamed from: b */
    private static int m1865b(int index) {
        return index;
    }

    /* renamed from: q */
    private static void m1876q(AtomicReferenceArray atomicReferenceArray, int offset, Object e) {
        atomicReferenceArray.lazySet(offset, e);
    }

    /* renamed from: i */
    private static Object m1870i(AtomicReferenceArray atomicReferenceArray, int offset) {
        return atomicReferenceArray.get(offset);
    }
}
