package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.analytics.pro.TBinaryProtocol;
import com.umeng.analytics.pro.TDeserializer;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.UMInternalConfig;
import com.umeng.commonsdk.internal.UMInternalData;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.stateless.UMSLUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.idtracking.IdTracker;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.HeaderHelper;
import com.umeng.commonsdk.statistics.internal.NetworkHelper;
import com.umeng.commonsdk.statistics.internal.OnImprintChangedListener;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.statistics.noise.Defcon;
import com.umeng.commonsdk.statistics.proto.Response;
import java.io.File;

/* renamed from: com.umeng.commonsdk.statistics.c */
public class NetWorkManager {

    /* renamed from: b */
    private static final int f1370b = 1;

    /* renamed from: c */
    private static final int f1371c = 2;

    /* renamed from: d */
    private static final int f1372d = 3;

    /* renamed from: o */
    private static final String f1373o = "thtstart";

    /* renamed from: p */
    private static final String f1374p = "gkvc";

    /* renamed from: q */
    private static final String f1375q = "ekvc";

    /* renamed from: a */
    String f1376a = null;

    /* renamed from: e */
    private final int f1377e = 1;

    /* renamed from: f */
    private NetworkHelper f1378f;

    /* renamed from: g */
    private ImprintHandler f1379g;

    /* renamed from: h */
    private IdTracker f1380h = null;

    /* renamed from: i */
    private ImprintHandler.C0195a f1381i = null;

    /* renamed from: j */
    private ABTest f1382j = null;

    /* renamed from: k */
    private Defcon f1383k = null;

    /* renamed from: l */
    private long f1384l = 0;

    /* renamed from: m */
    private int f1385m = 0;

    /* renamed from: n */
    private int f1386n = 0;

    /* renamed from: r */
    private Context f1387r;

    public NetWorkManager(Context context) {
        this.f1387r = context;
        this.f1381i = ImprintHandler.getImprintService(context).mo771c();
        this.f1383k = Defcon.getService(this.f1387r);
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.f1387r);
        this.f1384l = sharedPreferences.getLong(f1373o, 0);
        this.f1385m = sharedPreferences.getInt(f1374p, 0);
        this.f1386n = sharedPreferences.getInt(f1375q, 0);
        this.f1376a = UMEnvelopeBuild.imprintProperty(this.f1387r, "track_list", null);
        ImprintHandler imprintService = ImprintHandler.getImprintService(this.f1387r);
        this.f1379g = imprintService;
        imprintService.mo765a(new OnImprintChangedListener() {
            /* class com.umeng.commonsdk.statistics.NetWorkManager.C01871 */

            @Override // com.umeng.commonsdk.statistics.internal.OnImprintChangedListener
            public void onImprintChanged(ImprintHandler.C0195a aVar) {
                NetWorkManager.this.f1383k.onImprintChanged(aVar);
                NetWorkManager cVar = NetWorkManager.this;
                cVar.f1376a = UMEnvelopeBuild.imprintProperty(cVar.f1387r, "track_list", null);
            }
        });
        if (!UMConfigure.needSendZcfgEnv(this.f1387r)) {
            this.f1380h = IdTracker.m1480a(this.f1387r);
        }
        NetworkHelper cVar = new NetworkHelper(this.f1387r);
        this.f1378f = cVar;
        cVar.mo823a(StatTracer.getInstance(this.f1387r));
    }

    /* renamed from: a */
    public boolean mo719a(File file) {
        String str;
        int i;
        if (file == null) {
            return false;
        }
        try {
            byte[] byteArray = UMFrUtils.toByteArray(file.getPath());
            if (byteArray == null) {
                return false;
            }
            String name = file.getName();
            if (TextUtils.isEmpty(name)) {
                return false;
            }
            HeaderHelper a = HeaderHelper.m1513a(this.f1387r);
            a.mo822d(name);
            boolean a2 = a.mo818a(name);
            boolean b = a.mo820b(name);
            boolean c = a.mo821c(name);
            String d = UMSLUtils.m1330d(name);
            if (!TextUtils.isEmpty(d)) {
                str = UMSLUtils.m1327c(d);
            } else if (c) {
                str = UMServerURL.ZCFG_PATH;
            } else {
                str = UMServerURL.PATH_ANALYTICS;
            }
            byte[] a3 = this.f1378f.mo825a(byteArray, a2, c, str);
            if (a3 == null) {
                i = 1;
            } else {
                i = m1347a(a3);
            }
            if (UMConfigure.isDebugLog()) {
                if (c && i == 2) {
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "Zero req: succeed.");
                } else if (b && i == 2) {
                    MLog.m1351d("本次启动数据: 发送成功!");
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "Send instant data: succeed.");
                } else if (!a2 || i != 2) {
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "Inner req: succeed.");
                } else {
                    MLog.m1351d("普通统计数据: 发送成功!");
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "Send analytics data: succeed.");
                }
            }
            if (i == 2) {
                IdTracker eVar = this.f1380h;
                if (eVar != null) {
                    eVar.mo793e();
                }
                StatTracer.getInstance(this.f1387r).saveSate();
            } else if (i == 3) {
                StatTracer.getInstance(this.f1387r).saveSate();
                if (c) {
                    FieldManager.m1113a().mo663a(this.f1387r);
                    UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> 零号报文应答内容报错!!! ，特殊处理!，继续正常流程。");
                    Context context = this.f1387r;
                    UMWorkDispatch.sendEvent(context, UMInternalConfig.f1224s, UMInternalData.m1179a(context).mo677a(), null);
                    return true;
                }
            }
            if (i == 2) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.f1387r, th);
            return false;
        }
    }

    /* renamed from: a */
    private int m1347a(byte[] bArr) {
        Response response = new Response();
        try {
            new TDeserializer(new TBinaryProtocol.C0085a()).mo386a(response, bArr);
            if (response.resp_code == 1) {
                this.f1379g.mo770b(response.getImprint());
                this.f1379g.mo772d();
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.f1387r, th);
        }
        if (response.resp_code == 1) {
            return 2;
        }
        return 3;
    }
}
