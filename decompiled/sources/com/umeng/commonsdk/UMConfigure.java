package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.analytics.pro.AutoViewPageTracker;
import com.umeng.analytics.pro.Constants;
import com.umeng.analytics.pro.OpenDeviceId;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.listener.OnGetOaidListener;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.stateless.UMSLConfig;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.BusinessWrapperConfig;
import com.umeng.commonsdk.statistics.EnvelopeManager;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UMConfigure {
    public static final int DEVICE_TYPE_BOX = 2;
    public static final int DEVICE_TYPE_PHONE = 1;
    private static final String KEY_FILE_NAME_APPKEY = "APPKEY";
    private static final String KEY_FILE_NAME_LOG = "LOG";
    private static final String KEY_METHOD_NAME_PUSH_SETCHANNEL = "setMessageChannel";
    private static final String KEY_METHOD_NAME_PUSH_SET_SECRET = "setSecret";
    private static final String KEY_METHOD_NAME_SETAPPKEY = "setAppkey";
    private static final String KEY_METHOD_NAME_SETCHANNEL = "setChannel";
    private static final String KEY_METHOD_NAME_SETDEBUGMODE = "setDebugMode";
    private static Object PreInitLock = new Object();
    private static final String TAG = "UMConfigure";
    private static final String WRAPER_TYPE_COCOS2DX_X = "Cocos2d-x";
    private static final String WRAPER_TYPE_COCOS2DX_XLUA = "Cocos2d-x_lua";
    private static final String WRAPER_TYPE_FLUTTER = "flutter";
    private static final String WRAPER_TYPE_HYBRID = "hybrid";
    private static final String WRAPER_TYPE_NATIVE = "native";
    private static final String WRAPER_TYPE_PHONEGAP = "phonegap";
    private static final String WRAPER_TYPE_REACTNATIVE = "react-native";
    private static final String WRAPER_TYPE_UNITY = "Unity";
    private static final String WRAPER_TYPE_WEEX = "weex";
    private static boolean debugLog = false;
    private static boolean isFinish = false;
    public static boolean isInit = false;
    private static Object lockObject = new Object();
    private static OnGetOaidListener mOnGetOaidListener;
    private static boolean preInitComplete = false;
    public static String sAppkey = "";
    public static String sChannel = "";
    public static UMLog umDebugLog = new UMLog();

    private static Class getClass(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private static Object getDecInstanceObject(Class cls) {
        Constructor constructor;
        if (cls == null) {
            return null;
        }
        try {
            constructor = cls.getDeclaredConstructor(new Class[0]);
        } catch (NoSuchMethodException e) {
            constructor = null;
        }
        if (constructor == null) {
            return null;
        }
        constructor.setAccessible(true);
        try {
            return constructor.newInstance(new Object[0]);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException e2) {
            return null;
        }
    }

    private static Method getDecMethod(Class cls, String str, Class[] clsArr) {
        Method method = null;
        if (cls != null) {
            try {
                method = cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
            }
            if (method != null) {
                method.setAccessible(true);
            }
        }
        return method;
    }

    private static void invoke(Method method, Object obj, Object[] objArr) {
        if (method != null && obj != null) {
            try {
                method.invoke(obj, objArr);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            }
        }
    }

    private static void invoke(Method method, Object[] objArr) {
        if (method != null) {
            try {
                method.invoke(null, objArr);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            }
        }
    }

    private static void setFile(Class cls, String str, String str2) {
        if (cls != null) {
            try {
                cls.getField(str).set(str, str2);
            } catch (Exception e) {
            }
        }
    }

    private static void setFile(Class cls, String str, boolean z) {
        if (cls != null) {
            try {
                cls.getField(str).set(str, Boolean.valueOf(z));
            } catch (Exception e) {
            }
        }
    }

    public static boolean getInitStatus() {
        boolean z;
        synchronized (lockObject) {
            z = isFinish;
        }
        return z;
    }

    private static boolean checkShareSdk(Class cls) {
        try {
            if (cls.getDeclaredField("isZyb") != null) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public static void init(Context context, int i, String str) {
        init(context, null, null, i, str);
    }

    private static boolean isPreInit() {
        boolean z;
        synchronized (PreInitLock) {
            z = preInitComplete;
        }
        return z;
    }

    public static void preInit(Context context, String str, String str2) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            if (TextUtils.isEmpty(str)) {
                str = UMUtils.getAppkeyByXML(applicationContext);
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = UMUtils.getChannelByXML(applicationContext);
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = "Unknown";
            }
            if (!TextUtils.isEmpty(str)) {
                sAppkey = str;
                sChannel = str2;
                UMGlobalContext.getInstance(applicationContext);
                AutoViewPageTracker.m890a(applicationContext);
                if (!needSendZcfgEnv(applicationContext)) {
                    FieldManager.m1113a().mo663a(applicationContext);
                }
                synchronized (PreInitLock) {
                    preInitComplete = true;
                }
            }
        } else if (debugLog) {
            Log.e(TAG, "preInit: context is null, pls check!");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0411, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0414, code lost:
        if (com.umeng.commonsdk.UMConfigure.debugLog != false) goto L_0x0416;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0416, code lost:
        android.util.Log.e(com.umeng.commonsdk.UMConfigure.TAG, "init e is " + r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x042d, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0430, code lost:
        if (com.umeng.commonsdk.UMConfigure.debugLog != false) goto L_0x0432;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0432, code lost:
        android.util.Log.e(com.umeng.commonsdk.UMConfigure.TAG, "init e is " + r11);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x0411 A[ExcHandler: all (r11v6 'th' java.lang.Object A[CUSTOM_DECLARE]), Splitter:B:1:0x0004] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void init(android.content.Context r10, java.lang.String r11, java.lang.String r12, int r13, java.lang.String r14) {
        /*
        // Method dump skipped, instructions count: 1159
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.UMConfigure.init(android.content.Context, java.lang.String, java.lang.String, int, java.lang.String):void");
    }

    public static boolean needSendZcfgEnv(Context context) {
        File filesDir = context.getFilesDir();
        if (new File(filesDir.getAbsolutePath() + File.separator + Constants.f445l).exists()) {
            return false;
        }
        return true;
    }

    public static boolean isDebugLog() {
        return debugLog;
    }

    public static void setLogEnabled(boolean z) {
        try {
            debugLog = z;
            MLog.DEBUG = z;
            Class cls = getClass("com.umeng.message.PushAgent");
            Object decInstanceObject = getDecInstanceObject(cls);
            Class cls2 = Boolean.TYPE;
            invoke(getDecMethod(cls, KEY_METHOD_NAME_SETDEBUGMODE, new Class[]{cls2}), decInstanceObject, new Object[]{Boolean.valueOf(z)});
            setFile(getClass("com.umeng.socialize.Config"), "DEBUG", z);
            invoke(getDecMethod(getClass("com.umeng.umcrash.UMCrash"), "setDebug", new Class[]{cls2}), new Object[]{Boolean.valueOf(z)});
        } catch (Exception e) {
            if (debugLog) {
                Log.e(TAG, "set log enabled e is " + e);
            }
        } catch (Throwable th) {
            if (debugLog) {
                Log.e(TAG, "set log enabled e is " + th);
            }
        }
    }

    public static void setEncryptEnabled(boolean z) {
        EnvelopeManager.m1342a(z);
    }

    public static String getUMIDString(Context context) {
        if (context != null) {
            return UMUtils.getUMId(context.getApplicationContext());
        }
        return null;
    }

    public static String getUmengZID(Context context) {
        if (context != null) {
            return UMUtils.getZid(context.getApplicationContext());
        }
        return null;
    }

    public static void setProcessEvent(boolean z) {
        AnalyticsConstants.SUB_PROCESS_EVENT = z;
    }

    private static void setLatencyWindow(long j) {
        BusinessWrapperConfig.f1358c = ((int) j) * 1000;
    }

    private static void setCheckDevice(boolean z) {
        AnalyticsConstants.CHECK_DEVICE = z;
    }

    private static void setWraperType(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            if (str.equals(WRAPER_TYPE_NATIVE)) {
                UMSLConfig.f1323a = WRAPER_TYPE_NATIVE;
                BusinessWrapperConfig.f1356a = WRAPER_TYPE_NATIVE;
            } else if (str.equals(WRAPER_TYPE_COCOS2DX_X)) {
                UMSLConfig.f1323a = WRAPER_TYPE_COCOS2DX_X;
                BusinessWrapperConfig.f1356a = WRAPER_TYPE_COCOS2DX_X;
            } else if (str.equals(WRAPER_TYPE_COCOS2DX_XLUA)) {
                UMSLConfig.f1323a = WRAPER_TYPE_COCOS2DX_XLUA;
                BusinessWrapperConfig.f1356a = WRAPER_TYPE_COCOS2DX_XLUA;
            } else if (str.equals(WRAPER_TYPE_UNITY)) {
                UMSLConfig.f1323a = WRAPER_TYPE_UNITY;
                BusinessWrapperConfig.f1356a = WRAPER_TYPE_UNITY;
            } else if (str.equals(WRAPER_TYPE_REACTNATIVE)) {
                UMSLConfig.f1323a = WRAPER_TYPE_REACTNATIVE;
                BusinessWrapperConfig.f1356a = WRAPER_TYPE_REACTNATIVE;
            } else if (str.equals(WRAPER_TYPE_PHONEGAP)) {
                UMSLConfig.f1323a = WRAPER_TYPE_PHONEGAP;
                BusinessWrapperConfig.f1356a = WRAPER_TYPE_PHONEGAP;
            } else if (str.equals(WRAPER_TYPE_WEEX)) {
                UMSLConfig.f1323a = WRAPER_TYPE_WEEX;
                BusinessWrapperConfig.f1356a = WRAPER_TYPE_WEEX;
            } else if (str.equals(WRAPER_TYPE_HYBRID)) {
                UMSLConfig.f1323a = WRAPER_TYPE_HYBRID;
                BusinessWrapperConfig.f1356a = WRAPER_TYPE_HYBRID;
            } else if (str.equals(WRAPER_TYPE_FLUTTER)) {
                UMSLConfig.f1323a = WRAPER_TYPE_FLUTTER;
                BusinessWrapperConfig.f1356a = WRAPER_TYPE_FLUTTER;
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            UMSLConfig.f1324b = str2;
            BusinessWrapperConfig.f1357b = str2;
        }
    }

    public static String[] getTestDeviceInfo(Context context) {
        String[] strArr = new String[2];
        if (context != null) {
            try {
                strArr[0] = DeviceConfig.getDeviceIdForGeneral(context);
                strArr[1] = DeviceConfig.getMac(context);
            } catch (Exception e) {
            }
        }
        return strArr;
    }

    public static void getOaid(Context context, OnGetOaidListener onGetOaidListener) {
        if (context != null) {
            final Context applicationContext = context.getApplicationContext();
            mOnGetOaidListener = onGetOaidListener;
            new Thread(new Runnable() {
                /* class com.umeng.commonsdk.UMConfigure.RunnableC01384 */

                public void run() {
                    String a = OpenDeviceId.m1067a(applicationContext);
                    if (UMConfigure.mOnGetOaidListener != null) {
                        UMConfigure.mOnGetOaidListener.onGetOaid(a);
                    }
                }
            }).start();
        } else if (debugLog) {
            Log.e(TAG, "context is null !!!");
        }
    }
}
