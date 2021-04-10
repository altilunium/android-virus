package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.statistics.common.AdvertisingId;
import com.umeng.commonsdk.utils.UMConstant;

/* renamed from: com.umeng.commonsdk.statistics.idtracking.c */
public class IDFATracker extends AbstractIdTracker {

    /* renamed from: a */
    private static final String f1431a = "idfa";

    /* renamed from: b */
    private Context f1432b;

    public IDFATracker(Context context) {
        super(f1431a);
        this.f1432b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractIdTracker
    /* renamed from: f */
    public String mo788f() {
        String str;
        if (FieldManager.allow(UMConstant.f1657w)) {
            str = AdvertisingId.m1411a(this.f1432b);
        } else {
            str = null;
        }
        return str == null ? "" : str;
    }
}
