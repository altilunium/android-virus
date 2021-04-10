package com.umeng.analytics.pro;

/* renamed from: com.umeng.analytics.pro.ao */
public class ShortStack {

    /* renamed from: a */
    private short[] f446a;

    /* renamed from: b */
    private int f447b = -1;

    public ShortStack(int i) {
        this.f446a = new short[i];
    }

    /* renamed from: a */
    public short mo373a() {
        short[] sArr = this.f446a;
        int i = this.f447b;
        this.f447b = i - 1;
        return sArr[i];
    }

    /* renamed from: a */
    public void mo374a(short s) {
        if (this.f446a.length == this.f447b + 1) {
            m534d();
        }
        short[] sArr = this.f446a;
        int i = this.f447b + 1;
        this.f447b = i;
        sArr[i] = s;
    }

    /* renamed from: d */
    private void m534d() {
        short[] sArr = this.f446a;
        short[] sArr2 = new short[(sArr.length * 2)];
        System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
        this.f446a = sArr2;
    }

    /* renamed from: b */
    public short mo375b() {
        return this.f446a[this.f447b];
    }

    /* renamed from: c */
    public void mo376c() {
        this.f447b = -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ShortStack vector:[");
        for (int i = 0; i < this.f446a.length; i++) {
            if (i != 0) {
                sb.append(" ");
            }
            if (i == this.f447b) {
                sb.append(">>");
            }
            sb.append((int) this.f446a[i]);
            if (i == this.f447b) {
                sb.append("<<");
            }
        }
        sb.append("]>");
        return sb.toString();
    }
}
