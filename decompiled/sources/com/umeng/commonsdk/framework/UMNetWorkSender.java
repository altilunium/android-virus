package com.umeng.commonsdk.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.FileObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.UMInternalConfig;
import com.umeng.commonsdk.internal.UMInternalData;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.NetWorkManager;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* access modifiers changed from: package-private */
/* renamed from: com.umeng.commonsdk.framework.a */
public class UMNetWorkSender implements UMImprintChangeCallback {

    /* renamed from: a */
    private static HandlerThread f1177a = null;

    /* renamed from: b */
    private static Handler f1178b = null;

    /* renamed from: c */
    private static Handler f1179c = null;

    /* renamed from: d */
    private static final int f1180d = 200;

    /* renamed from: e */
    private static final int f1181e = 273;

    /* renamed from: f */
    private static final int f1182f = 274;

    /* renamed from: g */
    private static final int f1183g = 512;

    /* renamed from: h */
    private static final int f1184h = 769;

    /* renamed from: i */
    private static FileObserverC0160a f1185i = null;

    /* renamed from: j */
    private static ConnectivityManager f1186j = null;

    /* renamed from: k */
    private static NetworkInfo f1187k = null;

    /* renamed from: l */
    private static IntentFilter f1188l = null;

    /* renamed from: m */
    private static boolean f1189m = false;

    /* renamed from: n */
    private static ArrayList f1190n = null;

    /* renamed from: o */
    private static Object f1191o = new Object();

    /* renamed from: p */
    private static ReentrantLock f1192p = new ReentrantLock();

    /* renamed from: q */
    private static final String f1193q = "report_policy";

    /* renamed from: r */
    private static final String f1194r = "report_interval";

    /* renamed from: s */
    private static boolean f1195s = false;

    /* renamed from: t */
    private static final int f1196t = 15;

    /* renamed from: u */
    private static final int f1197u = 3;

    /* renamed from: v */
    private static final int f1198v = 90;

    /* renamed from: w */
    private static int f1199w = f1196t;

    /* renamed from: x */
    private static Object f1200x = new Object();

