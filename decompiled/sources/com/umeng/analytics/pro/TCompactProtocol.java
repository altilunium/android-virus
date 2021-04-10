package com.umeng.analytics.pro;

import com.umeng.commonsdk.statistics.common.ReportPolicy;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* renamed from: com.umeng.analytics.pro.bj */
public class TCompactProtocol extends TProtocol {

    /* renamed from: d */
    private static final TStruct f507d = new TStruct("");

    /* renamed from: e */
    private static final TField f508e = new TField("", (byte) 0, 0);

    /* renamed from: f */
    private static final byte[] f509f;

    /* renamed from: h */
    private static final byte f510h = -126;

    /* renamed from: i */
    private static final byte f511i = 1;

    /* renamed from: j */
    private static final byte f512j = 31;

    /* renamed from: k */
    private static final byte f513k = -32;

    /* renamed from: l */
    private static final int f514l = 5;

    /* renamed from: a */
    byte[] f515a;

    /* renamed from: b */
    byte[] f516b;

    /* renamed from: c */
    byte[] f517c;

    /* renamed from: m */
    private ShortStack f518m;

    /* renamed from: n */
    private short f519n;

    /* renamed from: o */
    private TField f520o;

    /* renamed from: p */
    private Boolean f521p;

    /* renamed from: q */
    private final long f522q;

    /* renamed from: r */
    private byte[] f523r;

    static {
        byte[] bArr = new byte[16];
        f509f = bArr;
        bArr[0] = 0;
        bArr[2] = 1;
        bArr[3] = 3;
        bArr[6] = 4;
        bArr[8] = 5;
        bArr[10] = 6;
        bArr[4] = 7;
        bArr[11] = 8;
        bArr[15] = 9;
        bArr[14] = 10;
        bArr[13] = 11;
        bArr[12] = 12;
    }

    /* renamed from: com.umeng.analytics.pro.bj$a */
    /* compiled from: TCompactProtocol */
    public class C0086a implements TProtocolFactory {

        /* renamed from: a */
        private final long f524a;

        public C0086a() {
            this.f524a = -1;
        }

        public C0086a(int i) {
            this.f524a = (long) i;
        }

        @Override // com.umeng.analytics.pro.TProtocolFactory
        /* renamed from: a */
        public TProtocol mo476a(TTransport cdVar) {
            return new TCompactProtocol(cdVar, this.f524a);
        }
    }

    /* renamed from: com.umeng.analytics.pro.bj$b */
    /* compiled from: TCompactProtocol */
    class C0087b {

        /* renamed from: a */
        public static final byte f525a = 1;

        /* renamed from: b */
        public static final byte f526b = 2;

        /* renamed from: c */
        public static final byte f527c = 3;

        /* renamed from: d */
        public static final byte f528d = 4;

        /* renamed from: e */
        public static final byte f529e = 5;

        /* renamed from: f */
        public static final byte f530f = 6;

        /* renamed from: g */
        public static final byte f531g = 7;

        /* renamed from: h */
        public static final byte f532h = 8;

        /* renamed from: i */
        public static final byte f533i = 9;

        /* renamed from: j */
        public static final byte f534j = 10;

        /* renamed from: k */
        public static final byte f535k = 11;

        /* renamed from: l */
        public static final byte f536l = 12;

        private C0087b() {
        }
    }

    public TCompactProtocol(TTransport cdVar, long j) {
        super(cdVar);
        this.f518m = new ShortStack(15);
        this.f519n = 0;
        this.f520o = null;
        this.f521p = null;
        this.f515a = new byte[5];
        this.f516b = new byte[10];
        this.f523r = new byte[1];
        this.f517c = new byte[1];
        this.f522q = j;
    }

