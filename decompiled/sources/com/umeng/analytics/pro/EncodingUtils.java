package com.umeng.analytics.pro;

/* renamed from: com.umeng.analytics.pro.an */
public class EncodingUtils {
    /* renamed from: a */
    public static final void m524a(int i, byte[] bArr) {
        m525a(i, bArr, 0);
    }

    /* renamed from: a */
    public static final void m525a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) ((i >> 24) & 255);
        bArr[i2 + 1] = (byte) ((i >> 16) & 255);
        bArr[i2 + 2] = (byte) ((i >> 8) & 255);
        bArr[i2 + 3] = (byte) (i & 255);
    }

    /* renamed from: a */
    public static final int m520a(byte[] bArr) {
        return m521a(bArr, 0);
    }

    /* renamed from: a */
    public static final int m521a(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    /* renamed from: a */
    public static final boolean m526a(byte b, int i) {
        return m527a((int) b, i);
    }

    /* renamed from: a */
    public static final boolean m529a(short s, int i) {
        return m527a((int) s, i);
    }

    /* renamed from: a */
    public static final boolean m527a(int i, int i2) {
        return (i & (1 << i2)) != 0;
    }

    /* renamed from: a */
    public static final boolean m528a(long j, int i) {
        return (j & (1 << i)) != 0;
    }

    /* renamed from: b */
    public static final byte m530b(byte b, int i) {
        return (byte) m531b((int) b, i);
    }

    /* renamed from: b */
    public static final short m533b(short s, int i) {
        return (short) m531b((int) s, i);
    }

    /* renamed from: b */
    public static final int m531b(int i, int i2) {
        return i & ((1 << i2) ^ -1);
    }

    /* renamed from: b */
    public static final long m532b(long j, int i) {
        return j & ((1 << i) ^ -1);
    }

    /* renamed from: a */
    public static final byte m518a(byte b, int i, boolean z) {
        return (byte) m519a((int) b, i, z);
    }

    /* renamed from: a */
    public static final short m523a(short s, int i, boolean z) {
        return (short) m519a((int) s, i, z);
    }

    /* renamed from: a */
    public static final int m519a(int i, int i2, boolean z) {
        if (z) {
            return i | (1 << i2);
        }
        return m531b(i, i2);
    }

    /* renamed from: a */
    public static final long m522a(long j, int i, boolean z) {
        if (z) {
            return j | (1 << i);
        }
        return m532b(j, i);
    }
}
