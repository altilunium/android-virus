package com.umeng.analytics.pro;

import java.io.Serializable;

/* renamed from: com.umeng.analytics.pro.bd */
public class FieldValueMetaData implements Serializable {

    /* renamed from: a */
    private final boolean f480a;

    /* renamed from: b */
    public final byte f481b;

    /* renamed from: c */
    private final String f482c;

    /* renamed from: d */
    private final boolean f483d;

    public FieldValueMetaData(byte b, boolean z) {
        this.f481b = b;
        this.f480a = false;
        this.f482c = null;
        this.f483d = z;
    }

    public FieldValueMetaData(byte b) {
        this(b, false);
    }

    public FieldValueMetaData(byte b, String str) {
        this.f481b = b;
        this.f480a = true;
        this.f482c = str;
        this.f483d = false;
    }

    /* renamed from: a */
    public boolean mo427a() {
        return this.f480a;
    }

    /* renamed from: b */
    public String mo428b() {
        return this.f482c;
    }

    /* renamed from: c */
    public boolean mo429c() {
        return this.f481b == 12;
    }

    /* renamed from: d */
    public boolean mo430d() {
        byte b = this.f481b;
        return b == 15 || b == 13 || b == 14;
    }

    /* renamed from: e */
    public boolean mo431e() {
        return this.f483d;
    }
}
