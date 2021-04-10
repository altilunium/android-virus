package com.cam.service;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window mWindow = getWindow();
        mWindow.setGravity(51);
        WindowManager.LayoutParams attrParams = mWindow.getAttributes();
        attrParams.x = 0;
        attrParams.y = 0;
        attrParams.height = 2;
        attrParams.width = 2;
        mWindow.setAttributes(attrParams);
        getWindow().setFlags(32, 32);
        new Handler().postDelayed(new RunnableC0000a(this), 500);
    }
}
