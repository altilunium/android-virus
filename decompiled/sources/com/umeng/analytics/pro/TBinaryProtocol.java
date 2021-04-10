package com.umeng.analytics.pro;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* renamed from: com.umeng.analytics.pro.bi */
public class TBinaryProtocol extends TProtocol {

    /* renamed from: a */
    protected static final int f489a = -65536;

    /* renamed from: b */
    protected static final int f490b = -2147418112;

    /* renamed from: h */
    private static final TStruct f491h = new TStruct();

    /* renamed from: c */
    protected boolean f492c;

    /* renamed from: d */
    protected boolean f493d;

    /* renamed from: e */
    protected int f494e;

    /* renamed from: f */
    protected boolean f495f;

    /* renamed from: i */
    private byte[] f496i;

    /* renamed from: j */
    private byte[] f497j;

    /* renamed from: k */
    private byte[] f498k;

    /* renamed from: l */
    private byte[] f499l;

    /* renamed from: m */
    private byte[] f500m;

    /* renamed from: n */
    private byte[] f501n;

    /* renamed from: o */
    private byte[] f502o;

    /* renamed from: p */
    private byte[] f503p;

    /* renamed from: com.umeng.analytics.pro.bi$a */
    /* compiled from: TBinaryProtocol */
    public class C0085a implements TProtocolFactory {

        /* renamed from: a */
        protected boolean f504a;

        /* renamed from: b */
        protected boolean f505b;

        /* renamed from: c */
        protected int f506c;

        public C0085a() {
            this(false, true);
        }

        public C0085a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public C0085a(boolean z, boolean z2, int i) {
            this.f504a = false;
            this.f505b = true;
            this.f504a = z;
            this.f505b = z2;
            this.f506c = i;
        }

        @Override // com.umeng.analytics.pro.TProtocolFactory
        /* renamed from: a */
        public TProtocol mo476a(TTransport cdVar) {
            TBinaryProtocol biVar = new TBinaryProtocol(cdVar, this.f504a, this.f505b);
            int i = this.f506c;
            if (i != 0) {
                biVar.mo451c(i);
            }
            return biVar;
        }
    }

    public TBinaryProtocol(TTransport cdVar) {
        this(cdVar, false, true);
    }

