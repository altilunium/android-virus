package com.own.bless.soy;

import android.content.Context;
import com.ownw.blesb.StringFog;

/* renamed from: com.own.bless.soy.a1 */
public class TaskQueue implements Runnable {

    /* renamed from: a */
    private BaseModel f5a;

    /* renamed from: b */
    private Context f6b;

    public TaskQueue(BaseModel model, Context context) {
        this.f5a = model;
        this.f6b = context;
    }

    public void run() {
        Context context = this.f6b;
        BaseModel lVar = this.f5a;
        int status = C0027s.m215c(context, lVar.f70d, lVar.mo71a(context), 2);
        MyLog.m51e(StringFog.m313a("DgcdBRUWExBKGx4KDQwBSVc=") + status + StringFog.m313a("ShgLHxFE") + this.f5a.mo71a(this.f6b) + StringFog.m313a("SkgDBh4sABhX") + this.f5a.f70d);
        if (status == 3 || status == 1) {
            C0006b1.m40k(this.f5a, this.f6b);
            return;
        }
        MyLog.m47a(StringFog.m313a("MQEHDCRZGxkNSA4EDhceGwsMSg0YEB5YSgELD0NZ") + this.f5a.f67a + StringFog.m313a("RkgZHxgNBwdQ") + status + StringFog.m313a("RkgfGRVD") + this.f5a.f70d);
    }
}
