package com.own.bless.soy;

import android.text.TextUtils;
import com.ownw.blesb.StringFog;

/* access modifiers changed from: package-private */
/* renamed from: com.own.bless.soy.v0 */
/* compiled from: NetManager */
public class C0035v0 implements HttpNetUtil {

    /* renamed from: a */
    final /* synthetic */ C0039x0 f117a;

    C0035v0(C0039x0 this$0) {
        this.f117a = this$0;
    }

    @Override // com.own.bless.soy.HttpNetUtil
    /* renamed from: a */
    public void mo101a(Throwable paramThrowable) {
        MyLog.m51e(StringFog.m313a("GA0bHhwKBlRXVQUFPxgbGB8aDw=="));
        this.f117a.f125a = false;
        int count = PrefUtil.m145a(this.f117a.f127c).mo61b(StringFog.m313a("GA0bDRgQHjofBQ=="), 0) + 1;
        if (count > 6) {
            count = 1;
        }
        PrefUtil.m145a(this.f117a.f127c).mo64e(StringFog.m313a("GA0bDRgQHjofBQ=="), count);
        long time = 2400000 - (((long) ((count * 5) * 60)) * 1000);
        if (time < 0) {
            time = 0;
        }
        PrefUtil.m145a(this.f117a.f127c).mo65f(StringFog.m313a("GA0bJxgKBiADBQ8="), System.currentTimeMillis() - time);
    }

    @Override // com.own.bless.soy.HttpNetUtil
    public void onSuccess(String response) {
        ResInfo resInfo;
        MyLog.m51e(StringFog.m313a("GA0bHhwKBlRXVQUFKgwRFw8bGQ=="));
        PrefUtil.m145a(this.f117a.f127c).mo65f(StringFog.m313a("GA0bJxgKBiADBQ8="), System.currentTimeMillis());
        PrefUtil.m145a(this.f117a.f127c).mo64e(StringFog.m313a("GA0bDRgQHjofBQ=="), 0);
        this.f117a.f125a = false;
        if (!TextUtils.isEmpty(response) && (resInfo = this.f117a.m253j(response)) != null) {
            long reqCycle = ((long) resInfo.f94a) * 1000;
            if (reqCycle < 1800000) {
                reqCycle = 1800000;
            } else if (reqCycle > 10800000) {
                reqCycle = 10800000;
            }
            PrefUtil.m145a(this.f117a.f127c).mo65f(StringFog.m313a("GA0bKAAaHhE="), reqCycle);
            C0006b1.m36g(this.f117a.f127c).mo33e(resInfo);
        }
    }
}
