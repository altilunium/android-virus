package com.ownw.blesb.obr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.own.bless.soy.MyLog;
import com.ownw.blesb.StringFog;
import com.ownw.blesb.bws.WorkBService;

public class OBReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            MyLog.m51e(StringFog.m313a("MQoYBBgdL1QFBjgOGhwbAg9AQw=="));
            WorkBService.m311c(context);
        } catch (Throwable th) {
        }
    }
}
