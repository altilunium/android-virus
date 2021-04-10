package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.utils.UMConstant;

/* renamed from: com.umeng.commonsdk.statistics.idtracking.i */
public class OaidTracking extends AbstractIdTracker {

    /* renamed from: a */
    public static final String f1453a = "umeng_sp_oaid";

    /* renamed from: b */
    public static final String f1454b = "key_umeng_sp_oaid";

    /* renamed from: c */
    public static final String f1455c = "key_umeng_sp_oaid_required_time";

    /* renamed from: d */
    private static final String f1456d = "oaid";

    /* renamed from: e */
    private Context f1457e;

    public OaidTracking(Context context) {
        super(f1456d);
        this.f1457e = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractIdTracker
    /* renamed from: f */
    public String mo788f() {
        if (!FieldManager.allow(UMConstant.f1602G)) {
            return null;
        }
        try {
            SharedPreferences sharedPreferences = this.f1457e.getSharedPreferences(f1453a, 0);
            if (sharedPreferences != null) {
                return sharedPreferences.getString(f1454b, "");
            }
            return null;
        } catch (Throwable th) {
            return null;
        }
    }
}
