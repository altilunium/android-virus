package com.own.bless.soy;

/* renamed from: com.own.bless.soy.d */
/* compiled from: Base64 */
public final class C0012d {
    /* renamed from: a */
    public static byte[] m60a(String str, int flags) {
        return m61b(str.getBytes(), flags);
    }

    /* renamed from: b */
    public static byte[] m61b(byte[] input, int flags) {
        return m62c(input, 0, input.length, flags);
    }

    /* renamed from: c */
    private static byte[] m62c(byte[] input, int offset, int len, int flags) {
        C0009c decoder = new C0009c(flags, new byte[((len * 3) / 4)]);
        if (decoder.mo35a(input, offset, len, true)) {
            int i = decoder.f9b;
            byte[] bArr = decoder.f8a;
            if (i == bArr.length) {
                return bArr;
            }
            byte[] temp = new byte[i];
            System.arraycopy(bArr, 0, temp, 0, i);
            return temp;
        }
        throw new IllegalArgumentException("bad base-64");
    }
}
