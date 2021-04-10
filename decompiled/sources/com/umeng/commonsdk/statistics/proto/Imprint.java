package com.umeng.commonsdk.statistics.proto;

import com.umeng.analytics.pro.EncodingUtils;
import com.umeng.analytics.pro.FieldMetaData;
import com.umeng.analytics.pro.FieldValueMetaData;
import com.umeng.analytics.pro.MapMetaData;
import com.umeng.analytics.pro.SchemeFactory;
import com.umeng.analytics.pro.StandardScheme;
import com.umeng.analytics.pro.StructMetaData;
import com.umeng.analytics.pro.TBase;
import com.umeng.analytics.pro.TCompactProtocol;
import com.umeng.analytics.pro.TException;
import com.umeng.analytics.pro.TField;
import com.umeng.analytics.pro.TFieldIdEnum;
import com.umeng.analytics.pro.TIOStreamTransport;
import com.umeng.analytics.pro.TMap;
import com.umeng.analytics.pro.TProtocol;
import com.umeng.analytics.pro.TProtocolException;
import com.umeng.analytics.pro.TProtocolUtil;
import com.umeng.analytics.pro.TStruct;
import com.umeng.analytics.pro.TTupleProtocol;
import com.umeng.analytics.pro.TType;
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

/* renamed from: com.umeng.commonsdk.statistics.proto.d */
public class Imprint implements TBase, Serializable, Cloneable {

    /* renamed from: d */
    public static final Map f1548d;

    /* renamed from: e */
    private static final long f1549e = 2846460275012375038L;

    /* renamed from: f */
    private static final TStruct f1550f = new TStruct("Imprint");

    /* renamed from: g */
    private static final TField f1551g = new TField("property", TType.f575k, 1);

    /* renamed from: h */
    private static final TField f1552h = new TField("version", (byte) 8, 2);

    /* renamed from: i */
    private static final TField f1553i = new TField("checksum", (byte) 11, 3);

    /* renamed from: j */
    private static final Map f1554j;

    /* renamed from: k */
    private static final int f1555k = 0;

    /* renamed from: a */
    public Map f1556a;

    /* renamed from: b */
    public int f1557b;

    /* renamed from: c */
    public String f1558c;

    /* renamed from: l */
    private byte f1559l;

