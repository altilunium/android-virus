package com.ownw.blesb.p004na;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.own.bless.soy.AppUtil;
import com.own.bless.soy.C0039x0;
import com.own.bless.soy.JsonUtils;
import com.own.bless.soy.MyLog;
import com.own.bless.soy.WinModel;
import com.ownw.blesb.AAABaseActivity;
import com.ownw.blesb.StringFog;
import com.ownw.blesb.arcs.AAABeanActivityAAA;
import com.ownw.blesb.view.WinLayout;

/* renamed from: com.ownw.blesb.na.AAANutActivityAAA */
public class AAANutActivityAAA extends AAABaseActivity implements View.OnClickListener {

    /* renamed from: b */
    private WinLayout f167b;

    /* renamed from: c */
    private RelativeLayout f168c;

    /* renamed from: d */
    private ImageView f169d;

    /* renamed from: e */
    private TextView f170e;

    /* renamed from: f */
    private WinModel f171f;

    /* renamed from: g */
    Bitmap f172g = null;

    /* renamed from: h */
    private long f173h = 0;

    /* access modifiers changed from: protected */
    @Override // com.ownw.blesb.AAABaseActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT != 26) {
            int ori = getResources().getConfiguration().orientation;
            if (ori == 2) {
                setRequestedOrientation(0);
            } else if (ori == 1) {
                setRequestedOrientation(1);
            }
        }
        m315d();
        WinModel qVar = this.f171f;
        if (qVar == null) {
            finish();
            return;
        }
        if (qVar.f100h != 1) {
            m320i();
        }
        WinLayout bVar = new WinLayout(this);
        this.f167b = bVar;
        this.f168c = bVar.getLayout();
        setContentView(this.f167b);
        try {
            Bitmap decodeFile = BitmapFactory.decodeFile(this.f171f.mo71a(this));
            this.f172g = decodeFile;
            if (decodeFile == null) {
                finish();
                return;
            }
            m317f();
            if (this.f171f.f100h == 1) {
                m316e();
            } else {
                m314c();
            }
            C0039x0.m249e(getApplicationContext()).mo122k(this.f171f.f67a, 1);
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    /* renamed from: f */
    private void m317f() {
        this.f169d = this.f167b.getIVPic();
        this.f170e = this.f167b.getIVClose();
        this.f169d.setOnClickListener(this);
        this.f170e.setOnClickListener(this);
        this.f169d.setImageBitmap(this.f172g);
    }

    /* renamed from: e */
    private void m316e() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this.f168c.getLayoutParams();
        params.height = -1;
        params.width = -1;
        this.f168c.setBackgroundColor(-1);
        this.f168c.setLayoutParams(params);
    }

    /* renamed from: c */
    private void m314c() {
        double g = (double) AppUtil.m234g(this);
        Double.isNaN(g);
        int height = (int) (g * 0.4d);
        int width = AppUtil.m235h(this) - AppUtil.m230c(this, 10.0f);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this.f168c.getLayoutParams();
        MyLog.m51e(StringFog.m313a("CAEeBhgJUgMDDB4DRA==") + this.f172g.getWidth() + StringFog.m313a("SgAPAh4RBkk=") + this.f172g.getHeight() + StringFog.m313a("ShsJGRwcHCMDDB4DRA==") + width);
        if (this.f172g.getWidth() < width) {
            params.width = this.f172g.getWidth();
        } else {
            params.width = width;
        }
        if (this.f172g.getHeight() < height) {
            params.height = this.f172g.getHeight();
        } else {
            params.height = height;
        }
        if (this.f172g.getHeight() < height && this.f172g.getWidth() < width) {
            float wRate = ((float) width) / (((float) this.f172g.getWidth()) * 1.0f);
            float hRate = ((float) height) / (((float) this.f172g.getHeight()) * 1.0f);
            float rate = wRate > hRate ? hRate : wRate;
            params.height = (int) (((float) params.height) * rate);
            params.width = (int) (((float) params.width) * rate);
        }
        this.f168c.setLayoutParams(params);
    }

    /* renamed from: d */
    private void m315d() {
        Intent intent = getIntent();
        if (intent != null) {
            String data = intent.getStringExtra(StringFog.m313a("LzA+OTgmJT0kNysoLTA9OjUmKyY8"));
            if (!TextUtils.isEmpty(data)) {
                this.f171f = JsonUtils.m270c(data);
            }
        }
    }

    public void onClick(View v) {
        if (v instanceof ImageView) {
            m318g();
        } else if (v instanceof TextView) {
            finish();
        }
    }

    /* renamed from: g */
    private void m318g() {
        if (this.f171f != null && System.currentTimeMillis() - this.f173h >= 2000) {
            this.f173h = System.currentTimeMillis();
            WinModel qVar = this.f171f;
            if (qVar.f69c == 1) {
                AAABeanActivityAAA.m283w(qVar, false, this);
            } else {
                AppUtil.m237j(qVar.f68b, this);
            }
            C0039x0.m249e(getApplicationContext()).mo122k(this.f171f.f67a, 2);
            finish();
        }
    }

    /* renamed from: h */
    public static void m319h(Context context, WinModel model) {
        try {
            Intent intent = new Intent(context, AAANutActivityAAA.class);
            intent.putExtra(StringFog.m313a("LzA+OTgmJT0kNysoLTA9OjUmKyY8"), JsonUtils.m268a(model));
            intent.setFlags(268468224);
            context.startActivity(intent);
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.ownw.blesb.AAABaseActivity
    public void onDestroy() {
        super.onDestroy();
        Bitmap bitmap = this.f172g;
        if (bitmap != null) {
            bitmap.recycle();
            this.f172g = null;
        }
    }

    /* renamed from: i */
    private void m320i() {
        MyLog.m51e(StringFog.m313a("EgEPVkQOGxorCx4CDxAGDUocDxgNLRoRBw0="));
        requestWindowFeature(1);
        getWindow().getDecorView().setBackgroundColor(0);
        getWindow().setFlags(32, 32);
    }
}
