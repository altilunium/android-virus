package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* renamed from: com.umeng.commonsdk.statistics.idtracking.d */
public class IDMD5Tracker extends AbstractIdTracker {

    /* renamed from: a */
    private static final String f1433a = "idmd5";

    /* renamed from: b */
    private Context f1434b;

    public IDMD5Tracker(Context context) {
        super("idmd5");
        this.f1434b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractIdTracker
    /* renamed from: f */
    public String mo788f() {
        return DeviceConfig.getDeviceIdUmengMD5(this.f1434b);
    }
}
