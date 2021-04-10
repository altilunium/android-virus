package com.umeng.commonsdk.framework;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.umeng.analytics.pro.UMCommonContent;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.UMErrorCode;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.internal.HeaderHelper;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class UMFrUtils {
    private static final String KEY_LAST_INSTANT_SUCC_BUILD_TIME = "last_instant_build_time";
    private static final String KEY_LAST_SUCC_BUILD_TIME = "last_successful_build_time";
    private static String mDefaultEnvelopeDir = "envelope";
    private static String mDefaultEnvelopeDirPath = null;
    private static Object mEnvelopeBuildTimeLock = new Object();
    private static Object mEnvelopeFileLock = new Object();
    private static String sCurrentProcessName = "";

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003e A[SYNTHETIC, Splitter:B:15:0x003e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getProcessName(int r5) {
        /*
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x003a }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ all -> 0x003a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x003a }
            r3.<init>()     // Catch:{ all -> 0x003a }
            java.lang.String r4 = "/proc/"
            r3.append(r4)     // Catch:{ all -> 0x003a }
            r3.append(r5)     // Catch:{ all -> 0x003a }
            java.lang.String r5 = "/cmdline"
            r3.append(r5)     // Catch:{ all -> 0x003a }
            java.lang.String r5 = r3.toString()     // Catch:{ all -> 0x003a }
            r2.<init>(r5)     // Catch:{ all -> 0x003a }
            r1.<init>(r2)     // Catch:{ all -> 0x003a }
            java.lang.String r5 = r1.readLine()     // Catch:{ all -> 0x0038 }
            boolean r2 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0038 }
            if (r2 != 0) goto L_0x0030
            java.lang.String r5 = r5.trim()     // Catch:{ all -> 0x0038 }
        L_0x0030:
            r1.close()     // Catch:{ all -> 0x0036 }
            goto L_0x0037
        L_0x0036:
            r0 = move-exception
        L_0x0037:
            return r5
        L_0x0038:
            r5 = move-exception
            goto L_0x003c
        L_0x003a:
            r5 = move-exception
            r1 = r0
        L_0x003c:
            if (r1 == 0) goto L_0x0044
            r1.close()     // Catch:{ all -> 0x0042 }
            goto L_0x0044
        L_0x0042:
            r5 = move-exception
            goto L_0x0045
        L_0x0044:
        L_0x0045:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.framework.UMFrUtils.getProcessName(int):java.lang.String");
    }

    public static String getCurrentProcessName(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (TextUtils.isEmpty(sCurrentProcessName)) {
            try {
                int myPid = Process.myPid();
                String processName = getProcessName(myPid);
                if (!TextUtils.isEmpty(processName)) {
                    sCurrentProcessName = processName;
                } else {
                    ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                    if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null && runningAppProcesses.size() > 0) {
                        Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            ActivityManager.RunningAppProcessInfo next = it.next();
                            if (next.pid == myPid) {
                                sCurrentProcessName = next.processName;
                                break;
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context.getApplicationContext(), th);
            }
        }
        return sCurrentProcessName;
    }

    public static String getSubProcessName(Context context) {
        String str = "";
        try {
            String currentProcessName = getCurrentProcessName(context);
            int indexOf = currentProcessName.indexOf(":");
            if (indexOf >= 0) {
                str = currentProcessName.substring(indexOf + 1);
            }
            if (indexOf >= 0) {
                return str;
            }
            String packageName = context.getPackageName();
            if (currentProcessName.length() > packageName.length()) {
                return currentProcessName.substring(packageName.length() + 1, currentProcessName.length());
            }
            return currentProcessName;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context.getApplicationContext(), th);
            return str;
        }
    }

    private static boolean checkPermission(Context context, String str) {
        if (context == null) {
            return false;
        }
        Context applicationContext = context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", String.class).invoke(context, str)).intValue() == 0) {
                    return true;
                }
                return false;
            } catch (Throwable th) {
                UMCrashManager.reportCrash(applicationContext, th);
                return false;
            }
        } else {
            try {
                if (applicationContext.getPackageManager().checkPermission(str, applicationContext.getPackageName()) == 0) {
                    return true;
                }
                return false;
            } catch (Throwable th2) {
                UMCrashManager.reportCrash(applicationContext, th2);
                return false;
            }
        }
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            if (!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE") || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isConnectedOrConnecting();
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context.getApplicationContext(), th);
            return false;
        }
    }

    public static int envelopeFileNumber(Context context) {
        String[] list;
        if (context == null) {
            return 0;
        }
        try {
            File file = new File(getEnvelopeDirPath(context));
            synchronized (mEnvelopeFileLock) {
                if (file.isDirectory() && (list = file.list()) != null) {
                    return list.length;
                }
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
        return 0;
    }

    private static long getDistanceDays(long j, long j2) {
        long j3;
        if (j < j2) {
            j3 = j2 - j;
        } else {
            j3 = j - j2;
        }
        return j3 / 86400000;
    }

    public static void removeRedundantEnvelopeFiles(Context context, int i) {
        File file = new File(getEnvelopeDirPath(context));
        synchronized (mEnvelopeFileLock) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                if (listFiles.length > i) {
                    Arrays.sort(listFiles, new Comparator() {
                        /* class com.umeng.commonsdk.framework.UMFrUtils.C01551 */

                        /* renamed from: a */
                        public int compare(File file, File file2) {
                            long lastModified = file.lastModified() - file2.lastModified();
                            if (lastModified > 0) {
                                return 1;
                            }
                            if (lastModified == 0) {
                                return 0;
                            }
                            return -1;
                        }
                    });
                    if (listFiles.length > i) {
                        for (int i2 = 0; i2 < listFiles.length - i; i2++) {
                            try {
                                if (!listFiles[i2].delete()) {
                                    ULog.m1381d("--->>> remove [" + i2 + "] file fail.");
                                }
                            } catch (Throwable th) {
                                UMCrashManager.reportCrash(context, th);
                            }
                        }
                    }
                }
            }
        }
    }

    public static File getEnvelopeFile(Context context) {
        if (context == null) {
            return null;
        }
        File file = new File(getEnvelopeDirPath(context));
        synchronized (mEnvelopeFileLock) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                if (listFiles.length != 0) {
                    Arrays.sort(listFiles, new Comparator() {
                        /* class com.umeng.commonsdk.framework.UMFrUtils.C01562 */

                        /* renamed from: a */
                        public int compare(File file, File file2) {
                            long lastModified = file.lastModified() - file2.lastModified();
                            if (lastModified > 0) {
                                return 1;
                            }
                            if (lastModified == 0) {
                                return 0;
                            }
                            return -1;
                        }
                    });
                    return listFiles[0];
                }
            }
            return null;
        }
    }

    public static void syncLegacyEnvelopeIfNeeded(Context context) {
        if (context != null) {
            try {
                String legacyEnvelopeDir = getLegacyEnvelopeDir(context);
                if (!TextUtils.isEmpty(legacyEnvelopeDir) && !legacyEnvelopeDir.equals(mDefaultEnvelopeDir)) {
                    File file = new File(context.getFilesDir().getAbsolutePath() + "/." + legacyEnvelopeDir);
                    if (file.exists()) {
                        File[] listFiles = file.listFiles();
                        if (listFiles == null || listFiles.length == 0) {
                            try {
                                if (file.isDirectory()) {
                                    file.delete();
                                }
                            } catch (Throwable th) {
                                UMCrashManager.reportCrash(context, th);
                            }
                        } else {
                            try {
                                String envelopeDirPath = getEnvelopeDirPath(context);
                                for (int i = 0; i < listFiles.length; i++) {
                                    File file2 = listFiles[i];
                                    file2.renameTo(new File(envelopeDirPath + File.separator + listFiles[i].getName()));
                                }
                                if (file.isDirectory()) {
                                    file.delete();
                                }
                            } catch (Throwable th2) {
                                UMCrashManager.reportCrash(context, th2);
                            }
                        }
                    }
                }
            } catch (Throwable th3) {
                UMCrashManager.reportCrash(context, th3);
            }
        }
    }

    public static String getLegacyEnvelopeDir(Context context) {
        try {
            String processName = getProcessName(Process.myPid());
            if (!TextUtils.isEmpty(processName)) {
                String replace = processName.replace(':', '_');
                ULog.m1381d("--->>> getEnvelopeDir: use current process name as envelope directory.");
                return replace;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null) {
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses == null) {
                    ULog.m1381d("--->>> getEnvelopeDir: can't get process name, use default envelope directory.");
                    return mDefaultEnvelopeDir;
                } else if (runningAppProcesses.size() == 0) {
                    return mDefaultEnvelopeDir;
                } else {
                    try {
                        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                            if (runningAppProcessInfo.pid == Process.myPid()) {
                                String replace2 = runningAppProcessInfo.processName.replace(':', '_');
                                ULog.m1381d("--->>> getEnvelopeDir: use current process name as envelope directory.");
                                return replace2;
                            }
                        }
                    } catch (Throwable th) {
                        UMCrashManager.reportCrash(context, th);
                    }
                }
            }
            return mDefaultEnvelopeDir;
        } catch (Throwable th2) {
            UMCrashManager.reportCrash(context, th2);
        }
    }

    public static String getEnvelopeDirPath(Context context) {
        String str;
        synchronized (mEnvelopeFileLock) {
            try {
                if (mDefaultEnvelopeDirPath == null) {
                    mDefaultEnvelopeDirPath = context.getFilesDir().getAbsolutePath() + File.separator + "." + mDefaultEnvelopeDir;
                }
                File file = new File(mDefaultEnvelopeDirPath);
                if (!file.exists() && !file.mkdir()) {
                    ULog.m1381d("--->>> Create Envelope Directory failed!!!");
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
            str = mDefaultEnvelopeDirPath;
        }
        return str;
    }

    public static long getLastSuccessfulBuildTime(Context context) {
        long j;
        synchronized (mEnvelopeBuildTimeLock) {
            j = PreferenceWrapper.getDefault(context).getLong(KEY_LAST_SUCC_BUILD_TIME, 0);
        }
        return j;
    }

    public static long getLastInstantBuildTime(Context context) {
        long j;
        synchronized (mEnvelopeBuildTimeLock) {
            j = PreferenceWrapper.getDefault(context).getLong(KEY_LAST_INSTANT_SUCC_BUILD_TIME, 0);
        }
        return j;
    }

    private static void updateLastSuccessfulBuildTime(Context context) {
        synchronized (mEnvelopeBuildTimeLock) {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            sharedPreferences.edit().putLong(KEY_LAST_SUCC_BUILD_TIME, System.currentTimeMillis()).commit();
        }
    }

    private static void updateLastInstantBuildTime(Context context) {
        synchronized (mEnvelopeBuildTimeLock) {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            sharedPreferences.edit().putLong(KEY_LAST_INSTANT_SUCC_BUILD_TIME, System.currentTimeMillis()).commit();
        }
    }

    public static int saveEnvelopeFile(Context context, String str, byte[] bArr) {
        Throwable th;
        IOException e;
        if (bArr == null) {
            return UMErrorCode.E_UM_BE_SAVE_FAILED;
        }
        File file = new File(getEnvelopeDirPath(context) + File.separator + str);
        synchronized (mEnvelopeFileLock) {
            FileOutputStream fileOutputStream = null;
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileOutputStream2.write(bArr);
                    fileOutputStream2.close();
                    boolean a = HeaderHelper.m1513a(context).mo818a(str);
                    boolean b = HeaderHelper.m1513a(context).mo820b(str);
                    if (a) {
                        updateLastSuccessfulBuildTime(context);
                    }
                    if (b) {
                        updateLastInstantBuildTime(context);
                    }
                    return 0;
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th3) {
                            UMCrashManager.reportCrash(context, th3);
                        }
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    UMCrashManager.reportCrash(context, e);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th4) {
                            UMCrashManager.reportCrash(context, th4);
                        }
                    }
                    return UMErrorCode.E_UM_BE_SAVE_FAILED;
                } catch (Throwable th5) {
                    th = th5;
                }
            }
        }
    }

    public static boolean removeEnvelopeFile(File file) {
        Context appContext = UMModuleRegister.getAppContext();
        synchronized (mEnvelopeFileLock) {
            if (file != null) {
                try {
                    if (file.exists()) {
                        return file.delete();
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(appContext, th);
                }
            }
            return true;
        }
    }

    public static byte[] toByteArray(String str) {
        byte[] bArr;
        Context appContext = UMModuleRegister.getAppContext();
        synchronized (mEnvelopeFileLock) {
            FileChannel fileChannel = null;
            try {
                FileChannel channel = new RandomAccessFile(str, "r").getChannel();
                MappedByteBuffer load = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size()).load();
                bArr = new byte[((int) channel.size())];
                if (load.remaining() > 0) {
                    load.get(bArr, 0, load.remaining());
                }
                try {
                    channel.close();
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(appContext, th);
                }
            } catch (IOException e) {
                UMCrashManager.reportCrash(appContext, e);
                throw e;
            } catch (Throwable th2) {
                UMCrashManager.reportCrash(appContext, th2);
            }
        }
        return bArr;
        throw th;
    }

    public static boolean hasEnvelopeFile(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        String str = UMCommonContent.f336at;
        if (uMBusinessType == UMLogDataProtocol.UMBusinessType.U_INTERNAL) {
            str = UMCommonContent.f291aA;
        }
        if (uMBusinessType == UMLogDataProtocol.UMBusinessType.U_ZeroEnv) {
            str = UMCommonContent.f292aB;
        }
        String envelopeDirPath = getEnvelopeDirPath(context);
        if (envelopeDirPath == null) {
            return false;
        }
        File file = new File(envelopeDirPath);
        synchronized (mEnvelopeFileLock) {
            try {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    if (listFiles.length != 0) {
                        for (File file2 : listFiles) {
                            if (file2.getName().startsWith(str)) {
                                return true;
                            }
                        }
                        return false;
                    }
                }
                return false;
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
    }
}
