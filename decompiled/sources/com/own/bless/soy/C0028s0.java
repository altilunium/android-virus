package com.own.bless.soy;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ShortcutManager;
import android.os.Build;

/* renamed from: com.own.bless.soy.s0 */
/* compiled from: ShortcutManagerCompat */
public class C0028s0 {
    /* JADX WARNING: Removed duplicated region for block: B:10:0x003e  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m219a(android.content.Context r6) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 26
            if (r0 < r1) goto L_0x0013
            java.lang.Class<android.content.pm.ShortcutManager> r0 = android.content.pm.ShortcutManager.class
            java.lang.Object r0 = r6.getSystemService(r0)
            android.content.pm.ShortcutManager r0 = (android.content.pm.ShortcutManager) r0
            boolean r0 = r0.isRequestPinShortcutSupported()
            return r0
        L_0x0013:
            int r0 = android.os.Process.myPid()
            int r1 = android.os.Process.myUid()
            java.lang.String r2 = "com.android.launcher.permission.INSTALL_SHORTCUT"
            int r0 = r6.checkPermission(r2, r0, r1)
            r1 = 0
            if (r0 == 0) goto L_0x0025
            return r1
        L_0x0025:
            android.content.pm.PackageManager r0 = r6.getPackageManager()
            android.content.Intent r3 = new android.content.Intent
            java.lang.String r4 = "com.android.launcher.action.INSTALL_SHORTCUT"
            r3.<init>(r4)
            java.util.List r0 = r0.queryBroadcastReceivers(r3, r1)
            java.util.Iterator r0 = r0.iterator()
        L_0x0038:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0058
            java.lang.Object r3 = r0.next()
            android.content.pm.ResolveInfo r3 = (android.content.pm.ResolveInfo) r3
            android.content.pm.ActivityInfo r4 = r3.activityInfo
            java.lang.String r4 = r4.permission
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L_0x0056
            boolean r5 = r2.equals(r4)
            if (r5 == 0) goto L_0x0055
            goto L_0x0056
        L_0x0055:
            goto L_0x0038
        L_0x0056:
            r0 = 1
            return r0
        L_0x0058:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.own.bless.soy.C0028s0.m219a(android.content.Context):boolean");
    }

    /* renamed from: b */
    public static boolean m220b(Context context, C0022q0 shortcut, IntentSender callback) {
        if (Build.VERSION.SDK_INT >= 26) {
            return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).requestPinShortcut(shortcut.mo97c(), callback);
        }
        if (!m219a(context)) {
            return false;
        }
        Intent intent = shortcut.mo96a(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"));
        if (callback == null) {
            context.sendBroadcast(intent);
            return true;
        }
        context.sendOrderedBroadcast(intent, null, new ShortcutManagerCompat(callback), null, -1, null, null);
        return true;
    }
}
