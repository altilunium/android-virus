package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMModuleRegister;
import com.umeng.commonsdk.internal.UMInternalManager;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.internal.utils.ApplicationLayerUtil;
import com.umeng.commonsdk.internal.utils.BaseStationUtils;
import com.umeng.commonsdk.internal.utils.BatteryUtils;
import com.umeng.commonsdk.internal.utils.UMProbe;
import com.umeng.commonsdk.statistics.common.ULog;

public class UMInnerImpl {
    private static boolean isInternal = false;

    private static synchronized void sendInternal(final Context context) {
        synchronized (UMInnerImpl.class) {
            if (context != null) {
                try {
                    new Thread(new Runnable() {
                        /* class com.umeng.commonsdk.UMInnerImpl.RunnableC01411 */

                        public void run() {
                            try {
                                String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                                String packageName = context.getPackageName();
                                if (!TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName)) {
                                    try {
                                        UMInternalManager.m1203b(context);
                                    } catch (Throwable th) {
                                        ULog.m1390e(UMModuleRegister.INNER, "e is " + th);
                                    }
                                }
                            } catch (Throwable th2) {
                                UMCrashManager.reportCrash(context, th2);
                            }
                        }
                    }).start();
                    isInternal = true;
                } catch (Throwable th) {
                    ULog.m1390e(UMModuleRegister.INNER, "e is " + th.getMessage());
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }
        return;
    }

    public static synchronized void initAndSendInternal(final Context context) {
        synchronized (UMInnerImpl.class) {
            if (context != null) {
                try {
                    if (!isInternal) {
                        new Thread(new Runnable() {
                            /* class com.umeng.commonsdk.UMInnerImpl.RunnableC01422 */

                            public void run() {
                                try {
                                    String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                                    String packageName = context.getPackageName();
                                    if (!TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName)) {
                                        try {
                                            if (!BatteryUtils.m1249a(context).mo687a()) {
                                                BatteryUtils.m1249a(context).mo688b();
                                            }
                                        } catch (Throwable th) {
                                            ULog.m1390e(UMModuleRegister.INNER, "e is " + th);
                                        }
                                        try {
                                            UMProbe.m1282b(context);
                                        } catch (Throwable th2) {
                                            ULog.m1390e(UMModuleRegister.INNER, "e is " + th2);
                                        }
                                        try {
                                            ApplicationLayerUtil.m1223c(context);
                                        } catch (Throwable th3) {
                                            ULog.m1390e(UMModuleRegister.INNER, "e is " + th3);
                                        }
                                        try {
                                            if (!BaseStationUtils.m1241a(context).mo683a()) {
                                                BaseStationUtils.m1241a(context).mo684b();
                                            }
                                        } catch (Throwable th4) {
                                            ULog.m1390e(UMModuleRegister.INNER, "get station is null ");
                                        }
                                    }
                                } catch (Throwable th5) {
                                    UMCrashManager.reportCrash(context, th5);
                                }
                            }
                        }).start();
                        isInternal = true;
                        sendInternal(context);
                    }
                } catch (Throwable th) {
                    ULog.m1390e(UMModuleRegister.INNER, "e is " + th.getMessage());
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }
        return;
    }
}
