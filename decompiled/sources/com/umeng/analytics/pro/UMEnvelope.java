package com.umeng.analytics.pro;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.umeng.analytics.pro.aj */
public class UMEnvelope implements TBase, Serializable, Cloneable {

    /* renamed from: A */
    private static final int f378A = 2;

    /* renamed from: B */
    private static final int f379B = 3;

    /* renamed from: k */
    public static final Map f380k;

    /* renamed from: l */
    private static final long f381l = 420342210744516016L;

    /* renamed from: m */
    private static final TStruct f382m = new TStruct("UMEnvelope");

    /* renamed from: n */
    private static final TField f383n = new TField("version", (byte) 11, 1);

    /* renamed from: o */
    private static final TField f384o = new TField("address", (byte) 11, 2);

    /* renamed from: p */
    private static final TField f385p = new TField("signature", (byte) 11, 3);

    /* renamed from: q */
    private static final TField f386q = new TField("serial_num", (byte) 8, 4);

    /* renamed from: r */
    private static final TField f387r = new TField("ts_secs", (byte) 8, 5);

    /* renamed from: s */
    private static final TField f388s = new TField("length", (byte) 8, 6);

    /* renamed from: t */
    private static final TField f389t = new TField("entity", (byte) 11, 7);

    /* renamed from: u */
    private static final TField f390u = new TField("guid", (byte) 11, 8);

    /* renamed from: v */
    private static final TField f391v = new TField("checksum", (byte) 11, 9);

    /* renamed from: w */
    private static final TField f392w = new TField("codex", (byte) 8, 10);

    /* renamed from: x */
    private static final Map f393x;

    /* renamed from: y */
    private static final int f394y = 0;

    /* renamed from: z */
    private static final int f395z = 1;

    /* renamed from: C */
    private byte f396C;

    /* renamed from: D */
    private EnumC0077e[] f397D;

    /* renamed from: a */
    public String f398a;

    /* renamed from: b */
    public String f399b;

    /* renamed from: c */
    public String f400c;

    /* renamed from: d */
    public int f401d;

    /* renamed from: e */
    public int f402e;

    /* renamed from: f */
    public int f403f;

    /* renamed from: g */
    public ByteBuffer f404g;

    /* renamed from: h */
    public String f405h;

    /* renamed from: i */
    public String f406i;

    /* renamed from: j */
    public int f407j;

