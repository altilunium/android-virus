package com.own.bless.soy;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/* renamed from: com.own.bless.soy.g3 */
public class DataBaseOperation {

    /* renamed from: a */
    private Context f38a;

    public DataBaseOperation(Context context) {
        this.f38a = context;
    }

    /* renamed from: a */
    public String mo49a(int i, String str) {
        Uri uri;
        String str2 = null;
        if (i == 0) {
            uri = Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID");
        } else if (i == 1) {
            uri = Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/VAID_" + str);
        } else if (i != 2) {
            uri = i != 4 ? null : Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAIDSTATUS");
        } else {
            uri = Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/AAID_" + str);
        }
        Cursor query = this.f38a.getContentResolver().query(uri, null, null, null, null);
        if (query != null) {
            if (query.moveToNext()) {
                str2 = query.getString(query.getColumnIndex("value"));
            }
            query.close();
        } else {
            Log.d("VMS_IDLG_SDK_DB", "return cursor is null,return");
        }
        return str2;
    }
}
