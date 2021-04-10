package com.ownw.blesb.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.MotionEvent;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.own.bless.soy.AppUtil;
import com.own.bless.soy.BaseModel;
import com.own.bless.soy.MyLog;
import com.ownw.blesb.StringFog;
import com.umeng.commonsdk.statistics.UMErrorCode;

/* renamed from: com.ownw.blesb.view.a */
public class WebLayout extends RelativeLayout {

    /* renamed from: a */
    public int f175a = UMErrorCode.E_UM_BE_SAVE_FAILED;

    /* renamed from: b */
    private ProgressBar f176b;

    /* renamed from: c */
    private BaseModel f177c;

    /* renamed from: d */
    private long f178d;

    public WebLayout(Context context, BaseModel model) {
        super(context);
        this.f177c = model;
        this.f178d = System.currentTimeMillis();
        m321a(context);
    }

    /* renamed from: a */
    private void m321a(Context context) {
        setBackgroundColor(-1);
        ProgressBar progressBar = new ProgressBar(context, null, 16842872);
        this.f176b = progressBar;
        progressBar.setId(this.f175a);
        RelativeLayout.LayoutParams progressBarParam = new RelativeLayout.LayoutParams(-1, AppUtil.m230c(context, 4.0f));
        progressBarParam.addRule(10);
        m322b(context);
        addView(this.f176b, progressBarParam);
    }

    /* renamed from: b */
    private void m322b(Context context) {
        int roundRadius = AppUtil.m230c(context, 2.0f);
        GradientDrawable progressBg = new GradientDrawable();
        progressBg.setCornerRadius((float) roundRadius);
        progressBg.setColor(Color.parseColor(StringFog.m313a("SS1cLk88RA==")));
        GradientDrawable progressContent = new GradientDrawable();
        progressContent.setCornerRadius((float) roundRadius);
        progressContent.setColor(Color.parseColor(StringFog.m313a("SQ4MCBpJQkRa")));
        LayerDrawable progressLayerDrawable = new LayerDrawable(new Drawable[]{progressBg, new ClipDrawable(progressContent, 3, 1)});
        progressLayerDrawable.setId(0, 16908288);
        progressLayerDrawable.setId(1, 16908301);
        this.f176b.setProgressDrawable(progressLayerDrawable);
    }

    public ProgressBar getProgressBar() {
        return this.f176b;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        BaseModel lVar = this.f177c;
        if (lVar == null || lVar.f72f <= 0 || Math.abs(System.currentTimeMillis() - this.f178d) > ((long) this.f177c.f72f) * 1000) {
            return super.onInterceptTouchEvent(ev);
        }
        MyLog.m47a(StringFog.m313a("MR8PCQ8QFwM3SAUFMBcGERgLDxsNLR0BCQAvHRwXBg=="));
        return true;
    }
}
