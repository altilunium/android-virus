package com.umeng.analytics;

import android.content.Context;
import com.umeng.analytics.pro.CoreProtocolImpl;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMSenderStateNotify;
import org.json.JSONObject;

public class CoreProtocol implements UMLogDataProtocol, UMSenderStateNotify {

    /* renamed from: a */
    private static Context f187a = null;

    private CoreProtocol() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.CoreProtocol$a */
    public class C0059a {

        /* renamed from: a */
        private static final CoreProtocol f188a = new CoreProtocol();

        private C0059a() {
        }
    }

    public static CoreProtocol getInstance(Context context) {
        if (f187a == null && context != null) {
            f187a = context.getApplicationContext();
        }
        return C0059a.f188a;
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void workEvent(Object obj, int i) {
        CoreProtocolImpl.m921a(f187a).mo559a(obj, i);
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void removeCacheData(Object obj) {
        CoreProtocolImpl.m921a(f187a).mo558a(obj);
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public JSONObject setupReportData(long j) {
        return CoreProtocolImpl.m921a(f187a).mo556a(j);
    }

    @Override // com.umeng.commonsdk.framework.UMSenderStateNotify
    public void onConnectionAvailable() {
        CoreProtocolImpl.m921a(f187a).mo557a();
    }

    @Override // com.umeng.commonsdk.framework.UMSenderStateNotify
    public void onSenderIdle() {
        CoreProtocolImpl.m921a(f187a).mo564b();
    }
}
