package com.umeng.analytics.pro;

/* renamed from: com.umeng.analytics.pro.bn */
public final class TMessage {

    /* renamed from: a */
    public final String f545a;

    /* renamed from: b */
    public final byte f546b;

    /* renamed from: c */
    public final int f547c;

    public TMessage() {
        this("", (byte) 0, 0);
    }

    public TMessage(String str, byte b, int i) {
        this.f545a = str;
        this.f546b = b;
        this.f547c = i;
    }

    public String toString() {
        return "<TMessage name:'" + this.f545a + "' type: " + ((int) this.f546b) + " seqid:" + this.f547c + ">";
    }

    public boolean equals(Object obj) {
        if (obj instanceof TMessage) {
            return mo481a((TMessage) obj);
        }
        return false;
    }

    /* renamed from: a */
    public boolean mo481a(TMessage bnVar) {
        return this.f545a.equals(bnVar.f545a) && this.f546b == bnVar.f546b && this.f547c == bnVar.f547c;
    }
}
