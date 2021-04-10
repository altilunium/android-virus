package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.analytics.pro.UMCommonContent;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;

/* renamed from: com.umeng.commonsdk.statistics.idtracking.h */
public class NewUMIDTracker extends AbstractIdTracker {

    /* renamed from: a */
    private static final String f1451a = "newumid";

    /* renamed from: b */
    private Context f1452b;

    public NewUMIDTracker(Context context) {
        super(f1451a);
        this.f1452b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractIdTracker
    /* renamed from: f */
    public String mo788f() {
        return UMEnvelopeBuild.imprintProperty(this.f1452b, UMCommonContent.f358g, null);
    }
}
