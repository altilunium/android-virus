package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.utils.UMConstant;

/* renamed from: com.umeng.commonsdk.statistics.idtracking.l */
public class UTDIdTracker extends AbstractIdTracker {

    /* renamed from: a */
    private static final String f1463a = "utdid";

    /* renamed from: b */
    private Context f1464b;

    public UTDIdTracker(Context context) {
        super(f1463a);
        this.f1464b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractIdTracker
    /* renamed from: f */
    public String mo788f() {
        try {
            if (!FieldManager.allow(UMConstant.f1655u)) {
                return null;
            }
            return (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", Context.class).invoke(null, this.f1464b);
        } catch (Exception e) {
            return null;
        }
    }
}
