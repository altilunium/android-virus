package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.UMCommonContent;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import java.io.File;

/* renamed from: com.umeng.commonsdk.statistics.idtracking.j */
public class OldUMIDTracker extends AbstractIdTracker {

    /* renamed from: a */
    private static final String f1458a = "oldumid";

    /* renamed from: b */
    private Context f1459b;

    /* renamed from: c */
    private String f1460c = null;

    /* renamed from: d */
    private String f1461d = null;

    public OldUMIDTracker(Context context) {
        super(f1458a);
        this.f1459b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractIdTracker
    /* renamed from: f */
    public String mo788f() {
        return this.f1460c;
    }

    /* renamed from: g */
    public boolean mo801g() {
        return mo802h();
    }

    /* renamed from: h */
    public boolean mo802h() {
        String imprintProperty = UMEnvelopeBuild.imprintProperty(this.f1459b, UMCommonContent.f358g, null);
        this.f1461d = imprintProperty;
        if (TextUtils.isEmpty(imprintProperty)) {
            return false;
        }
        this.f1461d = DataHelper.encryptBySHA1(this.f1461d);
        String readFile = HelperUtils.readFile(new File("/sdcard/Android/data/.um/sysid.dat"));
        String readFile2 = HelperUtils.readFile(new File("/sdcard/Android/obj/.um/sysid.dat"));
        String readFile3 = HelperUtils.readFile(new File("/data/local/tmp/.um/sysid.dat"));
        if (TextUtils.isEmpty(readFile)) {
            m1506l();
        } else if (!this.f1461d.equals(readFile)) {
            this.f1460c = readFile;
            return true;
        }
        if (TextUtils.isEmpty(readFile2)) {
            m1505k();
        } else if (!this.f1461d.equals(readFile2)) {
            this.f1460c = readFile2;
            return true;
        }
        if (TextUtils.isEmpty(readFile3)) {
            m1504j();
            return false;
        } else if (this.f1461d.equals(readFile3)) {
            return false;
        } else {
            this.f1460c = readFile3;
            return true;
        }
    }

    /* renamed from: i */
    public void mo803i() {
        try {
            m1506l();
            m1505k();
            m1504j();
        } catch (Exception e) {
        }
    }

    /* renamed from: j */
    private void m1504j() {
        try {
            m1503b("/data/local/tmp/.um");
            HelperUtils.writeFile(new File("/data/local/tmp/.um/sysid.dat"), this.f1461d);
        } catch (Throwable th) {
        }
    }

    /* renamed from: k */
    private void m1505k() {
        try {
            m1503b("/sdcard/Android/obj/.um");
            HelperUtils.writeFile(new File("/sdcard/Android/obj/.um/sysid.dat"), this.f1461d);
        } catch (Throwable th) {
        }
    }

    /* renamed from: l */
    private void m1506l() {
        try {
            m1503b("/sdcard/Android/data/.um");
            HelperUtils.writeFile(new File("/sdcard/Android/data/.um/sysid.dat"), this.f1461d);
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    private void m1503b(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
