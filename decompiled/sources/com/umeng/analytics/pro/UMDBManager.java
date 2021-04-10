package com.umeng.analytics.pro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;

/* access modifiers changed from: package-private */
/* renamed from: com.umeng.analytics.pro.f */
public class UMDBManager {

    /* renamed from: b */
    private static SQLiteOpenHelper f747b;

    /* renamed from: d */
    private static Context f748d;

    /* renamed from: a */
    private AtomicInteger f749a;

    /* renamed from: c */
    private SQLiteDatabase f750c;

    private UMDBManager() {
        this.f749a = new AtomicInteger();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.pro.f$a */
    /* compiled from: UMDBManager */
    public class C0105a {

        /* renamed from: a */
        private static final UMDBManager f751a = new UMDBManager();

        private C0105a() {
        }
    }

    /* renamed from: a */
    public static UMDBManager m840a(Context context) {
        if (f748d == null && context != null) {
            Context applicationContext = context.getApplicationContext();
            f748d = applicationContext;
            f747b = C0101e.m829a(applicationContext);
        }
        return C0105a.f751a;
    }

    /* renamed from: a */
    public synchronized SQLiteDatabase mo510a() {
        if (this.f749a.incrementAndGet() == 1) {
            this.f750c = f747b.getWritableDatabase();
        }
        return this.f750c;
    }

    /* renamed from: b */
    public synchronized void mo511b() {
        try {
            if (this.f749a.decrementAndGet() == 0) {
                this.f750c.close();
            }
        } catch (Throwable th) {
        }
    }
}
