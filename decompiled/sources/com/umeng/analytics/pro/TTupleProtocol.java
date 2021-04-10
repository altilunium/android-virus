package com.umeng.analytics.pro;

import java.util.BitSet;

/* renamed from: com.umeng.analytics.pro.bv */
public final class TTupleProtocol extends TCompactProtocol {

    /* renamed from: com.umeng.analytics.pro.bv$a */
    /* compiled from: TTupleProtocol */
    public class C0088a implements TProtocolFactory {
        @Override // com.umeng.analytics.pro.TProtocolFactory
        /* renamed from: a */
        public TProtocol mo476a(TTransport cdVar) {
            return new TTupleProtocol(cdVar);
        }
    }

    public TTupleProtocol(TTransport cdVar) {
        super(cdVar);
    }

    @Override // com.umeng.analytics.pro.TProtocol
    /* renamed from: D */
    public Class mo485D() {
        return TupleScheme.class;
    }

    /* renamed from: a */
    public void mo487a(BitSet bitSet, int i) {
        for (byte b : m788b(bitSet, i)) {
            mo434a(b);
        }
    }

    /* renamed from: b */
    public BitSet mo488b(int i) {
        double d = (double) i;
        Double.isNaN(d);
        int ceil = (int) Math.ceil(d / 8.0d);
        byte[] bArr = new byte[ceil];
        for (int i2 = 0; i2 < ceil; i2++) {
            bArr[i2] = mo470u();
        }
        return m787a(bArr);
    }

    /* renamed from: a */
    public static BitSet m787a(byte[] bArr) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < bArr.length * 8; i++) {
            if ((bArr[(bArr.length - (i / 8)) - 1] & (1 << (i % 8))) > 0) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }

    /* renamed from: b */
    public static byte[] m788b(BitSet bitSet, int i) {
        double d = (double) i;
        Double.isNaN(d);
        int ceil = (int) Math.ceil(d / 8.0d);
        byte[] bArr = new byte[ceil];
        for (int i2 = 0; i2 < bitSet.length(); i2++) {
            if (bitSet.get(i2)) {
                int i3 = (ceil - (i2 / 8)) - 1;
                bArr[i3] = (byte) ((1 << (i2 % 8)) | bArr[i3]);
            }
        }
        return bArr;
    }
}
