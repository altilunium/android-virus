package com.p000ib.p001mk.qmux.p002ym.p003a;

/* renamed from: com.ib.mk.qmux.ym.a.a */
public class C0002a {

    /* renamed from: a */
    private byte[] f4a;

    public C0002a() {
        this("skaxscyomkrxyveuejhmfcbj");
    }

    public C0002a(String str) {
        this.f4a = null;
        mo23a(str);
    }

    /* renamed from: a */
    public void mo23a(String str) {
        this.f4a = str.getBytes();
    }

    /* renamed from: a */
    public byte[] mo24a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            byte[] bArr3 = this.f4a;
            bArr2[i] = (byte) (b ^ bArr3[i % bArr3.length]);
        }
        return bArr2;
    }
}
