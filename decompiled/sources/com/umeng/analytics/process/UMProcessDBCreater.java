package com.umeng.analytics.process;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.umeng.analytics.pro.UMDBUtils;
import java.io.File;

/* access modifiers changed from: package-private */
/* renamed from: com.umeng.analytics.process.b */
public class UMProcessDBCreater extends SQLiteOpenHelper {
    UMProcessDBCreater(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    /* renamed from: a */
    static UMProcessDBCreater m1072a(Context context, String str) {
        String b = m1075b(context, str);
        if (DBConstant.f1007h.equals(str)) {
        }
        return new UMProcessDBCreater(context, b, null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        m1074a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    /* renamed from: a */
    private void m1074a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table if not exists __et_p(id INTEGER primary key autoincrement, __i TEXT, __e TEXT, __s TEXT, __t INTEGER, __pn TEXT, __av TEXT, __vc TEXT)");
        } catch (SQLException e) {
        }
    }

    /* renamed from: a */
    public static String m1073a(Context context) {
        return UMDBUtils.m850b(context) + "subprocess/";
    }

    /* renamed from: b */
    public static String m1075b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = DBConstant.f1007h;
        }
        String str2 = UMDBUtils.m850b(context) + "subprocess/";
        if (DBConstant.f1007h.equals(str)) {
            str2 = UMDBUtils.m850b(context);
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return String.format(str2 + DBConstant.f1004e, str);
    }
}
