package com.own.bless.soy;

import android.os.Handler;
import android.os.Message;

/* access modifiers changed from: package-private */
/* renamed from: com.own.bless.soy.t */
public class RequestManager implements Handler.Callback {
    RequestManager(C0033u this$0) {
    }

    public boolean handleMessage(Message msg) {
        int i = msg.what;
        if (i == 0) {
            RequestInfo requestInfo = (RequestInfo) msg.obj;
            C0027s.m214b(requestInfo.mo80c(), requestInfo.mo79b(), requestInfo.mo78a());
            return false;
        } else if (i != 1) {
            return false;
        } else {
            RequestInfo requestInfo2 = (RequestInfo) msg.obj;
            C0027s.m213a(requestInfo2.mo80c(), requestInfo2.mo79b(), requestInfo2.mo78a());
            return false;
        }
    }
}
