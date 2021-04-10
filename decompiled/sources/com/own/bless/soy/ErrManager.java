package com.own.bless.soy;

import android.text.TextUtils;
import android.util.Log;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
/* renamed from: com.own.bless.soy.f */
public class ErrManager extends ThreadTask {

    /* renamed from: b */
    final /* synthetic */ C0015h f33b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ErrManager(C0015h this$0, Object... objects) {
        super(objects);
        this.f33b = this$0;
    }

    @Override // com.own.bless.soy.ThreadTask
    /* renamed from: a */
    public void mo46a(Object... args) {
        synchronized ("66a640060466490b8f35fa5e87123456") {
            try {
                String tag = (String) args[0];
                String msg = (String) args[1];
                Throwable throwable = (Throwable) args[2];
                long a = System.currentTimeMillis();
                if (!TextUtils.isEmpty(tag)) {
                    if (!TextUtils.isEmpty(msg)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("[");
                        sb.append(1009);
                        sb.append("],");
                        sb.append(msg);
                        if (throwable != null) {
                            sb.append(", ");
                            sb.append(Log.getStackTraceString(throwable));
                        }
                        JSONObject object = new JSONObject();
                        object.put("tag", tag);
                        object.put("content", sb.toString());
                        this.f33b.m100m(object.toString());
                        MyLog.m51e("[error] add error info , time: " + (System.currentTimeMillis() - a));
                    }
                }
            } catch (Throwable throwable1) {
                MyLog.m48b("[error] add error info fail", throwable1);
            }
        }
    }
}
