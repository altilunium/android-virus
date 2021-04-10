package com.umeng.commonsdk.statistics.idtracking;

import com.umeng.commonsdk.statistics.proto.IdJournal;
import com.umeng.commonsdk.statistics.proto.IdSnapshot;
import com.umeng.commonsdk.statistics.proto.IdTracking;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: com.umeng.commonsdk.statistics.idtracking.a */
public abstract class AbstractIdTracker {

    /* renamed from: a */
    private final int f1424a = 10;

    /* renamed from: b */
    private final int f1425b = 100;

    /* renamed from: c */
    private final String f1426c;

    /* renamed from: d */
    private List f1427d;

    /* renamed from: e */
    private IdSnapshot f1428e;

    /* renamed from: f */
    public abstract String mo788f();

    public AbstractIdTracker(String str) {
        this.f1426c = str;
    }

    /* renamed from: a */
    public boolean mo783a() {
        return mo801g();
    }

    /* renamed from: b */
    public String mo784b() {
        return this.f1426c;
    }

    /* renamed from: c */
    public boolean mo785c() {
        IdSnapshot bVar = this.f1428e;
        if (bVar == null || bVar.mo911h() <= 100) {
            return true;
        }
        return false;
    }

    /* renamed from: g */
    private boolean mo801g() {
        IdSnapshot bVar = this.f1428e;
        String b = bVar == null ? null : bVar.mo903b();
        int h = bVar == null ? 0 : bVar.mo911h();
        String a = mo779a(mo788f());
        if (a == null || a.equals(b)) {
            return false;
        }
        if (bVar == null) {
            bVar = new IdSnapshot();
        }
        bVar.mo900a(a);
        bVar.mo899a(System.currentTimeMillis());
        bVar.mo898a(h + 1);
        IdJournal aVar = new IdJournal();
        aVar.mo870a(this.f1426c);
        aVar.mo875c(a);
        aVar.mo872b(b);
        aVar.mo869a(bVar.mo908e());
        if (this.f1427d == null) {
            this.f1427d = new ArrayList(2);
        }
        this.f1427d.add(aVar);
        if (this.f1427d.size() > 10) {
            this.f1427d.remove(0);
        }
        this.f1428e = bVar;
        return true;
    }

    /* renamed from: d */
    public IdSnapshot mo786d() {
        return this.f1428e;
    }

    /* renamed from: a */
    public void mo780a(IdSnapshot bVar) {
        this.f1428e = bVar;
    }

    /* renamed from: e */
    public List mo787e() {
        return this.f1427d;
    }

    /* renamed from: a */
    public void mo782a(List list) {
        this.f1427d = list;
    }

    /* renamed from: a */
    public String mo779a(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() != 0 && !"0".equals(trim) && !"unknown".equals(trim.toLowerCase(Locale.US))) {
            return trim;
        }
        return null;
    }

    /* renamed from: a */
    public void mo781a(IdTracking cVar) {
        this.f1428e = (IdSnapshot) cVar.mo932c().get(this.f1426c);
        List<IdJournal> h = cVar.mo938h();
        if (h != null && h.size() > 0) {
            if (this.f1427d == null) {
                this.f1427d = new ArrayList();
            }
            for (IdJournal aVar : h) {
                if (this.f1426c.equals(aVar.f1496a)) {
                    this.f1427d.add(aVar);
                }
            }
        }
    }
}
