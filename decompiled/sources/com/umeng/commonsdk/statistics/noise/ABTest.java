package com.umeng.commonsdk.statistics.noise;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.UContent;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.idtracking.Envelope;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.OnImprintChangedListener;

public class ABTest implements OnImprintChangedListener {
    private static ABTest instance = null;
    private Context context = null;
    private boolean isInTest = false;
    private int mGroup = -1;
    private int mInterval = -1;
    private String mPoli = null;
    private int mPolicy = -1;
    private float mProb07 = 0.0f;
    private float mProb13 = 0.0f;

    public static synchronized ABTest getService(Context context2) {
        ABTest aBTest;
        synchronized (ABTest.class) {
            if (instance == null) {
                instance = new ABTest(context2, UMEnvelopeBuild.imprintProperty(context2, "client_test", null), Integer.valueOf(UMEnvelopeBuild.imprintProperty(context2, "test_report_interval", "0")).intValue());
            }
            aBTest = instance;
        }
        return aBTest;
    }

    private ABTest(Context context2, String str, int i) {
        this.context = context2;
        onExperimentChanged(str, i);
    }

    private float prob(String str, int i) {
        int i2 = i * 2;
        if (str == null) {
            return 0.0f;
        }
        return ((float) Integer.valueOf(str.substring(i2, i2 + 5), 16).intValue()) / 1048576.0f;
    }

    public void onExperimentChanged(String str, int i) {
        this.mInterval = i;
        String signature = Envelope.getSignature(this.context);
        if (TextUtils.isEmpty(signature) || TextUtils.isEmpty(str)) {
            this.isInTest = false;
            return;
        }
        try {
            this.mProb13 = prob(signature, 12);
            this.mProb07 = prob(signature, 6);
            if (str.startsWith("SIG7")) {
                parseSig7(str);
            } else if (str.startsWith("FIXED")) {
                parseFIXED(str);
            }
        } catch (Exception e) {
            this.isInTest = false;
            MLog.m1359e("v:" + str, e);
        }
    }

    public static boolean validate(String str) {
        int parseInt;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\|");
        if (split.length != 6) {
            return false;
        }
        if (split[0].startsWith("SIG7") && split[1].split(",").length == split[5].split(",").length) {
            return true;
        }
        if (!split[0].startsWith("FIXED") || split[5].split(",").length < (parseInt = Integer.parseInt(split[1])) || parseInt < 1) {
            return false;
        }
        return true;
    }

    private void parseSig7(String str) {
        float f;
        float[] fArr;
        if (str != null) {
            String[] split = str.split("\\|");
            float f2 = 0.0f;
            if (split[2].equals("SIG13")) {
                f = Float.valueOf(split[3]).floatValue();
            } else {
                f = 0.0f;
            }
            if (this.mProb13 > f) {
                this.isInTest = false;
                return;
            }
            int[] iArr = null;
            if (split[0].equals("SIG7")) {
                String[] split2 = split[1].split(",");
                fArr = new float[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    fArr[i] = Float.valueOf(split2[i]).floatValue();
                }
            } else {
                fArr = null;
            }
            if (split[4].equals("RPT")) {
                this.mPoli = "RPT";
                String[] split3 = split[5].split(",");
                iArr = new int[split3.length];
                for (int i2 = 0; i2 < split3.length; i2++) {
                    iArr[i2] = Integer.valueOf(split3[i2]).intValue();
                }
            } else if (split[4].equals("DOM")) {
                this.isInTest = true;
                this.mPoli = "DOM";
                try {
                    String[] split4 = split[5].split(",");
                    iArr = new int[split4.length];
                    for (int i3 = 0; i3 < split4.length; i3++) {
                        iArr[i3] = Integer.valueOf(split4[i3]).intValue();
                    }
                } catch (Exception e) {
                }
            }
            int i4 = 0;
            while (true) {
                if (i4 >= fArr.length) {
                    i4 = -1;
                    break;
                }
                f2 += fArr[i4];
                if (this.mProb07 < f2) {
                    break;
                }
                i4++;
            }
            if (i4 != -1) {
                this.isInTest = true;
                this.mGroup = i4 + 1;
                if (iArr != null) {
                    this.mPolicy = iArr[i4];
                    return;
                }
                return;
            }
            this.isInTest = false;
        }
    }

    private void parseFIXED(String str) {
        int i;
        if (str != null) {
            String[] split = str.split("\\|");
            float f = 0.0f;
            if (split[2].equals("SIG13")) {
                f = Float.valueOf(split[3]).floatValue();
            }
            if (this.mProb13 > f) {
                this.isInTest = false;
                return;
            }
            if (split[0].equals("FIXED")) {
                i = Integer.valueOf(split[1]).intValue();
            } else {
                i = -1;
            }
            int[] iArr = null;
            if (split[4].equals("RPT")) {
                this.mPoli = "RPT";
                String[] split2 = split[5].split(",");
                iArr = new int[split2.length];
                for (int i2 = 0; i2 < split2.length; i2++) {
                    iArr[i2] = Integer.valueOf(split2[i2]).intValue();
                }
            } else if (split[4].equals("DOM")) {
                this.mPoli = "DOM";
                this.isInTest = true;
                try {
                    String[] split3 = split[5].split(",");
                    iArr = new int[split3.length];
                    for (int i3 = 0; i3 < split3.length; i3++) {
                        iArr[i3] = Integer.valueOf(split3[i3]).intValue();
                    }
                } catch (Exception e) {
                }
            }
            if (i != -1) {
                this.isInTest = true;
                this.mGroup = i;
                if (iArr != null) {
                    this.mPolicy = iArr[i - 1];
                    return;
                }
                return;
            }
            this.isInTest = false;
        }
    }

    public boolean isInTest() {
        return this.isInTest;
    }

    public int getTestPolicy() {
        return this.mPolicy;
    }

    public int getTestInterval() {
        return this.mInterval;
    }

    public int getGroup() {
        return this.mGroup;
    }

    public String getGroupInfo() {
        if (!this.isInTest) {
            return UContent.f593O;
        }
        return String.valueOf(this.mGroup);
    }

    public String getTestName() {
        return this.mPoli;
    }

    @Override // com.umeng.commonsdk.statistics.internal.OnImprintChangedListener
    public void onImprintChanged(ImprintHandler.C0195a aVar) {
        onExperimentChanged(aVar.mo776a("client_test", null), Integer.valueOf(aVar.mo776a("test_report_interval", "0")).intValue());
    }

    public String toString() {
        return " p13:" + this.mProb13 + " p07:" + this.mProb07 + " policy:" + this.mPolicy + " interval:" + this.mInterval;
    }
}
