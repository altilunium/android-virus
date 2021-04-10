package com.ownw.blesb;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class AAABaseActivity extends Activity {

    /* renamed from: a */
    private C0055b f134a;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m271a();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        m271a();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        m272b();
    }

    /* renamed from: a */
    private void m271a() {
        try {
            if (this.f134a == null) {
                this.f134a = new C0055b(this);
                registerReceiver(this.f134a, new IntentFilter(StringFog.m313a("CwYOGRYQFloDBh4OFw1cFQkcAwQXVzE4JTsvNCogISAvJTUvMDg+Oy07")));
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    private void m272b() {
        try {
            C0055b bVar = this.f134a;
            if (bVar != null) {
                unregisterReceiver(bVar);
                this.f134a = null;
            }
        } catch (Throwable th) {
        }
    }
}