    /* renamed from: y */
    private static BroadcastReceiver f1201y = new BroadcastReceiver() {
        /* class com.umeng.commonsdk.framework.UMNetWorkSender.C01581 */

        public void onReceive(Context context, Intent intent) {
            int size;
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                Context appContext = UMModuleRegister.getAppContext();
                try {
                    if (UMNetWorkSender.f1186j != null) {
                        NetworkInfo unused = UMNetWorkSender.f1187k = UMNetWorkSender.f1186j.getActiveNetworkInfo();
                        if (UMNetWorkSender.f1187k == null || !UMNetWorkSender.f1187k.isAvailable()) {
                            ULog.m1393i("--->>> network disconnected.");
                            boolean unused2 = UMNetWorkSender.f1189m = false;
                            return;
                        }
                        ULog.m1393i("--->>> network isAvailable, check if there are any files to send.");
                        boolean unused3 = UMNetWorkSender.f1189m = true;
                        synchronized (UMNetWorkSender.f1191o) {
                            if (UMNetWorkSender.f1190n != null && (size = UMNetWorkSender.f1190n.size()) > 0) {
                                for (int i = 0; i < size; i++) {
                                    ((UMSenderStateNotify) UMNetWorkSender.f1190n.get(i)).onConnectionAvailable();
                                }
                            }
                        }
                        UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "网络状态通知：尝试发送 MSG_PROCESS_NEXT");
                        UMNetWorkSender.m1164d();
                        if (UMNetWorkSender.f1187k.getType() == 1 && context != null) {
                            try {
                                if (!UMWorkDispatch.eventHasExist(UMInternalConfig.f1216k)) {
                                    UMWorkDispatch.sendEvent(context, UMInternalConfig.f1216k, UMInternalData.m1179a(context).mo677a(), null);
                                }
                            } catch (Throwable th) {
                            }
                        }
                    }
                } catch (Throwable th2) {
                    UMCrashManager.reportCrash(appContext, th2);
                }
            }
        }
    };

    /* renamed from: a */
    public static void m1157a(UMSenderStateNotify uMSenderStateNotify) {
        synchronized (f1191o) {
            try {
                if (f1190n == null) {
                    f1190n = new ArrayList();
                }
                if (uMSenderStateNotify != null) {
                    for (int i = 0; i < f1190n.size(); i++) {
                        if (uMSenderStateNotify == f1190n.get(i)) {
                            UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> addConnStateObserver: input item has exist.");
                            return;
                        }
                    }
                    f1190n.add(uMSenderStateNotify);
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(UMModuleRegister.getAppContext(), th);
            }
        }
    }

    /* renamed from: a */
    public static boolean m1158a() {
        boolean z;
        synchronized (f1200x) {
            z = f1195s;
        }
        return z;
    }

    /* renamed from: b */
    public static int m1160b() {
        int i;
        synchronized (f1200x) {
            i = f1199w;
        }
        return i;
    }

    /* renamed from: n */
    private void m1174n() {
        synchronized (f1200x) {
            if ("11".equals(UMEnvelopeBuild.imprintProperty(UMModuleRegister.getAppContext(), f1193q, ""))) {
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> switch to report_policy 11");
                f1195s = true;
                f1199w = f1196t;
                int intValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(UMModuleRegister.getAppContext(), f1194r, "15")).intValue();
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> set report_interval value to: " + intValue);
                if (intValue >= 3) {
                    if (intValue <= f1198v) {
                        f1199w = intValue * 1000;
                    }
                }
                f1199w = f1196t;
            } else {
                f1195s = false;
            }
        }
    }

    @Override // com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback
    public void onImprintValueChanged(String str, String str2) {
        synchronized (f1200x) {
            if (f1193q.equals(str)) {
                if ("11".equals(str2)) {
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> switch to report_policy 11");
                    f1195s = true;
                } else {
                    f1195s = false;
                }
            }
            if (f1194r.equals(str)) {
                int intValue = Integer.valueOf(str2).intValue();
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> set report_interval value to: " + intValue);
                if (intValue >= 3) {
                    if (intValue <= f1198v) {
                        f1199w = intValue * 1000;
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> really set report_interval value to: " + f1199w);
                    }
                }
                f1199w = 15000;
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> really set report_interval value to: " + f1199w);
            }
        }
    }

    public UMNetWorkSender(Context context, Handler handler) {
        Context appContext = UMModuleRegister.getAppContext();
        f1186j = (ConnectivityManager) appContext.getSystemService("connectivity");
        f1179c = handler;
        try {
            if (f1177a == null) {
                HandlerThread handlerThread = new HandlerThread("NetWorkSender");
                f1177a = handlerThread;
                handlerThread.start();
                if (f1185i == null) {
                    FileObserverC0160a aVar = new FileObserverC0160a(UMFrUtils.getEnvelopeDirPath(context));
                    f1185i = aVar;
                    aVar.startWatching();
                    ULog.m1381d("--->>> FileMonitor has already started!");
                }
                if (DeviceConfig.checkPermission(appContext, "android.permission.ACCESS_NETWORK_STATE") && f1186j != null && f1188l == null) {
                    IntentFilter intentFilter = new IntentFilter();
                    f1188l = intentFilter;
                    intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                    BroadcastReceiver broadcastReceiver = f1201y;
                    if (broadcastReceiver != null) {
                        appContext.registerReceiver(broadcastReceiver, f1188l);
                    }
                }
                m1174n();
                if (f1178b == null) {
                    f1178b = new Handler(f1177a.getLooper()) {
                        /* class com.umeng.commonsdk.framework.UMNetWorkSender.HandlerC01592 */

                        public void handleMessage(Message message) {
                            ReentrantLock reentrantLock;
                            int i = message.what;
                            if (i == 273) {
                                ULog.m1381d("--->>> handleMessage: recv MSG_PROCESS_NEXT msg.");
                                try {
                                    UMNetWorkSender.f1192p.tryLock(1, TimeUnit.SECONDS);
                                    try {
                                        UMNetWorkSender.m1178r();
                                        reentrantLock = UMNetWorkSender.f1192p;
                                    } catch (Throwable th) {
                                        reentrantLock = UMNetWorkSender.f1192p;
                                    }
                                    reentrantLock.unlock();
                                } catch (Throwable th2) {
                                }
                            } else if (i == UMNetWorkSender.f1182f) {
                                UMNetWorkSender.m1176p();
                            } else if (i == UMNetWorkSender.f1183g) {
                                UMNetWorkSender.m1177q();
                            }
                        }
                    };
                }
                ImprintHandler.getImprintService(context).registImprintCallback(f1193q, this);
                ImprintHandler.getImprintService(context).registImprintCallback(f1194r, this);
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
    }

    /* renamed from: o */
    private static void m1175o() {
        if (f1177a != null) {
            f1177a = null;
        }
        if (f1178b != null) {
            f1178b = null;
        }
        if (f1179c != null) {
            f1179c = null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: p */
    public static void m1176p() {
        int size;
        synchronized (f1191o) {
            ArrayList arrayList = f1190n;
            if (arrayList != null && (size = arrayList.size()) > 0) {
                for (int i = 0; i < size; i++) {
                    ((UMSenderStateNotify) f1190n.get(i)).onSenderIdle();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: q */
    public static void m1177q() {
    }

    /* renamed from: c */
    public static void m1162c() {
    }

    /* renamed from: b */
    private static void m1161b(int i) {
        Handler handler;
        if (f1189m && (handler = f1178b) != null && !handler.hasMessages(i)) {
            Message obtainMessage = f1178b.obtainMessage();
            obtainMessage.what = i;
            f1178b.sendMessage(obtainMessage);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static void m1163c(int i) {
        Handler handler;
        if (f1189m && (handler = f1178b) != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = i;
            f1178b.sendMessage(obtainMessage);
        }
    }

    /* renamed from: a */
    private static void m1156a(int i, long j) {
        Handler handler;
        if (f1189m && (handler = f1178b) != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = i;
            UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> sendMsgDelayed: " + j);
            f1178b.sendMessageDelayed(obtainMessage, j);
        }
    }

    /* renamed from: d */
    public static void m1164d() {
        if (f1192p.tryLock()) {
            try {
                m1161b(273);
            } finally {
                f1192p.unlock();
            }
        }
    }

    /* renamed from: a */
    private static void m1155a(int i, int i2) {
        Handler handler;
        if (f1189m && (handler = f1178b) != null) {
            handler.removeMessages(i);
            Message obtainMessage = f1178b.obtainMessage();
            obtainMessage.what = i;
            f1178b.sendMessageDelayed(obtainMessage, (long) i2);
        }
    }

    /* renamed from: e */
    public static void m1165e() {
        m1155a((int) f1182f, 3000);
    }

    /* renamed from: com.umeng.commonsdk.framework.a$a */
    /* compiled from: UMNetWorkSender */
    class FileObserverC0160a extends FileObserver {
        public FileObserverC0160a(String str) {
            super(str);
        }

        public void onEvent(int i, String str) {
            if ((i & 8) == 8) {
                ULog.m1381d("--->>> envelope file created >>> " + str);
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> envelope file created >>> " + str);
                UMNetWorkSender.m1163c(273);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: r */
    public static void m1178r() {
        ULog.m1381d("--->>> handleProcessNext: Enter...");
        if (f1189m) {
            Context appContext = UMModuleRegister.getAppContext();
            try {
                if (UMFrUtils.envelopeFileNumber(appContext) > 0) {
                    ULog.m1381d("--->>> The envelope file exists.");
                    if (UMFrUtils.envelopeFileNumber(appContext) > f1180d) {
                        ULog.m1381d("--->>> Number of envelope files is greater than 200, remove old files first.");
                        UMFrUtils.removeRedundantEnvelopeFiles(appContext, f1180d);
                    }
                    File envelopeFile = UMFrUtils.getEnvelopeFile(appContext);
                    if (envelopeFile != null) {
                        String path = envelopeFile.getPath();
                        ULog.m1381d("--->>> Ready to send envelope file [" + path + "].");
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> send envelope file [ " + path + "].");
                        if (new NetWorkManager(appContext).mo719a(envelopeFile)) {
                            ULog.m1381d("--->>> Send envelope file success, delete it.");
                            if (!UMFrUtils.removeEnvelopeFile(envelopeFile)) {
                                ULog.m1381d("--->>> Failed to delete already processed file. We try again after delete failed.");
                                UMFrUtils.removeEnvelopeFile(envelopeFile);
                            }
                            m1163c(273);
                            return;
                        }
                        ULog.m1381d("--->>> Send envelope file failed, abandon and wait next trigger!");
                        return;
                    }
                }
                m1165e();
            } catch (Throwable th) {
                UMCrashManager.reportCrash(appContext, th);
            }
        }
    }
}
