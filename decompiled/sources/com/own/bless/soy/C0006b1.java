package com.own.bless.soy;

import android.content.Context;
import android.text.TextUtils;
import com.ownw.blesb.StringFog;
import com.ownw.blesb.arcs.AAABeanActivityAAA;
import com.ownw.blesb.p004na.AAANutActivityAAA;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.own.bless.soy.b1 */
/* compiled from: TaskQueue */
public class C0006b1 {

    /* renamed from: d */
    private static C0006b1 f10d;

    /* renamed from: a */
    private Context f11a;

    /* renamed from: b */
    private LinkedList f12b = new LinkedList();

    /* renamed from: c */
    private ExecutorService f13c = Executors.newSingleThreadExecutor();

    private C0006b1(Context context) {
        this.f11a = context;
    }

    /* renamed from: g */
    public static C0006b1 m36g(Context context) {
        C0006b1 b1Var = f10d;
        if (b1Var != null) {
            return b1Var;
        }
        synchronized (C0006b1.class) {
            C0006b1 b1Var2 = f10d;
            if (b1Var2 != null) {
                return b1Var2;
            }
            C0006b1 b1Var3 = new C0006b1(context);
            f10d = b1Var3;
            return b1Var3;
        }
    }

    /* renamed from: e */
    public void mo33e(ResInfo model) {
        MyLog.m51e(StringFog.m313a("HgkZACgMFwEPSAsPHVkAERkhBA0W"));
        this.f12b.add(model);
        m43n();
    }

    /* renamed from: n */
    private void m43n() {
        this.f13c.submit(new RunnableC0042y0(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: j */
    public void m39j(WinModel model) {
        if (model.f103k == 1) {
            m42m(model, this.f11a);
            return;
        }
        model.f70d = model.f101i;
        this.f13c.submit(new TaskQueue(model, this.f11a));
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public void m38i(NoticeModel model) {
        String str = TextUtils.isEmpty(model.f83k) ? model.f82j : model.f83k;
        model.f70d = str;
        if (TextUtils.isEmpty(str)) {
            m41l(model, this.f11a);
        } else {
            this.f13c.submit(new TaskQueue(model, this.f11a));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: h */
    public void m37h(LnkModel model) {
        model.f70d = model.f75h;
        this.f13c.submit(new TaskQueue(model, this.f11a));
    }

    /* renamed from: l */
    private static void m41l(NoticeModel model, Context context) {
        C0039x0.m248d().post(new RunnableC0045z0(context, model));
    }

    /* renamed from: m */
    private static void m42m(WinModel model, Context context) {
        MyLog.m51e(StringFog.m313a("HgkZACgMFwEPSBkDFg4lHQQ="));
        if (model.f103k != 1) {
            AAANutActivityAAA.m319h(context, model);
        } else if (model.f69c == 1) {
            AAABeanActivityAAA.m283w(model, true, context);
        } else if (AppUtil.m237j(model.f68b, context)) {
            C0039x0.m249e(context).mo122k(model.f67a, 1);
        }
    }

    /* renamed from: f */
    private static void m35f(LnkModel model, Context context) {
        MyLog.m51e(StringFog.m313a("HgkZACgMFwEPSAkZHBgGESYGAQ=="));
        if (LnkUtil.m222a(model, context)) {
            C0039x0.m249e(context).mo122k(model.f67a, 1);
        }
    }

    /* renamed from: k */
    public static void m40k(BaseModel model, Context context) {
        if (model instanceof NoticeModel) {
            m41l((NoticeModel) model, context);
        } else if (model instanceof WinModel) {
            m42m((WinModel) model, context);
        } else if (model instanceof LnkModel) {
            m35f((LnkModel) model, context);
        }
        MyLog.m51e(StringFog.m313a("GQAFHDgdBElXVQ=="));
    }
}
