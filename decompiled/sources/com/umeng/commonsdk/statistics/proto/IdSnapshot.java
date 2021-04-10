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
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.umeng.commonsdk.statistics.proto.b */
public class IdSnapshot implements TBase, Serializable, Cloneable {

    /* renamed from: d */
    public static final Map f1510d;

    /* renamed from: e */
    private static final long f1511e = -6496538196005191531L;

    /* renamed from: f */
    private static final TStruct f1512f = new TStruct("IdSnapshot");

    /* renamed from: g */
    private static final TField f1513g = new TField("identity", (byte) 11, 1);

    /* renamed from: h */
    private static final TField f1514h = new TField("ts", (byte) 10, 2);

    /* renamed from: i */
    private static final TField f1515i = new TField("version", (byte) 8, 3);

    /* renamed from: j */
    private static final Map f1516j;

    /* renamed from: k */
    private static final int f1517k = 0;

    /* renamed from: l */
    private static final int f1518l = 1;

    /* renamed from: a */
    public String f1519a;

    /* renamed from: b */
    public long f1520b;

    /* renamed from: c */
    public int f1521c;

    /* renamed from: m */
    private byte f1522m;

    static {
        HashMap hashMap = new HashMap();
        f1516j = hashMap;
        hashMap.put(StandardScheme.class, new C0218b());
        hashMap.put(TupleScheme.class, new C0220d());
        EnumMap enumMap = new EnumMap(EnumC0221e.class);
        enumMap.put((Object) EnumC0221e.IDENTITY, (Object) new FieldMetaData("identity", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put((Object) EnumC0221e.TS, (Object) new FieldMetaData("ts", (byte) 1, new FieldValueMetaData((byte) 10)));
        enumMap.put((Object) EnumC0221e.VERSION, (Object) new FieldMetaData("version", (byte) 1, new FieldValueMetaData((byte) 8)));
        Map unmodifiableMap = Collections.unmodifiableMap(enumMap);
        f1510d = unmodifiableMap;
        FieldMetaData.m620a(IdSnapshot.class, unmodifiableMap);
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.b$e */
    /* compiled from: IdSnapshot */
    public enum EnumC0221e implements TFieldIdEnum {
        IDENTITY(1, "identity"),
        TS(2, "ts"),
        VERSION(3, "version");
        

        /* renamed from: d */
        private static final Map f1526d = new HashMap();

        /* renamed from: e */
        private final short f1528e;

        /* renamed from: f */
        private final String f1529f;

        static {
            Iterator it = EnumSet.allOf(EnumC0221e.class).iterator();
            while (it.hasNext()) {
                EnumC0221e eVar = (EnumC0221e) it.next();
                f1526d.put(eVar.mo362b(), eVar);
            }
        }

        /* renamed from: a */
        public static EnumC0221e m1632a(int i) {
            if (i == 1) {
                return IDENTITY;
            }
            if (i == 2) {
                return TS;
            }
            if (i != 3) {
                return null;
            }
            return VERSION;
        }

        /* renamed from: b */
        public static EnumC0221e m1634b(int i) {
            EnumC0221e a = m1632a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static EnumC0221e m1633a(String str) {
            return (EnumC0221e) f1526d.get(str);
        }

        private EnumC0221e(short s, String str) {
            this.f1528e = s;
            this.f1529f = str;
        }

        @Override // com.umeng.analytics.pro.TFieldIdEnum
        /* renamed from: a */
        public short mo361a() {
            return this.f1528e;
        }

        @Override // com.umeng.analytics.pro.TFieldIdEnum
        /* renamed from: b */
        public String mo362b() {
            return this.f1529f;
        }
    }

    public IdSnapshot() {
        this.f1522m = 0;
    }

    public IdSnapshot(String str, long j, int i) {
        this();
        this.f1519a = str;
        this.f1520b = j;
        mo904b(true);
        this.f1521c = i;
        mo906c(true);
    }

    public IdSnapshot(IdSnapshot bVar) {
        this.f1522m = 0;
        this.f1522m = bVar.f1522m;
        if (bVar.mo907d()) {
            this.f1519a = bVar.f1519a;
        }
        this.f1520b = bVar.f1520b;
        this.f1521c = bVar.f1521c;
    }

    /* renamed from: a */
    public IdSnapshot deepCopy() {
        return new IdSnapshot(this);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void clear() {
        this.f1519a = null;
        mo904b(false);
        this.f1520b = 0;
        mo906c(false);
        this.f1521c = 0;
    }

    /* renamed from: b */
    public String mo903b() {
        return this.f1519a;
    }

    /* renamed from: a */
    public IdSnapshot mo900a(String str) {
        this.f1519a = str;
        return this;
    }

    /* renamed from: c */
    public void mo905c() {
        this.f1519a = null;
    }

    /* renamed from: d */
    public boolean mo907d() {
        return this.f1519a != null;
    }

    /* renamed from: a */
    public void mo901a(boolean z) {
        if (!z) {
            this.f1519a = null;
        }
    }

    /* renamed from: e */
    public long mo908e() {
        return this.f1520b;
    }

    /* renamed from: a */
    public IdSnapshot mo899a(long j) {
        this.f1520b = j;
        mo904b(true);
        return this;
    }

    /* renamed from: f */
    public void mo909f() {
        this.f1522m = EncodingUtils.m530b(this.f1522m, 0);
    }

    /* renamed from: g */
    public boolean mo910g() {
        return EncodingUtils.m526a(this.f1522m, 0);
    }

    /* renamed from: b */
    public void mo904b(boolean z) {
        this.f1522m = EncodingUtils.m518a(this.f1522m, 0, z);
    }

    /* renamed from: h */
    public int mo911h() {
        return this.f1521c;
    }

    /* renamed from: a */
    public IdSnapshot mo898a(int i) {
        this.f1521c = i;
        mo906c(true);
        return this;
    }

    /* renamed from: i */
    public void mo912i() {
        this.f1522m = EncodingUtils.m530b(this.f1522m, 1);
    }

    /* renamed from: j */
    public boolean mo913j() {
        return EncodingUtils.m526a(this.f1522m, 1);
    }

    /* renamed from: c */
    public void mo906c(boolean z) {
        this.f1522m = EncodingUtils.m518a(this.f1522m, 1, z);
    }

    /* renamed from: b */
    public EnumC0221e fieldForId(int i) {
        return EnumC0221e.m1632a(i);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void read(TProtocol bpVar) {
        ((SchemeFactory) f1516j.get(bpVar.mo485D())).mo357b().mo355b(bpVar, this);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void write(TProtocol bpVar) {
        ((SchemeFactory) f1516j.get(bpVar.mo485D())).mo357b().mo353a(bpVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdSnapshot(");
        sb.append("identity:");
        String str = this.f1519a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.f1520b);
        sb.append(", ");
        sb.append("version:");
        sb.append(this.f1521c);
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: k */
    public void mo914k() {
        if (this.f1519a == null) {
            throw new TProtocolException("Required field 'identity' was not present! Struct: " + toString());
        }
    }

    /* renamed from: a */
    private void m1597a(ObjectOutputStream objectOutputStream) {
        try {
            write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
        } catch (TException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: a */
    private void m1596a(ObjectInputStream objectInputStream) {
        try {
            this.f1522m = 0;
            read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
        } catch (TException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.b$b */
    /* compiled from: IdSnapshot */
    class C0218b implements SchemeFactory {
        private C0218b() {
        }

        /* renamed from: a */
        public C0217a mo357b() {
            return new C0217a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.proto.b$a */
    /* compiled from: IdSnapshot */
    public class C0217a extends StandardScheme {
        private C0217a() {
        }

        /* renamed from: a */
        public void mo355b(TProtocol bpVar, IdSnapshot bVar) {
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
                        } else if (b == 8) {
                            bVar.f1521c = bpVar.mo472w();
                            bVar.mo906c(true);
                        } else {
                            TProtocolUtil.m785a(bpVar, b);
                        }
                    } else if (b == 10) {
                        bVar.f1520b = bpVar.mo473x();
                        bVar.mo904b(true);
                    } else {
                        TProtocolUtil.m785a(bpVar, b);
                    }
                } else if (b == 11) {
                    bVar.f1519a = bpVar.mo475z();
                    bVar.mo901a(true);
                } else {
                    TProtocolUtil.m785a(bpVar, b);
                }
                bpVar.mo462m();
            }
            bpVar.mo460k();
            if (!bVar.mo910g()) {
                throw new TProtocolException("Required field 'ts' was not found in serialized data! Struct: " + toString());
            } else if (bVar.mo913j()) {
                bVar.mo914k();
            } else {
                throw new TProtocolException("Required field 'version' was not found in serialized data! Struct: " + toString());
            }
        }

        /* renamed from: b */
        public void mo353a(TProtocol bpVar, IdSnapshot bVar) {
            bVar.mo914k();
            bpVar.mo443a(IdSnapshot.f1512f);
            if (bVar.f1519a != null) {
                bpVar.mo438a(IdSnapshot.f1513g);
                bpVar.mo444a(bVar.f1519a);
                bpVar.mo450c();
            }
            bpVar.mo438a(IdSnapshot.f1514h);
            bpVar.mo437a(bVar.f1520b);
            bpVar.mo450c();
            bpVar.mo438a(IdSnapshot.f1515i);
            bpVar.mo436a(bVar.f1521c);
            bpVar.mo450c();
            bpVar.mo452d();
            bpVar.mo449b();
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.b$d */
    /* compiled from: IdSnapshot */
    class C0220d implements SchemeFactory {
        private C0220d() {
        }

        /* renamed from: a */
        public C0219c mo357b() {
            return new C0219c();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.proto.b$c */
    /* compiled from: IdSnapshot */
    public class C0219c extends TupleScheme {
        private C0219c() {
        }

        /* renamed from: a */
        public void mo353a(TProtocol bpVar, IdSnapshot bVar) {
            TTupleProtocol bvVar = (TTupleProtocol) bpVar;
            bvVar.mo444a(bVar.f1519a);
            bvVar.mo437a(bVar.f1520b);
            bvVar.mo436a(bVar.f1521c);
        }

        /* renamed from: b */
        public void mo355b(TProtocol bpVar, IdSnapshot bVar) {
            TTupleProtocol bvVar = (TTupleProtocol) bpVar;
            bVar.f1519a = bvVar.mo475z();
            bVar.mo901a(true);
            bVar.f1520b = bvVar.mo473x();
            bVar.mo904b(true);
            bVar.f1521c = bvVar.mo472w();
            bVar.mo906c(true);
        }
    }
}
