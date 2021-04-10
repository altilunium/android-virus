package com.umeng.commonsdk.statistics.proto;

import com.umeng.analytics.pro.EncodingUtils;
import com.umeng.analytics.pro.FieldMetaData;
import com.umeng.analytics.pro.FieldValueMetaData;
import com.umeng.analytics.pro.SchemeFactory;
import com.umeng.analytics.pro.StandardScheme;
import com.umeng.analytics.pro.TBase;
import com.umeng.analytics.pro.TCompactProtocol;
import com.umeng.analytics.pro.TException;
import com.umeng.analytics.pro.TField;
import com.umeng.analytics.pro.TFieldIdEnum;
import com.umeng.analytics.pro.TIOStreamTransport;
import com.umeng.analytics.pro.TProtocol;
import com.umeng.analytics.pro.TProtocolException;
import com.umeng.analytics.pro.TProtocolUtil;
import com.umeng.analytics.pro.TStruct;
import com.umeng.analytics.pro.TTupleProtocol;
import com.umeng.analytics.pro.TupleScheme;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.umeng.commonsdk.statistics.proto.a */
public class IdJournal implements TBase, Serializable, Cloneable {

    /* renamed from: e */
    public static final Map f1487e;

    /* renamed from: f */
    private static final long f1488f = 9132678615281394583L;

    /* renamed from: g */
    private static final TStruct f1489g = new TStruct("IdJournal");

    /* renamed from: h */
    private static final TField f1490h = new TField("domain", (byte) 11, 1);

    /* renamed from: i */
    private static final TField f1491i = new TField("old_id", (byte) 11, 2);

    /* renamed from: j */
    private static final TField f1492j = new TField("new_id", (byte) 11, 3);

    /* renamed from: k */
    private static final TField f1493k = new TField("ts", (byte) 10, 4);

    /* renamed from: l */
    private static final Map f1494l;

    /* renamed from: m */
    private static final int f1495m = 0;

    /* renamed from: a */
    public String f1496a;

    /* renamed from: b */
    public String f1497b;

    /* renamed from: c */
    public String f1498c;

    /* renamed from: d */
    public long f1499d;

    /* renamed from: n */
    private byte f1500n;

    /* renamed from: o */
    private EnumC0215e[] f1501o;

