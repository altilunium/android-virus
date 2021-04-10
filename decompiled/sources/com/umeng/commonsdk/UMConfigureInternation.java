package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMModuleRegister;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.UMInternalConfig;
import com.umeng.commonsdk.internal.UMInternalData;
import com.umeng.commonsdk.statistics.common.ULog;

public class UMConfigureInternation {
    private static boolean isInternal = false;

    public static synchronized void sendInternal(final Context context) {
        synchronized (UMConfigureInternation.class) {
            if (context != null) {
                try {
                    if (!isInternal) {
                        new Thread(new Runnable() {
                            /* class com.umeng.commonsdk.UMConfigureInternation.RunnableC01401 */

                            public void run() {
                                try {
                                    String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                                    String packageName = context.getPackageName();
                                    if (!TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName)) {
                                        try {
                                            if (UMEnvelopeBuild.isReadyBuild(context, UMLogDataProtocol.UMBusinessType.U_INTERNAL)) {
                                                Context context = context;
                                                UMWorkDispatch.sendEvent(context, UMInternalConfig.f1219n, UMInternalData.m1179a(context).mo677a(), null);
                                            }
                                        } catch (Throwable th) {
                                        }
                                    }
                                } catch (Throwable th2) {
                                }
                            }
                        }).start();
                        isInternal = true;
                    }
                } catch (Throwable th) {
                    ULog.m1390e(UMModuleRegister.INNER, "e is " + th.getMessage());
                }
            }
        }
        return;
    }

    public static void doSelfCheck(Context context) {
        try {
            String currentProcessName = UMFrUtils.getCurrentProcessName(context);
            String packageName = context.getPackageName();
            if (!TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName)) {
                try {
                    UMWorkDispatch.sendEvent(context, UMInternalConfig.f1231z, UMInternalData.m1179a(context).mo677a(), null);
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}
