package com.umeng.commonsdk.internal.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.UMInternalConfig;
import com.umeng.commonsdk.internal.UMInternalData;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONObject;

/* renamed from: com.umeng.commonsdk.internal.utils.c */
public class BatteryUtils {

    /* renamed from: a */
    private static final String f1271a = "BatteryUtils";

    /* renamed from: b */
    private static boolean f1272b = false;

    /* renamed from: c */
    private static Context f1273c = null;

    /* renamed from: d */
    private BroadcastReceiver f1274d;

    private BatteryUtils() {
        this.f1274d = new BroadcastReceiver() {
            /* class com.umeng.commonsdk.internal.utils.BatteryUtils.C01701 */

            public void onReceive(Context context, Intent intent) {
                try {
                    if (intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("le", intent.getIntExtra("level", 0));
                        } catch (Exception e) {
                        }
                        try {
                            jSONObject.put("vol", intent.getIntExtra("voltage", 0));
                        } catch (Exception e2) {
                        }
                        try {
                            jSONObject.put("temp", intent.getIntExtra("temperature", 0));
                            jSONObject.put("ts", System.currentTimeMillis());
                        } catch (Exception e3) {
                        }
                        int intExtra = intent.getIntExtra("status", 0);
                        int i = -1;
                        int i2 = 2;
                        if (intExtra != 1) {
                            if (intExtra == 2) {
                                i = 1;
                            } else if (intExtra == 4) {
                                i = 0;
                            } else if (intExtra == 5) {
                                i = 2;
                            }
                        }
                        try {
                            jSONObject.put("st", i);
                        } catch (Exception e4) {
                        }
                        int intExtra2 = intent.getIntExtra("plugged", 0);
                        if (intExtra2 == 1) {
                            i2 = 1;
                        } else if (intExtra2 != 2) {
                            i2 = 0;
                        }
                        try {
                            jSONObject.put("ct", i2);
                            jSONObject.put("ts", System.currentTimeMillis());
                        } catch (Exception e5) {
                        }
                        ULog.m1396i(BatteryUtils.f1271a, jSONObject.toString());
                        UMWorkDispatch.sendEvent(context, UMInternalConfig.f1213h, UMInternalData.m1179a(BatteryUtils.f1273c).mo677a(), jSONObject.toString());
                        BatteryUtils.this.mo689c();
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(BatteryUtils.f1273c, th);
                }
            }
        };
    }

    /* renamed from: com.umeng.commonsdk.internal.utils.c$a */
    /* compiled from: BatteryUtils */
    class C0171a {

        /* renamed from: a */
        private static final BatteryUtils f1276a = new BatteryUtils();

        private C0171a() {
        }
    }

    /* renamed from: a */
    public static BatteryUtils m1249a(Context context) {
        if (f1273c == null && context != null) {
            f1273c = context.getApplicationContext();
        }
        return C0171a.f1276a;
    }

    /* renamed from: a */
    public synchronized boolean mo687a() {
        return f1272b;
    }

    /* renamed from: b */
    public synchronized void mo688b() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            f1273c.registerReceiver(this.f1274d, intentFilter);
            f1272b = true;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(f1273c, th);
        }
        return;
    }

    /* renamed from: c */
    public synchronized void mo689c() {
        try {
            f1273c.unregisterReceiver(this.f1274d);
            f1272b = false;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(f1273c, th);
        }
        return;
    }
}
