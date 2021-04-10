package com.umeng.analytics.pro;

import com.umeng.analytics.pro.TCompactProtocol;
import com.umeng.commonsdk.statistics.common.ReportPolicy;

/* renamed from: com.umeng.analytics.pro.bs */
public class TProtocolUtil {

    /* renamed from: a */
    private static int f561a = Integer.MAX_VALUE;

    /* renamed from: a */
    public static void m784a(int i) {
        f561a = i;
    }

    /* renamed from: a */
    public static void m785a(TProtocol bpVar, byte b) {
        m786a(bpVar, b, f561a);
    }

    /* renamed from: a */
    public static void m786a(TProtocol bpVar, byte b, int i) {
        if (i > 0) {
            int i2 = 0;
            switch (b) {
                case 2:
                    bpVar.mo469t();
                    return;
                case 3:
                    bpVar.mo470u();
                    return;
                case 4:
                    bpVar.mo474y();
                    return;
                case 5:
                case TApplicationException.f455h:
                case 9:
                default:
                    return;
                case 6:
                    bpVar.mo471v();
                    return;
                case 8:
                    bpVar.mo472w();
                    return;
                case 10:
                    bpVar.mo473x();
                    return;
                case ReportPolicy.QUASI_REALTIME_POLICY /*{ENCODED_INT: 11}*/:
                    bpVar.mo432A();
                    return;
                case 12:
                    bpVar.mo459j();
                    while (true) {
                        byte b2 = bpVar.mo461l().f538b;
                        if (b2 == 0) {
                            bpVar.mo460k();
                            return;
                        } else {
                            m786a(bpVar, b2, i - 1);
                            bpVar.mo462m();
                        }
                    }
                case 13:
                    TMap n = bpVar.mo463n();
                    while (i2 < n.f544c) {
                        int i3 = i - 1;
                        m786a(bpVar, n.f542a, i3);
                        m786a(bpVar, n.f543b, i3);
                        i2++;
                    }
                    bpVar.mo464o();
                    return;
                case 14:
                    TSet r = bpVar.mo467r();
                    while (i2 < r.f563b) {
                        m786a(bpVar, r.f562a, i - 1);
                        i2++;
                    }
                    bpVar.mo468s();
                    return;
                case 15:
                    TList p = bpVar.mo465p();
                    while (i2 < p.f541b) {
                        m786a(bpVar, p.f540a, i - 1);
                        i2++;
                    }
                    bpVar.mo466q();
                    return;
            }
        } else {
            throw new TException("Maximum skip depth exceeded");
        }
    }

    /* renamed from: a */
    public static TProtocolFactory m783a(byte[] bArr, TProtocolFactory brVar) {
        if (bArr[0] > 16) {
            return new TCompactProtocol.C0086a();
        }
        if (bArr.length <= 1 || (bArr[1] & 128) == 0) {
            return brVar;
        }
        return new TCompactProtocol.C0086a();
    }
}
