package com.umeng.commonsdk.statistics.proto;

import com.umeng.analytics.pro.FieldMetaData;
import com.umeng.analytics.pro.FieldValueMetaData;
import com.umeng.analytics.pro.ListMetaData;
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
import com.umeng.analytics.pro.TList;
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
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.umeng.commonsdk.statistics.proto.c */
public class IdTracking implements TBase, Serializable, Cloneable {

    /* renamed from: d */
    public static final Map f1530d;

    /* renamed from: e */
    private static final long f1531e = -5764118265293965743L;

    /* renamed from: f */
    private static final TStruct f1532f = new TStruct("IdTracking");

    /* renamed from: g */
    private static final TField f1533g = new TField("snapshots", TType.f575k, 1);

    /* renamed from: h */
    private static final TField f1534h = new TField("journals", TType.f577m, 2);

    /* renamed from: i */
    private static final TField f1535i = new TField("checksum", (byte) 11, 3);

    /* renamed from: j */
    private static final Map f1536j;

    /* renamed from: a */
    public Map f1537a;

    /* renamed from: b */
    public List f1538b;

    /* renamed from: c */
    public String f1539c;

    /* renamed from: k */
    private EnumC0227e[] f1540k;

    static {
        HashMap hashMap = new HashMap();
        f1536j = hashMap;
        hashMap.put(StandardScheme.class, new C0224b());
        hashMap.put(TupleScheme.class, new C0226d());
        EnumMap enumMap = new EnumMap(EnumC0227e.class);
        enumMap.put((Object) EnumC0227e.SNAPSHOTS, (Object) new FieldMetaData("snapshots", (byte) 1, new MapMetaData(TType.f575k, new FieldValueMetaData((byte) 11), new StructMetaData((byte) 12, IdSnapshot.class))));
        enumMap.put((Object) EnumC0227e.JOURNALS, (Object) new FieldMetaData("journals", (byte) 2, new ListMetaData(TType.f577m, new StructMetaData((byte) 12, IdJournal.class))));
        enumMap.put((Object) EnumC0227e.CHECKSUM, (Object) new FieldMetaData("checksum", (byte) 2, new FieldValueMetaData((byte) 11)));
        Map unmodifiableMap = Collections.unmodifiableMap(enumMap);
        f1530d = unmodifiableMap;
        FieldMetaData.m620a(IdTracking.class, unmodifiableMap);
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.c$e */
    /* compiled from: IdTracking */
    public enum EnumC0227e implements TFieldIdEnum {
        SNAPSHOTS(1, "snapshots"),
        JOURNALS(2, "journals"),
        CHECKSUM(3, "checksum");
        

        /* renamed from: d */
        private static final Map f1544d = new HashMap();

        /* renamed from: e */
        private final short f1546e;

        /* renamed from: f */
        private final String f1547f;

        static {
            Iterator it = EnumSet.allOf(EnumC0227e.class).iterator();
            while (it.hasNext()) {
                EnumC0227e eVar = (EnumC0227e) it.next();
                f1544d.put(eVar.mo362b(), eVar);
            }
        }

        /* renamed from: a */
        public static EnumC0227e m1678a(int i) {
            if (i == 1) {
                return SNAPSHOTS;
            }
            if (i == 2) {
                return JOURNALS;
            }
            if (i != 3) {
                return null;
            }
            return CHECKSUM;
        }

        /* renamed from: b */
        public static EnumC0227e m1680b(int i) {
            EnumC0227e a = m1678a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static EnumC0227e m1679a(String str) {
            return (EnumC0227e) f1544d.get(str);
        }

        private EnumC0227e(short s, String str) {
            this.f1546e = s;
            this.f1547f = str;
        }

        @Override // com.umeng.analytics.pro.TFieldIdEnum
        /* renamed from: a */
        public short mo361a() {
            return this.f1546e;
        }

        @Override // com.umeng.analytics.pro.TFieldIdEnum
        /* renamed from: b */
        public String mo362b() {
            return this.f1547f;
        }
    }

    public IdTracking() {
        this.f1540k = new EnumC0227e[]{EnumC0227e.JOURNALS, EnumC0227e.CHECKSUM};
    }

    public IdTracking(Map map) {
        this();
        this.f1537a = map;
    }

    public IdTracking(IdTracking cVar) {
        this.f1540k = new EnumC0227e[]{EnumC0227e.JOURNALS, EnumC0227e.CHECKSUM};
        if (cVar.mo935e()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry entry : cVar.f1537a.entrySet()) {
                hashMap.put((String) entry.getKey(), new IdSnapshot((IdSnapshot) entry.getValue()));
            }
            this.f1537a = hashMap;
        }
        if (cVar.mo940j()) {
            ArrayList arrayList = new ArrayList();
            for (IdJournal aVar : cVar.f1538b) {
                arrayList.add(new IdJournal(aVar));
            }
            this.f1538b = arrayList;
        }
        if (cVar.mo943m()) {
            this.f1539c = cVar.f1539c;
        }
    }

    /* renamed from: a */
    public IdTracking deepCopy() {
        return new IdTracking(this);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void clear() {
        this.f1537a = null;
        this.f1538b = null;
        this.f1539c = null;
    }

    /* renamed from: b */
    public int mo930b() {
        Map map = this.f1537a;
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    /* renamed from: a */
    public void mo928a(String str, IdSnapshot bVar) {
        if (this.f1537a == null) {
            this.f1537a = new HashMap();
        }
        this.f1537a.put(str, bVar);
    }

    /* renamed from: c */
    public Map mo932c() {
        return this.f1537a;
    }

    /* renamed from: a */
    public IdTracking mo926a(Map map) {
        this.f1537a = map;
        return this;
    }

    /* renamed from: d */
    public void mo934d() {
        this.f1537a = null;
    }

    /* renamed from: e */
    public boolean mo935e() {
        return this.f1537a != null;
    }

    /* renamed from: a */
    public void mo929a(boolean z) {
        if (!z) {
            this.f1537a = null;
        }
    }

    /* renamed from: f */
    public int mo936f() {
        List list = this.f1538b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* renamed from: g */
    public Iterator mo937g() {
        List list = this.f1538b;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    /* renamed from: a */
    public void mo927a(IdJournal aVar) {
        if (this.f1538b == null) {
            this.f1538b = new ArrayList();
        }
        this.f1538b.add(aVar);
    }

    /* renamed from: h */
    public List mo938h() {
        return this.f1538b;
    }

    /* renamed from: a */
    public IdTracking mo925a(List list) {
        this.f1538b = list;
        return this;
    }

    /* renamed from: i */
    public void mo939i() {
        this.f1538b = null;
    }

    /* renamed from: j */
    public boolean mo940j() {
        return this.f1538b != null;
    }

    /* renamed from: b */
    public void mo931b(boolean z) {
        if (!z) {
            this.f1538b = null;
        }
    }

    /* renamed from: k */
    public String mo941k() {
        return this.f1539c;
    }

    /* renamed from: a */
    public IdTracking mo924a(String str) {
        this.f1539c = str;
        return this;
    }

    /* renamed from: l */
    public void mo942l() {
        this.f1539c = null;
    }

    /* renamed from: m */
    public boolean mo943m() {
        return this.f1539c != null;
    }

    /* renamed from: c */
    public void mo933c(boolean z) {
        if (!z) {
            this.f1539c = null;
        }
    }

    /* renamed from: a */
    public EnumC0227e fieldForId(int i) {
        return EnumC0227e.m1678a(i);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void read(TProtocol bpVar) {
        ((SchemeFactory) f1536j.get(bpVar.mo485D())).mo357b().mo355b(bpVar, this);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void write(TProtocol bpVar) {
        ((SchemeFactory) f1536j.get(bpVar.mo485D())).mo357b().mo353a(bpVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdTracking(");
        sb.append("snapshots:");
        Map map = this.f1537a;
        if (map == null) {
            sb.append("null");
        } else {
            sb.append(map);
        }
        if (mo940j()) {
            sb.append(", ");
            sb.append("journals:");
            List list = this.f1538b;
            if (list == null) {
                sb.append("null");
            } else {
                sb.append(list);
            }
        }
        if (mo943m()) {
            sb.append(", ");
            sb.append("checksum:");
            String str = this.f1539c;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: n */
    public void mo944n() {
        if (this.f1537a == null) {
            throw new TProtocolException("Required field 'snapshots' was not present! Struct: " + toString());
        }
    }

    /* renamed from: a */
    private void m1638a(ObjectOutputStream objectOutputStream) {
        try {
            write(new TCompactProtocol(new TIOStreamTransport(objectOutputStream)));
        } catch (TException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: a */
    private void m1637a(ObjectInputStream objectInputStream) {
        try {
            read(new TCompactProtocol(new TIOStreamTransport(objectInputStream)));
        } catch (TException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.c$b */
    /* compiled from: IdTracking */
    class C0224b implements SchemeFactory {
        private C0224b() {
        }

        /* renamed from: a */
        public C0223a mo357b() {
            return new C0223a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.proto.c$a */
    /* compiled from: IdTracking */
    public class C0223a extends StandardScheme {
        private C0223a() {
        }

        /* renamed from: a */
        public void mo355b(TProtocol bpVar, IdTracking cVar) {
            bpVar.mo459j();
            while (true) {
                TField l = bpVar.mo461l();
                byte b = l.f538b;
                if (b == 0) {
                    bpVar.mo460k();
                    cVar.mo944n();
                    return;
                }
                short s = l.f539c;
                int i = 0;
                if (s != 1) {
                    if (s != 2) {
                        if (s != 3) {
                            TProtocolUtil.m785a(bpVar, b);
                        } else if (b == 11) {
                            cVar.f1539c = bpVar.mo475z();
                            cVar.mo933c(true);
                        } else {
                            TProtocolUtil.m785a(bpVar, b);
                        }
                    } else if (b == 15) {
                        TList p = bpVar.mo465p();
                        cVar.f1538b = new ArrayList(p.f541b);
                        while (i < p.f541b) {
                            IdJournal aVar = new IdJournal();
                            aVar.read(bpVar);
                            cVar.f1538b.add(aVar);
                            i++;
                        }
                        bpVar.mo466q();
                        cVar.mo931b(true);
                    } else {
                        TProtocolUtil.m785a(bpVar, b);
                    }
                } else if (b == 13) {
                    TMap n = bpVar.mo463n();
                    cVar.f1537a = new HashMap(n.f544c * 2);
                    while (i < n.f544c) {
                        String z = bpVar.mo475z();
                        IdSnapshot bVar = new IdSnapshot();
                        bVar.read(bpVar);
                        cVar.f1537a.put(z, bVar);
                        i++;
                    }
                    bpVar.mo464o();
                    cVar.mo929a(true);
                } else {
                    TProtocolUtil.m785a(bpVar, b);
                }
                bpVar.mo462m();
            }
        }

        /* renamed from: b */
        public void mo353a(TProtocol bpVar, IdTracking cVar) {
            cVar.mo944n();
            bpVar.mo443a(IdTracking.f1532f);
            if (cVar.f1537a != null) {
                bpVar.mo438a(IdTracking.f1533g);
                bpVar.mo440a(new TMap((byte) 11, (byte) 12, cVar.f1537a.size()));
                for (Map.Entry entry : cVar.f1537a.entrySet()) {
                    bpVar.mo444a((String) entry.getKey());
                    ((IdSnapshot) entry.getValue()).write(bpVar);
                }
                bpVar.mo454e();
                bpVar.mo450c();
            }
            if (cVar.f1538b != null && cVar.mo940j()) {
                bpVar.mo438a(IdTracking.f1534h);
                bpVar.mo439a(new TList((byte) 12, cVar.f1538b.size()));
                for (IdJournal aVar : cVar.f1538b) {
                    aVar.write(bpVar);
                }
                bpVar.mo455f();
                bpVar.mo450c();
            }
            if (cVar.f1539c != null && cVar.mo943m()) {
                bpVar.mo438a(IdTracking.f1535i);
                bpVar.mo444a(cVar.f1539c);
                bpVar.mo450c();
            }
            bpVar.mo452d();
            bpVar.mo449b();
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.c$d */
    /* compiled from: IdTracking */
    class C0226d implements SchemeFactory {
        private C0226d() {
        }

        /* renamed from: a */
        public C0225c mo357b() {
            return new C0225c();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.statistics.proto.c$c */
    /* compiled from: IdTracking */
    public class C0225c extends TupleScheme {
        private C0225c() {
        }

        /* renamed from: a */
        public void mo353a(TProtocol bpVar, IdTracking cVar) {
            TTupleProtocol bvVar = (TTupleProtocol) bpVar;
            bvVar.mo436a(cVar.f1537a.size());
            for (Map.Entry entry : cVar.f1537a.entrySet()) {
                bvVar.mo444a((String) entry.getKey());
                ((IdSnapshot) entry.getValue()).write(bvVar);
            }
            BitSet bitSet = new BitSet();
            if (cVar.mo940j()) {
                bitSet.set(0);
            }
            if (cVar.mo943m()) {
                bitSet.set(1);
            }
            bvVar.mo487a(bitSet, 2);
            if (cVar.mo940j()) {
                bvVar.mo436a(cVar.f1538b.size());
                for (IdJournal aVar : cVar.f1538b) {
                    aVar.write(bvVar);
                }
            }
            if (cVar.mo943m()) {
                bvVar.mo444a(cVar.f1539c);
            }
        }

        /* renamed from: b */
        public void mo355b(TProtocol bpVar, IdTracking cVar) {
            TTupleProtocol bvVar = (TTupleProtocol) bpVar;
            TMap bmVar = new TMap((byte) 11, (byte) 12, bvVar.mo472w());
            cVar.f1537a = new HashMap(bmVar.f544c * 2);
            for (int i = 0; i < bmVar.f544c; i++) {
                String z = bvVar.mo475z();
                IdSnapshot bVar = new IdSnapshot();
                bVar.read(bvVar);
                cVar.f1537a.put(z, bVar);
            }
            cVar.mo929a(true);
            BitSet b = bvVar.mo488b(2);
            if (b.get(0)) {
                TList blVar = new TList((byte) 12, bvVar.mo472w());
                cVar.f1538b = new ArrayList(blVar.f541b);
                for (int i2 = 0; i2 < blVar.f541b; i2++) {
                    IdJournal aVar = new IdJournal();
                    aVar.read(bvVar);
                    cVar.f1538b.add(aVar);
                }
                cVar.mo931b(true);
            }
            if (b.get(1)) {
                cVar.f1539c = bvVar.mo475z();
                cVar.mo933c(true);
            }
        }
    }
}