    static {
        HashMap hashMap = new HashMap();
        f1554j = hashMap;
        hashMap.put(StandardScheme.class, new C0230b());
        hashMap.put(TupleScheme.class, new C0232d());
        EnumMap enumMap = new EnumMap(EnumC0233e.class);
        enumMap.put((Object) EnumC0233e.PROPERTY, (Object) new FieldMetaData("property", (byte) 1, new MapMetaData(TType.f575k, new FieldValueMetaData((byte) 11), new StructMetaData((byte) 12, ImprintValue.class))));
        enumMap.put((Object) EnumC0233e.VERSION, (Object) new FieldMetaData("version", (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put((Object) EnumC0233e.CHECKSUM, (Object) new FieldMetaData("checksum", (byte) 1, new FieldValueMetaData((byte) 11)));
        Map unmodifiableMap = Collections.unmodifiableMap(enumMap);
        f1548d = unmodifiableMap;
        FieldMetaData.m620a(Imprint.class, unmodifiableMap);
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.d$e */
    /* compiled from: Imprint */
    public enum EnumC0233e implements TFieldIdEnum {
        PROPERTY(1, "property"),
        VERSION(2, "version"),
        CHECKSUM(3, "checksum");
        

        /* renamed from: d */
        private static final Map f1563d = new HashMap();

        /* renamed from: e */
        private final short f1565e;

        /* renamed from: f */
        private final String f1566f;

        static {
            Iterator it = EnumSet.allOf(EnumC0233e.class).iterator();
            while (it.hasNext()) {
                EnumC0233e eVar = (EnumC0233e) it.next();
                f1563d.put(eVar.mo362b(), eVar);
            }
        }

        /* renamed from: a */
        public static EnumC0233e m1721a(int i) {
            if (i == 1) {
                return PROPERTY;
            }
            if (i == 2) {
                return VERSION;
            }
            if (i != 3) {
                return null;
            }
            return CHECKSUM;
        }

        /* renamed from: b */
        public static EnumC0233e m1723b(int i) {
            EnumC0233e a = m1721a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static EnumC0233e m1722a(String str) {
            return (EnumC0233e) f1563d.get(str);
        }

        private EnumC0233e(short s, String str) {
            this.f1565e = s;
            this.f1566f = str;
        }

        @Override // com.umeng.analytics.pro.TFieldIdEnum
        /* renamed from: a */
        public short mo361a() {
            return this.f1565e;
        }

        @Override // com.umeng.analytics.pro.TFieldIdEnum
        /* renamed from: b */
        public String mo362b() {
            return this.f1566f;
        }
    }

    public Imprint() {
        this.f1559l = 0;
    }

    public Imprint(Map map, int i, String str) {
        this();
        this.f1556a = map;
        this.f1557b = i;
        mo960b(true);
        this.f1558c = str;
    }

    public Imprint(Imprint dVar) {
        this.f1559l = 0;
        this.f1559l = dVar.f1559l;
        if (dVar.mo964e()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry entry : dVar.f1556a.entrySet()) {
                hashMap.put((String) entry.getKey(), new ImprintValue((ImprintValue) entry.getValue()));
            }
            this.f1556a = hashMap;
        }
        this.f1557b = dVar.f1557b;
        if (dVar.mo970k()) {
            this.f1558c = dVar.f1558c;
        }
    }

    /* renamed from: a */
    public Imprint deepCopy() {
        return new Imprint(this);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void clear() {
        this.f1556a = null;
        mo960b(false);
        this.f1557b = 0;
        this.f1558c = null;
    }

    /* renamed from: b */
    public int mo958b() {
        Map map = this.f1556a;
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    /* renamed from: a */
    public void mo956a(String str, ImprintValue eVar) {
        if (this.f1556a == null) {
            this.f1556a = new HashMap();
        }
        this.f1556a.put(str, eVar);
    }

    /* renamed from: c */
    public Map mo961c() {
        return this.f1556a;
    }

    /* renamed from: a */
    public Imprint mo955a(Map map) {
        this.f1556a = map;
        return this;
    }

    /* renamed from: d */
    public void mo963d() {
        this.f1556a = null;
    }

    /* renamed from: e */
    public boolean mo964e() {
        return this.f1556a != null;
    }

    /* renamed from: a */
    public void mo957a(boolean z) {
        if (!z) {
            this.f1556a = null;
        }
    }

    /* renamed from: f */
    public int mo965f() {
        return this.f1557b;
    }

    /* renamed from: a */
    public Imprint mo953a(int i) {
        this.f1557b = i;
        mo960b(true);
        return this;
    }

    /* renamed from: g */
    public void mo966g() {
        this.f1559l = EncodingUtils.m530b(this.f1559l, 0);
    }

    /* renamed from: h */
    public boolean mo967h() {
        return EncodingUtils.m526a(this.f1559l, 0);
    }

    /* renamed from: b */
    public void mo960b(boolean z) {
        this.f1559l = EncodingUtils.m518a(this.f1559l, 0, z);
    }

    /* renamed from: i */
    public String mo968i() {
        return this.f1558c;
    }

    /* renamed from: a */
    public Imprint mo954a(String str) {
        this.f1558c = str;
        return this;
    }

    /* renamed from: j */
    public void mo969j() {
        this.f1558c = null;
    }

    /* renamed from: k */
    public boolean mo970k() {
        return this.f1558c != null;
    }

    /* renamed from: c */
    public void mo962c(boolean z) {
        if (!z) {
            this.f1558c = null;
        }
    }

    /* renamed from: b */
    public EnumC0233e fieldForId(int i) {
        return EnumC0233e.m1721a(i);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void read(TProtocol bpVar) {
        ((SchemeFactory) f1554j.get(bpVar.mo485D())).mo357b().mo355b(bpVar, this);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void write(TProtocol bpVar) {
        ((SchemeFactory) f1554j.get(bpVar.mo485D())).mo357b().mo353a(bpVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Imprint(");
        sb.append("property:");
        Map map = this.f1556a;
        if (map == null) {
            sb.append("null");
        } else {
            sb.append(map);
        }
        sb.append(", ");
        sb.append("version:");
        sb.append(this.f1557b);
        sb.append(", ");
        sb.append("checksum:");
        String str = this.f1558c;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: l */
    public void mo971l() {
        if (this.f1556a == null) {
            throw new TProtocolException("Required field 'property' was not present! Struct: " + toString());
        } else if (this.f1558c == null) {
            throw new TProtocolException("Required field 'checksum' was not present! Struct: " + toString());
        }
    }

    /* renamed from: a */
    private void m1684a(ObjectOutputStream objectOutputStream) {
        try {
            write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
        } catch (TException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: a */
    private void m1683a(ObjectInputStream objectInputStream) {
        try {
            this.f1559l = 0;
            read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
        } catch (TException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.d$b */
    /* compiled from: Imprint */
    class C0230b implements SchemeFactory {
        private C0230b() {
        }

        /* renamed from: a */
        public C0229a mo357b() {
            return new C0229a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.proto.d$a */
    /* compiled from: Imprint */
    public class C0229a extends StandardScheme {
        private C0229a() {
        }

        /* renamed from: a */
        public void mo355b(TProtocol bpVar, Imprint dVar) {
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
                            dVar.f1558c = bpVar.mo475z();
                            dVar.mo962c(true);
                        } else {
                            TProtocolUtil.m785a(bpVar, b);
                        }
                    } else if (b == 8) {
                        dVar.f1557b = bpVar.mo472w();
                        dVar.mo960b(true);
                    } else {
                        TProtocolUtil.m785a(bpVar, b);
                    }
                } else if (b == 13) {
                    TMap n = bpVar.mo463n();
                    dVar.f1556a = new HashMap(n.f544c * 2);
                    for (int i = 0; i < n.f544c; i++) {
                        String z = bpVar.mo475z();
                        ImprintValue eVar = new ImprintValue();
                        eVar.read(bpVar);
                        dVar.f1556a.put(z, eVar);
                    }
                    bpVar.mo464o();
                    dVar.mo957a(true);
                } else {
                    TProtocolUtil.m785a(bpVar, b);
                }
                bpVar.mo462m();
            }
            bpVar.mo460k();
            if (dVar.mo967h()) {
                dVar.mo971l();
                return;
            }
            throw new TProtocolException("Required field 'version' was not found in serialized data! Struct: " + toString());
        }

        /* renamed from: b */
        public void mo353a(TProtocol bpVar, Imprint dVar) {
            dVar.mo971l();
            bpVar.mo443a(Imprint.f1550f);
            if (dVar.f1556a != null) {
                bpVar.mo438a(Imprint.f1551g);
                bpVar.mo440a(new TMap((byte) 11, (byte) 12, dVar.f1556a.size()));
                for (Map.Entry entry : dVar.f1556a.entrySet()) {
                    bpVar.mo444a((String) entry.getKey());
                    ((ImprintValue) entry.getValue()).write(bpVar);
                }
                bpVar.mo454e();
                bpVar.mo450c();
            }
            bpVar.mo438a(Imprint.f1552h);
            bpVar.mo436a(dVar.f1557b);
            bpVar.mo450c();
            if (dVar.f1558c != null) {
                bpVar.mo438a(Imprint.f1553i);
                bpVar.mo444a(dVar.f1558c);
                bpVar.mo450c();
            }
            bpVar.mo452d();
            bpVar.mo449b();
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.d$d */
    /* compiled from: Imprint */
    class C0232d implements SchemeFactory {
        private C0232d() {
        }

        /* renamed from: a */
        public C0231c mo357b() {
            return new C0231c();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.proto.d$c */
    /* compiled from: Imprint */
    public class C0231c extends TupleScheme {
        private C0231c() {
        }

        /* renamed from: a */
        public void mo353a(TProtocol bpVar, Imprint dVar) {
            TTupleProtocol bvVar = (TTupleProtocol) bpVar;
            bvVar.mo436a(dVar.f1556a.size());
            for (Map.Entry entry : dVar.f1556a.entrySet()) {
                bvVar.mo444a((String) entry.getKey());
                ((ImprintValue) entry.getValue()).write(bvVar);
            }
            bvVar.mo436a(dVar.f1557b);
            bvVar.mo444a(dVar.f1558c);
        }

        /* renamed from: b */
        public void mo355b(TProtocol bpVar, Imprint dVar) {
            TTupleProtocol bvVar = (TTupleProtocol) bpVar;
            TMap bmVar = new TMap((byte) 11, (byte) 12, bvVar.mo472w());
            dVar.f1556a = new HashMap(bmVar.f544c * 2);
            for (int i = 0; i < bmVar.f544c; i++) {
                String z = bvVar.mo475z();
                ImprintValue eVar = new ImprintValue();
                eVar.read(bvVar);
                dVar.f1556a.put(z, eVar);
            }
            dVar.mo957a(true);
            dVar.f1557b = bvVar.mo472w();
            dVar.mo960b(true);
            dVar.f1558c = bvVar.mo475z();
            dVar.mo962c(true);
        }
    }
}
