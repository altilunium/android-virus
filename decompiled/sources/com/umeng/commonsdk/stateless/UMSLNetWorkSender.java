package com.umeng.commonsdk.stateless;

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
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.UMCommonContent;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.UMInternalConfig;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.vchannel.Constant;
import java.io.File;
import java.util.LinkedList;

/* renamed from: com.umeng.commonsdk.stateless.b */
public class UMSLNetWorkSender {

    /* renamed from: a */
    public static final int f1334a = 273;

    /* renamed from: b */
    private static Context f1335b = null;

    /* renamed from: c */
    private static HandlerThread f1336c = null;

    /* renamed from: d */
    private static Handler f1337d = null;

    /* renamed from: e */
    private static Object f1338e = new Object();

    /* renamed from: f */
    private static final int f1339f = 274;

    /* renamed from: g */
    private static final int f1340g = 275;

    /* renamed from: h */
    private static final int f1341h = 512;

    /* renamed from: i */
    private static FileObserverC0180a f1342i;

    /* renamed from: j */
    private static IntentFilter f1343j;

    /* renamed from: k */
    private static boolean f1344k = false;

    /* renamed from: l */
    private static LinkedList f1345l = new LinkedList();

    /* renamed from: m */
    private static BroadcastReceiver f1346m = new BroadcastReceiver() {
        /* class com.umeng.commonsdk.stateless.UMSLNetWorkSender.C01781 */

        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager;
            if (context != null && intent != null) {
                try {
                    if (intent.getAction() != null && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                        Context unused = UMSLNetWorkSender.f1335b = context.getApplicationContext();
                        if (UMSLNetWorkSender.f1335b != null && (connectivityManager = (ConnectivityManager) UMSLNetWorkSender.f1335b.getSystemService("connectivity")) != null) {
                            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>>网络断连： 2号数据仓");
                                boolean unused2 = UMSLNetWorkSender.f1344k = false;
                                return;
                            }
                            boolean unused3 = UMSLNetWorkSender.f1344k = true;
                            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>>网络可用： 触发2号数据仓信封消费动作。");
                            UMSLNetWorkSender.m1292b(UMSLNetWorkSender.f1339f);
                        }
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }
    };

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.stateless.b$a */
    /* compiled from: UMSLNetWorkSender */
    public class FileObserverC0180a extends FileObserver {
        public FileObserverC0180a(String str) {
            super(str);
        }

        public void onEvent(int i, String str) {
            if ((i & 8) == 8) {
                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> envelope file created >>> " + str);
                UMSLNetWorkSender.m1288a((int) UMSLNetWorkSender.f1339f);
            }
        }
    }

    /* renamed from: a */
    public static boolean m1289a() {
        synchronized (f1338e) {
            if (f1342i != null) {
                return true;
            }
            return false;
        }
    }

    public UMSLNetWorkSender(Context context) {
        synchronized (f1338e) {
            if (context != null) {
                try {
                    Context applicationContext = context.getApplicationContext();
                    f1335b = applicationContext;
                    if (applicationContext != null && f1336c == null) {
                        HandlerThread handlerThread = new HandlerThread("SL-NetWorkSender");
                        f1336c = handlerThread;
                        handlerThread.start();
                        if (f1342i == null) {
                            String str = f1335b.getFilesDir() + File.separator + UMSLConfig.f1328f;
                            File file = new File(str);
                            if (!file.exists()) {
                                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 2号数据仓目录不存在，创建之。");
                                file.mkdir();
                            }
                            FileObserverC0180a aVar = new FileObserverC0180a(str);
                            f1342i = aVar;
                            aVar.startWatching();
                            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 2号数据仓File Monitor启动.");
                        }
                        if (f1337d == null) {
                            f1337d = new Handler(f1336c.getLooper()) {
                                /* class com.umeng.commonsdk.stateless.UMSLNetWorkSender.HandlerC01792 */

                                /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
                                public void handleMessage(Message message) {
                                    int i = message.what;
                                    if (i != UMSLNetWorkSender.f1341h) {
                                        switch (i) {
                                            case UMSLNetWorkSender.f1334a /*{ENCODED_INT: 273}*/:
                                                UMSLNetWorkSender.m1303m();
                                                return;
                                            case UMSLNetWorkSender.f1339f /*{ENCODED_INT: 274}*/:
                                                UMSLNetWorkSender.m1305o();
                                                return;
                                            case UMSLNetWorkSender.f1340g /*{ENCODED_INT: 275}*/:
                                                UMSLNetWorkSender.m1307q();
                                                break;
                                            default:
                                                return;
                                        }
                                    }
                                    UMSLNetWorkSender.m1308r();
                                }
                            };
                        }
                        if (DeviceConfig.checkPermission(f1335b, "android.permission.ACCESS_NETWORK_STATE")) {
                            ULog.m1396i("walle", "[stateless] begin register receiver");
                            if (f1343j == null) {
                                IntentFilter intentFilter = new IntentFilter();
                                f1343j = intentFilter;
                                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                                if (f1346m != null) {
                                    UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 2号数据仓：注册网络状态监听器。");
                                    f1335b.registerReceiver(f1346m, f1343j);
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }
    }

    /* renamed from: a */
    public static void m1288a(int i) {
        Handler handler;
        if (f1344k && (handler = f1337d) != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = i;
            f1337d.sendMessage(obtainMessage);
        }
    }

    /* renamed from: b */
    public static void m1292b(int i) {
        Handler handler;
        try {
            if (f1344k && (handler = f1337d) != null && !handler.hasMessages(i)) {
                Message obtainMessage = f1337d.obtainMessage();
                obtainMessage.what = i;
                f1337d.sendMessage(obtainMessage);
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(f1335b, th);
        }
    }

    /* renamed from: j */
    private static void m1300j() {
        File[] c = UMSLUtils.m1329c(f1335b);
        if (c != null) {
            if (f1345l.size() > 0) {
                f1345l.clear();
            }
            for (File file : c) {
                f1345l.add(file.getAbsolutePath());
            }
        }
    }

    /* renamed from: k */
    private static String m1301k() {
        String str = null;
        try {
            String str2 = (String) f1345l.peek();
            if (str2 == null) {
                return str2;
            }
            try {
                f1345l.removeFirst();
                return str2;
            } catch (Throwable th) {
                str = str2;
            }
        } catch (Throwable th2) {
            return str;
        }
    }

    /* renamed from: l */
    private static void m1302l() {
        String str;
        String str2;
        if (f1345l.size() <= 0) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> todoList无内容，无需处理。");
            return;
        }
        do {
            str = (String) f1345l.pollFirst();
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (!file.exists()) {
                    UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 信封文件不存在，处理下一个文件。");
                    continue;
                } else {
                    UMSLNetWorkSenderHelper cVar = new UMSLNetWorkSenderHelper(f1335b);
                    byte[] bArr = null;
                    try {
                        bArr = UMSLUtils.m1320a(str);
                    } catch (Exception e) {
                    }
                    String name = file.getName();
                    if (!TextUtils.isEmpty(name)) {
                        str2 = name.substring(0, 1);
                    } else {
                        str2 = UMCommonContent.f295aE;
                    }
                    String d = UMSLUtils.m1330d(name);
                    String str3 = UMSLConfig.f1332j;
                    String c = UMSLUtils.m1327c(d);
                    if (Constant.f1663c.equalsIgnoreCase(c)) {
                        str3 = Constant.f1661a;
                    }
                    if (cVar.mo705a(bArr, c, str3, str2) && !file.delete()) {
                        file.delete();
                        continue;
                    }
                }
            }
        } while (str != null);
        f1345l.clear();
    }

    /* access modifiers changed from: private */
    /* renamed from: m */
    public static void m1303m() {
        File a;
        if (f1344k && f1335b != null) {
            do {
                try {
                    a = UMSLUtils.m1314a(f1335b);
                    if (!(a == null || a.getParentFile() == null || TextUtils.isEmpty(a.getParentFile().getName()))) {
                        UMSLNetWorkSenderHelper cVar = new UMSLNetWorkSenderHelper(f1335b);
                        String str = new String(Base64.decode(a.getParentFile().getName(), 0));
                        if (!UMInternalConfig.f1206a.equalsIgnoreCase(str) && !UMInternalConfig.f1207b.equalsIgnoreCase(str)) {
                            if (!UMInternalConfig.f1203A.equalsIgnoreCase(str)) {
                                ULog.m1396i("walle", "[stateless] handleProcessNext, pathUrl is " + str);
                                byte[] bArr = null;
                                try {
                                    bArr = UMSLUtils.m1320a(a.getAbsolutePath());
                                } catch (Exception e) {
                                }
                                String str2 = "";
                                if (Constant.f1663c.equalsIgnoreCase(str)) {
                                    str2 = Constant.f1661a;
                                }
                                String str3 = UMCommonContent.f295aE;
                                if (UMServerURL.PATH_SHARE.equalsIgnoreCase(str)) {
                                    str3 = UMCommonContent.f342az;
                                }
                                if (UMServerURL.PATH_PUSH_LAUNCH.equalsIgnoreCase(str) || UMServerURL.PATH_PUSH_REGIST.equalsIgnoreCase(str) || UMServerURL.PATH_PUSH_LOG.equalsIgnoreCase(str)) {
                                    str3 = UMCommonContent.f338av;
                                }
                                if (cVar.mo705a(bArr, str, str2, str3)) {
                                    ULog.m1396i("walle", "[stateless] Send envelope file success, delete it.");
                                    File file = new File(a.getAbsolutePath());
                                    if (!file.delete()) {
                                        ULog.m1396i("walle", "[stateless] Failed to delete already processed file. We try again after delete failed.");
                                        file.delete();
                                        continue;
                                    } else {
                                        continue;
                                    }
                                } else {
                                    ULog.m1396i("walle", "[stateless] Send envelope file failed, abandon and wait next trigger!");
                                    return;
                                }
                            }
                        }
                        new File(a.getAbsolutePath()).delete();
                        continue;
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(f1335b, th);
                }
            } while (a != null);
            m1304n();
        }
    }

    /* renamed from: n */
    private static void m1304n() {
        try {
            File file = new File(f1335b.getFilesDir() + File.separator + UMSLConfig.f1327e);
            if (file.exists() && file.isDirectory()) {
                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 2号数据仓：删除stateless目录。");
                UMSLUtils.m1319a(file);
            }
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: o */
    public static void m1305o() {
        if (f1344k && f1335b != null) {
            m1300j();
            m1302l();
            m1293c();
        }
    }

    /* renamed from: p */
    private static void m1306p() {
        try {
            File file = new File(f1335b.getFilesDir() + File.separator + UMSLConfig.f1327e);
            if (file.exists() && file.isDirectory()) {
                UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>>2号数据仓：检测到stateless目录。");
                m1292b(f1334a);
            }
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: q */
    public static void m1307q() {
        m1306p();
    }

    /* renamed from: b */
    public static void m1291b() {
        UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>>信封构建成功： 触发2号数据仓信封消费动作。");
        m1292b(f1339f);
    }

    /* renamed from: c */
    public static void m1293c() {
        m1292b(f1340g);
    }

    /* renamed from: d */
    public static void m1294d() {
        m1292b(f1341h);
    }

    /* access modifiers changed from: private */
    /* renamed from: r */
    public static void m1308r() {
    }
}
