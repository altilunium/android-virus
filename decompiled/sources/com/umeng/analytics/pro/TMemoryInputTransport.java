package com.umeng.analytics.pro;

/* renamed from: com.umeng.analytics.pro.cc */
public final class TMemoryInputTransport extends TTransport {

    /* renamed from: a */
    private byte[] f667a;

    /* renamed from: b */
    private int f668b;

    /* renamed from: c */
    private int f669c;

    public TMemoryInputTransport() {
    }

    public TMemoryInputTransport(byte[] bArr) {
        mo496a(bArr);
    }

    public TMemoryInputTransport(byte[] bArr, int i, int i2) {
        mo497c(bArr, i, i2);
    }

    /* renamed from: a */
    public void mo496a(byte[] bArr) {
        mo497c(bArr, 0, bArr.length);
    }

    /* renamed from: c */
    public void mo497c(byte[] bArr, int i, int i2) {
        this.f667a = bArr;
        this.f668b = i;
        this.f669c = i + i2;
    }

    /* renamed from: e */
    public void mo498e() {
        this.f667a = null;
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: c */
    public void mo493c() {
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: a */
    public boolean mo490a() {
        return true;
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: b */
    public void mo491b() {
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: a */
    public int mo489a(byte[] bArr, int i, int i2) {
        int h = mo501h();
        if (i2 > h) {
            i2 = h;
        }
        if (i2 > 0) {
            System.arraycopy(this.f667a, this.f668b, bArr, i, i2);
            mo495a(i2);
        }
        return i2;
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: b */
    public void mo492b(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: f */
    public byte[] mo499f() {
        return this.f667a;
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: g */
    public int mo500g() {
        return this.f668b;
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: h */
    public int mo501h() {
        return this.f669c - this.f668b;
    }

    @Override // com.umeng.analytics.pro.TTransport
    /* renamed from: a */
    public void mo495a(int i) {
        this.f668b += i;
    }
}
