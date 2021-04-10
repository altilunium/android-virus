package com.umeng.commonsdk.service;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.utils.UMUtils;

public class UMGlobalContext {
    private static final String TAG = "UMGlobalContext";
    private String mAppVersion;
    private String mAppkey;
    private Context mApplicationContext;
    private String mChannel;
    private String mProcessName;

    private UMGlobalContext() {
        this.mProcessName = "";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.service.UMGlobalContext$a */
    public class C0176a {

        /* renamed from: a */
        private static final UMGlobalContext f1320a = new UMGlobalContext();

        private C0176a() {
        }
    }

    public static UMGlobalContext getInstance(Context context) {
        if (C0176a.f1320a.mApplicationContext == null && context != null) {
            C0176a.f1320a.mApplicationContext = context;
        }
        return C0176a.f1320a;
    }

    public static Context getAppContext(Context context) {
        if (C0176a.f1320a.mApplicationContext == null && context != null) {
            C0176a.f1320a.mApplicationContext = context.getApplicationContext();
        }
        return C0176a.f1320a.mApplicationContext;
    }

    public static Context getAppContext() {
        return C0176a.f1320a.mApplicationContext;
    }

    public String getAppkey() {
        if (TextUtils.isEmpty(this.mAppkey)) {
            this.mAppkey = UMConfigure.sAppkey;
        }
        return this.mAppkey;
    }

    public String getChannel() {
        if (TextUtils.isEmpty(this.mChannel)) {
            this.mChannel = UMConfigure.sChannel;
        }
        return this.mChannel;
    }

    public String getProcessName(Context context) {
        if (TextUtils.isEmpty(this.mProcessName)) {
            if (context != null) {
                Context context2 = C0176a.f1320a.mApplicationContext;
                if (context2 != null) {
                    this.mProcessName = UMFrUtils.getCurrentProcessName(context2);
                } else {
                    this.mProcessName = UMFrUtils.getCurrentProcessName(context);
                }
            } else {
                this.mProcessName = UMFrUtils.getCurrentProcessName(C0176a.f1320a.mApplicationContext);
            }
        }
        return this.mProcessName;
    }

    public String getAppVersion() {
        if (TextUtils.isEmpty(this.mAppVersion)) {
            this.mAppVersion = UMUtils.getAppVersionName(this.mApplicationContext);
        }
        return this.mAppVersion;
    }

    public boolean isMainProcess(Context context) {
        return UMUtils.isMainProgress(context);
    }

    public String toString() {
        if (C0176a.f1320a.mApplicationContext == null) {
            return "uninitialized.";
        }
        StringBuilder sb = new StringBuilder("[");
        sb.append("appkey:" + this.mAppkey + ",");
        sb.append("channel:" + this.mChannel + ",");
        sb.append("procName:" + this.mProcessName + "]");
        return sb.toString();
    }
}
