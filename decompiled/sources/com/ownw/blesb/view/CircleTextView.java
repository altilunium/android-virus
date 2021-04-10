package com.ownw.blesb.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

public class CircleTextView extends TextView {

    /* renamed from: a */
    private Paint f174a;

    public CircleTextView(Context context) {
        super(context);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f174a = new Paint();
        RectF rectf = new RectF();
        this.f174a.setColor(-16777216);
        this.f174a.setStyle(Paint.Style.FILL);
        this.f174a.setAntiAlias(true);
        this.f174a.setStrokeWidth(2.0f);
        int r = getMeasuredWidth() > getMeasuredHeight() ? getMeasuredWidth() : getMeasuredHeight();
        rectf.set((float) getPaddingLeft(), (float) getPaddingTop(), (float) (r - getPaddingRight()), (float) (r - getPaddingBottom()));
        canvas.drawArc(rectf, 0.0f, 360.0f, false, this.f174a);
        this.f174a.setColor(-1);
        this.f174a.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectf, 0.0f, 360.0f, false, this.f174a);
        this.f174a.setStrokeWidth(3.0f);
        canvas.drawLine((float) (getWidth() / 3), (float) (getWidth() / 3), (float) (getWidth() - (getWidth() / 3)), (float) (getWidth() - (getWidth() / 3)), this.f174a);
        canvas.drawLine((float) (getWidth() - (getWidth() / 3)), (float) (getWidth() / 3), (float) (getWidth() / 3), (float) (getWidth() - (getWidth() / 3)), this.f174a);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
