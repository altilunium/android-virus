package com.umeng.analytics.pro;

import com.umeng.analytics.pro.TCompactProtocol;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/* renamed from: com.umeng.analytics.pro.az */
public class TSerializer {

    /* renamed from: a */
    private final ByteArrayOutputStream f468a;

    /* renamed from: b */
    private final TIOStreamTransport f469b;

    /* renamed from: c */
    private TProtocol f470c;

    public TSerializer() {
        this(new TCompactProtocol.C0086a());
    }

    public TSerializer(TProtocolFactory brVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.f468a = byteArrayOutputStream;
        TIOStreamTransport cbVar = new TIOStreamTransport(byteArrayOutputStream);
        this.f469b = cbVar;
        this.f470c = brVar.mo476a(cbVar);
    }

    /* renamed from: a */
    public byte[] mo398a(TBase aqVar) {
        this.f468a.reset();
        aqVar.write(this.f470c);
        return this.f468a.toByteArray();
    }

    /* renamed from: a */
    public String mo397a(TBase aqVar, String str) {
        try {
            return new String(mo398a(aqVar), str);
        } catch (UnsupportedEncodingException e) {
            throw new TException("JVM DOES NOT SUPPORT ENCODING: " + str);
        }
    }

    /* renamed from: b */
    public String mo399b(TBase aqVar) {
        return new String(mo398a(aqVar));
    }
}
