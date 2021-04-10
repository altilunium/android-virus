package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* renamed from: com.umeng.commonsdk.statistics.idtracking.g */
public class MacTracker extends AbstractIdTracker {

    /* renamed from: a */
    private static final String f1449a = "mac";

    /* renamed from: b */
    private Context f1450b;

    public MacTracker(Context context) {
        super(f1449a);
        this.f1450b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractIdTracker
    /* renamed from: f */
    public String mo788f() {
        try {
            return DeviceConfig.getMac(this.f1450b);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                e.printStackTrace();
            }
            UMCrashManager.reportCrash(this.f1450b, e);
            return null;
        }
    }
}
