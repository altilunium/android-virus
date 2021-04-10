package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.TDeserializer;
import com.umeng.analytics.pro.TSerializer;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.proto.IdTracking;
import com.umeng.commonsdk.utils.UMConstant;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.umeng.commonsdk.statistics.idtracking.e */
public class IdTracker {

    /* renamed from: a */
    public static final long f1435a = 86400000;

    /* renamed from: b */
    public static IdTracker f1436b = null;

    /* renamed from: c */
    private static final String f1437c = "umeng_it.cache";

    /* renamed from: j */
    private static Object f1438j = new Object();

    /* renamed from: d */
    private File f1439d;

    /* renamed from: e */
    private IdTracking f1440e = null;

    /* renamed from: f */
    private long f1441f;

    /* renamed from: g */
    private long f1442g;

    /* renamed from: h */
    private Set f1443h = new HashSet();

    /* renamed from: i */
    private C0196a f1444i = null;

    /* renamed from: a */
    public static synchronized void m1481a() {
        synchronized (IdTracker.class) {
            IdTracker eVar = f1436b;
            if (eVar != null) {
                eVar.mo793e();
                f1436b = null;
            }
        }
    }

    IdTracker(Context context) {
        this.f1439d = new File(context.getFilesDir(), f1437c);
        this.f1442g = 86400000;
        C0196a aVar = new C0196a(context);
        this.f1444i = aVar;
        aVar.mo798b();
    }

    /* renamed from: a */
    public static synchronized IdTracker m1480a(Context context) {
        IdTracker eVar;
        synchronized (IdTracker.class) {
            if (f1436b == null) {
                IdTracker eVar2 = new IdTracker(context);
                f1436b = eVar2;
                eVar2.m1483a(new ImeiTracker(context));
                f1436b.m1483a(new AndroidIdTracker(context));
                f1436b.m1483a(new UTDIdTracker(context));
                f1436b.m1483a(new IDMD5Tracker(context));
                f1436b.m1483a(new IDFATracker(context));
                f1436b.m1483a(new MacTracker(context));
                f1436b.m1483a(new SerialTracker());
                if (FieldManager.allow(UMConstant.f1602G)) {
                    f1436b.m1483a(new OaidTracking(context));
                }
                OldUMIDTracker jVar = new OldUMIDTracker(context);
                if (jVar.mo801g()) {
                    f1436b.m1483a(jVar);
                    f1436b.m1483a(new NewUMIDTracker(context));
                    jVar.mo803i();
                }
                f1436b.mo794f();
            }
            eVar = f1436b;
        }
        return eVar;
    }

    /* renamed from: a */
    private boolean m1483a(AbstractIdTracker aVar) {
        if (this.f1444i.mo797a(aVar.mo784b())) {
            return this.f1443h.add(aVar);
        }
        if (!AnalyticsConstants.UM_DEBUG) {
            return false;
        }
        MLog.m1375w("invalid domain: " + aVar.mo784b());
        return false;
    }

    /* renamed from: a */
    public void mo789a(long j) {
        this.f1442g = j;
    }

