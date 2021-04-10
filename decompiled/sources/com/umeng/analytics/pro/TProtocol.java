package com.umeng.analytics.pro;

import java.nio.ByteBuffer;

/* renamed from: com.umeng.analytics.pro.bp */
public abstract class TProtocol {

    /* renamed from: g */
    protected TTransport f552g;

    /* renamed from: A */
    public abstract ByteBuffer mo432A();

    /* renamed from: a */
    public abstract void mo433a();

    /* renamed from: a */
    public abstract void mo434a(byte b);

    /* renamed from: a */
    public abstract void mo435a(double d);

    /* renamed from: a */
    public abstract void mo436a(int i);

    /* renamed from: a */
    public abstract void mo437a(long j);

    /* renamed from: a */
    public abstract void mo438a(TField bkVar);

    /* renamed from: a */
    public abstract void mo439a(TList blVar);

    /* renamed from: a */
    public abstract void mo440a(TMap bmVar);

    /* renamed from: a */
    public abstract void mo441a(TMessage bnVar);

    /* renamed from: a */
    public abstract void mo442a(TSet btVar);

    /* renamed from: a */
    public abstract void mo443a(TStruct buVar);

    /* renamed from: a */
    public abstract void mo444a(String str);

    /* renamed from: a */
    public abstract void mo445a(ByteBuffer byteBuffer);

    /* renamed from: a */
    public abstract void mo446a(short s);

    /* renamed from: a */
    public abstract void mo447a(boolean z);

    /* renamed from: b */
    public abstract void mo449b();

    /* renamed from: c */
    public abstract void mo450c();

    /* renamed from: d */
    public abstract void mo452d();

    /* renamed from: e */
    public abstract void mo454e();

    /* renamed from: f */
    public abstract void mo455f();

    /* renamed from: g */
    public abstract void mo456g();

    /* renamed from: h */
    public abstract TMessage mo457h();

    /* renamed from: i */
    public abstract void mo458i();

    /* renamed from: j */
    public abstract TStruct mo459j();

    /* renamed from: k */
    public abstract void mo460k();

    /* renamed from: l */
    public abstract TField mo461l();

    /* renamed from: m */
    public abstract void mo462m();

    /* renamed from: n */
    public abstract TMap mo463n();

    /* renamed from: o */
    public abstract void mo464o();

    /* renamed from: p */
    public abstract TList mo465p();

    /* renamed from: q */
    public abstract void mo466q();

    /* renamed from: r */
    public abstract TSet mo467r();

    /* renamed from: s */
    public abstract void mo468s();

    /* renamed from: t */
    public abstract boolean mo469t();

    /* renamed from: u */
    public abstract byte mo470u();

    /* renamed from: v */
    public abstract short mo471v();

    /* renamed from: w */
    public abstract int mo472w();

    /* renamed from: x */
    public abstract long mo473x();

    /* renamed from: y */
    public abstract double mo474y();

    /* renamed from: z */
    public abstract String mo475z();

    private TProtocol() {
    }

    protected TProtocol(TTransport cdVar) {
        this.f552g = cdVar;
    }

    /* renamed from: C */
    public TTransport mo484C() {
        return this.f552g;
    }

    /* renamed from: B */
    public void mo477B() {
    }

    /* renamed from: D */
    public Class mo485D() {
        return StandardScheme.class;
    }
}
