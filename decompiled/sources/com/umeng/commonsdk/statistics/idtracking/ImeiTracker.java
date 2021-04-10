package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* renamed from: com.umeng.commonsdk.statistics.idtracking.f */
public class ImeiTracker extends AbstractIdTracker {

    /* renamed from: a */
    private static final String f1447a = "imei";

    /* renamed from: b */
    private Context f1448b;

    public ImeiTracker(Context context) {
        super(f1447a);
        this.f1448b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractIdTracker
    /* renamed from: f */
    public String mo788f() {
        return DeviceConfig.getImeiNew(this.f1448b);
    }
}
