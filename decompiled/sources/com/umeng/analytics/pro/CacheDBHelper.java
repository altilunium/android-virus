package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.commonsdk.debug.UMRTLog;

/* renamed from: com.umeng.analytics.pro.ak */
public class CacheDBHelper extends SQLiteOpenHelper {

    /* renamed from: b */
    private static final Object f422b = new Object();

    /* renamed from: c */
    private static CacheDBHelper f423c = null;

    /* renamed from: d */
    private static final String f424d = "CREATE TABLE IF NOT EXISTS stf(_id INTEGER PRIMARY KEY AUTOINCREMENT, _tp TEXT, _hd TEXT, _bd TEXT, _ts TEXT, _uuid TEXT, _re1 TEXT, _re2 TEXT)";

    /* renamed from: e */
    private static final String f425e = "DROP TABLE IF EXISTS stf";

    /* renamed from: f */
    private static final String f426f = "DELETE FROM stf WHERE _id IN( SELECT _id FROM stf ORDER BY _id LIMIT 1)";

    /* renamed from: a */
    private final Context f427a;

    /* renamed from: a */
    public static CacheDBHelper m506a(Context context) {
        CacheDBHelper akVar;
        synchronized (f422b) {
            if (f423c == null) {
                f423c = new CacheDBHelper(context, Constants.f435b, null, 1);
            }
            akVar = f423c;
        }
        return akVar;
    }

    private CacheDBHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
        this.f427a = context;
    }

    /* renamed from: a */
    public static final int m505a() {
        return 1;
    }

    /* renamed from: a */
    private void m507a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(f425e);
            sQLiteDatabase.execSQL(f424d);
        } catch (SQLException e) {
        }
    }

    /* renamed from: b */
    private void m508b(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(f424d);
        } catch (SQLiteDatabaseCorruptException e) {
            m507a(sQLiteDatabase);
        } catch (Throwable th) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]创建二级缓存数据库失败: " + th.getMessage());
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        m508b(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    /* renamed from: b */
    public void mo368b() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null && writableDatabase.isOpen()) {
                writableDatabase.close();
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public void mo365a(String str, ContentValues contentValues) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null && writableDatabase.isOpen()) {
                try {
                    writableDatabase.beginTransaction();
                    writableDatabase.insert(str, null, contentValues);
                    writableDatabase.setTransactionSuccessful();
                    UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> [有状态]插入二级缓存数据记录 成功。");
                    writableDatabase.endTransaction();
                } catch (Throwable th) {
                    writableDatabase.endTransaction();
                }
                writableDatabase.close();
            }
        } catch (Throwable th2) {
        }
    }

    /* renamed from: a */
    public void mo367a(String str, String str2, String[] strArr) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null && writableDatabase.isOpen()) {
                try {
                    writableDatabase.beginTransaction();
                    writableDatabase.delete(str, str2, strArr);
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                } catch (Throwable th) {
                    writableDatabase.endTransaction();
                }
                writableDatabase.close();
            }
        } catch (Throwable th2) {
        }
    }

    /* renamed from: d */
    private void m509d() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null && writableDatabase.isOpen()) {
                try {
                    writableDatabase.execSQL(f426f);
                } catch (Throwable th) {
                }
                writableDatabase.close();
            }
        } catch (Throwable th2) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.umeng.analytics.pro.CacheData mo364a(java.lang.String r19) {
        /*
        // Method dump skipped, instructions count: 165
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.CacheDBHelper.mo364a(java.lang.String):com.umeng.analytics.pro.al");
    }

    /* renamed from: a */
    public void mo366a(String str, String str2) {
        mo367a(str, "_uuid=?", new String[]{str2});
    }

    /* renamed from: a */
    public Cursor mo363a(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null || !writableDatabase.isOpen()) {
                return null;
            }
            return writableDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
        } catch (Throwable th) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo369b(java.lang.String r12) {
        /*
            r11 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r10 = r11.getWritableDatabase()     // Catch:{ all -> 0x003b }
            if (r10 == 0) goto L_0x0020
            boolean r1 = r10.isOpen()     // Catch:{ all -> 0x001e }
            if (r1 == 0) goto L_0x0020
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r1 = r10
            r2 = r12
            android.database.Cursor r12 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x001e }
            r0 = r12
            goto L_0x0020
        L_0x001e:
            r12 = move-exception
            goto L_0x003d
        L_0x0020:
            if (r0 == 0) goto L_0x0033
            int r12 = r0.getCount()     // Catch:{ all -> 0x001e }
            if (r12 <= 0) goto L_0x0033
            r12 = 1
            r0.close()
            if (r10 == 0) goto L_0x0032
            r10.close()
        L_0x0032:
            return r12
        L_0x0033:
            if (r0 == 0) goto L_0x0038
            r0.close()
        L_0x0038:
            if (r10 == 0) goto L_0x0047
            goto L_0x0044
        L_0x003b:
            r12 = move-exception
            r10 = r0
        L_0x003d:
            if (r0 == 0) goto L_0x0042
            r0.close()
        L_0x0042:
            if (r10 == 0) goto L_0x0047
        L_0x0044:
            r10.close()
        L_0x0047:
            r12 = 0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.CacheDBHelper.mo369b(java.lang.String):boolean");
    }

    /* renamed from: c */
    public boolean mo370c() {
        if (!mo369b(Constants.f436c)) {
            return true;
        }
        return false;
    }
}
