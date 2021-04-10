package com.umeng.analytics.pro;

/* renamed from: com.umeng.analytics.pro.ce */
public class TTransportException extends TException {

    /* renamed from: a */
    public static final int f670a = 0;

    /* renamed from: b */
    public static final int f671b = 1;

    /* renamed from: c */
    public static final int f672c = 2;

    /* renamed from: d */
    public static final int f673d = 3;

    /* renamed from: e */
    public static final int f674e = 4;

    /* renamed from: g */
    private static final long f675g = 1;

    /* renamed from: f */
    protected int f676f = 0;

    public TTransportException() {
    }

    public TTransportException(int i) {
        this.f676f = i;
    }

    public TTransportException(int i, String str) {
        super(str);
        this.f676f = i;
    }

    public TTransportException(String str) {
        super(str);
    }

    public TTransportException(int i, Throwable th) {
        super(th);
        this.f676f = i;
    }

    public TTransportException(Throwable th) {
        super(th);
    }

    public TTransportException(String str, Throwable th) {
        super(str, th);
    }

    public TTransportException(int i, String str, Throwable th) {
        super(str, th);
        this.f676f = i;
    }

    /* renamed from: a */
    public int mo505a() {
        return this.f676f;
    }
}
