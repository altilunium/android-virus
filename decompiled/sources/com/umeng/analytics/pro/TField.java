package com.umeng.analytics.pro;

/* renamed from: com.umeng.analytics.pro.bk */
public class TField {

    /* renamed from: a */
    public final String f537a;

    /* renamed from: b */
    public final byte f538b;

    /* renamed from: c */
    public final short f539c;

    public TField() {
        this("", (byte) 0, 0);
    }

    public TField(String str, byte b, short s) {
        this.f537a = str;
        this.f538b = b;
        this.f539c = s;
    }

    public String toString() {
        return "<TField name:'" + this.f537a + "' type:" + ((int) this.f538b) + " field-id:" + ((int) this.f539c) + ">";
    }

    /* renamed from: a */
    public boolean mo479a(TField bkVar) {
        return this.f538b == bkVar.f538b && this.f539c == bkVar.f539c;
    }
}
