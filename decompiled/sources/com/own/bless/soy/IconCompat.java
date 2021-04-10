package com.own.bless.soy;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;

/* renamed from: com.own.bless.soy.o0 */
public class IconCompat {

    /* renamed from: e */
    static final PorterDuff.Mode f88e = PorterDuff.Mode.SRC_IN;

    /* renamed from: a */
    public int f89a = -1;

    /* renamed from: b */
    Object f90b;

    /* renamed from: c */
    public int f91c = 0;

    /* renamed from: d */
    PorterDuff.Mode f92d = f88e;

    /* renamed from: d */
    public static IconCompat m191d(Bitmap bits) {
        if (bits != null) {
            IconCompat rep = new IconCompat(1);
            rep.f90b = bits;
            return rep;
        }
        throw new IllegalArgumentException("Bitmap must not be null.");
    }

    private IconCompat(int i) {
        this.f89a = i;
    }

    /* renamed from: g */
    public String mo87g() {
        int i = this.f89a;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return m193h((Icon) this.f90b);
        }
        if (i == 2) {
            return ((String) this.f90b).split(":", -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    /* renamed from: e */
    public int mo86e() {
        int i = this.f89a;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return m192f((Icon) this.f90b);
        }
        if (i == 2) {
            return this.f91c;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    @TargetApi(23)
    /* renamed from: j */
    public Icon mo88j() {
        Icon icon;
        int i = this.f89a;
        if (i == -1) {
            return (Icon) this.f90b;
        }
        if (i == 1) {
            icon = Icon.createWithBitmap((Bitmap) this.f90b);
        } else if (i == 2) {
            icon = Icon.createWithResource(mo87g(), this.f91c);
        } else if (i == 3) {
            icon = Icon.createWithData((byte[]) this.f90b, this.f91c, 0);
        } else if (i == 4) {
            icon = Icon.createWithContentUri((String) this.f90b);
        } else if (i != 5) {
            throw new IllegalArgumentException("Unknown type");
        } else if (Build.VERSION.SDK_INT >= 26) {
            icon = Icon.createWithAdaptiveBitmap((Bitmap) this.f90b);
        } else {
            icon = Icon.createWithBitmap(m190c((Bitmap) this.f90b, false));
        }
        PorterDuff.Mode mode = this.f92d;
        if (mode != f88e) {
            icon.setTintMode(mode);
        }
        return icon;
    }

    /* renamed from: b */
    public void mo85b(Context context) {
        if (this.f89a == 2) {
            String resPackage = (String) this.f90b;
            if (resPackage.contains(":")) {
                String resName = resPackage.split(":", -1)[1];
                String resType = resName.split("/", -1)[0];
                String resName2 = resName.split("/", -1)[1];
                String resPackage2 = resPackage.split(":", -1)[0];
                int id = m194i(context, resPackage2).getIdentifier(resName2, resType, resPackage2);
                if (this.f91c != id) {
                    Log.i("IconCompat", "Id has changed for " + resPackage2 + "/" + resName2);
                    this.f91c = id;
                }
            }
        }
    }

    /* renamed from: i */
    private static Resources m194i(Context context, String resPackage) {
        if ("android".equals(resPackage)) {
            return Resources.getSystem();
        }
        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo ai = pm.getApplicationInfo(resPackage, 8192);
            if (ai != null) {
                return pm.getResourcesForApplication(ai);
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("IconCompat", String.format("Unable to find pkg=%s for icon", resPackage), e);
            return null;
        }
    }

    /* renamed from: a */
    public void mo84a(Intent outIntent, Drawable badge, Context c) {
        Bitmap icon;
        mo85b(c);
        int i = this.f89a;
        if (i == 1) {
            icon = (Bitmap) this.f90b;
            if (badge != null) {
                icon = icon.copy(icon.getConfig(), true);
            }
        } else if (i == 5) {
            icon = m190c((Bitmap) this.f90b, true);
        } else {
            throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
        }
        if (badge != null) {
            int w = icon.getWidth();
            int h = icon.getHeight();
            badge.setBounds(w / 2, h / 2, w, h);
            badge.draw(new Canvas(icon));
        }
        outIntent.putExtra("android.intent.extra.shortcut.ICON", icon);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        if (r1 != 5) goto L_0x008e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0095  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
        // Method dump skipped, instructions count: 169
        */
        throw new UnsupportedOperationException("Method not decompiled: com.own.bless.soy.IconCompat.toString():java.lang.String");
    }

    /* renamed from: k */
    private static String m195k(int x) {
        if (x == 1) {
            return "BITMAP";
        }
        if (x == 2) {
            return "RESOURCE";
        }
        if (x == 3) {
            return "DATA";
        }
        if (x == 4) {
            return "URI";
        }
        if (x != 5) {
            return "UNKNOWN";
        }
        return "BITMAP_MASKABLE";
    }

    @TargetApi(23)
    /* renamed from: h */
    private static String m193h(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e("IconCompat", "Unable to get icon package", e);
            return null;
        } catch (InvocationTargetException e2) {
            Log.e("IconCompat", "Unable to get icon package", e2);
            return null;
        } catch (NoSuchMethodException e3) {
            Log.e("IconCompat", "Unable to get icon package", e3);
            return null;
        }
    }

    @TargetApi(23)
    /* renamed from: f */
    private static int m192f(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            Log.e("IconCompat", "Unable to get icon resource", e);
            return 0;
        } catch (InvocationTargetException e2) {
            Log.e("IconCompat", "Unable to get icon resource", e2);
            return 0;
        } catch (NoSuchMethodException e3) {
            Log.e("IconCompat", "Unable to get icon resource", e3);
            return 0;
        }
    }

    /* renamed from: c */
    static Bitmap m190c(Bitmap adaptiveIconBitmap, boolean addShadow) {
        int size = (int) (((float) Math.min(adaptiveIconBitmap.getWidth(), adaptiveIconBitmap.getHeight())) * 0.6666667f);
        Bitmap icon = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(icon);
        Paint paint = new Paint(3);
        float center = ((float) size) * 0.5f;
        float radius = 0.9166667f * center;
        if (addShadow) {
            float blur = ((float) size) * 0.010416667f;
            paint.setColor(0);
            paint.setShadowLayer(blur, 0.0f, ((float) size) * 0.020833334f, 1023410176);
            canvas.drawCircle(center, center, radius, paint);
            paint.setShadowLayer(blur, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(center, center, radius, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(-16777216);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader shader = new BitmapShader(adaptiveIconBitmap, tileMode, tileMode);
        Matrix shift = new Matrix();
        shift.setTranslate((float) ((-(adaptiveIconBitmap.getWidth() - size)) / 2), (float) ((-(adaptiveIconBitmap.getHeight() - size)) / 2));
        shader.setLocalMatrix(shift);
        paint.setShader(shader);
        canvas.drawCircle(center, center, radius, paint);
        canvas.setBitmap(null);
        return icon;
    }
}
