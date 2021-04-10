package com.umeng.analytics.pro;

/* renamed from: com.umeng.analytics.pro.ap */
public class TApplicationException extends TException {

    /* renamed from: a */
    public static final int f448a = 0;

    /* renamed from: b */
    public static final int f449b = 1;

    /* renamed from: c */
    public static final int f450c = 2;

    /* renamed from: d */
    public static final int f451d = 3;

    /* renamed from: e */
    public static final int f452e = 4;

    /* renamed from: f */
    public static final int f453f = 5;

    /* renamed from: g */
    public static final int f454g = 6;

    /* renamed from: h */
    public static final int f455h = 7;

    /* renamed from: j */
    private static final TStruct f456j = new TStruct("TApplicationException");

    /* renamed from: k */
    private static final TField f457k = new TField("message", (byte) 11, 1);

    /* renamed from: l */
    private static final TField f458l = new TField(UContent.f663y, (byte) 8, 2);

    /* renamed from: m */
    private static final long f459m = 1;

    /* renamed from: i */
    protected int f460i = 0;

    public TApplicationException() {
    }

    public TApplicationException(int i) {
        this.f460i = i;
    }

    public TApplicationException(int i, String str) {
        super(str);
        this.f460i = i;
    }

    public TApplicationException(String str) {
        super(str);
    }

    /* renamed from: a */
    public int mo378a() {
        return this.f460i;
    }

    /* renamed from: a */
    public static TApplicationException m539a(TProtocol bpVar) {
        bpVar.mo459j();
        String str = null;
        int i = 0;
        while (true) {
            TField l = bpVar.mo461l();
            byte b = l.f538b;
            if (b == 0) {
                bpVar.mo460k();
                return new TApplicationException(i, str);
            }
            short s = l.f539c;
            if (s != 1) {
                if (s != 2) {
                    TProtocolUtil.m785a(bpVar, b);
                } else if (b == 8) {
                    i = bpVar.mo472w();
                } else {
                    TProtocolUtil.m785a(bpVar, b);
                }
            } else if (b == 11) {
                str = bpVar.mo475z();
            } else {
                TProtocolUtil.m785a(bpVar, b);
            }
            bpVar.mo462m();
        }
    }

    /* renamed from: b */
    public void mo379b(TProtocol bpVar) {
        bpVar.mo443a(f456j);
        if (getMessage() != null) {
            bpVar.mo438a(f457k);
            bpVar.mo444a(getMessage());
            bpVar.mo450c();
        }
        bpVar.mo438a(f458l);
        bpVar.mo436a(this.f460i);
        bpVar.mo450c();
        bpVar.mo452d();
        bpVar.mo449b();
    }
}
