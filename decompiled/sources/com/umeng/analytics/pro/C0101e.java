package com.umeng.analytics.pro;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.umeng.analytics.pro.UMDBConfig;

/* access modifiers changed from: package-private */
/* renamed from: com.umeng.analytics.pro.e */
/* compiled from: UMDBCreater */
public class C0101e extends SQLiteOpenHelper {

    /* renamed from: b */
    private static Context f744b = null;

    /* renamed from: a */
    private String f745a;

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.pro.e$a */
    /* compiled from: UMDBCreater */
    public class C0103a {

        /* renamed from: a */
        private static final C0101e f746a = new C0101e(C0101e.f744b, UMDBUtils.m850b(C0101e.f744b), UMDBConfig.f678b, null, 2);

        private C0103a() {
        }
    }

    /* renamed from: a */
    public static C0101e m829a(Context context) {
        if (f744b == null) {
            f744b = context.getApplicationContext();
        }
        return C0103a.f746a;
    }

    private C0101e(Context context, String str, String str2, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        this(new UMDBCreater(context, str), str2, cursorFactory, i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private C0101e(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, TextUtils.isEmpty(str) ? UMDBConfig.f678b : str, cursorFactory, i);
        this.f745a = null;
        mo507a();
    }

    /* renamed from: a */
    public void mo507a() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (!UMDBUtils.m849a(UMDBConfig.C0098d.f719a, writableDatabase)) {
                m834c(writableDatabase);
            }
            if (!UMDBUtils.m849a(UMDBConfig.C0095c.f706a, writableDatabase)) {
                m835d(writableDatabase);
            }
            if (!UMDBUtils.m849a(UMDBConfig.C0092b.f693a, writableDatabase)) {
                m833b(writableDatabase);
            }
            if (!UMDBUtils.m849a(UMDBConfig.C0089a.f682a, writableDatabase)) {
                m830a(writableDatabase);
            }
        } catch (Exception e) {
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            m834c(sQLiteDatabase);
            m835d(sQLiteDatabase);
            m833b(sQLiteDatabase);
            m830a(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
        } catch (SQLiteDatabaseCorruptException e) {
            UMDBUtils.m846a(f744b);
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th2) {
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    private void m830a(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f745a = "create table if not exists __er(id INTEGER primary key autoincrement, __i TEXT, __a TEXT, __t INTEGER, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL("create table if not exists __er(id INTEGER primary key autoincrement, __i TEXT, __a TEXT, __t INTEGER, __av TEXT, __vc TEXT)");
        } catch (SQLException e) {
        }
    }

    /* renamed from: b */
    private void m833b(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f745a = "create table if not exists __et(id INTEGER primary key autoincrement, __i TEXT, __e TEXT, __s TEXT, __t INTEGER, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL("create table if not exists __et(id INTEGER primary key autoincrement, __i TEXT, __e TEXT, __s TEXT, __t INTEGER, __av TEXT, __vc TEXT)");
        } catch (SQLException e) {
        }
    }

    /* renamed from: c */
    private void m834c(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f745a = "create table if not exists __sd(id INTEGER primary key autoincrement, __ii TEXT unique, __a TEXT, __b TEXT, __c TEXT, __d TEXT, __e TEXT, __f TEXT, __g TEXT, __sp TEXT, __pp TEXT, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL("create table if not exists __sd(id INTEGER primary key autoincrement, __ii TEXT unique, __a TEXT, __b TEXT, __c TEXT, __d TEXT, __e TEXT, __f TEXT, __g TEXT, __sp TEXT, __pp TEXT, __av TEXT, __vc TEXT)");
        } catch (SQLException e) {
        }
    }

    /* renamed from: d */
    private void m835d(SQLiteDatabase sQLiteDatabase) {
        try {
            this.f745a = "create table if not exists __is(id INTEGER primary key autoincrement, __ii TEXT unique, __e TEXT, __sp TEXT, __pp TEXT, __av TEXT, __vc TEXT)";
            sQLiteDatabase.execSQL("create table if not exists __is(id INTEGER primary key autoincrement, __ii TEXT unique, __e TEXT, __sp TEXT, __pp TEXT, __av TEXT, __vc TEXT)");
        } catch (SQLException e) {
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i2 > i && i == 1) {
            try {
                m836e(sQLiteDatabase);
            } catch (Exception e) {
                try {
                    m836e(sQLiteDatabase);
                } catch (Exception e2) {
                    m837f(sQLiteDatabase);
                }
            }
        }
    }

    /* renamed from: e */
    private void m836e(SQLiteDatabase sQLiteDatabase) {
        if (!UMDBUtils.m848a(sQLiteDatabase, UMDBConfig.C0098d.f719a, "__av")) {
            UMDBUtils.m847a(sQLiteDatabase, UMDBConfig.C0098d.f719a, "__sp", "TEXT");
            UMDBUtils.m847a(sQLiteDatabase, UMDBConfig.C0098d.f719a, "__pp", "TEXT");
            UMDBUtils.m847a(sQLiteDatabase, UMDBConfig.C0098d.f719a, "__av", "TEXT");
            UMDBUtils.m847a(sQLiteDatabase, UMDBConfig.C0098d.f719a, "__vc", "TEXT");
        }
        if (!UMDBUtils.m848a(sQLiteDatabase, UMDBConfig.C0092b.f693a, "__av")) {
            UMDBUtils.m847a(sQLiteDatabase, UMDBConfig.C0092b.f693a, "__av", "TEXT");
            UMDBUtils.m847a(sQLiteDatabase, UMDBConfig.C0092b.f693a, "__vc", "TEXT");
        }
        if (!UMDBUtils.m848a(sQLiteDatabase, UMDBConfig.C0089a.f682a, "__av")) {
            UMDBUtils.m847a(sQLiteDatabase, UMDBConfig.C0089a.f682a, "__av", "TEXT");
            UMDBUtils.m847a(sQLiteDatabase, UMDBConfig.C0089a.f682a, "__vc", "TEXT");
        }
    }

    /* renamed from: f */
    private void m837f(SQLiteDatabase sQLiteDatabase) {
        m831a(sQLiteDatabase, UMDBConfig.C0098d.f719a);
        m831a(sQLiteDatabase, UMDBConfig.C0092b.f693a);
        m831a(sQLiteDatabase, UMDBConfig.C0089a.f682a);
        mo507a();
    }

    /* renamed from: a */
    private void m831a(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
        } catch (SQLException e) {
        }
    }
}
