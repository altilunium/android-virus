package com.umeng.analytics.filter;

import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;

/* renamed from: com.umeng.analytics.filter.a */
public class EventBlackList extends EventList {

    /* renamed from: a */
    private SmartDict f234a;

    /* renamed from: b */
    private Object f235b = new Object();

    public EventBlackList(String str, String str2) {
        super(str, str2);
    }

    @Override // com.umeng.analytics.filter.EventList
    public boolean matchHit(String str) {
        boolean a;
        if (TextUtils.isEmpty(this.mEventList)) {
            return false;
        }
        synchronized (this.f235b) {
            if (this.f234a == null) {
                this.f234a = new SmartDict(false, this.mEventList);
            }
            a = this.f234a.mo272a(str);
        }
        return a;
    }

    @Override // com.umeng.analytics.filter.EventList
    public void setMD5ClearFlag(boolean z) {
        AnalyticsConfig.CLEAR_EKV_BL = z;
    }

    /* access modifiers changed from: protected */
    @Override // com.umeng.analytics.filter.EventList
    public void eventListChange() {
        if (!TextUtils.isEmpty(this.mEventList)) {
            synchronized (this.f235b) {
                this.f234a = null;
                this.f234a = new SmartDict(false, this.mEventList);
            }
        }
    }
}
