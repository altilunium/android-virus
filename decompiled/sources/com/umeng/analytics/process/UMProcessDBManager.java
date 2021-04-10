package com.umeng.analytics.process;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.commonsdk.service.UMGlobalContext;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* access modifiers changed from: package-private */
/* renamed from: com.umeng.analytics.process.c */
public class UMProcessDBManager {

    /* renamed from: a */
    private static UMProcessDBManager f1016a;

    /* renamed from: b */
    private ConcurrentHashMap f1017b = new ConcurrentHashMap();

    /* renamed from: c */
    private Context f1018c;

    private UMProcessDBManager() {
    }

    /* renamed from: a */
    static UMProcessDBManager m1076a(Context context) {
        if (f1016a == null) {
            synchronized (UMProcessDBManager.class) {
                if (f1016a == null) {
                    f1016a = new UMProcessDBManager();
                }
            }
        }
        UMProcessDBManager cVar = f1016a;
        cVar.f1018c = context;
        return cVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized SQLiteDatabase mo634a(String str) {
        return m1077c(str).mo636a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public synchronized void mo635b(String str) {
        m1077c(str).mo637b();
    }

    /* renamed from: c */
    private C0131a m1077c(String str) {
        if (this.f1017b.get(str) != null) {
            return (C0131a) this.f1017b.get(str);
        }
        C0131a a = C0131a.m1080a(this.f1018c, str);
        this.f1017b.put(str, a);
        return a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.process.c$a */
    /* compiled from: UMProcessDBManager */
    public class C0131a {

        /* renamed from: a */
        private AtomicInteger f1019a = new AtomicInteger();

        /* renamed from: b */
        private SQLiteOpenHelper f1020b;

        /* renamed from: c */
        private SQLiteDatabase f1021c;

        private C0131a() {
        }

        /* renamed from: a */
        static C0131a m1080a(Context context, String str) {
            Context appContext = UMGlobalContext.getAppContext(context);
            C0131a aVar = new C0131a();
            aVar.f1020b = UMProcessDBCreater.m1072a(appContext, str);
            return aVar;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public synchronized SQLiteDatabase mo636a() {
            if (this.f1019a.incrementAndGet() == 1) {
                this.f1021c = this.f1020b.getWritableDatabase();
            }
            return this.f1021c;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public synchronized void mo637b() {
            try {
                if (this.f1019a.decrementAndGet() == 0) {
                    this.f1021c.close();
                }
            } catch (Throwable th) {
            }
        }
    }
}
