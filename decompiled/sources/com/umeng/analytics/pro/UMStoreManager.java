package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.UMDBConfig;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.umeng.analytics.pro.h */
public class UMStoreManager {

    /* renamed from: a */
    public static final int f752a = 2049;

    /* renamed from: b */
    public static final int f753b = 2050;

    /* renamed from: c */
    private static final int f754c = 1000;

    /* renamed from: d */
    private static Context f755d = null;

    /* renamed from: e */
    private static String f756e = null;

    /* renamed from: f */
    private static final String f757f = "umeng+";

    /* renamed from: g */
    private static final String f758g = "ek__id";

    /* renamed from: h */
    private static final String f759h = "ek_key";

    /* renamed from: i */
    private List f760i;

    /* renamed from: j */
    private List f761j;

    /* renamed from: k */
    private String f762k;

    /* renamed from: l */
    private List f763l;

    /* renamed from: com.umeng.analytics.pro.h$a */
    /* compiled from: UMStoreManager */
    public enum EnumC0107a {
        AUTOPAGE,
        PAGE,
        BEGIN,
        END,
        NEWSESSION,
        INSTANTSESSIONBEGIN
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.pro.h$b */
    /* compiled from: UMStoreManager */
    public class C0108b {

        /* renamed from: a */
        private static final UMStoreManager f771a = new UMStoreManager();

        private C0108b() {
        }
    }

    private UMStoreManager() {
        this.f760i = new ArrayList();
        this.f761j = new ArrayList();
        this.f762k = null;
        this.f763l = new ArrayList();
    }

    /* renamed from: a */
    public static UMStoreManager m853a(Context context) {
        UMStoreManager hVar = C0108b.f771a;
        if (f755d == null && context != null) {
            f755d = context.getApplicationContext();
            hVar.m863k();
        }
        return hVar;
    }

    /* renamed from: k */
    private void m863k() {
        synchronized (this) {
            m864l();
            this.f760i.clear();
            this.f763l.clear();
            this.f761j.clear();
        }
    }

    /* renamed from: a */
    public void mo514a() {
        this.f760i.clear();
    }

    /* renamed from: b */
    public void mo521b() {
        this.f763l.clear();
    }