    public TCompactProtocol(TTransport cdVar) {
        this(cdVar, -1);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: B */
    public void mo477B() {
        this.f518m.mo376c();
        this.f519n = 0;
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo441a(TMessage bnVar) {
        m678b(f510h);
        m686d(((bnVar.f546b << 5) & -32) | 1);
        mo488b(bnVar.f547c);
        mo444a(bnVar.f545a);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo443a(TStruct buVar) {
        this.f518m.mo374a(this.f519n);
        this.f519n = 0;
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: b */
    public void mo449b() {
        this.f519n = this.f518m.mo373a();
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo438a(TField bkVar) {
        if (bkVar.f538b == 2) {
            this.f520o = bkVar;
        } else {
            m676a(bkVar, (byte) -1);
        }
    }

    /* renamed from: a */
    private void m676a(TField bkVar, byte b) {
        if (b == -1) {
            b = m687e(bkVar.f538b);
        }
        short s = bkVar.f539c;
        short s2 = this.f519n;
        if (s <= s2 || s - s2 > 15) {
            m678b(b);
            mo446a(bkVar.f539c);
        } else {
            m686d(b | ((s - s2) << 4));
        }
        this.f519n = bkVar.f539c;
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: d */
    public void mo452d() {
        m678b((byte) 0);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo440a(TMap bmVar) {
        int i = bmVar.f544c;
        if (i == 0) {
            m686d(0);
            return;
        }
        mo488b(i);
        m686d(m687e(bmVar.f543b) | (m687e(bmVar.f542a) << 4));
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo439a(TList blVar) {
        mo478a(blVar.f540a, blVar.f541b);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo442a(TSet btVar) {
        mo478a(btVar.f562a, btVar.f563b);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo447a(boolean z) {
        TField bkVar = this.f520o;
        byte b = 1;
        if (bkVar != null) {
            if (!z) {
                b = 2;
            }
            m676a(bkVar, b);
            this.f520o = null;
            return;
        }
        if (!z) {
            b = 2;
        }
        m678b(b);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo434a(byte b) {
        m678b(b);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo446a(short s) {
        mo488b(m681c((int) s));
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo436a(int i) {
        mo488b(m681c(i));
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo437a(long j) {
        m680b(m682c(j));
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo435a(double d) {
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0};
        m675a(Double.doubleToLongBits(d), bArr, 0);
        this.f552g.mo502b(bArr);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo444a(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            m677a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new TException("UTF-8 not supported!");
        }
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo445a(ByteBuffer byteBuffer) {
        m677a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    /* renamed from: a */
    private void m677a(byte[] bArr, int i, int i2) {
        mo488b(i2);
        this.f552g.mo492b(bArr, i, i2);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo433a() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: e */
    public void mo454e() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: f */
    public void mo455f() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: g */
    public void mo456g() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: c */
    public void mo450c() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo478a(byte b, int i) {
        if (i <= 14) {
            m686d(m687e(b) | (i << 4));
            return;
        }
        m686d(m687e(b) | 240);
        mo488b(i);
    }

    /* renamed from: b */
    private void mo488b(int i) {
        int i2 = 0;
        while ((i & -128) != 0) {
            this.f515a[i2] = (byte) ((i & 127) | 128);
            i >>>= 7;
            i2++;
        }
        byte[] bArr = this.f515a;
        bArr[i2] = (byte) i;
        this.f552g.mo492b(bArr, 0, i2 + 1);
    }

    /* renamed from: b */
    private void m680b(long j) {
        int i = 0;
        while ((-128 & j) != 0) {
            this.f516b[i] = (byte) ((int) ((127 & j) | 128));
            j >>>= 7;
            i++;
        }
        byte[] bArr = this.f516b;
        bArr[i] = (byte) ((int) j);
        this.f552g.mo492b(bArr, 0, i + 1);
    }

    /* renamed from: c */
    private long m682c(long j) {
        return (j >> 63) ^ (j << 1);
    }

    /* renamed from: c */
    private int m681c(int i) {
        return (i >> 31) ^ (i << 1);
    }

    /* renamed from: a */
    private void m675a(long j, byte[] bArr, int i) {
        bArr[i + 0] = (byte) ((int) (j & 255));
        bArr[i + 1] = (byte) ((int) ((j >> 8) & 255));
        bArr[i + 2] = (byte) ((int) ((j >> 16) & 255));
        bArr[i + 3] = (byte) ((int) ((j >> 24) & 255));
        bArr[i + 4] = (byte) ((int) ((j >> 32) & 255));
        bArr[i + 5] = (byte) ((int) ((j >> 40) & 255));
        bArr[i + 6] = (byte) ((int) ((j >> 48) & 255));
        bArr[i + 7] = (byte) ((int) ((j >> 56) & 255));
    }

    /* renamed from: b */
    private void m678b(byte b) {
        byte[] bArr = this.f523r;
        bArr[0] = b;
        this.f552g.mo502b(bArr);
    }

    /* renamed from: d */
    private void m686d(int i) {
        m678b((byte) i);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: h */
    public TMessage mo457h() {
        byte u = mo470u();
        if (u == -126) {
            byte u2 = mo470u();
            byte b = (byte) (u2 & f512j);
            if (b == 1) {
                int E = m672E();
                return new TMessage(mo475z(), (byte) ((u2 >> 5) & 3), E);
            }
            throw new TProtocolException("Expected version 1 but got " + ((int) b));
        }
        throw new TProtocolException("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(u));
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: j */
    public TStruct mo459j() {
        this.f518m.mo374a(this.f519n);
        this.f519n = 0;
        return f507d;
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: k */
    public void mo460k() {
        this.f519n = this.f518m.mo373a();
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: l */
    public TField mo461l() {
        short s;
        byte u = mo470u();
        if (u == 0) {
            return f508e;
        }
        short s2 = (short) ((u & 240) >> 4);
        if (s2 == 0) {
            s = mo471v();
        } else {
            s = (short) (this.f519n + s2);
        }
        byte b = (byte) (u & TType.f577m);
        TField bkVar = new TField("", m684d(b), s);
        if (m683c(u)) {
            this.f521p = b == 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.f519n = bkVar.f539c;
        return bkVar;
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: n */
    public TMap mo463n() {
        int E = m672E();
        byte u = E == 0 ? 0 : mo470u();
        return new TMap(m684d((byte) (u >> 4)), m684d((byte) (u & TType.f577m)), E);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: p */
    public TList mo465p() {
        byte u = mo470u();
        int i = (u >> 4) & 15;
        if (i == 15) {
            i = m672E();
        }
        return new TList(m684d(u), i);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: r */
    public TSet mo467r() {
        return new TSet(mo465p());
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: t */
    public boolean mo469t() {
        Boolean bool = this.f521p;
        if (bool == null) {
            return mo470u() == 1;
        }
        boolean booleanValue = bool.booleanValue();
        this.f521p = null;
        return booleanValue;
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: u */
    public byte mo470u() {
        if (this.f552g.mo501h() > 0) {
            byte b = this.f552g.mo499f()[this.f552g.mo500g()];
            this.f552g.mo495a(1);
            return b;
        }
        this.f552g.mo503d(this.f517c, 0, 1);
        return this.f517c[0];
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: v */
    public short mo471v() {
        return (short) m690g(m672E());
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: w */
    public int mo472w() {
        return m690g(m672E());
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: x */
    public long mo473x() {
        return m685d(m673F());
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: y */
    public double mo474y() {
        byte[] bArr = new byte[8];
        this.f552g.mo503d(bArr, 0, 8);
        return Double.longBitsToDouble(m674a(bArr));
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: z */
    public String mo475z() {
        int E = m672E();
        m689f(E);
        if (E == 0) {
            return "";
        }
        try {
            if (this.f552g.mo501h() < E) {
                return new String(m688e(E), "UTF-8");
            }
            String str = new String(this.f552g.mo499f(), this.f552g.mo500g(), E, "UTF-8");
            this.f552g.mo495a(E);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new TException("UTF-8 not supported!");
        }
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: A */
    public ByteBuffer mo432A() {
        int E = m672E();
        m689f(E);
        if (E == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[E];
        this.f552g.mo503d(bArr, 0, E);
        return ByteBuffer.wrap(bArr);
    }

    /* renamed from: e */
    private byte[] m688e(int i) {
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        this.f552g.mo503d(bArr, 0, i);
        return bArr;
    }

    /* renamed from: f */
    private void m689f(int i) {
        if (i >= 0) {
            long j = this.f522q;
            if (j != -1 && ((long) i) > j) {
                throw new TProtocolException("Length exceeded max allowed: " + i);
            }
            return;
        }
        throw new TProtocolException("Negative length: " + i);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: i */
    public void mo458i() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: m */
    public void mo462m() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: o */
    public void mo464o() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: q */
    public void mo466q() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: s */
    public void mo468s() {
    }

    /* renamed from: E */
    private int m672E() {
        int i = 0;
        if (this.f552g.mo501h() >= 5) {
            byte[] f = this.f552g.mo499f();
            int g = this.f552g.mo500g();
            int i2 = 0;
            int i3 = 0;
            while (true) {
                byte b = f[g + i];
                i2 |= (b & Byte.MAX_VALUE) << i3;
                if ((b & 128) != 128) {
                    this.f552g.mo495a(i + 1);
                    return i2;
                }
                i3 += 7;
                i++;
            }
        } else {
            int i4 = 0;
            while (true) {
                byte u = mo470u();
                i |= (u & Byte.MAX_VALUE) << i4;
                if ((u & 128) != 128) {
                    return i;
                }
                i4 += 7;
            }
        }
    }

    /* renamed from: F */
    private long m673F() {
        int i = 0;
        long j = 0;
        if (this.f552g.mo501h() >= 10) {
            byte[] f = this.f552g.mo499f();
            int g = this.f552g.mo500g();
            long j2 = 0;
            int i2 = 0;
            while (true) {
                byte b = f[g + i];
                j2 |= ((long) (b & Byte.MAX_VALUE)) << i2;
                if ((b & 128) != 128) {
                    this.f552g.mo495a(i + 1);
                    return j2;
                }
                i2 += 7;
                i++;
            }
        } else {
            while (true) {
                byte u = mo470u();
                j |= ((long) (u & Byte.MAX_VALUE)) << i;
                if ((u & 128) != 128) {
                    return j;
                }
                i += 7;
            }
        }
    }

    /* renamed from: g */
    private int m690g(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    /* renamed from: d */
    private long m685d(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    /* renamed from: a */
    private long m674a(byte[] bArr) {
        return ((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48) | ((((long) bArr[5]) & 255) << 40) | ((((long) bArr[4]) & 255) << 32) | ((((long) bArr[3]) & 255) << 24) | ((((long) bArr[2]) & 255) << 16) | ((((long) bArr[1]) & 255) << 8) | (255 & ((long) bArr[0]));
    }

    /* renamed from: c */
    private boolean m683c(byte b) {
        int i = b & TType.f577m;
        return i == 1 || i == 2;
    }

    /* renamed from: d */
    private byte m684d(byte b) {
        byte b2 = (byte) (b & TType.f577m);
        switch (b2) {
            case 0:
                return 0;
            case 1:
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 6;
            case 5:
                return 8;
            case 6:
                return 10;
            case TApplicationException.f455h:
                return 4;
            case 8:
                return 11;
            case 9:
                return TType.f577m;
            case 10:
                return TType.f576l;
            case ReportPolicy.QUASI_REALTIME_POLICY /*{ENCODED_INT: 11}*/:
                return TType.f575k;
            case 12:
                return 12;
            default:
                throw new TProtocolException("don't know what type: " + ((int) b2));
        }
    }

    /* renamed from: e */
    private byte m687e(byte b) {
        return f509f[b];
    }
}
