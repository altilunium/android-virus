package com.umeng.analytics.process;

import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.process.DBFileTraversalUtil;
import com.umeng.common.EncryptHelper;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class UMProcessDBHelper {
    private static UMProcessDBHelper mInstance;
    private InsertEventCallback ekvCallBack = new InsertEventCallback();
    private Context mContext;
    private FileLockUtil mFileLock = new FileLockUtil();

    /* access modifiers changed from: package-private */
    public class InsertEventCallback implements FileLockCallback {
        private InsertEventCallback() {
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str) {
            return false;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(File file, int i) {
            return false;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str, Object obj) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            if (str.startsWith(DBConstant.f1002c)) {
                str = str.replaceFirst(DBConstant.f1002c, "");
            }
            UMProcessDBHelper.this.insertEvents(str.replace(DBConstant.f1003d, ""), (JSONArray) obj);
            return true;
        }
    }

    private UMProcessDBHelper() {
    }

    private UMProcessDBHelper(Context context) {
        EncryptHelper.m1084a().mo646a(context);
    }

    public static UMProcessDBHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (UMProcessDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new UMProcessDBHelper(context);
                }
            }
        }
        UMProcessDBHelper uMProcessDBHelper = mInstance;
        uMProcessDBHelper.mContext = context;
        return uMProcessDBHelper;
    }

    public void createDBByProcess(String str) {
        try {
            UMProcessDBManager.m1076a(this.mContext).mo634a(str);
            UMProcessDBManager.m1076a(this.mContext).mo635b(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertEventsInSubProcess(String str, JSONArray jSONArray) {
        if (AnalyticsConstants.SUB_PROCESS_EVENT && !TextUtils.isEmpty(str)) {
            File file = new File(UMProcessDBCreater.m1075b(this.mContext, str));
            if (file.exists()) {
                this.mFileLock.doFileOperateion(file, this.ekvCallBack, jSONArray);
            } else {
                insertEvents(str, jSONArray);
            }
        }
    }

    public void insertEvents(String str, JSONArray jSONArray) {
        if (AnalyticsConstants.SUB_PROCESS_EVENT && !TextUtils.isEmpty(str)) {
            insertEvents_(str, datasAdapter(str, jSONArray));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void processToMain(String str) {
        if (dbIsExists(str)) {
            List readEventByProcess = readEventByProcess(str);
            if (!readEventByProcess.isEmpty() && insertEvents_(DBConstant.f1007h, readEventByProcess)) {
                deleteEventDatas(str, null, readEventByProcess);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.Throwable] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0170  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x018e  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0193 A[SYNTHETIC, Splitter:B:74:0x0193] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject readMainEvents(long r20, java.util.List r22) {
        /*
        // Method dump skipped, instructions count: 421
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.readMainEvents(long, java.util.List):org.json.JSONObject");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0047, code lost:
        if (0 == 0) goto L_0x004c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void deleteMainProcessEventDatasByIds(java.util.List r8) {
        /*
            r7 = this;
            java.lang.String r0 = "_main_"
            r1 = 0
            android.content.Context r2 = r7.mContext     // Catch:{ Exception -> 0x0046, all -> 0x0036 }
            com.umeng.analytics.process.c r2 = com.umeng.analytics.process.UMProcessDBManager.m1076a(r2)     // Catch:{ Exception -> 0x0046, all -> 0x0036 }
            android.database.sqlite.SQLiteDatabase r1 = r2.mo634a(r0)     // Catch:{ Exception -> 0x0046, all -> 0x0036 }
            r1.beginTransaction()     // Catch:{ Exception -> 0x0046, all -> 0x0036 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ Exception -> 0x0046, all -> 0x0036 }
        L_0x0014:
            boolean r2 = r8.hasNext()     // Catch:{ Exception -> 0x0046, all -> 0x0036 }
            if (r2 == 0) goto L_0x0032
            java.lang.Object r2 = r8.next()     // Catch:{ Exception -> 0x0046, all -> 0x0036 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Exception -> 0x0046, all -> 0x0036 }
            java.lang.String r3 = "__et_p"
            java.lang.String r4 = "id=?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ Exception -> 0x0046, all -> 0x0036 }
            r6 = 0
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x0046, all -> 0x0036 }
            r5[r6] = r2     // Catch:{ Exception -> 0x0046, all -> 0x0036 }
            r1.delete(r3, r4, r5)     // Catch:{ Exception -> 0x0046, all -> 0x0036 }
            goto L_0x0014
        L_0x0032:
            r1.setTransactionSuccessful()     // Catch:{ Exception -> 0x0046, all -> 0x0036 }
            goto L_0x0049
        L_0x0036:
            r8 = move-exception
            if (r1 == 0) goto L_0x003c
            r1.endTransaction()
        L_0x003c:
            android.content.Context r1 = r7.mContext
            com.umeng.analytics.process.c r1 = com.umeng.analytics.process.UMProcessDBManager.m1076a(r1)
            r1.mo635b(r0)
            throw r8
        L_0x0046:
            r8 = move-exception
            if (r1 == 0) goto L_0x004c
        L_0x0049:
            r1.endTransaction()
        L_0x004c:
            android.content.Context r8 = r7.mContext
            com.umeng.analytics.process.c r8 = com.umeng.analytics.process.UMProcessDBManager.m1076a(r8)
            r8.mo635b(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.deleteMainProcessEventDatasByIds(java.util.List):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void deleteEventDatas(java.lang.String r5, java.lang.String r6, java.util.List r7) {
        /*
        // Method dump skipped, instructions count: 116
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.deleteEventDatas(java.lang.String, java.lang.String, java.util.List):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0087, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0088, code lost:
        r0 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0087 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0090 A[SYNTHETIC, Splitter:B:28:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a4 A[SYNTHETIC, Splitter:B:36:0x00a4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean insertEvents_(java.lang.String r8, java.util.List r9) {
        /*
        // Method dump skipped, instructions count: 181
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.insertEvents_(java.lang.String, java.util.List):boolean");
    }

    private List datasAdapter(String str, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                C0129a aVar = new C0129a();
                aVar.f993c = jSONObject.optString("id");
                aVar.f997g = UMUtils.getAppVersionName(this.mContext);
                aVar.f998h = UMUtils.getAppVersionCode(this.mContext);
                aVar.f992b = jSONObject.optString("__i");
                aVar.f995e = jSONObject.optInt("__t");
                aVar.f996f = str;
                if (jSONObject.has("ds")) {
                    jSONObject.remove("ds");
                }
                jSONObject.put("ds", getDataSource());
                jSONObject.remove("__i");
                jSONObject.remove("__t");
                aVar.f994d = EncryptHelper.m1084a().mo645a(jSONObject.toString());
                jSONObject.remove("ds");
                arrayList.add(aVar);
            } catch (Exception e) {
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0060, code lost:
        r3 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0062, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0063, code lost:
        r5 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005f A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x002e] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x008b A[SYNTHETIC, Splitter:B:40:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0093 A[Catch:{ Exception -> 0x008f }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a5 A[SYNTHETIC, Splitter:B:49:0x00a5] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ad A[Catch:{ Exception -> 0x00a9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject readVersionInfoFromColumId(java.lang.Integer r9) {
        /*
        // Method dump skipped, instructions count: 187
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.readVersionInfoFromColumId(java.lang.Integer):org.json.JSONObject");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a9 A[SYNTHETIC, Splitter:B:27:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b1 A[Catch:{ Exception -> 0x00ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c4 A[SYNTHETIC, Splitter:B:38:0x00c4] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00cc A[Catch:{ Exception -> 0x00c8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List readEventByProcess(java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 220
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.process.UMProcessDBHelper.readEventByProcess(java.lang.String):java.util.List");
    }

    private boolean dbIsExists(String str) {
        try {
            if (new File(UMProcessDBCreater.m1075b(this.mContext, str)).exists()) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.process.UMProcessDBHelper$a */
    public class C0129a implements Serializable {

        /* renamed from: a */
        int f991a;

        /* renamed from: b */
        String f992b;

        /* renamed from: c */
        String f993c;

        /* renamed from: d */
        String f994d;

        /* renamed from: e */
        int f995e;

        /* renamed from: f */
        String f996f;

        /* renamed from: g */
        String f997g;

        /* renamed from: h */
        String f998h;

        private C0129a() {
        }
    }

    class ProcessToMainCallback implements FileLockCallback {
        private ProcessToMainCallback() {
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            if (str.startsWith(DBConstant.f1002c)) {
                str = str.replaceFirst(DBConstant.f1002c, "");
            }
            UMProcessDBHelper.this.processToMain(str.replace(DBConstant.f1003d, ""));
            return true;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(File file, int i) {
            return false;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str, Object obj) {
            return false;
        }
    }

    public void processDBToMain() {
        try {
            DBFileTraversalUtil.traverseDBFiles(UMProcessDBCreater.m1073a(this.mContext), new ProcessToMainCallback(), new DBFileTraversalUtil.AbstractC0126a() {
                /* class com.umeng.analytics.process.UMProcessDBHelper.C01281 */

                @Override // com.umeng.analytics.process.DBFileTraversalUtil.AbstractC0126a
                /* renamed from: a */
                public void mo622a() {
                    if (AnalyticsConstants.SUB_PROCESS_EVENT) {
                        UMWorkDispatch.sendEvent(UMProcessDBHelper.this.mContext, UMProcessDBDatasSender.UM_PROCESS_CONSTRUCTMESSAGE, UMProcessDBDatasSender.getInstance(UMProcessDBHelper.this.mContext), null);
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    private int getDataSource() {
        return 0;
    }

    private boolean processIsService(Context context) {
        try {
            if (context.getPackageManager().getServiceInfo(new ComponentName(context, this.mContext.getClass()), 0) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
        }
    }
}
