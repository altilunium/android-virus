package com.umeng.commonsdk.statistics.idtracking;

import com.umeng.commonsdk.statistics.common.DeviceConfig;

/* renamed from: com.umeng.commonsdk.statistics.idtracking.k */
public class SerialTracker extends AbstractIdTracker {

    /* renamed from: a */
    private static final String f1462a = "serial";

    public SerialTracker() {
        super(f1462a);
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractIdTracker
    /* renamed from: f */
    public String mo788f() {
        return DeviceConfig.getSerial();
    }
}
