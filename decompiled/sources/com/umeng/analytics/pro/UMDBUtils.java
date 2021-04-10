package com.umeng.analytics.pro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.umeng.analytics.pro.g */
public class UMDBUtils {
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        if (0 == 0) goto L_0x0041;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m849a(java.lang.String r7, android.database.sqlite.SQLiteDatabase r8) {
        /*
            java.lang.String r0 = "table"
            r1 = 0
            if (r7 != 0) goto L_0x0006
            return r1
        L_0x0006:
            r2 = 0
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            r4[r1] = r0     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            java.lang.String r5 = r7.trim()     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            r6 = 1
            r4[r6] = r5     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            java.lang.String r4 = "select count(*) as c from sqlite_master where type=? and name=?"
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            r3[r1] = r0     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            java.lang.String r7 = r7.trim()     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            r3[r6] = r7     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            android.database.Cursor r2 = r8.rawQuery(r4, r3)     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            boolean r7 = r2.moveToNext()     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            if (r7 == 0) goto L_0x0031
            int r7 = r2.getInt(r1)     // Catch:{ Exception -> 0x003d, all -> 0x0036 }
            if (r7 <= 0) goto L_0x0031
            r1 = 1
        L_0x0031:
        L_0x0032:
            r2.close()
            goto L_0x0041
        L_0x0036:
            r7 = move-exception
            if (r2 == 0) goto L_0x003c
            r2.close()
        L_0x003c:
            throw r7
        L_0x003d:
            r7 = move-exception
            if (r2 == 0) goto L_0x0041
            goto L_0x0032
        L_0x0041:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMDBUtils.m849a(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
    }

    /* renamed from: a */
    public static void m846a(Context context) {
        if (context != null) {
            try {
                File databasePath = context.getDatabasePath(UMDBConfig.f678b);
                if (databasePath != null && databasePath.exists()) {
                    databasePath.delete();
                }
                C0101e.m829a(context).mo507a();
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: b */
    public static String m850b(Context context) {
        File databasePath = context.getDatabasePath(UMDBConfig.f678b);
        return databasePath.getParent() + File.separator;
    }

    /* renamed from: c */
    public static String m852c(Context context) {
        return m850b(context) + "subprocess/";
    }

    /* renamed from: a */
    public static String m844a(List list) {
        return TextUtils.join("!", list);
    }

    /* renamed from: a */
    public static List m845a(String str) {
        return new ArrayList(Arrays.asList(str.split("!")));
    }

    /* renamed from: b */
    public static List m851b(List list) {
        ArrayList arrayList = new ArrayList();
        try {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (Collections.frequency(arrayList, str) < 1) {
                    arrayList.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002f, code lost:
        if (r0.isClosed() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        if (r0.isClosed() == false) goto L_0x0031;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m848a(android.database.sqlite.SQLiteDatabase r4, java.lang.String r5, java.lang.String r6) {
        /*
            r0 = 0
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            r2.<init>()     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            java.lang.String r3 = "SELECT * FROM "
            r2.append(r3)     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            r2.append(r5)     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            java.lang.String r5 = " LIMIT 0"
            r2.append(r5)     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            android.database.Cursor r0 = r4.rawQuery(r5, r0)     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            if (r0 == 0) goto L_0x0029
            int r4 = r0.getColumnIndex(r6)     // Catch:{ Exception -> 0x0042, all -> 0x0035 }
            r5 = -1
            if (r4 == r5) goto L_0x0029
            r4 = 1
            r1 = 1
        L_0x0029:
            if (r0 == 0) goto L_0x004c
            boolean r4 = r0.isClosed()
            if (r4 != 0) goto L_0x004c
        L_0x0031:
            r0.close()
            goto L_0x004c
        L_0x0035:
            r4 = move-exception
            if (r0 == 0) goto L_0x0041
            boolean r5 = r0.isClosed()
            if (r5 != 0) goto L_0x0041
            r0.close()
        L_0x0041:
            throw r4
        L_0x0042:
            r4 = move-exception
            if (r0 == 0) goto L_0x004c
            boolean r4 = r0.isClosed()
            if (r4 != 0) goto L_0x004c
            goto L_0x0031
        L_0x004c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.UMDBUtils.m848a(android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String):boolean");
    }

    /* renamed from: a */
    public static void m847a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        sQLiteDatabase.execSQL("alter table " + str + " add " + str2 + " " + str3);
    }
}
