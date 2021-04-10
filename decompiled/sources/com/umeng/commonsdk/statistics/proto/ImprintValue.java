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

/* renamed from: com.umeng.commonsdk.statistics.proto.e */
public class ImprintValue implements TBase, Serializable, Cloneable {

    /* renamed from: d */
    public static final Map f1567d;

    /* renamed from: e */
    private static final long f1568e = 7501688097813630241L;

    /* renamed from: f */
    private static final TStruct f1569f = new TStruct("ImprintValue");

    /* renamed from: g */
    private static final TField f1570g = new TField("value", (byte) 11, 1);

    /* renamed from: h */
    private static final TField f1571h = new TField("ts", (byte) 10, 2);

    /* renamed from: i */
    private static final TField f1572i = new TField("guid", (byte) 11, 3);

    /* renamed from: j */
    private static final Map f1573j;

    /* renamed from: k */
    private static final int f1574k = 0;

    /* renamed from: a */
    public String f1575a;

    /* renamed from: b */
    public long f1576b;

    /* renamed from: c */
    public String f1577c;

    /* renamed from: l */
    private byte f1578l;

    /* renamed from: m */
    private EnumC0239e[] f1579m;

    static {
        HashMap hashMap = new HashMap();
        f1573j = hashMap;
        hashMap.put(StandardScheme.class, new C0236b());
        hashMap.put(TupleScheme.class, new C0238d());
        EnumMap enumMap = new EnumMap(EnumC0239e.class);
        enumMap.put((Object) EnumC0239e.VALUE, (Object) new FieldMetaData("value", (byte) 2, new FieldValueMetaData((byte) 11)));
        enumMap.put((Object) EnumC0239e.TS, (Object) new FieldMetaData("ts", (byte) 1, new FieldValueMetaData((byte) 10)));
        enumMap.put((Object) EnumC0239e.GUID, (Object) new FieldMetaData("guid", (byte) 1, new FieldValueMetaData((byte) 11)));
        Map unmodifiableMap = Collections.unmodifiableMap(enumMap);
        f1567d = unmodifiableMap;
        FieldMetaData.m620a(ImprintValue.class, unmodifiableMap);
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.e$e */
    /* compiled from: ImprintValue */
    public enum EnumC0239e implements TFieldIdEnum {
        VALUE(1, "value"),
        TS(2, "ts"),
        GUID(3, "guid");
        

        /* renamed from: d */
        private static final Map f1583d = new HashMap();

        /* renamed from: e */
        private final short f1585e;

        /* renamed from: f */
        private final String f1586f;

        static {
            Iterator it = EnumSet.allOf(EnumC0239e.class).iterator();
            while (it.hasNext()) {
                EnumC0239e eVar = (EnumC0239e) it.next();
                f1583d.put(eVar.mo362b(), eVar);
            }
        }

        /* renamed from: a */
        public static EnumC0239e m1762a(int i) {
            if (i == 1) {
                return VALUE;
            }
            if (i == 2) {
                return TS;
            }
            if (i != 3) {
                return null;
            }
            return GUID;
        }

        /* renamed from: b */
        public static EnumC0239e m1764b(int i) {
            EnumC0239e a = m1762a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static EnumC0239e m1763a(String str) {
            return (EnumC0239e) f1583d.get(str);
        }

        private EnumC0239e(short s, String str) {
            this.f1585e = s;
            this.f1586f = str;
        }

        @Override // com.umeng.analytics.pro.TFieldIdEnum
        /* renamed from: a */
        public short mo361a() {
            return this.f1585e;
        }

        @Override // com.umeng.analytics.pro.TFieldIdEnum
        /* renamed from: b */
        public String mo362b() {
            return this.f1586f;
        }
    }

    public ImprintValue() {
        this.f1578l = 0;
        this.f1579m = new EnumC0239e[]{EnumC0239e.VALUE};
    }

    public ImprintValue(long j, String str) {
        this();
        this.f1576b = j;
        mo986b(true);
        this.f1577c = str;
    }

    public ImprintValue(ImprintValue eVar) {
        this.f1578l = 0;
        this.f1579m = new EnumC0239e[]{EnumC0239e.VALUE};
        this.f1578l = eVar.f1578l;
        if (eVar.mo989d()) {
            this.f1575a = eVar.f1575a;
        }
        this.f1576b = eVar.f1576b;
        if (eVar.mo995j()) {
            this.f1577c = eVar.f1577c;
        }
    }

    /* renamed from: a */
    public ImprintValue deepCopy() {
        return new ImprintValue(this);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void clear() {
        this.f1575a = null;
        mo986b(false);
        this.f1576b = 0;
        this.f1577c = null;
    }

    /* renamed from: b */
    public String mo985b() {
        return this.f1575a;
    }

    /* renamed from: a */
    public ImprintValue mo982a(String str) {
        this.f1575a = str;
        return this;
    }

    /* renamed from: c */
    public void mo987c() {
        this.f1575a = null;
    }

    /* renamed from: d */
    public boolean mo989d() {
        return this.f1575a != null;
    }

    /* renamed from: a */
    public void mo983a(boolean z) {
        if (!z) {
            this.f1575a = null;
        }
    }

    /* renamed from: e */
    public long mo990e() {
        return this.f1576b;
    }

    /* renamed from: a */
    public ImprintValue mo981a(long j) {
        this.f1576b = j;
        mo986b(true);
        return this;
    }

    /* renamed from: f */
    public void mo991f() {
        this.f1578l = EncodingUtils.m530b(this.f1578l, 0);
    }

    /* renamed from: g */
    public boolean mo992g() {
        return EncodingUtils.m526a(this.f1578l, 0);
    }

    /* renamed from: b */
    public void mo986b(boolean z) {
        this.f1578l = EncodingUtils.m518a(this.f1578l, 0, z);
    }

    /* renamed from: h */
    public String mo993h() {
        return this.f1577c;
    }

    /* renamed from: b */
    public ImprintValue mo984b(String str) {
        this.f1577c = str;
        return this;
    }

    /* renamed from: i */
    public void mo994i() {
        this.f1577c = null;
    }

    /* renamed from: j */
    public boolean mo995j() {
        return this.f1577c != null;
    }

    /* renamed from: c */
    public void mo988c(boolean z) {
        if (!z) {
            this.f1577c = null;
        }
    }

    /* renamed from: a */
    public EnumC0239e fieldForId(int i) {
        return EnumC0239e.m1762a(i);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void read(TProtocol bpVar) {
        ((SchemeFactory) f1573j.get(bpVar.mo485D())).mo357b().mo355b(bpVar, this);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void write(TProtocol bpVar) {
        ((SchemeFactory) f1573j.get(bpVar.mo485D())).mo357b().mo353a(bpVar, this);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("ImprintValue(");
        if (mo989d()) {
            sb.append("value:");
            String str = this.f1575a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("ts:");
        sb.append(this.f1576b);
        sb.append(", ");
        sb.append("guid:");
        String str2 = this.f1577c;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: k */
    public void mo996k() {
        if (this.f1577c == null) {
            throw new TProtocolException("Required field 'guid' was not present! Struct: " + toString());
        }
    }

    /* renamed from: a */
    private void m1727a(ObjectOutputStream objectOutputStream) {
        try {
            write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
        } catch (TException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: a */
    private void m1726a(ObjectInputStream objectInputStream) {
        try {
            this.f1578l = 0;
            read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
        } catch (TException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.e$b */
    /* compiled from: ImprintValue */
    class C0236b implements SchemeFactory {
        private C0236b() {
        }

        /* renamed from: a */
        public C0235a mo357b() {
            return new C0235a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.proto.e$a */
    /* compiled from: ImprintValue */
    public class C0235a extends StandardScheme {
        private C0235a() {
        }

        /* renamed from: a */
        public void mo355b(TProtocol bpVar, ImprintValue eVar) {
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
                            TProtocolUtil.m785a(bpVar, b);
                        } else if (b == 11) {
                            eVar.f1577c = bpVar.mo475z();
                            eVar.mo988c(true);
                        } else {
                            TProtocolUtil.m785a(bpVar, b);
                        }
                    } else if (b == 10) {
                        eVar.f1576b = bpVar.mo473x();
                        eVar.mo986b(true);
                    } else {
                        TProtocolUtil.m785a(bpVar, b);
                    }
                } else if (b == 11) {
                    eVar.f1575a = bpVar.mo475z();
                    eVar.mo983a(true);
                } else {
                    TProtocolUtil.m785a(bpVar, b);
                }
                bpVar.mo462m();
            }
            bpVar.mo460k();
            if (eVar.mo992g()) {
                eVar.mo996k();
                return;
            }
            throw new TProtocolException("Required field 'ts' was not found in serialized data! Struct: " + toString());
        }

        /* renamed from: b */
        public void mo353a(TProtocol bpVar, ImprintValue eVar) {
            eVar.mo996k();
            bpVar.mo443a(ImprintValue.f1569f);
            if (eVar.f1575a != null && eVar.mo989d()) {
                bpVar.mo438a(ImprintValue.f1570g);
                bpVar.mo444a(eVar.f1575a);
                bpVar.mo450c();
            }
            bpVar.mo438a(ImprintValue.f1571h);
            bpVar.mo437a(eVar.f1576b);
            bpVar.mo450c();
            if (eVar.f1577c != null) {
                bpVar.mo438a(ImprintValue.f1572i);
                bpVar.mo444a(eVar.f1577c);
                bpVar.mo450c();
            }
            bpVar.mo452d();
            bpVar.mo449b();
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.e$d */
    /* compiled from: ImprintValue */
    class C0238d implements SchemeFactory {
        private C0238d() {
        }

        /* renamed from: a */
        public C0237c mo357b() {
            return new C0237c();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.proto.e$c */
    /* compiled from: ImprintValue */
    public class C0237c extends TupleScheme {
        private C0237c() {
        }

        /* renamed from: a */
        public void mo353a(TProtocol bpVar, ImprintValue eVar) {
            TTupleProtocol bvVar = (TTupleProtocol) bpVar;
            bvVar.mo437a(eVar.f1576b);
            bvVar.mo444a(eVar.f1577c);
            BitSet bitSet = new BitSet();
            if (eVar.mo989d()) {
                bitSet.set(0);
            }
            bvVar.mo487a(bitSet, 1);
            if (eVar.mo989d()) {
                bvVar.mo444a(eVar.f1575a);
            }
        }

        /* renamed from: b */
        public void mo355b(TProtocol bpVar, ImprintValue eVar) {
            TTupleProtocol bvVar = (TTupleProtocol) bpVar;
            eVar.f1576b = bvVar.mo473x();
            eVar.mo986b(true);
            eVar.f1577c = bvVar.mo475z();
            eVar.mo988c(true);
            if (bvVar.mo488b(1).get(0)) {
                eVar.f1575a = bvVar.mo475z();
                eVar.mo983a(true);
            }
        }
    }
}