    /* renamed from: b */
    public synchronized void mo790b() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f1441f >= this.f1442g) {
            boolean z = false;
            for (AbstractIdTracker aVar : this.f1443h) {
                if (aVar.mo785c()) {
                    if (aVar.mo783a()) {
                        z = true;
                        if (!aVar.mo785c()) {
                            this.f1444i.mo799b(aVar.mo784b());
                        }
                    }
                }
            }
            if (z) {
                m1485h();
                this.f1444i.mo796a();
                mo795g();
            }
            this.f1441f = currentTimeMillis;
        }
    }

    /* renamed from: c */
    public synchronized IdTracking mo791c() {
        return this.f1440e;
    }

    /* renamed from: h */
    private synchronized void m1485h() {
        IdTracking cVar = new IdTracking();
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (AbstractIdTracker aVar : this.f1443h) {
            if (aVar.mo785c()) {
                if (aVar.mo786d() != null) {
                    hashMap.put(aVar.mo784b(), aVar.mo786d());
                }
                if (aVar.mo787e() != null && !aVar.mo787e().isEmpty()) {
                    arrayList.addAll(aVar.mo787e());
                }
            }
        }
        cVar.mo925a(arrayList);
        cVar.mo926a(hashMap);
        synchronized (this) {
            this.f1440e = cVar;
        }
    }

    /* renamed from: d */
    public String mo792d() {
        return null;
    }

    /* renamed from: e */
    public synchronized void mo793e() {
        if (f1436b != null) {
            boolean z = false;
            for (AbstractIdTracker aVar : this.f1443h) {
                if (aVar.mo785c() && aVar.mo787e() != null && !aVar.mo787e().isEmpty()) {
                    aVar.mo782a((List) null);
                    z = true;
                }
            }
            if (z) {
                this.f1440e.mo931b(false);
                mo795g();
            }
        }
    }

    /* renamed from: f */
    public synchronized void mo794f() {
        IdTracking i = m1486i();
        if (i != null) {
            m1482a(i);
            ArrayList arrayList = new ArrayList(this.f1443h.size());
            synchronized (this) {
                this.f1440e = i;
                for (AbstractIdTracker aVar : this.f1443h) {
                    aVar.mo781a(this.f1440e);
                    if (!aVar.mo785c()) {
                        arrayList.add(aVar);
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    this.f1443h.remove((AbstractIdTracker) it.next());
                }
                m1485h();
            }
        }
    }

    /* renamed from: g */
    public synchronized void mo795g() {
        IdTracking cVar = this.f1440e;
        if (cVar != null) {
            m1484b(cVar);
        }
    }

    /* renamed from: i */
    private IdTracking m1486i() {
        Throwable th;
        FileInputStream fileInputStream;
        Exception e;
        synchronized (f1438j) {
            if (!this.f1439d.exists()) {
                return null;
            }
            try {
                fileInputStream = new FileInputStream(this.f1439d);
                try {
                    byte[] readStreamToByteArray = HelperUtils.readStreamToByteArray(fileInputStream);
                    IdTracking cVar = new IdTracking();
                    new TDeserializer().mo386a(cVar, readStreamToByteArray);
                    HelperUtils.safeClose(fileInputStream);
                    return cVar;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        HelperUtils.safeClose(fileInputStream);
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        HelperUtils.safeClose(fileInputStream);
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                fileInputStream = null;
                e.printStackTrace();
                HelperUtils.safeClose(fileInputStream);
                return null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                HelperUtils.safeClose(fileInputStream);
                throw th;
            }
        }
    }

    /* renamed from: a */
    private void m1482a(IdTracking cVar) {
        Map map;
        if (cVar != null && (map = cVar.f1537a) != null) {
            if (map.containsKey("mac") && !FieldManager.allow(UMConstant.f1642h)) {
                cVar.f1537a.remove("mac");
            }
            if (cVar.f1537a.containsKey("imei") && !FieldManager.allow(UMConstant.f1641g)) {
                cVar.f1537a.remove("imei");
            }
            if (cVar.f1537a.containsKey("android_id") && !FieldManager.allow(UMConstant.f1643i)) {
                cVar.f1537a.remove("android_id");
            }
            if (cVar.f1537a.containsKey("serial") && !FieldManager.allow(UMConstant.f1644j)) {
                cVar.f1537a.remove("serial");
            }
            if (cVar.f1537a.containsKey("idfa") && !FieldManager.allow(UMConstant.f1657w)) {
                cVar.f1537a.remove("idfa");
            }
            if (cVar.f1537a.containsKey("oaid") && !FieldManager.allow(UMConstant.f1602G)) {
                cVar.f1537a.remove("oaid");
            }
        }
    }

    /* renamed from: b */
    private void m1484b(IdTracking cVar) {
        byte[] a;
        synchronized (f1438j) {
            if (cVar != null) {
                try {
                    synchronized (this) {
                        m1482a(cVar);
                        a = new TSerializer().mo398a(cVar);
                    }
                    if (a != null) {
                        HelperUtils.writeFile(this.f1439d, a);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.idtracking.e$a */
    /* compiled from: IdTracker */
    public class C0196a {

        /* renamed from: a */
        private Context f1445a;

        /* renamed from: b */
        private Set f1446b = new HashSet();

        public C0196a(Context context) {
            this.f1445a = context;
        }

        /* renamed from: a */
        public synchronized boolean mo797a(String str) {
            return !this.f1446b.contains(str);
        }

        /* renamed from: b */
        public synchronized void mo799b(String str) {
            this.f1446b.add(str);
        }

        /* renamed from: c */
        public void mo800c(String str) {
            this.f1446b.remove(str);
        }

        /* renamed from: a */
        public synchronized void mo796a() {
            if (!this.f1446b.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (String str : this.f1446b) {
                    sb.append(str);
                    sb.append(',');
                }
                sb.deleteCharAt(sb.length() - 1);
                PreferenceWrapper.getDefault(this.f1445a).edit().putString("invld_id", sb.toString()).commit();
            }
        }

        /* renamed from: b */
        public synchronized void mo798b() {
            String[] split;
            String string = PreferenceWrapper.getDefault(this.f1445a).getString("invld_id", null);
            if (!TextUtils.isEmpty(string) && (split = string.split(",")) != null) {
                for (String str : split) {
                    if (!TextUtils.isEmpty(str)) {
                        this.f1446b.add(str);
                    }
                }
            }
        }
    }
}
