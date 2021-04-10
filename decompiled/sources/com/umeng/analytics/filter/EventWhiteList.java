package com.umeng.analytics.filter;

import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;

/* renamed from: com.umeng.analytics.filter.b */
public class EventWhiteList extends EventList {

    /* renamed from: a */
    private SmartDict f236a;

    /* renamed from: b */
    private Object f237b = new Object();

    public EventWhiteList(String str, String str2) {
        super(str, str2);
    }

    @Override // com.umeng.analytics.filter.EventList
    public boolean matchHit(String str) {
        boolean a;
        if (TextUtils.isEmpty(this.mEventList)) {
            return true;
        }
        synchronized (this.f237b) {
            if (this.f236a == null) {
                this.f236a = new SmartDict(true, this.mEventList);
            }
            a = this.f236a.mo272a(str);
        }
        return a;
    }

    @Override // com.umeng.analytics.filter.EventList
    public void setMD5ClearFlag(boolean z) {
        AnalyticsConfig.CLEAR_EKV_WL = z;
    }

    /* access modifiers changed from: protected */
    @Override // com.umeng.analytics.filter.EventList
    public void eventListChange() {
        if (!TextUtils.isEmpty(this.mEventList)) {
            synchronized (this.f237b) {
                this.f236a = null;
                this.f236a = new SmartDict(true, this.mEventList);
            }
        }
    }
}
