package com.umeng.analytics.pro;

/* renamed from: com.umeng.analytics.pro.cd */
public abstract class TTransport {
    /* renamed from: a */
    public abstract int mo489a(byte[] bArr, int i, int i2);

    /* renamed from: a */
    public abstract boolean mo490a();

    /* renamed from: b */
    public abstract void mo491b();

    /* renamed from: b */
    public abstract void mo492b(byte[] bArr, int i, int i2);

    /* renamed from: c */
    public abstract void mo493c();

    /* renamed from: i */
    public boolean mo504i() {
        return mo490a();
    }

    /* renamed from: d */
    public int mo503d(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int a = mo489a(bArr, i + i3, i2 - i3);
            if (a > 0) {
                i3 += a;
            } else {
                throw new TTransportException("Cannot read. Remote side has closed. Tried to read " + i2 + " bytes, but only got " + i3 + " bytes. (This is often indicative of an internal error on the server side. Please check your server logs.)");
            }
        }
        return i3;
    }

    /* renamed from: b */
    public void mo502b(byte[] bArr) {
        mo492b(bArr, 0, bArr.length);
    }

    /* renamed from: d */
    public void mo494d() {
    }

    /* renamed from: f */
    public byte[] mo499f() {
        return null;
    }

    /* renamed from: g */
    public int mo500g() {
        return 0;
    }

    /* renamed from: h */
    public int mo501h() {
        return -1;
    }

    /* renamed from: a */
    public void mo495a(int i) {
    }
}
