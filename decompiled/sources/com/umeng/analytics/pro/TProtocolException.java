package com.umeng.analytics.pro;

/* renamed from: com.umeng.analytics.pro.bq */
public class TProtocolException extends TException {

    /* renamed from: a */
    public static final int f553a = 0;

    /* renamed from: b */
    public static final int f554b = 1;

    /* renamed from: c */
    public static final int f555c = 2;

    /* renamed from: d */
    public static final int f556d = 3;

    /* renamed from: e */
    public static final int f557e = 4;

    /* renamed from: f */
    public static final int f558f = 5;

    /* renamed from: h */
    private static final long f559h = 1;

    /* renamed from: g */
    protected int f560g = 0;

    public TProtocolException() {
    }

    public TProtocolException(int i) {
        this.f560g = i;
    }

    public TProtocolException(int i, String str) {
        super(str);
        this.f560g = i;
    }

    public TProtocolException(String str) {
        super(str);
    }

    public TProtocolException(int i, Throwable th) {
        super(th);
        this.f560g = i;
    }

    public TProtocolException(Throwable th) {
        super(th);
    }

    public TProtocolException(String str, Throwable th) {
        super(str, th);
    }

    public TProtocolException(int i, String str, Throwable th) {
        super(str, th);
        this.f560g = i;
    }

    /* renamed from: a */
    public int mo486a() {
        return this.f560g;
    }
}