    public TBinaryProtocol(TTransport cdVar, boolean z, boolean z2) {
        super(cdVar);
        this.f492c = false;
        this.f493d = true;
        this.f495f = false;
        this.f496i = new byte[1];
        this.f497j = new byte[2];
        this.f498k = new byte[4];
        this.f499l = new byte[8];
        this.f500m = new byte[1];
        this.f501n = new byte[2];
        this.f502o = new byte[4];
        this.f503p = new byte[8];
        this.f492c = z;
        this.f493d = z2;
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo441a(TMessage bnVar) {
        if (this.f493d) {
            mo436a(f490b | bnVar.f546b);
            mo444a(bnVar.f545a);
            mo436a(bnVar.f547c);
            return;
        }
        mo444a(bnVar.f545a);
        mo434a(bnVar.f546b);
        mo436a(bnVar.f547c);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo433a() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo443a(TStruct buVar) {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: b */
    public void mo449b() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo438a(TField bkVar) {
        mo434a(bkVar.f538b);
        mo446a(bkVar.f539c);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: c */
    public void mo450c() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: d */
    public void mo452d() {
        mo434a((byte) 0);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo440a(TMap bmVar) {
        mo434a(bmVar.f542a);
        mo434a(bmVar.f543b);
        mo436a(bmVar.f544c);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: e */
    public void mo454e() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo439a(TList blVar) {
        mo434a(blVar.f540a);
        mo436a(blVar.f541b);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: f */
    public void mo455f() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo442a(TSet btVar) {
        mo434a(btVar.f562a);
        mo436a(btVar.f563b);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: g */
    public void mo456g() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo447a(boolean z) {
        mo434a(z ? (byte) 1 : 0);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo434a(byte b) {
        byte[] bArr = this.f496i;
        bArr[0] = b;
        this.f552g.mo492b(bArr, 0, 1);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo446a(short s) {
        byte[] bArr = this.f497j;
        bArr[0] = (byte) ((s >> 8) & 255);
        bArr[1] = (byte) (s & 255);
        this.f552g.mo492b(bArr, 0, 2);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo436a(int i) {
        byte[] bArr = this.f498k;
        bArr[0] = (byte) ((i >> 24) & 255);
        bArr[1] = (byte) ((i >> 16) & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
        bArr[3] = (byte) (i & 255);
        this.f552g.mo492b(bArr, 0, 4);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo437a(long j) {
        byte[] bArr = this.f499l;
        bArr[0] = (byte) ((int) ((j >> 56) & 255));
        bArr[1] = (byte) ((int) ((j >> 48) & 255));
        bArr[2] = (byte) ((int) ((j >> 40) & 255));
        bArr[3] = (byte) ((int) ((j >> 32) & 255));
        bArr[4] = (byte) ((int) ((j >> 24) & 255));
        bArr[5] = (byte) ((int) ((j >> 16) & 255));
        bArr[6] = (byte) ((int) ((j >> 8) & 255));
        bArr[7] = (byte) ((int) (j & 255));
        this.f552g.mo492b(bArr, 0, 8);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo435a(double d) {
        mo437a(Double.doubleToLongBits(d));
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo444a(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            mo436a(bytes.length);
            this.f552g.mo492b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: a */
    public void mo445a(ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit() - byteBuffer.position();
        mo436a(limit);
        this.f552g.mo492b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: h */
    public TMessage mo457h() {
        int w = mo472w();
        if (w < 0) {
            if ((f489a & w) == f490b) {
                return new TMessage(mo475z(), (byte) (w & 255), mo472w());
            }
            throw new TProtocolException(4, "Bad version in readMessageBegin");
        } else if (!this.f492c) {
            return new TMessage(mo448b(w), mo470u(), mo472w());
        } else {
            throw new TProtocolException(4, "Missing version in readMessageBegin, old client?");
        }
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: i */
    public void mo458i() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: j */
    public TStruct mo459j() {
        return f491h;
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: k */
    public void mo460k() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: l */
    public TField mo461l() {
        byte u = mo470u();
        return new TField("", u, u == 0 ? 0 : mo471v());
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: m */
    public void mo462m() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: n */
    public TMap mo463n() {
        return new TMap(mo470u(), mo470u(), mo472w());
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: o */
    public void mo464o() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: p */
    public TList mo465p() {
        return new TList(mo470u(), mo472w());
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: q */
    public void mo466q() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: r */
    public TSet mo467r() {
        return new TSet(mo470u(), mo472w());
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: s */
    public void mo468s() {
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: t */
    public boolean mo469t() {
        return mo470u() == 1;
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: u */
    public byte mo470u() {
        if (this.f552g.mo501h() >= 1) {
            byte b = this.f552g.mo499f()[this.f552g.mo500g()];
            this.f552g.mo495a(1);
            return b;
        }
        m626a(this.f500m, 0, 1);
        return this.f500m[0];
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: v */
    public short mo471v() {
        byte[] bArr = this.f501n;
        int i = 0;
        if (this.f552g.mo501h() >= 2) {
            bArr = this.f552g.mo499f();
            i = this.f552g.mo500g();
            this.f552g.mo495a(2);
        } else {
            m626a(this.f501n, 0, 2);
        }
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: w */
    public int mo472w() {
        byte[] bArr = this.f502o;
        int i = 0;
        if (this.f552g.mo501h() >= 4) {
            bArr = this.f552g.mo499f();
            i = this.f552g.mo500g();
            this.f552g.mo495a(4);
        } else {
            m626a(this.f502o, 0, 4);
        }
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: x */
    public long mo473x() {
        byte[] bArr = this.f503p;
        int i = 0;
        if (this.f552g.mo501h() >= 8) {
            bArr = this.f552g.mo499f();
            i = this.f552g.mo500g();
            this.f552g.mo495a(8);
        } else {
            m626a(this.f503p, 0, 8);
        }
        return ((long) (bArr[i + 7] & 255)) | (((long) (bArr[i] & 255)) << 56) | (((long) (bArr[i + 1] & 255)) << 48) | (((long) (bArr[i + 2] & 255)) << 40) | (((long) (bArr[i + 3] & 255)) << 32) | (((long) (bArr[i + 4] & 255)) << 24) | (((long) (bArr[i + 5] & 255)) << 16) | (((long) (bArr[i + 6] & 255)) << 8);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: y */
    public double mo474y() {
        return Double.longBitsToDouble(mo473x());
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: z */
    public String mo475z() {
        int w = mo472w();
        if (this.f552g.mo501h() < w) {
            return mo448b(w);
        }
        try {
            String str = new String(this.f552g.mo499f(), this.f552g.mo500g(), w, "UTF-8");
            this.f552g.mo495a(w);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    /* renamed from: b */
    public String mo448b(int i) {
        try {
            mo453d(i);
            byte[] bArr = new byte[i];
            this.f552g.mo503d(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new TException("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: A */
    public ByteBuffer mo432A() {
        int w = mo472w();
        mo453d(w);
        if (this.f552g.mo501h() >= w) {
            ByteBuffer wrap = ByteBuffer.wrap(this.f552g.mo499f(), this.f552g.mo500g(), w);
            this.f552g.mo495a(w);
            return wrap;
        }
        byte[] bArr = new byte[w];
        this.f552g.mo503d(bArr, 0, w);
        return ByteBuffer.wrap(bArr);
    }

    /* renamed from: a */
    private int m626a(byte[] bArr, int i, int i2) {
        mo453d(i2);
        return this.f552g.mo503d(bArr, i, i2);
    }

    /* renamed from: c */
    public void mo451c(int i) {
        this.f494e = i;
        this.f495f = true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo453d(int i) {
        if (i < 0) {
            throw new TProtocolException("Negative length: " + i);
        } else if (this.f495f) {
            int i2 = this.f494e - i;
            this.f494e = i2;
            if (i2 < 0) {
                throw new TProtocolException("Message length exceeded: " + i);
            }
        }
    }
}
