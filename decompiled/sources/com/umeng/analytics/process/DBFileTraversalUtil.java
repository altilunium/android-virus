package com.umeng.analytics.process;

import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBFileTraversalUtil {

    /* renamed from: a */
    private static ExecutorService f980a = Executors.newSingleThreadExecutor();

    /* renamed from: b */
    private static FileLockUtil f981b = new FileLockUtil();

    /* renamed from: com.umeng.analytics.process.DBFileTraversalUtil$a */
    public interface AbstractC0126a {
        /* renamed from: a */
        void mo622a();
    }

    public static void traverseDBFiles(String str, final FileLockCallback fileLockCallback, final AbstractC0126a aVar) {
        final File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            f980a.execute(new Runnable() {
                /* class com.umeng.analytics.process.DBFileTraversalUtil.RunnableC01251 */

                public void run() {
                    try {
                        File[] listFiles = file.listFiles();
                        for (File file : listFiles) {
                            if (file.getName().endsWith(DBConstant.f1003d)) {
                                DBFileTraversalUtil.f981b.doFileOperateion(file, fileLockCallback);
                                UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> file: " + file.getName());
                            }
                        }
                        AbstractC0126a aVar = aVar;
                        if (aVar != null) {
                            aVar.mo622a();
                        }
                    } catch (Throwable th) {
                    }
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> end *** ");
                }
            });
        }
    }
}