    static {
        HashMap hashMap = new HashMap();
        f393x = hashMap;
        hashMap.put(StandardScheme.class, new C0074b());
        hashMap.put(TupleScheme.class, new C0076d());
        EnumMap enumMap = new EnumMap(EnumC0077e.class);
        enumMap.put((Object) EnumC0077e.VERSION, (Object) new FieldMetaData("version", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put((Object) EnumC0077e.ADDRESS, (Object) new FieldMetaData("address", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put((Object) EnumC0077e.SIGNATURE, (Object) new FieldMetaData("signature", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put((Object) EnumC0077e.SERIAL_NUM, (Object) new FieldMetaData("serial_num", (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put((Object) EnumC0077e.TS_SECS, (Object) new FieldMetaData("ts_secs", (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put((Object) EnumC0077e.LENGTH, (Object) new FieldMetaData("length", (byte) 1, new FieldValueMetaData((byte) 8)));
        enumMap.put((Object) EnumC0077e.ENTITY, (Object) new FieldMetaData("entity", (byte) 1, new FieldValueMetaData((byte) 11, true)));
        enumMap.put((Object) EnumC0077e.GUID, (Object) new FieldMetaData("guid", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put((Object) EnumC0077e.CHECKSUM, (Object) new FieldMetaData("checksum", (byte) 1, new FieldValueMetaData((byte) 11)));
        enumMap.put((Object) EnumC0077e.CODEX, (Object) new FieldMetaData("codex", (byte) 2, new FieldValueMetaData((byte) 8)));
        Map unmodifiableMap = Collections.unmodifiableMap(enumMap);
        f380k = unmodifiableMap;
        FieldMetaData.m620a(UMEnvelope.class, unmodifiableMap);
    }

    /* renamed from: com.umeng.analytics.pro.aj$e */
    /* compiled from: UMEnvelope */
    public enum EnumC0077e implements TFieldIdEnum {
        VERSION(1, "version"),
        ADDRESS(2, "address"),
        SIGNATURE(3, "signature"),
        SERIAL_NUM(4, "serial_num"),
        TS_SECS(5, "ts_secs"),
        LENGTH(6, "length"),
        ENTITY(7, "entity"),
        GUID(8, "guid"),
        CHECKSUM(9, "checksum"),
        CODEX(10, "codex");
        

        /* renamed from: k */
        private static final Map f418k = new HashMap();

        /* renamed from: l */
        private final short f420l;

        /* renamed from: m */
        private final String f421m;

        static {
            Iterator it = EnumSet.allOf(EnumC0077e.class).iterator();
            while (it.hasNext()) {
                EnumC0077e eVar = (EnumC0077e) it.next();
                f418k.put(eVar.mo362b(), eVar);
            }
        }

        /* renamed from: a */
        public static EnumC0077e m500a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case TApplicationException.f455h /*{ENCODED_INT: 7}*/:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static EnumC0077e m502b(int i) {
            EnumC0077e a = m500a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static EnumC0077e m501a(String str) {
            return (EnumC0077e) f418k.get(str);
        }

        private EnumC0077e(short s, String str) {
            this.f420l = s;
            this.f421m = str;
        }

        @Override // com.umeng.analytics.pro.TFieldIdEnum
        /* renamed from: a */
        public short mo361a() {
            return this.f420l;
        }

        @Override // com.umeng.analytics.pro.TFieldIdEnum
        /* renamed from: b */
        public String mo362b() {
            return this.f421m;
        }
    }

    public UMEnvelope() {
        this.f396C = 0;
        this.f397D = new EnumC0077e[]{EnumC0077e.CODEX};
    }

    public UMEnvelope(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.f398a = str;
        this.f399b = str2;
        this.f400c = str3;
        this.f401d = i;
        mo315d(true);
        this.f402e = i2;
        mo321e(true);
        this.f403f = i3;
        mo323f(true);
        this.f404g = byteBuffer;
        this.f405h = str4;
        this.f406i = str5;
    }

    public UMEnvelope(UMEnvelope ajVar) {
        this.f396C = 0;
        this.f397D = new EnumC0077e[]{EnumC0077e.CODEX};
        this.f396C = ajVar.f396C;
        if (ajVar.mo316d()) {
            this.f398a = ajVar.f398a;
        }
        if (ajVar.mo326g()) {
            this.f399b = ajVar.f399b;
        }
        if (ajVar.mo332j()) {
            this.f400c = ajVar.f400c;
        }
        this.f401d = ajVar.f401d;
        this.f402e = ajVar.f402e;
        this.f403f = ajVar.f403f;
        if (ajVar.mo347w()) {
            this.f404g = TBaseHelper.m562d(ajVar.f404g);
        }
        if (ajVar.mo351z()) {
            this.f405h = ajVar.f405h;
        }
        if (ajVar.mo293C()) {
            this.f406i = ajVar.f406i;
        }
        this.f407j = ajVar.f407j;
    }

    /* renamed from: a */
    public UMEnvelope deepCopy() {
        return new UMEnvelope(this);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void clear() {
        this.f398a = null;
        this.f399b = null;
        this.f400c = null;
        mo315d(false);
        this.f401d = 0;
        mo321e(false);
        this.f402e = 0;
        mo323f(false);
        this.f403f = 0;
        this.f404g = null;
        this.f405h = null;
        this.f406i = null;
        mo331j(false);
        this.f407j = 0;
    }

    /* renamed from: b */
    public String mo306b() {
        return this.f398a;
    }

    /* renamed from: a */
    public UMEnvelope mo300a(String str) {
        this.f398a = str;
        return this;
    }

    /* renamed from: c */
    public void mo310c() {
        this.f398a = null;
    }

    /* renamed from: d */
    public boolean mo316d() {
        return this.f398a != null;
    }

    /* renamed from: a */
    public void mo303a(boolean z) {
        if (!z) {
            this.f398a = null;
        }
    }

    /* renamed from: e */
    public String mo320e() {
        return this.f399b;
    }

    /* renamed from: b */
    public UMEnvelope mo305b(String str) {
        this.f399b = str;
        return this;
    }

    /* renamed from: f */
    public void mo322f() {
        this.f399b = null;
    }

    /* renamed from: g */
    public boolean mo326g() {
        return this.f399b != null;
    }

    /* renamed from: b */
    public void mo307b(boolean z) {
        if (!z) {
            this.f399b = null;
        }
    }

    /* renamed from: h */
    public String mo327h() {
        return this.f400c;
    }

    /* renamed from: c */
    public UMEnvelope mo309c(String str) {
        this.f400c = str;
        return this;
    }

    /* renamed from: i */
    public void mo329i() {
        this.f400c = null;
    }

    /* renamed from: j */
    public boolean mo332j() {
        return this.f400c != null;
    }

    /* renamed from: c */
    public void mo311c(boolean z) {
        if (!z) {
            this.f400c = null;
        }
    }

    /* renamed from: k */
    public int mo333k() {
        return this.f401d;
    }

    /* renamed from: a */
    public UMEnvelope mo299a(int i) {
        this.f401d = i;
        mo315d(true);
        return this;
    }

    /* renamed from: l */
    public void mo334l() {
        this.f396C = EncodingUtils.m530b(this.f396C, 0);
    }

    /* renamed from: m */
    public boolean mo335m() {
        return EncodingUtils.m526a(this.f396C, 0);
    }

    /* renamed from: d */
    public void mo315d(boolean z) {
        this.f396C = EncodingUtils.m518a(this.f396C, 0, z);
    }

    /* renamed from: n */
    public int mo336n() {
        return this.f402e;
    }

    /* renamed from: b */
    public UMEnvelope mo304b(int i) {
        this.f402e = i;
        mo321e(true);
        return this;
    }

    /* renamed from: o */
    public void mo337o() {
        this.f396C = EncodingUtils.m530b(this.f396C, 1);
    }

    /* renamed from: p */
    public boolean mo338p() {
        return EncodingUtils.m526a(this.f396C, 1);
    }

    /* renamed from: e */
    public void mo321e(boolean z) {
        this.f396C = EncodingUtils.m518a(this.f396C, 1, z);
    }

    /* renamed from: q */
    public int mo339q() {
        return this.f403f;
    }

    /* renamed from: c */
    public UMEnvelope mo308c(int i) {
        this.f403f = i;
        mo323f(true);
        return this;
    }

    /* renamed from: r */
    public void mo340r() {
        this.f396C = EncodingUtils.m530b(this.f396C, 2);
    }

    /* renamed from: s */
    public boolean mo342s() {
        return EncodingUtils.m526a(this.f396C, 2);
    }

    /* renamed from: f */
    public void mo323f(boolean z) {
        this.f396C = EncodingUtils.m518a(this.f396C, 2, z);
    }

    /* renamed from: t */
    public byte[] mo343t() {
        mo301a(TBaseHelper.m561c(this.f404g));
        ByteBuffer byteBuffer = this.f404g;
        if (byteBuffer == null) {
            return null;
        }
        return byteBuffer.array();
    }

    /* renamed from: u */
    public ByteBuffer mo345u() {
        return this.f404g;
    }

    /* renamed from: a */
    public UMEnvelope mo302a(byte[] bArr) {
        mo301a(bArr == null ? null : ByteBuffer.wrap(bArr));
        return this;
    }

    /* renamed from: a */
    public UMEnvelope mo301a(ByteBuffer byteBuffer) {
        this.f404g = byteBuffer;
        return this;
    }

    /* renamed from: v */
    public void mo346v() {
        this.f404g = null;
    }

    /* renamed from: w */
    public boolean mo347w() {
        return this.f404g != null;
    }

    /* renamed from: g */
    public void mo325g(boolean z) {
        if (!z) {
            this.f404g = null;
        }
    }

    /* renamed from: x */
    public String mo349x() {
        return this.f405h;
    }

    /* renamed from: d */
    public UMEnvelope mo314d(String str) {
        this.f405h = str;
        return this;
    }

    /* renamed from: y */
    public void mo350y() {
        this.f405h = null;
    }

    /* renamed from: z */
    public boolean mo351z() {
        return this.f405h != null;
    }

    /* renamed from: h */
    public void mo328h(boolean z) {
        if (!z) {
            this.f405h = null;
        }
    }

    /* renamed from: A */
    public String mo291A() {
        return this.f406i;
    }

    /* renamed from: e */
    public UMEnvelope mo319e(String str) {
        this.f406i = str;
        return this;
    }

    /* renamed from: B */
    public void mo292B() {
        this.f406i = null;
    }

    /* renamed from: C */
    public boolean mo293C() {
        return this.f406i != null;
    }

    /* renamed from: i */
    public void mo330i(boolean z) {
        if (!z) {
            this.f406i = null;
        }
    }

    /* renamed from: D */
    public int mo294D() {
        return this.f407j;
    }

    /* renamed from: d */
    public UMEnvelope mo313d(int i) {
        this.f407j = i;
        mo331j(true);
        return this;
    }

    /* renamed from: E */
    public void mo295E() {
        this.f396C = EncodingUtils.m530b(this.f396C, 3);
    }

    /* renamed from: F */
    public boolean mo296F() {
        return EncodingUtils.m526a(this.f396C, 3);
    }

    /* renamed from: j */
    public void mo331j(boolean z) {
        this.f396C = EncodingUtils.m518a(this.f396C, 3, z);
    }

    /* renamed from: e */
    public EnumC0077e fieldForId(int i) {
        return EnumC0077e.m500a(i);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void read(TProtocol bpVar) {
        ((SchemeFactory) f393x.get(bpVar.mo485D())).mo357b().mo355b(bpVar, this);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void write(TProtocol bpVar) {
        ((SchemeFactory) f393x.get(bpVar.mo485D())).mo357b().mo353a(bpVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UMEnvelope(");
        sb.append("version:");
        String str = this.f398a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("address:");
        String str2 = this.f399b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("signature:");
        String str3 = this.f400c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("serial_num:");
        sb.append(this.f401d);
        sb.append(", ");
        sb.append("ts_secs:");
        sb.append(this.f402e);
        sb.append(", ");
        sb.append("length:");
        sb.append(this.f403f);
        sb.append(", ");
        sb.append("entity:");
        ByteBuffer byteBuffer = this.f404g;
        if (byteBuffer == null) {
            sb.append("null");
        } else {
            TBaseHelper.m557a(byteBuffer, sb);
        }
        sb.append(", ");
        sb.append("guid:");
        String str4 = this.f405h;
        if (str4 == null) {
            sb.append("null");
        } else {
            sb.append(str4);
        }
        sb.append(", ");
        sb.append("checksum:");
        String str5 = this.f406i;
        if (str5 == null) {
            sb.append("null");
        } else {
            sb.append(str5);
        }
        if (mo296F()) {
            sb.append(", ");
            sb.append("codex:");
            sb.append(this.f407j);
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: G */
    public void mo297G() {
        if (this.f398a == null) {
            throw new TProtocolException("Required field 'version' was not present! Struct: " + toString());
        } else if (this.f399b == null) {
            throw new TProtocolException("Required field 'address' was not present! Struct: " + toString());
        } else if (this.f400c == null) {
            throw new TProtocolException("Required field 'signature' was not present! Struct: " + toString());
        } else if (this.f404g == null) {
            throw new TProtocolException("Required field 'entity' was not present! Struct: " + toString());
        } else if (this.f405h == null) {
            throw new TProtocolException("Required field 'guid' was not present! Struct: " + toString());
        } else if (this.f406i == null) {
            throw new TProtocolException("Required field 'checksum' was not present! Struct: " + toString());
        }
    }

    /* renamed from: a */
    private void m432a(ObjectOutputStream objectOutputStream) {
        try {
            write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
        } catch (TException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: a */
    private void m431a(ObjectInputStream objectInputStream) {
        try {
            this.f396C = 0;
            read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
        } catch (TException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: com.umeng.analytics.pro.aj$b */
    /* compiled from: UMEnvelope */
    class C0074b implements SchemeFactory {
        private C0074b() {
        }

        /* renamed from: a */
        public C0073a mo357b() {
            return new C0073a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.pro.aj$a */
    /* compiled from: UMEnvelope */
    public class C0073a extends StandardScheme {
        private C0073a() {
        }

        /* renamed from: a */
        public void mo355b(TProtocol bpVar, UMEnvelope ajVar) {
            bpVar.mo459j();
            while (true) {
                TField l = bpVar.mo461l();
                byte b = l.f538b;
                if (b == 0) {
                    bpVar.mo460k();
                    if (!ajVar.mo335m()) {
                        throw new TProtocolException("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    } else if (!ajVar.mo338p()) {
                        throw new TProtocolException("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    } else if (ajVar.mo342s()) {
                        ajVar.mo297G();
                        return;
                    } else {
                        throw new TProtocolException("Required field 'length' was not found in serialized data! Struct: " + toString());
                    }
                } else {
                    switch (l.f539c) {
                        case 1:
                            if (b != 11) {
                                TProtocolUtil.m785a(bpVar, b);
                                break;
                            } else {
                                ajVar.f398a = bpVar.mo475z();
                                ajVar.mo303a(true);
                                break;
                            }
                        case 2:
                            if (b != 11) {
                                TProtocolUtil.m785a(bpVar, b);
                                break;
                            } else {
                                ajVar.f399b = bpVar.mo475z();
                                ajVar.mo307b(true);
                                break;
                            }
                        case 3:
                            if (b != 11) {
                                TProtocolUtil.m785a(bpVar, b);
                                break;
                            } else {
                                ajVar.f400c = bpVar.mo475z();
                                ajVar.mo311c(true);
                                break;
                            }
                        case 4:
                            if (b != 8) {
                                TProtocolUtil.m785a(bpVar, b);
                                break;
                            } else {
                                ajVar.f401d = bpVar.mo472w();
                                ajVar.mo315d(true);
                                break;
                            }
                        case 5:
                            if (b != 8) {
                                TProtocolUtil.m785a(bpVar, b);
                                break;
                            } else {
                                ajVar.f402e = bpVar.mo472w();
                                ajVar.mo321e(true);
                                break;
                            }
                        case 6:
                            if (b != 8) {
                                TProtocolUtil.m785a(bpVar, b);
                                break;
                            } else {
                                ajVar.f403f = bpVar.mo472w();
                                ajVar.mo323f(true);
                                break;
                            }
                        case TApplicationException.f455h /*{ENCODED_INT: 7}*/:
                            if (b != 11) {
                                TProtocolUtil.m785a(bpVar, b);
                                break;
                            } else {
                                ajVar.f404g = bpVar.mo432A();
                                ajVar.mo325g(true);
                                break;
                            }
                        case 8:
                            if (b != 11) {
                                TProtocolUtil.m785a(bpVar, b);
                                break;
                            } else {
                                ajVar.f405h = bpVar.mo475z();
                                ajVar.mo328h(true);
                                break;
                            }
                        case 9:
                            if (b != 11) {
                                TProtocolUtil.m785a(bpVar, b);
                                break;
                            } else {
                                ajVar.f406i = bpVar.mo475z();
                                ajVar.mo330i(true);
                                break;
                            }
                        case 10:
                            if (b != 8) {
                                TProtocolUtil.m785a(bpVar, b);
                                break;
                            } else {
                                ajVar.f407j = bpVar.mo472w();
                                ajVar.mo331j(true);
                                break;
                            }
                        default:
                            TProtocolUtil.m785a(bpVar, b);
                            break;
                    }
                    bpVar.mo462m();
                }
            }
        }

        /* renamed from: b */
        public void mo353a(TProtocol bpVar, UMEnvelope ajVar) {
            ajVar.mo297G();
            bpVar.mo443a(UMEnvelope.f382m);
            if (ajVar.f398a != null) {
                bpVar.mo438a(UMEnvelope.f383n);
                bpVar.mo444a(ajVar.f398a);
                bpVar.mo450c();
            }
            if (ajVar.f399b != null) {
                bpVar.mo438a(UMEnvelope.f384o);
                bpVar.mo444a(ajVar.f399b);
                bpVar.mo450c();
            }
            if (ajVar.f400c != null) {
                bpVar.mo438a(UMEnvelope.f385p);
                bpVar.mo444a(ajVar.f400c);
                bpVar.mo450c();
            }
            bpVar.mo438a(UMEnvelope.f386q);
            bpVar.mo436a(ajVar.f401d);
            bpVar.mo450c();
            bpVar.mo438a(UMEnvelope.f387r);
            bpVar.mo436a(ajVar.f402e);
            bpVar.mo450c();
            bpVar.mo438a(UMEnvelope.f388s);
            bpVar.mo436a(ajVar.f403f);
            bpVar.mo450c();
            if (ajVar.f404g != null) {
                bpVar.mo438a(UMEnvelope.f389t);
                bpVar.mo445a(ajVar.f404g);
                bpVar.mo450c();
            }
            if (ajVar.f405h != null) {
                bpVar.mo438a(UMEnvelope.f390u);
                bpVar.mo444a(ajVar.f405h);
                bpVar.mo450c();
            }
            if (ajVar.f406i != null) {
                bpVar.mo438a(UMEnvelope.f391v);
                bpVar.mo444a(ajVar.f406i);
                bpVar.mo450c();
            }
            if (ajVar.mo296F()) {
                bpVar.mo438a(UMEnvelope.f392w);
                bpVar.mo436a(ajVar.f407j);
                bpVar.mo450c();
            }
            bpVar.mo452d();
            bpVar.mo449b();
        }
    }

    /* renamed from: com.umeng.analytics.pro.aj$d */
    /* compiled from: UMEnvelope */
    class C0076d implements SchemeFactory {
        private C0076d() {
        }

        /* renamed from: a */
        public C0075c mo357b() {
            return new C0075c();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.pro.aj$c */
    /* compiled from: UMEnvelope */
    public class C0075c extends TupleScheme {
        private C0075c() {
        }

        /* renamed from: a */
        public void mo353a(TProtocol bpVar, UMEnvelope ajVar) {
            TTupleProtocol bvVar = (TTupleProtocol) bpVar;
            bvVar.mo444a(ajVar.f398a);
            bvVar.mo444a(ajVar.f399b);
            bvVar.mo444a(ajVar.f400c);
            bvVar.mo436a(ajVar.f401d);
            bvVar.mo436a(ajVar.f402e);
            bvVar.mo436a(ajVar.f403f);
            bvVar.mo445a(ajVar.f404g);
            bvVar.mo444a(ajVar.f405h);
            bvVar.mo444a(ajVar.f406i);
            BitSet bitSet = new BitSet();
            if (ajVar.mo296F()) {
                bitSet.set(0);
            }
            bvVar.mo487a(bitSet, 1);
            if (ajVar.mo296F()) {
                bvVar.mo436a(ajVar.f407j);
            }
        }

        /* renamed from: b */
        public void mo355b(TProtocol bpVar, UMEnvelope ajVar) {
            TTupleProtocol bvVar = (TTupleProtocol) bpVar;
            ajVar.f398a = bvVar.mo475z();
            ajVar.mo303a(true);
            ajVar.f399b = bvVar.mo475z();
            ajVar.mo307b(true);
            ajVar.f400c = bvVar.mo475z();
            ajVar.mo311c(true);
            ajVar.f401d = bvVar.mo472w();
            ajVar.mo315d(true);
            ajVar.f402e = bvVar.mo472w();
            ajVar.mo321e(true);
            ajVar.f403f = bvVar.mo472w();
            ajVar.mo323f(true);
            ajVar.f404g = bvVar.mo432A();
            ajVar.mo325g(true);
            ajVar.f405h = bvVar.mo475z();
            ajVar.mo328h(true);
            ajVar.f406i = bvVar.mo475z();
            ajVar.mo330i(true);
            if (bvVar.mo488b(1).get(0)) {
                ajVar.f407j = bvVar.mo472w();
                ajVar.mo331j(true);
            }
        }
    }
}
