package com.xdandroid.hellodaemon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WakeUpReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent != null && "com.xdandroid.hellodaemon.CANCEL_JOB_ALARM_SUB".equals(intent.getAction())) {
            WatchDogService.m1812a();
        } else if (C0253b.f1700d) {
            C0253b.m1817c(C0253b.f1698b);
        }
    }

    public class WakeUpAutoStartReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (C0253b.f1700d) {
                C0253b.m1817c(C0253b.f1698b);
            }
        }
    }
}