    static {
        HashMap hashMap = new HashMap();
        f1494l = hashMap;
        hashMap.put(StandardScheme.class, new C0212b());
        hashMap.put(TupleScheme.class, new C0214d());
        EnumMap enumMap = new EnumMap(EnumC0215e.class);
        enumMap.put((Object) EnumC0215e.DOMAIN, (Object) new FieldMetaData("domain", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put((Object) EnumC0215e.OLD_ID, (Object) new FieldMetaData("old_id", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put((Object) EnumC0215e.NEW_ID, (Object) new FieldMetaData("new_id", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put((Object) EnumC0215e.TS, (Object) new FieldMetaData("ts", (byte) 1, new FieldValueMetaData((byte) 10)));
        Map unmodifiableMap = Collections.unmodifiableMap(enumMap);
        f1487e = unmodifiableMap;
        FieldMetaData.m620a(IdJournal.class, unmodifiableMap);
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.a$e */
    /* compiled from: IdJournal */
    public enum EnumC0215e implements TFieldIdEnum {
        DOMAIN(1, "domain"),
        OLD_ID(2, "old_id"),
        NEW_ID(3, "new_id"),
        TS(4, "ts");
        

        /* renamed from: e */
        private static final Map f1506e = new HashMap();

        /* renamed from: f */
        private final short f1508f;

        /* renamed from: g */
        private final String f1509g;

        static {
            Iterator it = EnumSet.allOf(EnumC0215e.class).iterator();
            while (it.hasNext()) {
                EnumC0215e eVar = (EnumC0215e) it.next();
                f1506e.put(eVar.mo362b(), eVar);
            }
        }

        /* renamed from: a */
        public static EnumC0215e m1591a(int i) {
            if (i == 1) {
                return DOMAIN;
            }
            if (i == 2) {
                return OLD_ID;
            }
            if (i == 3) {
                return NEW_ID;
            }
            if (i != 4) {
                return null;
            }
            return TS;
        }

        /* renamed from: b */
        public static EnumC0215e m1593b(int i) {
            EnumC0215e a = m1591a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static EnumC0215e m1592a(String str) {
            return (EnumC0215e) f1506e.get(str);
        }

        private EnumC0215e(short s, String str) {
            this.f1508f = s;
            this.f1509g = str;
        }

        @Override // com.umeng.analytics.pro.TFieldIdEnum
        /* renamed from: a */
        public short mo361a() {
            return this.f1508f;
        }

        @Override // com.umeng.analytics.pro.TFieldIdEnum
        /* renamed from: b */
        public String mo362b() {
            return this.f1509g;
        }
    }

    public IdJournal() {
        this.f1500n = 0;
        this.f1501o = new EnumC0215e[]{EnumC0215e.OLD_ID};
    }

    public IdJournal(String str, String str2, long j) {
        this();
        this.f1496a = str;
        this.f1498c = str2;
        this.f1499d = j;
        mo878d(true);
    }

    public IdJournal(IdJournal aVar) {
        this.f1500n = 0;
        this.f1501o = new EnumC0215e[]{EnumC0215e.OLD_ID};
        this.f1500n = aVar.f1500n;
        if (aVar.mo879d()) {
            this.f1496a = aVar.f1496a;
        }
        if (aVar.mo882g()) {
            this.f1497b = aVar.f1497b;
        }
        if (aVar.mo885j()) {
            this.f1498c = aVar.f1498c;
        }
        this.f1499d = aVar.f1499d;
    }

    /* renamed from: a */
    public IdJournal deepCopy() {
        return new IdJournal(this);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void clear() {
        this.f1496a = null;
        this.f1497b = null;
        this.f1498c = null;
        mo878d(false);
        this.f1499d = 0;
    }

    /* renamed from: b */
    public String mo873b() {
        return this.f1496a;
    }

    /* renamed from: a */
    public IdJournal mo870a(String str) {
        this.f1496a = str;
        return this;
    }

    /* renamed from: c */
    public void mo876c() {
        this.f1496a = null;
    }

    /* renamed from: d */
    public boolean mo879d() {
        return this.f1496a != null;
    }

    /* renamed from: a */
    public void mo871a(boolean z) {
        if (!z) {
            this.f1496a = null;
        }
    }

    /* renamed from: e */
    public String mo880e() {
        return this.f1497b;
    }

    /* renamed from: b */
    public IdJournal mo872b(String str) {
        this.f1497b = str;
        return this;
    }

    /* renamed from: f */
    public void mo881f() {
        this.f1497b = null;
    }

    /* renamed from: g */
    public boolean mo882g() {
        return this.f1497b != null;
    }

    /* renamed from: b */
    public void mo874b(boolean z) {
        if (!z) {
            this.f1497b = null;
        }
    }

    /* renamed from: h */
    public String mo883h() {
        return this.f1498c;
    }

    /* renamed from: c */
    public IdJournal mo875c(String str) {
        this.f1498c = str;
        return this;
    }

    /* renamed from: i */
    public void mo884i() {
        this.f1498c = null;
    }

    /* renamed from: j */
    public boolean mo885j() {
        return this.f1498c != null;
    }

    /* renamed from: c */
    public void mo877c(boolean z) {
        if (!z) {
            this.f1498c = null;
        }
    }

    /* renamed from: k */
    public long mo886k() {
        return this.f1499d;
    }

    /* renamed from: a */
    public IdJournal mo869a(long j) {
        this.f1499d = j;
        mo878d(true);
        return this;
    }

    /* renamed from: l */
    public void mo887l() {
        this.f1500n = EncodingUtils.m530b(this.f1500n, 0);
    }

    /* renamed from: m */
    public boolean mo888m() {
        return EncodingUtils.m526a(this.f1500n, 0);
    }

    /* renamed from: d */
    public void mo878d(boolean z) {
        this.f1500n = EncodingUtils.m518a(this.f1500n, 0, z);
    }

    /* renamed from: a */
    public EnumC0215e fieldForId(int i) {
        return EnumC0215e.m1591a(i);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void read(TProtocol bpVar) {
        ((SchemeFactory) f1494l.get(bpVar.mo485D())).mo357b().mo355b(bpVar, this);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void write(TProtocol bpVar) {
        ((SchemeFactory) f1494l.get(bpVar.mo485D())).mo357b().mo353a(bpVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdJournal(");
        sb.append("domain:");
        String str = this.f1496a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        if (mo882g()) {
            sb.append(", ");
            sb.append("old_id:");
            String str2 = this.f1497b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        sb.append(", ");
        sb.append("new_id:");
        String str3 = this.f1498c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.f1499d);
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: n */
    public void mo889n() {
        if (this.f1496a == null) {
            throw new TProtocolException("Required field 'domain' was not present! Struct: " + toString());
        } else if (this.f1498c == null) {
            throw new TProtocolException("Required field 'new_id' was not present! Struct: " + toString());
        }
    }

    /* renamed from: a */
    private void m1550a(ObjectOutputStream objectOutputStream) {
        try {
            write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
        } catch (TException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: a */
    private void m1549a(ObjectInputStream objectInputStream) {
        try {
            this.f1500n = 0;
            read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
        } catch (TException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.a$b */
    /* compiled from: IdJournal */
    class C0212b implements SchemeFactory {
        private C0212b() {
        }

        /* renamed from: a */
        public C0211a mo357b() {
            return new C0211a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.proto.a$a */
    /* compiled from: IdJournal */
    public class C0211a extends StandardScheme {
        private C0211a() {
        }

        /* renamed from: a */
        public void mo355b(TProtocol bpVar, IdJournal aVar) {
            bpVar.mo459j();
            while (true) {
                TField l = bpVar.mo461l();
                byte b = l.f538b;
                if (b == 0) {
                    break;
                }
                short s = l.f539c;
                if (s != 1) {
                    if (s != 2) {
                        if (s != 3) {
                            if (s != 4) {
                                TProtocolUtil.m785a(bpVar, b);
                            } else if (b == 10) {
                                aVar.f1499d = bpVar.mo473x();
                                aVar.mo878d(true);
                            } else {
                                TProtocolUtil.m785a(bpVar, b);
                            }
                        } else if (b == 11) {
                            aVar.f1498c = bpVar.mo475z();
                            aVar.mo877c(true);
                        } else {
                            TProtocolUtil.m785a(bpVar, b);
                        }
                    } else if (b == 11) {
                        aVar.f1497b = bpVar.mo475z();
                        aVar.mo874b(true);
                    } else {
                        TProtocolUtil.m785a(bpVar, b);
                    }
                } else if (b == 11) {
                    aVar.f1496a = bpVar.mo475z();
                    aVar.mo871a(true);
                } else {
                    TProtocolUtil.m785a(bpVar, b);
                }
                bpVar.mo462m();
            }
            bpVar.mo460k();
            if (aVar.mo888m()) {
                aVar.mo889n();
                return;
            }
            throw new TProtocolException("Required field 'ts' was not found in serialized data! Struct: " + toString());
        }

        /* renamed from: b */
        public void mo353a(TProtocol bpVar, IdJournal aVar) {
            aVar.mo889n();
            bpVar.mo443a(IdJournal.f1489g);
            if (aVar.f1496a != null) {
                bpVar.mo438a(IdJournal.f1490h);
                bpVar.mo444a(aVar.f1496a);
                bpVar.mo450c();
            }
            if (aVar.f1497b != null && aVar.mo882g()) {
                bpVar.mo438a(IdJournal.f1491i);
                bpVar.mo444a(aVar.f1497b);
                bpVar.mo450c();
            }
            if (aVar.f1498c != null) {
                bpVar.mo438a(IdJournal.f1492j);
                bpVar.mo444a(aVar.f1498c);
                bpVar.mo450c();
            }
            bpVar.mo438a(IdJournal.f1493k);
            bpVar.mo437a(aVar.f1499d);
            bpVar.mo450c();
            bpVar.mo452d();
            bpVar.mo449b();
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.a$d */
    /* compiled from: IdJournal */
    class C0214d implements SchemeFactory {
        private C0214d() {
        }

        /* renamed from: a */
        public C0213c mo357b() {
            return new C0213c();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.proto.a$c */
    /* compiled from: IdJournal */
    public class C0213c extends TupleScheme {
        private C0213c() {
        }

        /* renamed from: a */
        public void mo353a(TProtocol bpVar, IdJournal aVar) {
            TTupleProtocol bvVar = (TTupleProtocol) bpVar;
            bvVar.mo444a(aVar.f1496a);
            bvVar.mo444a(aVar.f1498c);
            bvVar.mo437a(aVar.f1499d);
            BitSet bitSet = new BitSet();
            if (aVar.mo882g()) {
                bitSet.set(0);
            }
            bvVar.mo487a(bitSet, 1);
            if (aVar.mo882g()) {
                bvVar.mo444a(aVar.f1497b);
            }
        }

        /* renamed from: b */
        public void mo355b(TProtocol bpVar, IdJournal aVar) {
            TTupleProtocol bvVar = (TTupleProtocol) bpVar;
            aVar.f1496a = bvVar.mo475z();
            aVar.mo871a(true);
            aVar.f1498c = bvVar.mo475z();
            aVar.mo877c(true);
            aVar.f1499d = bvVar.mo473x();
            aVar.mo878d(true);
            if (bvVar.mo488b(1).get(0)) {
                aVar.f1497b = bvVar.mo475z();
                aVar.mo874b(true);
            }
        }
    }
}
