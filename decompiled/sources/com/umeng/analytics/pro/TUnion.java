package com.umeng.analytics.pro;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.umeng.analytics.pro.ba */
public abstract class TUnion implements TBase {

    /* renamed from: c */
    private static final Map f472c;

    /* renamed from: a */
    protected Object f473a;

    /* renamed from: b */
    protected TFieldIdEnum f474b;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract TFieldIdEnum mo404a(short s);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Object mo407a(TProtocol bpVar, TField bkVar);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Object mo408a(TProtocol bpVar, short s);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo411a(TProtocol bpVar);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo413b(TFieldIdEnum axVar, Object obj);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo414b(TProtocol bpVar);

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract TField mo417c(TFieldIdEnum axVar);

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract TStruct mo419d();

    protected TUnion() {
        this.f474b = null;
        this.f473a = null;
    }

    static {
        HashMap hashMap = new HashMap();
        f472c = hashMap;
        hashMap.put(StandardScheme.class, new C0082b());
        hashMap.put(TupleScheme.class, new C0084d());
    }

    protected TUnion(TFieldIdEnum axVar, Object obj) {
        mo410a(axVar, obj);
    }

    protected TUnion(TUnion baVar) {
        if (baVar.getClass().equals(getClass())) {
            this.f474b = baVar.f474b;
            this.f473a = m586a(baVar.f473a);
            return;
        }
        throw new ClassCastException();
    }

    /* renamed from: a */
    private static Object m586a(Object obj) {
        if (obj instanceof TBase) {
            return ((TBase) obj).deepCopy();
        }
        if (obj instanceof ByteBuffer) {
            return TBaseHelper.m562d((ByteBuffer) obj);
        }
        if (obj instanceof List) {
            return m587a((List) obj);
        }
        if (obj instanceof Set) {
            return m589a((Set) obj);
        }
        if (obj instanceof Map) {
            return m588a((Map) obj);
        }
        return obj;
    }

    /* renamed from: a */
    private static Map m588a(Map map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            hashMap.put(m586a(entry.getKey()), m586a(entry.getValue()));
        }
        return hashMap;
    }

    /* renamed from: a */
    private static Set m589a(Set set) {
        HashSet hashSet = new HashSet();
        for (Object obj : set) {
            hashSet.add(m586a(obj));
        }
        return hashSet;
    }

    /* renamed from: a */
    private static List m587a(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Object obj : list) {
            arrayList.add(m586a(obj));
        }
        return arrayList;
    }

    /* renamed from: a */
    public TFieldIdEnum mo403a() {
        return this.f474b;
    }

    /* renamed from: b */
    public Object mo412b() {
        return this.f473a;
    }

    /* renamed from: a */
    public Object mo406a(TFieldIdEnum axVar) {
        if (axVar == this.f474b) {
            return mo412b();
        }
        throw new IllegalArgumentException("Cannot get the value of field " + axVar + " because union's set field is " + this.f474b);
    }

    /* renamed from: a */
    public Object mo405a(int i) {
        return mo406a(mo404a((short) i));
    }

    /* renamed from: c */
    public boolean mo418c() {
        return this.f474b != null;
    }

    /* renamed from: b */
    public boolean mo416b(TFieldIdEnum axVar) {
        return this.f474b == axVar;
    }

    /* renamed from: b */
    public boolean mo415b(int i) {
        return mo416b(mo404a((short) i));
    }

    @Override // com.umeng.analytics.pro.TBase
    public void read(TProtocol bpVar) {
        ((SchemeFactory) f472c.get(bpVar.mo485D())).mo357b().mo355b(bpVar, this);
    }

    /* renamed from: a */
    public void mo410a(TFieldIdEnum axVar, Object obj) {
        mo413b(axVar, obj);
        this.f474b = axVar;
        this.f473a = obj;
    }

    /* renamed from: a */
    public void mo409a(int i, Object obj) {
        mo410a(mo404a((short) i), obj);
    }

    @Override // com.umeng.analytics.pro.TBase
    public void write(TProtocol bpVar) {
        ((SchemeFactory) f472c.get(bpVar.mo485D())).mo357b().mo353a(bpVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getClass().getSimpleName());
        sb.append(" ");
        if (mo403a() != null) {
            Object b = mo412b();
            sb.append(mo417c(mo403a()).f537a);
            sb.append(":");
            if (b instanceof ByteBuffer) {
                TBaseHelper.m557a((ByteBuffer) b, sb);
            } else {
                sb.append(b.toString());
            }
        }
        sb.append(">");
        return sb.toString();
    }

    @Override // com.umeng.analytics.pro.TBase
    public final void clear() {
        this.f474b = null;
        this.f473a = null;
    }

    /* renamed from: com.umeng.analytics.pro.ba$b */
    /* compiled from: TUnion */
    class C0082b implements SchemeFactory {
        private C0082b() {
        }

        /* renamed from: a */
        public C0081a mo357b() {
            return new C0081a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.pro.ba$a */
    /* compiled from: TUnion */
    public class C0081a extends StandardScheme {
        private C0081a() {
        }

        /* renamed from: a */
        public void mo355b(TProtocol bpVar, TUnion baVar) {
            baVar.f474b = null;
            baVar.f473a = null;
            bpVar.mo459j();
            TField l = bpVar.mo461l();
            Object a = baVar.mo407a(bpVar, l);
            baVar.f473a = a;
            if (a != null) {
                baVar.f474b = baVar.mo404a(l.f539c);
            }
            bpVar.mo462m();
            bpVar.mo461l();
            bpVar.mo460k();
        }

        /* renamed from: b */
        public void mo353a(TProtocol bpVar, TUnion baVar) {
            if (baVar.mo403a() == null || baVar.mo412b() == null) {
                throw new TProtocolException("Cannot write a TUnion with no set value!");
            }
            bpVar.mo443a(baVar.mo419d());
            bpVar.mo438a(baVar.mo417c(baVar.f474b));
            baVar.mo411a(bpVar);
            bpVar.mo450c();
            bpVar.mo452d();
            bpVar.mo449b();
        }
    }

    /* renamed from: com.umeng.analytics.pro.ba$d */
    /* compiled from: TUnion */
    class C0084d implements SchemeFactory {
        private C0084d() {
        }

        /* renamed from: a */
        public C0083c mo357b() {
            return new C0083c();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.pro.ba$c */
    /* compiled from: TUnion */
    public class C0083c extends TupleScheme {
        private C0083c() {
        }

        /* renamed from: a */
        public void mo355b(TProtocol bpVar, TUnion baVar) {
            baVar.f474b = null;
            baVar.f473a = null;
            short v = bpVar.mo471v();
            Object a = baVar.mo408a(bpVar, v);
            baVar.f473a = a;
            if (a != null) {
                baVar.f474b = baVar.mo404a(v);
            }
        }

        /* renamed from: b */
        public void mo353a(TProtocol bpVar, TUnion baVar) {
            if (baVar.mo403a() == null || baVar.mo412b() == null) {
                throw new TProtocolException("Cannot write a TUnion with no set value!");
            }
            bpVar.mo446a(baVar.f474b.mo361a());
            baVar.mo414b(bpVar);
        }
    }
}
