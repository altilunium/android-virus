package com.ownw.blesb.bws;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.own.bless.soy.AppUtil;
import com.own.bless.soy.C0039x0;
import com.own.bless.soy.JsonUtils;
import com.own.bless.soy.MyLog;
import com.own.bless.soy.NoticeModel;
import com.own.bless.soy.PermissionUtil;
import com.ownw.blesb.StringFog;
import com.ownw.blesb.arcs.AAABeanActivityAAA;

public class BlessService extends IntentService {

    /* renamed from: a */
    private static final String f157a = StringFog.m313a("CwYOGRYQFloDBh4OFw1cFQkcAwQXVwoZRAYPHw==");

    /* renamed from: b */
    private static final String f158b = StringFog.m313a("CwYOGRYQFloDBh4OFw1cFQkcAwQXVwoZRAYPH1cdFxgLEQ==");

    /* renamed from: c */
    private static final String f159c = StringFog.m313a("AwYeDhcNXBUJHAMEF1ccGx4BCQ5XGh4dCQM=");

    /* renamed from: d */
    private static final String f160d = StringFog.m313a("AwYeDhcNXBUJHAMEF1cAERoHGB9XCgYVHg0=");

    /* renamed from: e */
    private static final String f161e = StringFog.m313a("CwseAhYXXB0EHA8FDVc1JismPg==");

    /* renamed from: f */
    private static final String f162f = StringFog.m313a("Iyku");

    /* renamed from: g */
    private static final String f163g = StringFog.m313a("Kys+IjY3");

    public BlessService() {
        super(StringFog.m313a("KAQPGAoqFwYcAQkO"));
    }

    /* renamed from: h */
    public static void m306h(Context context) {
        try {
            Intent intent = new Intent(context, BlessService.class);
            intent.setAction(f157a);
            context.startService(intent);
        } catch (Throwable th) {
        }
    }

    /* renamed from: i */
    public static void m307i(Context context) {
        try {
            Intent intent = new Intent(context, BlessService.class);
            intent.setAction(f158b);
            context.startService(intent);
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static Intent m299a(Context context) {
        Intent intent = new Intent(context, BlessService.class);
        intent.setAction(f159c);
        return intent;
    }

    /* renamed from: j */
    public static void m308j(Context context, String aid, int action) {
        try {
            Intent intent = new Intent(context, BlessService.class);
            intent.setAction(f160d);
            intent.putExtra(f162f, aid);
            intent.putExtra(f163g, action);
            context.startService(intent);
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            MyLog.m51e(StringFog.m313a("BQYiChcdHhEjBh4OFw1SFQkcAwQXRA==") + action);
            if (!TextUtils.isEmpty(action)) {
                char c = 65535;
                switch (action.hashCode()) {
                    case -1130872791:
                        if (action.equals(f157a)) {
                            c = 0;
                            break;
                        }
                        break;
                    case -945090216:
                        if (action.equals(f159c)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -292841027:
                        if (action.equals(f160d)) {
                            c = 4;
                            break;
                        }
                        break;
                    case 583760866:
                        if (action.equals(f161e)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1182229374:
                        if (action.equals(f158b)) {
                            c = 1;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    m302d();
                } else if (c == 1) {
                    m303e();
                } else if (c == 2) {
                    m304f(intent);
                } else if (c == 3) {
                    m301c(getApplicationContext());
                } else if (c == 4) {
                    m305g(intent);
                }
            }
        }
    }

    /* renamed from: g */
    private void m305g(Intent intent) {
        if (intent != null) {
            String aid = intent.getStringExtra(f162f);
            int action = intent.getIntExtra(f163g, -1);
            if (!TextUtils.isEmpty(aid) && action != -1) {
                C0039x0.m249e(getApplicationContext()).mo122k(aid, action);
            }
        }
    }

    /* renamed from: d */
    private void m302d() {
        C0039x0.m249e(getApplicationContext()).mo124n();
        C0039x0.m249e(getApplicationContext()).mo121i();
    }

    /* renamed from: e */
    private void m303e() {
        C0039x0.m249e(getApplicationContext()).mo124n();
        C0039x0.m249e(getApplicationContext()).mo123m();
    }

    /* renamed from: c */
    private void m301c(Context context) {
        m300b(context, StringFog.m313a("CwYOGRYQFloaDRgGEAoBHQUGRDwrMCYxNS0yPzwrPDUmNzk/NiszMy8="));
        m300b(context, StringFog.m313a("CwYOGRYQFloaDRgGEAoBHQUGRDk8ODYrLzA+Lis3Mzg1Oz4kKzg1MQ=="));
        m300b(context, StringFog.m313a("CwYOGRYQFloaDRgGEAoBHQUGRDk8ODYrOiAlJTwmISArPC8="));
        if (!AppUtil.m228a(context)) {
            PermissionUtil.m107b(getApplicationContext(), 43);
        }
    }

    /* renamed from: b */
    private void m300b(Context context, String permission) {
        if (!AppUtil.m229b(context, permission)) {
            PermissionUtil.m106a(context, permission);
            MyLog.m51e(StringFog.m313a("AgkEDxUcMxceAQUFPgsTGh5VV1Y=") + permission);
            try {
                Thread.sleep(200);
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: f */
    private void m304f(Intent intent) {
        NoticeModel noticeModel;
        try {
            String data = intent.getStringExtra(StringFog.m313a("DxAeGRgmFhUeCQ=="));
            MyLog.m51e(StringFog.m313a("V1VXVkRZFhUeCVBL") + data);
            if (!TextUtils.isEmpty(data) && (noticeModel = JsonUtils.m269b(data)) != null) {
                if (noticeModel.f69c == 1) {
                    AAABeanActivityAAA.m283w(noticeModel, false, getApplicationContext());
                } else {
                    AppUtil.m237j(noticeModel.f68b, getApplicationContext());
                }
                C0039x0.m249e(getApplicationContext()).mo122k(noticeModel.f67a, 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
