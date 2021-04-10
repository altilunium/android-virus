package com.own.bless.soy;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.umeng.analytics.pro.UContent;

/* access modifiers changed from: package-private */
/* renamed from: com.own.bless.soy.h3 */
public final class IdentifierIdClient extends Handler {
    IdentifierIdClient(Looper looper) {
        super(looper);
    }

    public void handleMessage(Message message) {
        if (message.what == 11) {
            String unused = C0016i3.f56i = C0016i3.f59l.mo49a(message.getData().getInt(UContent.f663y), message.getData().getString("appid"));
            synchronized (C0016i3.f53f) {
                C0016i3.f53f.notify();
            }
            return;
        }
        Log.e("VMS_IDLG_SDK_Client", "message type valid");
    }
}