    /* renamed from: c */
    public boolean mo525c() {
        return this.f763l.isEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009c, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009f, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x009b A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0011] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a4 A[SYNTHETIC, Splitter:B:29:0x00a4] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00bc A[SYNTHETIC, Splitter:B:37:0x00bc] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo515a(org.json.JSONArray r11) {
        /*
        // Method dump skipped, instructions count: 216
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMStoreManager.mo515a(org.json.JSONArray):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x005f A[SYNTHETIC, Splitter:B:17:0x005f] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0077 A[SYNTHETIC, Splitter:B:25:0x0077] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo518a(java.lang.String r5, java.lang.String r6, int r7) {
        /*
        // Method dump skipped, instructions count: 148
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMStoreManager.mo518a(java.lang.String, java.lang.String, int):boolean");
    }

    /* renamed from: d */
    public void mo527d() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = UMDBManager.m840a(f755d).mo510a();
            sQLiteDatabase.beginTransaction();
            String c = SessionTracker.m1011a().mo600c();
            if (TextUtils.isEmpty(c)) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Throwable th) {
                }
                UMDBManager.m840a(f755d).mo511b();
                return;
            }
            String[] strArr = {"", "-1"};
            for (int i = 0; i < 2; i++) {
                sQLiteDatabase.execSQL("update __et set __i=\"" + c + "\" where " + "__i" + "=\"" + strArr[i] + "\"");
            }
            sQLiteDatabase.setTransactionSuccessful();
            try {
                sQLiteDatabase.endTransaction();
            } catch (Throwable th2) {
            }
            UMDBManager.m840a(f755d).mo511b();
        } catch (SQLiteDatabaseCorruptException e) {
            UMDBUtils.m846a(f755d);
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th3) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x008e A[SYNTHETIC, Splitter:B:35:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a6 A[SYNTHETIC, Splitter:B:43:0x00a6] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo519a(java.lang.String r7, org.json.JSONObject r8, com.umeng.analytics.pro.UMStoreManager.EnumC0107a r9) {
        /*
        // Method dump skipped, instructions count: 194
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMStoreManager.mo519a(java.lang.String, org.json.JSONObject, com.umeng.analytics.pro.h$a):boolean");
    }

    /* renamed from: a */
    private void m855a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        String str2;
        try {
            long longValue = ((Long) jSONObject.opt(UMDBConfig.C0098d.C0099a.f726g)).longValue();
            long j = 0;
            Object opt = jSONObject.opt(UMDBConfig.C0098d.C0099a.f727h);
            if (opt != null && (opt instanceof Long)) {
                j = ((Long) opt).longValue();
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("__sp");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("__pp");
            String str3 = "";
            if (optJSONObject == null || optJSONObject.length() <= 0) {
                str2 = str3;
            } else {
                str2 = mo524c(optJSONObject.toString());
            }
            if (optJSONObject2 != null && optJSONObject2.length() > 0) {
                str3 = mo524c(optJSONObject2.toString());
            }
            sQLiteDatabase.execSQL("update __sd set __f=\"" + longValue + "\", " + UMDBConfig.C0098d.C0099a.f727h + "=\"" + j + "\", " + "__sp" + "=\"" + str2 + "\", " + "__pp" + "=\"" + str3 + "\" where " + "__ii" + "=\"" + str + "\"");
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    private void m860b(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        String str2;
        try {
            long longValue = ((Long) jSONObject.get("__e")).longValue();
            JSONObject optJSONObject = jSONObject.optJSONObject("__sp");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("__pp");
            String str3 = "";
            if (optJSONObject == null || optJSONObject.length() <= 0) {
                str2 = str3;
            } else {
                str2 = mo524c(optJSONObject.toString());
            }
            if (optJSONObject2 != null && optJSONObject2.length() > 0) {
                str3 = mo524c(optJSONObject2.toString());
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("__ii", str);
            contentValues.put("__e", String.valueOf(longValue));
            contentValues.put("__sp", str2);
            contentValues.put("__pp", str3);
            contentValues.put("__av", UMGlobalContext.getInstance(f755d).getAppVersion());
            contentValues.put("__vc", UMUtils.getAppVersionCode(f755d));
            sQLiteDatabase.insert(UMDBConfig.C0095c.f706a, null, contentValues);
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0043 A[SYNTHETIC, Splitter:B:18:0x0043] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004b A[Catch:{ Exception -> 0x0047 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x005d A[SYNTHETIC, Splitter:B:29:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0065 A[Catch:{ Exception -> 0x0061 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long mo512a(java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 116
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMStoreManager.mo512a(java.lang.String):long");
    }

    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r10v3, types: [java.lang.Throwable] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m862c(java.lang.String r10, org.json.JSONObject r11, android.database.sqlite.SQLiteDatabase r12) {
        /*
        // Method dump skipped, instructions count: 240
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMStoreManager.m862c(java.lang.String, org.json.JSONObject, android.database.sqlite.SQLiteDatabase):void");
    }

    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.lang.Throwable] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m856a(java.lang.String r8, org.json.JSONObject r9, android.database.sqlite.SQLiteDatabase r10, java.lang.String r11) {
        /*
        // Method dump skipped, instructions count: 254
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMStoreManager.m856a(java.lang.String, org.json.JSONObject, android.database.sqlite.SQLiteDatabase, java.lang.String):void");
    }

    /* renamed from: e */
    public boolean mo528e() {
        return this.f760i.isEmpty();
    }

    /* renamed from: a */
    public JSONObject mo513a(boolean z) {
        mo514a();
        this.f761j.clear();
        JSONObject jSONObject = new JSONObject();
        if (!z) {
            m854a(jSONObject, z);
            m861b(jSONObject, (String) null);
            m857a(jSONObject, (String) null);
        } else {
            String a = m854a(jSONObject, z);
            if (!TextUtils.isEmpty(a)) {
                m861b(jSONObject, a);
                m857a(jSONObject, a);
            }
        }
        return jSONObject;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Throwable] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008c A[SYNTHETIC, Splitter:B:39:0x008c] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ac A[SYNTHETIC, Splitter:B:50:0x00ac] */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject mo529f() {
        /*
        // Method dump skipped, instructions count: 205
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMStoreManager.mo529f():org.json.JSONObject");
    }

    /* renamed from: b */
    public JSONObject mo520b(boolean z) {
        JSONObject jSONObject = new JSONObject();
        m858b(jSONObject, z);
        return jSONObject;
    }

    /* JADX WARNING: Removed duplicated region for block: B:76:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0185 A[SYNTHETIC, Splitter:B:78:0x0185] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0199 A[SYNTHETIC, Splitter:B:87:0x0199] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m857a(org.json.JSONObject r11, java.lang.String r12) {
        /*
        // Method dump skipped, instructions count: 452
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMStoreManager.m857a(org.json.JSONObject, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0081 A[SYNTHETIC, Splitter:B:34:0x0081] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0095 A[SYNTHETIC, Splitter:B:43:0x0095] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m861b(org.json.JSONObject r6, java.lang.String r7) {
        /*
        // Method dump skipped, instructions count: 192
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMStoreManager.m861b(org.json.JSONObject, java.lang.String):void");
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Throwable] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008b A[SYNTHETIC, Splitter:B:39:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ab A[SYNTHETIC, Splitter:B:50:0x00ab] */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject mo530g() {
        /*
        // Method dump skipped, instructions count: 204
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMStoreManager.mo530g():org.json.JSONObject");
    }

    /* renamed from: b */
    private JSONArray m859b(JSONArray jSONArray) {
        JSONArray jSONArray2 = new JSONArray();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null && optJSONObject.optLong("duration") > 0) {
                jSONArray2.put(optJSONObject);
            }
        }
        return jSONArray2;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v21, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v24, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v27, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v29, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v31, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v33, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v35, types: [java.lang.Throwable] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x025a  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x025f A[SYNTHETIC, Splitter:B:123:0x025f] */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0270  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0275 A[SYNTHETIC, Splitter:B:132:0x0275] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m854a(org.json.JSONObject r22, boolean r23) {
        /*
        // Method dump skipped, instructions count: 674
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMStoreManager.m854a(org.json.JSONObject, boolean):java.lang.String");
    }

    /* JADX WARN: Type inference failed for: r12v4, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r12v7, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r12v9, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r11v7, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r11v11, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r11v15, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r11v18, types: [java.lang.Throwable] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00df A[SYNTHETIC, Splitter:B:49:0x00df] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f4 A[SYNTHETIC, Splitter:B:58:0x00f4] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m858b(org.json.JSONObject r11, boolean r12) {
        /*
        // Method dump skipped, instructions count: 287
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMStoreManager.m858b(org.json.JSONObject, boolean):java.lang.String");
    }

    /* renamed from: a */
    public void mo517a(boolean z, boolean z2) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase a = UMDBManager.m840a(f755d).mo510a();
            a.beginTransaction();
            if (!z2) {
                int size = this.f763l.size();
                int i = 0;
                if (size > 0) {
                    int i2 = 0;
                    while (i < size) {
                        String str = (String) this.f763l.get(i);
                        if (str == null) {
                            i2 = 1;
                        }
                        a.execSQL("delete from __is where __ii=\"" + str + "\"");
                        i++;
                    }
                    i = i2;
                }
                if (i != 0) {
                    a.execSQL("delete from __is where __ii is null");
                }
            } else if (z) {
                a.execSQL("delete from __is");
            }
            a.setTransactionSuccessful();
            try {
                a.endTransaction();
            } catch (Throwable th) {
            }
        } catch (SQLiteDatabaseCorruptException e) {
            UMDBUtils.m846a(f755d);
            if (0 != 0) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th2) {
        }
        UMDBManager.m840a(f755d).mo511b();
    }

    /* renamed from: b */
    public void mo523b(boolean z, boolean z2) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase a = UMDBManager.m840a(f755d).mo510a();
            a.beginTransaction();
            if (z2) {
                if (z) {
                    a.execSQL("delete from __sd");
                }
            } else if (this.f760i.size() > 0) {
                for (int i = 0; i < this.f760i.size(); i++) {
                    a.execSQL("delete from __sd where __ii=\"" + ((String) this.f760i.get(i)) + "\"");
                }
            }
            a.setTransactionSuccessful();
            try {
                a.endTransaction();
            } catch (Throwable th) {
            }
        } catch (SQLiteDatabaseCorruptException e) {
            UMDBUtils.m846a(f755d);
            if (0 != 0) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th2) {
        }
        UMDBManager.m840a(f755d).mo511b();
    }

    /* renamed from: h */
    public void mo531h() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase a = UMDBManager.m840a(f755d).mo510a();
            a.beginTransaction();
            if (this.f761j.size() > 0) {
                for (int i = 0; i < this.f761j.size(); i++) {
                    a.execSQL("delete from __et where rowid=" + this.f761j.get(i));
                }
            }
            this.f761j.clear();
            a.setTransactionSuccessful();
            try {
                a.endTransaction();
            } catch (Throwable th) {
            }
        } catch (SQLiteDatabaseCorruptException e) {
            UMDBUtils.m846a(f755d);
            if (0 != 0) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th2) {
        }
        UMDBManager.m840a(f755d).mo511b();
    }

    /* renamed from: i */
    public void mo532i() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = UMDBManager.m840a(f755d).mo510a();
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("delete from __er");
            sQLiteDatabase.setTransactionSuccessful();
            try {
                sQLiteDatabase.endTransaction();
            } catch (Throwable th) {
            }
        } catch (SQLiteDatabaseCorruptException e) {
            UMDBUtils.m846a(f755d);
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th2) {
        }
        UMDBManager.m840a(f755d).mo511b();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005d A[SYNTHETIC, Splitter:B:14:0x005d] */
    /* renamed from: j */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo533j() {
        /*
        // Method dump skipped, instructions count: 146
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMStoreManager.mo533j():void");
    }

    /* renamed from: a */
    public void mo516a(boolean z, String str) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase a = UMDBManager.m840a(f755d).mo510a();
            a.beginTransaction();
            if (!TextUtils.isEmpty(str)) {
                a.execSQL("delete from __er where __i=\"" + str + "\"");
                a.execSQL("delete from __et where __i=\"" + str + "\"");
                this.f761j.clear();
                a.execSQL("delete from __sd where __ii=\"" + str + "\"");
            }
            a.setTransactionSuccessful();
            try {
                a.endTransaction();
            } catch (Throwable th) {
            }
        } catch (SQLiteDatabaseCorruptException e) {
            UMDBUtils.m846a(f755d);
            if (0 != 0) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th2) {
        }
        UMDBManager.m840a(f755d).mo511b();
    }

    /* renamed from: b */
    public void mo522b(String str) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            SQLiteDatabase a = UMDBManager.m840a(f755d).mo510a();
            a.beginTransaction();
            if (!TextUtils.isEmpty(str)) {
                a.execSQL("delete from __is where __ii=\"" + str + "\"");
            }
            a.setTransactionSuccessful();
            try {
                a.endTransaction();
            } catch (Throwable th) {
            }
        } catch (SQLiteDatabaseCorruptException e) {
            UMDBUtils.m846a(f755d);
            if (0 != 0) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable th2) {
        }
        UMDBManager.m840a(f755d).mo511b();
    }

    /* renamed from: l */
    private void m864l() {
        try {
            if (TextUtils.isEmpty(f756e)) {
                String multiProcessSP = UMUtils.getMultiProcessSP(f755d, f758g);
                if (TextUtils.isEmpty(multiProcessSP)) {
                    multiProcessSP = PreferenceWrapper.getDefault(f755d).getString(f758g, null);
                    if (TextUtils.isEmpty(multiProcessSP)) {
                        multiProcessSP = UMUtils.genId();
                    }
                    if (!TextUtils.isEmpty(multiProcessSP)) {
                        UMUtils.setMultiProcessSP(f755d, f758g, multiProcessSP);
                    }
                }
                if (!TextUtils.isEmpty(multiProcessSP)) {
                    String substring = multiProcessSP.substring(1, 9);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < substring.length(); i++) {
                        char charAt = substring.charAt(i);
                        if (!Character.isDigit(charAt)) {
                            sb.append(charAt);
                        } else if (Integer.parseInt(Character.toString(charAt)) == 0) {
                            sb.append(0);
                        } else {
                            sb.append(10 - Integer.parseInt(Character.toString(charAt)));
                        }
                    }
                    f756e = sb.toString();
                }
                if (!TextUtils.isEmpty(f756e)) {
                    f756e += new StringBuilder(f756e).reverse().toString();
                    String multiProcessSP2 = UMUtils.getMultiProcessSP(f755d, f759h);
                    if (TextUtils.isEmpty(multiProcessSP2)) {
                        UMUtils.setMultiProcessSP(f755d, f759h, mo524c(f757f));
                    } else if (!f757f.equals(mo526d(multiProcessSP2))) {
                        mo523b(true, false);
                        mo517a(true, false);
                        mo531h();
                        mo532i();
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: c */
    public String mo524c(String str) {
        try {
            if (TextUtils.isEmpty(f756e)) {
                return str;
            }
            return Base64.encodeToString(DataHelper.encrypt(str.getBytes(), f756e.getBytes()), 0);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: d */
    public String mo526d(String str) {
        try {
            if (TextUtils.isEmpty(f756e)) {
                return str;
            }
            return new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), f756e.getBytes()));
        } catch (Exception e) {
            if (Build.VERSION.SDK_INT >= 29 && !TextUtils.isEmpty(str)) {
                new JSONObject(str);
                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> UMStoreManager decrypt failed, return origin data.");
                return str;
            }
        } catch (Throwable th) {
        }
        return null;
    }
}
