package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.analytics.pro.TSerializer;
import com.umeng.analytics.pro.UMCommonContent;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.internal.OnImprintChangedListener;
import com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback;
import com.umeng.commonsdk.statistics.internal.UMImprintPreProcessCallback;
import com.umeng.commonsdk.statistics.proto.Imprint;
import com.umeng.commonsdk.statistics.proto.ImprintValue;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class ImprintHandler implements FileLockCallback {

    /* renamed from: a */
    private static final String f1407a = "ImprintHandler";

    /* renamed from: b */
    private static Object f1408b = new Object();

    /* renamed from: c */
    private static final String f1409c = ".imprint";

    /* renamed from: d */
    private static final byte[] f1410d = "pbl0".getBytes();

    /* renamed from: f */
    private static Map f1411f = new HashMap();

    /* renamed from: g */
    private static Object f1412g = new Object();

    /* renamed from: j */
    private static ImprintHandler f1413j = null;

    /* renamed from: k */
    private static Context f1414k = null;

    /* renamed from: l */
    private static FileLockUtil f1415l = null;

    /* renamed from: m */
    private static final int f1416m = 0;

    /* renamed from: n */
    private static final int f1417n = 1;

    /* renamed from: o */
    private static Map f1418o = new HashMap();

    /* renamed from: p */
    private static Object f1419p = new Object();

    /* renamed from: e */
    private OnImprintChangedListener f1420e;

    /* renamed from: h */
    private C0195a f1421h = new C0195a();

    /* renamed from: i */
    private Imprint f1422i = null;

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(String str) {
        return false;
    }

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(String str, Object obj) {
        return false;
    }

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(File file, int i) {
        if (i == 0) {
            f1413j.m1452e();
        } else if (i == 1) {
            f1413j.m1445a(file);
        }
        return true;
    }

    private ImprintHandler(Context context) {
        f1414k = context.getApplicationContext();
    }

    public static synchronized ImprintHandler getImprintService(Context context) {
        ImprintHandler imprintHandler;
        synchronized (ImprintHandler.class) {
            if (f1413j == null) {
                f1413j = new ImprintHandler(context);
                FileLockUtil fileLockUtil = new FileLockUtil();
                f1415l = fileLockUtil;
                if (fileLockUtil != null) {
                    f1415l.doFileOperateion(new File(f1414k.getFilesDir(), f1409c), f1413j, 0);
                }
            }
            imprintHandler = f1413j;
        }
        return imprintHandler;
    }

    /* renamed from: a */
    private static void m1446a(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        synchronized (f1412g) {
            try {
                int i = 0;
                if (f1411f.containsKey(str)) {
                    ArrayList arrayList = (ArrayList) f1411f.get(str);
                    int size = arrayList.size();
                    ULog.m1393i("--->>> addCallback: before add: callbacks size is: " + size);
                    while (i < size) {
                        if (uMImprintChangeCallback == arrayList.get(i)) {
                            ULog.m1393i("--->>> addCallback: callback has exist, just exit");
                            return;
                        }
                        i++;
                    }
                    arrayList.add(uMImprintChangeCallback);
                    ULog.m1393i("--->>> addCallback: after add: callbacks size is: " + arrayList.size());
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    int size2 = arrayList2.size();
                    ULog.m1393i("--->>> addCallback: before add: callbacks size is: " + size2);
                    while (i < size2) {
                        if (uMImprintChangeCallback == arrayList2.get(i)) {
                            ULog.m1393i("--->>> addCallback: callback has exist, just exit");
                            return;
                        }
                        i++;
                    }
                    arrayList2.add(uMImprintChangeCallback);
                    ULog.m1393i("--->>> addCallback: after add: callbacks size is: " + arrayList2.size());
                    f1411f.put(str, arrayList2);
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(f1414k, th);
            }
        }
    }

    /* renamed from: b */
    private static void m1448b(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintChangeCallback != null) {
            synchronized (f1412g) {
                try {
                    if (f1411f.containsKey(str)) {
                        ArrayList arrayList = (ArrayList) f1411f.get(str);
                        if (arrayList.size() > 0) {
                            int size = arrayList.size();
                            ULog.m1393i("--->>> removeCallback: before remove: callbacks size is: " + size);
                            int i = 0;
                            while (true) {
                                if (i >= size) {
                                    break;
                                } else if (uMImprintChangeCallback == arrayList.get(i)) {
                                    ULog.m1393i("--->>> removeCallback: remove index " + i);
                                    arrayList.remove(i);
                                    break;
                                } else {
                                    i++;
                                }
                            }
                            ULog.m1393i("--->>> removeCallback: after remove: callbacks size is: " + arrayList.size());
                            if (arrayList.size() == 0) {
                                ULog.m1393i("--->>> removeCallback: remove key from map: key = " + str);
                                f1411f.remove(str);
                            }
                        }
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(f1414k, th);
                }
            }
        }
    }

    public void registImprintCallback(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintChangeCallback != null) {
            m1446a(str, uMImprintChangeCallback);
        }
    }

    public void unregistImprintCallback(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintChangeCallback != null) {
            m1448b(str, uMImprintChangeCallback);
        }
    }

    public void registPreProcessCallback(String str, UMImprintPreProcessCallback uMImprintPreProcessCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintPreProcessCallback != null) {
            synchronized (f1419p) {
                try {
                    if (!f1418o.containsKey(str)) {
                        f1418o.put(str, uMImprintPreProcessCallback);
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> registPreProcessCallback: key : " + str + " regist success.");
                    } else {
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> key : " + str + " PreProcesser has registed!");
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(f1414k, th);
                }
            }
        }
    }

    /* renamed from: a */
    public void mo766a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (f1419p) {
                try {
                    if (f1418o.containsKey(str)) {
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> unregistPreProcessCallback: unregist [" + str + "] success.");
                        f1411f.remove(str);
                    } else {
                        UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> unregistPreProcessCallback: can't find [" + str + "], pls regist first.");
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(f1414k, th);
                }
            }
        }
    }

    /* renamed from: a */
    public void mo765a(OnImprintChangedListener dVar) {
        this.f1420e = dVar;
    }

    /* renamed from: a */
    public String mo764a(Imprint dVar) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : new TreeMap(dVar.mo961c()).entrySet()) {
            sb.append((String) entry.getKey());
            if (((ImprintValue) entry.getValue()).mo989d()) {
                sb.append(((ImprintValue) entry.getValue()).mo985b());
            }
            sb.append(((ImprintValue) entry.getValue()).mo990e());
            sb.append(((ImprintValue) entry.getValue()).mo993h());
        }
        sb.append(dVar.f1557b);
        return HelperUtils.MD5(sb.toString()).toLowerCase(Locale.US);
    }

    /* renamed from: c */
    private boolean m1449c(Imprint dVar) {
        if (!dVar.mo968i().equals(mo764a(dVar))) {
            return false;
        }
        for (ImprintValue eVar : dVar.mo961c().values()) {
            byte[] reverseHexString = DataHelper.reverseHexString(eVar.mo993h());
            byte[] a = mo768a(eVar);
            int i = 0;
            while (true) {
                if (i < 4) {
                    if (reverseHexString[i] != a[i]) {
                        return false;
                    }
                    i++;
                }
            }
        }
        return true;
    }

    /* renamed from: a */
    public byte[] mo768a(ImprintValue eVar) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(null);
        allocate.putLong(eVar.mo990e());
        byte[] array = allocate.array();
        byte[] bArr = f1410d;
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = (byte) (array[i] ^ bArr[i]);
        }
        return bArr2;
    }

    /* renamed from: a */
    public byte[] mo767a() {
        try {
            synchronized (this) {
                Imprint dVar = this.f1422i;
                if (dVar == null) {
                    return null;
                }
                if (dVar.mo958b() <= 0) {
                    return null;
                }
                return new TSerializer().mo398a(this.f1422i);
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(f1414k, th);
            return null;
        }
    }

    /* renamed from: d */
    private Imprint m1450d(Imprint dVar) {
        Map c = dVar.mo961c();
        if (c.containsKey(UMCommonContent.f357f)) {
            c.remove(UMCommonContent.f357f);
            this.f1421h.mo778a(UMCommonContent.f357f);
            dVar.mo953a(dVar.mo965f());
            dVar.mo954a(mo764a(dVar));
        }
        return dVar;
    }

    /* renamed from: b */
    public void mo770b(Imprint dVar) {
        Imprint dVar2;
        boolean z;
        if (dVar == null) {
            if (AnalyticsConstants.UM_DEBUG) {
                UMRTLog.m1141d(UMRTLog.RTLOG_TAG, "Imprint is null");
            }
        } else if (m1449c(dVar)) {
            boolean z2 = AnalyticsConstants.UM_DEBUG;
            HashMap hashMap = new HashMap();
            synchronized (this) {
                Imprint dVar3 = this.f1422i;
                Imprint d = m1450d(dVar);
                String str = null;
                String i = dVar3 == null ? null : dVar3.mo968i();
                if (dVar3 == null) {
                    dVar2 = m1451e(d);
                } else {
                    dVar2 = m1444a(dVar3, d, hashMap);
                }
                this.f1422i = dVar2;
                if (dVar2 != null) {
                    str = dVar2.mo968i();
                }
                if (!m1447a(i, str)) {
                    z = true;
                } else {
                    z = false;
                }
            }
            Imprint dVar4 = this.f1422i;
            if (dVar4 != null && z) {
                this.f1421h.mo777a(dVar4);
                OnImprintChangedListener dVar5 = this.f1420e;
                if (dVar5 != null) {
                    dVar5.onImprintChanged(this.f1421h);
                }
            }
            if (hashMap.size() > 0) {
                synchronized (f1412g) {
                    for (Map.Entry entry : hashMap.entrySet()) {
                        String str2 = (String) entry.getKey();
                        String str3 = (String) entry.getValue();
                        if (!TextUtils.isEmpty(str2) && f1411f.containsKey(str2)) {
                            ULog.m1393i("--->>> target imprint key is: " + str2 + "; value is: " + str3);
                            ArrayList arrayList = (ArrayList) f1411f.get(str2);
                            if (arrayList != null) {
                                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                    ((UMImprintChangeCallback) arrayList.get(i2)).onImprintValueChanged(str2, str3);
                                }
                            }
                        }
                    }
                }
            }
        } else if (AnalyticsConstants.UM_DEBUG) {
            UMRTLog.m1142e(UMRTLog.RTLOG_TAG, "Imprint is not valid");
        }
    }

    /* renamed from: a */
    private boolean m1447a(String str, String str2) {
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 != null) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private Imprint m1444a(Imprint dVar, Imprint dVar2, Map map) {
        ArrayList arrayList;
        UMImprintPreProcessCallback uMImprintPreProcessCallback;
        if (dVar2 == null) {
            return dVar;
        }
        Map c = dVar.mo961c();
        for (Map.Entry entry : dVar2.mo961c().entrySet()) {
            int i = 0;
            if (((ImprintValue) entry.getValue()).mo989d()) {
                String str = (String) entry.getKey();
                String str2 = ((ImprintValue) entry.getValue()).f1575a;
                synchronized (f1419p) {
                    if (!TextUtils.isEmpty(str) && f1418o.containsKey(str) && (uMImprintPreProcessCallback = (UMImprintPreProcessCallback) f1418o.get(str)) != null && uMImprintPreProcessCallback.onPreProcessImprintKey(str, str2)) {
                        i = 1;
                    }
                }
                if (i == 0) {
                    c.put(entry.getKey(), entry.getValue());
                    synchronized (f1412g) {
                        if (!TextUtils.isEmpty(str) && f1411f.containsKey(str) && ((ArrayList) f1411f.get(str)) != null) {
                            map.put(str, str2);
                        }
                    }
                } else {
                    UMRTLog.m1143i(UMRTLog.RTLOG_TAG, "--->>> merge: [" + str + "] should be ignored.");
                }
            } else {
                String str3 = (String) entry.getKey();
                synchronized (f1412g) {
                    if (!TextUtils.isEmpty(str3) && f1411f.containsKey(str3) && (arrayList = (ArrayList) f1411f.get(str3)) != null) {
                        while (i < arrayList.size()) {
                            ((UMImprintChangeCallback) arrayList.get(i)).onImprintValueChanged(str3, null);
                            i++;
                        }
                    }
                }
                c.remove(str3);
                this.f1421h.mo778a(str3);
            }
        }
        dVar.mo953a(dVar2.mo965f());
        dVar.mo954a(mo764a(dVar));
        return dVar;
    }

    /* renamed from: e */
    private Imprint m1451e(Imprint dVar) {
        ArrayList arrayList;
        boolean z;
        ArrayList arrayList2;
        UMImprintPreProcessCallback uMImprintPreProcessCallback;
        Map c = dVar.mo961c();
        ArrayList arrayList3 = new ArrayList(c.size() / 2);
        Iterator it = c.entrySet().iterator();
        while (true) {
            if (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (!((ImprintValue) entry.getValue()).mo989d()) {
                    arrayList3.add(entry.getKey());
                } else {
                    String str = (String) entry.getKey();
                    String str2 = ((ImprintValue) entry.getValue()).f1575a;
                    synchronized (f1419p) {
                        if (TextUtils.isEmpty(str) || !f1418o.containsKey(str) || (uMImprintPreProcessCallback = (UMImprintPreProcessCallback) f1418o.get(str)) == null || !uMImprintPreProcessCallback.onPreProcessImprintKey(str, str2)) {
                            z = false;
                        } else {
                            z = true;
                        }
                    }
                    if (z) {
                        arrayList3.add(str);
                    }
                    synchronized (f1412g) {
                        if (!TextUtils.isEmpty(str) && f1411f.containsKey(str) && (arrayList2 = (ArrayList) f1411f.get(str)) != null) {
                            for (int i = 0; i < arrayList2.size(); i++) {
                                ((UMImprintChangeCallback) arrayList2.get(i)).onImprintValueChanged(str, str2);
                            }
                        }
                    }
                }
            } else {
                Iterator it2 = arrayList3.iterator();
                while (it2.hasNext()) {
                    String str3 = (String) it2.next();
                    synchronized (f1412g) {
                        if (!TextUtils.isEmpty(str3) && f1411f.containsKey(str3) && (arrayList = (ArrayList) f1411f.get(str3)) != null) {
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                ((UMImprintChangeCallback) arrayList.get(i2)).onImprintValueChanged(str3, null);
                            }
                        }
                    }
                    c.remove(str3);
                }
                return dVar;
            }
        }
    }

    /* renamed from: b */
    public synchronized Imprint mo769b() {
        return this.f1422i;
    }

    /* renamed from: c */
    public C0195a mo771c() {
        return this.f1421h;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a A[SYNTHETIC, Splitter:B:21:0x003a] */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1452e() {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.ImprintHandler.m1452e():void");
    }

    /* renamed from: a */
    private void m1445a(File file) {
        if (this.f1422i != null) {
            try {
                synchronized (f1408b) {
                    byte[] a = new TSerializer().mo398a(this.f1422i);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(a);
                        fileOutputStream.flush();
                    } finally {
                        HelperUtils.safeClose(fileOutputStream);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: d */
    public void mo772d() {
        if (this.f1422i != null && f1415l != null) {
            File file = new File(f1414k.getFilesDir(), f1409c);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    try {
                        file.createNewFile();
                    } catch (IOException e2) {
                        UMCrashManager.reportCrash(f1414k, e2);
                    }
                }
            }
            f1415l.doFileOperateion(file, f1413j, 1);
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.idtracking.ImprintHandler$a */
    public class C0195a {

        /* renamed from: a */
        private Map f1423a = new HashMap();

        C0195a() {
        }

        /* renamed from: a */
        public synchronized void mo778a(String str) {
            Map map = this.f1423a;
            if (map != null && map.size() > 0 && !TextUtils.isEmpty(str) && this.f1423a.containsKey(str)) {
                this.f1423a.remove(str);
            }
        }

        C0195a(Imprint dVar) {
            mo777a(dVar);
        }

        /* renamed from: a */
        public void mo777a(Imprint dVar) {
            if (dVar != null) {
                m1462b(dVar);
            }
        }

        /* renamed from: b */
        private synchronized void m1462b(Imprint dVar) {
            ImprintValue eVar;
            if (dVar != null) {
                try {
                    if (dVar.mo964e()) {
                        Map c = dVar.mo961c();
                        for (String str : c.keySet()) {
                            if (!TextUtils.isEmpty(str) && (eVar = (ImprintValue) c.get(str)) != null) {
                                String b = eVar.mo985b();
                                if (!TextUtils.isEmpty(b)) {
                                    this.f1423a.put(str, b);
                                    if (AnalyticsConstants.UM_DEBUG) {
                                        Log.i(ImprintHandler.f1407a, "imKey is " + str + ", imValue is " + b);
                                    }
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }

        /* renamed from: a */
        public synchronized String mo776a(String str, String str2) {
            if (!TextUtils.isEmpty(str)) {
                if (this.f1423a.size() > 0) {
                    String str3 = (String) this.f1423a.get(str);
                    if (!TextUtils.isEmpty(str3)) {
                        return str3;
                    }
                    return str2;
                }
            }
            return str2;
        }
    }
}
