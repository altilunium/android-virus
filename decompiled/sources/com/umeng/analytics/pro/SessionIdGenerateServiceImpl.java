package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;

/* access modifiers changed from: package-private */
/* renamed from: com.umeng.analytics.pro.w */
public class SessionIdGenerateServiceImpl implements SessionIdGenerateService {

    /* renamed from: a */
    private long f972a = AnalyticsConfig.kContinueSessionMillis;

    SessionIdGenerateServiceImpl() {
    }

    @Override // com.umeng.analytics.pro.SessionIdGenerateService
    /* renamed from: a */
    public void mo608a(long j) {
        this.f972a = j;
    }

    @Override // com.umeng.analytics.pro.SessionIdGenerateService
    /* renamed from: a */
    public long mo606a() {
        return this.f972a;
    }

    @Override // com.umeng.analytics.pro.SessionIdGenerateService
    /* renamed from: a */
    public String mo607a(Context context) {
        String appkey = UMUtils.getAppkey(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (appkey != null) {
            return UMUtils.MD5(currentTimeMillis + appkey + "02:00:00:00:00:00");
        }
        throw new RuntimeException("Appkey is null or empty, Please check!");
    }

    @Override // com.umeng.analytics.pro.SessionIdGenerateService
    /* renamed from: a */
    public boolean mo610a(long j, long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        if ((j == 0 || currentTimeMillis - j >= this.f972a) && j2 > 0 && currentTimeMillis - j2 > this.f972a) {
            return true;
        }
        return false;
    }

    @Override // com.umeng.analytics.pro.SessionIdGenerateService
    /* renamed from: a */
    public void mo609a(Context context, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            SharedPreferences.Editor edit = PreferenceWrapper.getDefault(context).edit();
            edit.putString("session_id", str);
            edit.putLong(SessionTracker.f953b, 0);
            edit.putLong(SessionTracker.f956e, currentTimeMillis);
            edit.putLong(SessionTracker.f957f, 0);
            edit.commit();
        } catch (Exception e) {
        }
    }
}
