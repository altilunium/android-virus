package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMConstant;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.umeng.analytics.pro.x */
public class SessionIdManager {

    /* renamed from: c */
    private static volatile SessionIdManager f973c;

    /* renamed from: a */
    private SessionIdGenerateService f974a = new SessionIdGenerateServiceImpl();

    /* renamed from: b */
    private String f975b;

    /* renamed from: d */
    private List f976d;

    /* renamed from: e */
    private String f977e;

    /* renamed from: com.umeng.analytics.pro.x$a */
    /* compiled from: SessionIdManager */
    public interface AbstractC0124a {
        /* renamed from: a */
        void mo594a(String str, long j, long j2, long j3);

        /* renamed from: a */
        void mo595a(String str, String str2, long j, long j2, long j3);
    }

    private SessionIdManager() {
    }

    /* renamed from: a */
    public static SessionIdManager m1047a() {
        if (f973c == null) {
            synchronized (SessionIdManager.class) {
                if (f973c == null) {
                    f973c = new SessionIdManager();
                }
            }
        }
        return f973c;
    }

    /* renamed from: a */
    public void mo613a(long j) {
        this.f974a.mo608a(j);
    }

    /* renamed from: b */
    public long mo615b() {
        return this.f974a.mo606a();
    }

    /* renamed from: a */
    public String mo611a(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        String str = "";
        try {
            synchronized (SessionIdManager.class) {
                str = PreferenceWrapper.getDefault(appContext).getString(SessionTracker.f955d, "");
            }
        } catch (Exception e) {
        }
        return str;
    }

    /* renamed from: b */
    public synchronized String mo616b(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        this.f975b = mo619d(appContext);
        if (mo620e(appContext)) {
            try {
                this.f975b = m1049f(appContext);
            } catch (Exception e) {
            }
        }
        return this.f975b;
    }

    /* renamed from: c */
    public String mo618c(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        try {
            this.f975b = m1049f(appContext);
        } catch (Exception e) {
        }
        return this.f975b;
    }

    /* renamed from: d */
    public String mo619d(Context context) {
        if (TextUtils.isEmpty(this.f975b)) {
            try {
                this.f975b = PreferenceWrapper.getDefault(context).getString("session_id", null);
            } catch (Exception e) {
            }
        }
        return this.f975b;
    }

    /* renamed from: a */
    public String mo612a(Context context, long j) {
        if (TextUtils.isEmpty(this.f977e)) {
            String str = "SUB" + j;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(String.format("%0" + (32 - str.length()) + "d", 0));
            this.f977e = sb.toString();
        }
        return this.f977e;
    }

    /* renamed from: e */
    public boolean mo620e(Context context) {
        if (TextUtils.isEmpty(this.f975b)) {
            this.f975b = mo619d(context);
        }
        return TextUtils.isEmpty(this.f975b) || m1053j(context) || m1050g(context);
    }

    /* renamed from: f */
    private String m1049f(Context context) {
        try {
            SharedPreferences.Editor edit = PreferenceWrapper.getDefault(context).edit();
            edit.putString(SessionTracker.f955d, mo619d(context));
            edit.commit();
        } catch (Exception e) {
        }
        long h = m1051h(context);
        long i = m1052i(context);
        String str = this.f975b;
        long a = SessionTracker.m1010a(context);
        long j = a * 5000;
        UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>>*** 读取 foreground count 值完成，count次数：" + a);
        if (!FieldManager.allow(UMConstant.f1600E)) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>>*** foreground count druation云控参数关闭。");
        } else if (UMWorkDispatch.eventHasExist()) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>>*** 读取 foreground count druation值完成，终止checker timer.");
            UMWorkDispatch.removeEvent();
        } else {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>>*** 读取 foreground count druation值完成，无未处理check timer事件。");
        }
        m1048a(i, h, j, str, false);
        this.f975b = this.f974a.mo607a(context);
        m1048a(i, h, j, str, true);
        this.f974a.mo609a(context, this.f975b);
        return this.f975b;
    }

    /* renamed from: g */
    private boolean m1050g(Context context) {
        if (!TextUtils.isEmpty(this.f975b) && UMStoreManager.m853a(context).mo512a(this.f975b) > 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private long m1046a(Context context, String str) {
        long j;
        try {
            j = PreferenceWrapper.getDefault(context).getLong(str, 0);
        } catch (Exception e) {
            j = 0;
        }
        if (j <= 0) {
            return System.currentTimeMillis();
        }
        return j;
    }

    /* renamed from: h */
    private long m1051h(Context context) {
        return m1046a(context, SessionTracker.f957f);
    }

    /* renamed from: i */
    private long m1052i(Context context) {
        return m1046a(context, SessionTracker.f952a);
    }

    /* renamed from: a */
    private void m1048a(long j, long j2, long j3, String str, boolean z) {
        List<AbstractC0124a> list = this.f976d;
        if (list != null) {
            for (AbstractC0124a aVar : list) {
                if (z) {
                    try {
                        aVar.mo595a(str, this.f975b, j, j2, j3);
                    } catch (Exception e) {
                    }
                } else {
                    aVar.mo594a(this.f975b, j, j2, j3);
                }
            }
        }
    }

    /* renamed from: j */
    private boolean m1053j(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(appContext);
            long j = sharedPreferences.getLong(SessionTracker.f956e, 0);
            long j2 = sharedPreferences.getLong(SessionTracker.f957f, 0);
            if (FieldManager.allow(UMConstant.f1600E) && j > 0 && j2 == 0) {
                long a = SessionTracker.m1010a(appContext);
                if (a > 0) {
                    UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "--->>> last session end time stamp = 0, reconstruct it by foreground count value.");
                    j2 = j + (a * 5000);
                }
            }
            UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> interval of last session is: " + (j2 - j));
            return this.f974a.mo610a(j, j2);
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public void mo614a(AbstractC0124a aVar) {
        if (aVar != null) {
            if (this.f976d == null) {
                this.f976d = new ArrayList();
            }
            if (!this.f976d.contains(aVar)) {
                this.f976d.add(aVar);
            }
        }
    }

    /* renamed from: b */
    public void mo617b(AbstractC0124a aVar) {
        List list;
        if (aVar != null && (list = this.f976d) != null && list.size() != 0) {
            this.f976d.remove(aVar);
        }
    }
}
