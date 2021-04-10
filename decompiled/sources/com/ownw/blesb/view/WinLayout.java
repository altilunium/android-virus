package com.ownw.blesb.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.own.bless.soy.AppUtil;
import com.ownw.blesb.StringFog;

/* renamed from: com.ownw.blesb.view.b */
public class WinLayout extends RelativeLayout {

    /* renamed from: a */
    private RelativeLayout f179a;

    /* renamed from: b */
    private ImageView f180b;

    /* renamed from: c */
    private TextView f181c;

    public WinLayout(Context context) {
        super(context);
        m323a(context);
        setBackgroundColor(0);
    }

    /* renamed from: a */
    private void m323a(Context context) {
        this.f179a = new RelativeLayout(context);
        ImageView imageView = new ImageView(context);
        this.f180b = imageView;
        imageView.setTag(StringFog.m313a("W1haWg=="));
        this.f180b.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.f180b.setBackgroundColor(-1);
        this.f179a.addView(this.f180b, new RelativeLayout.LayoutParams(-1, -1));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.f179a.setBackgroundColor(0);
        addView(this.f179a, layoutParams);
        int height = AppUtil.m230c(context, 30.0f);
        int width = AppUtil.m230c(context, 30.0f);
        RelativeLayout.LayoutParams closeParams = new RelativeLayout.LayoutParams(-2, -2);
        closeParams.addRule(10);
        closeParams.addRule(11);
        CircleTextView circleTextView = new CircleTextView(context);
        this.f181c = circleTextView;
        circleTextView.setAlpha(0.8f);
        this.f181c.setPadding(3, 3, 3, 3);
        this.f181c.setMinHeight(height);
        this.f181c.setMinWidth(width);
        this.f181c.setGravity(17);
        this.f179a.addView(this.f181c, closeParams);
    }

    public ImageView getIVPic() {
        return this.f180b;
    }

    public TextView getIVClose() {
        return this.f181c;
    }

    public RelativeLayout getLayout() {
        return this.f179a;
    }
}
