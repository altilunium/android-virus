package com.own.bless.soy;

import android.content.Context;

/* renamed from: com.own.bless.soy.q */
public class WinModel extends BaseModel {

    /* renamed from: h */
    public int f100h;

    /* renamed from: i */
    public String f101i;

    /* renamed from: j */
    public int f102j;

    /* renamed from: k */
    public int f103k;

    @Override // com.own.bless.soy.BaseModel
    /* renamed from: a */
    public String mo71a(Context context) {
        return context.getExternalCacheDir().getPath() + "/win/" + MD5.m29a(this.f70d);
    }
}
