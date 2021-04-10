package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* renamed from: com.umeng.commonsdk.statistics.idtracking.b */
public class AndroidIdTracker extends AbstractIdTracker {

    /* renamed from: a */
    private static final String f1429a = "android_id";

    /* renamed from: b */
    private Context f1430b;

    public AndroidIdTracker(Context context) {
        super(f1429a);
        this.f1430b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractIdTracker
    /* renamed from: f */
    public String mo788f() {
        return DeviceConfig.getAndroidId(this.f1430b);
    }
}
