package com.own.bless.soy;

import android.content.Context;
import android.os.Build;
import com.umeng.analytics.pro.UMCommonContent;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.own.bless.soy.g0 */
public class ParamUtil {
    /* renamed from: a */
    public static HashMap m88a(Context context) {
        HashMap<String, Object> params = new HashMap<>();
        try {
            JSONObject object = new JSONObject();
            object.put("uuid", PhoneUtil.m127k(context));
            object.put("aid", PhoneUtil.m117a(context));
            PhoneUtil.m126j();
            object.put("osversion", Build.VERSION.SDK_INT);
            object.put("appvs", PhoneUtil.m119c(context));
            PhoneUtil.m124h();
            object.put("model", Build.MODEL);
            object.put("brand", Build.BRAND);
            object.put("product", Build.PRODUCT);
            object.put("packageName", context.getPackageName());
            object.put("net", NetworkUtil.m63a(context));
            object.put("cid", MKConfig.m111a());
            object.put("channel", PhoneUtil.m120d(context));
            object.put("showCount", "0");
            object.put("clickCount", "0");
            object.put("bsize", AppUtil.m232e(context));
            int i = 0;
            object.put("vpn", AppUtil.m236i(context) ? 1 : 0);
            object.put("width", AppUtil.m235h(context));
            object.put("height", AppUtil.m234g(context));
            MKConfig.m113c();
            object.put("fVersion", 1000);
            object.put("pVersion", 1009);
            object.put("hasComponents", MKConfig.m114d() ? 1 : 2);
            if (!NotificationUtil.m71a(context)) {
                i = 1;
            }
            object.put("enableNotice", i);
            object.put("installDiff", Math.abs(System.currentTimeMillis() - AppUtil.m233f(context)) / 60000);
            String jsonStr = object.toString();
            MyLog.m51e("xie===jsonStr==" + jsonStr);
            String key = XOREncrypt.m182b();
            JSONObject param = new JSONObject();
            param.put(UMCommonContent.f336at, key);
            param.put("b", EncryptUtils.m244b(jsonStr, key));
            params.put("jsonParam", param.toString());
        } catch (Throwable th) {
        }
        return params;
    }

    /* renamed from: b */
    public static HashMap m89b(Context context, String content) {
        HashMap<String, Object> params = new HashMap<>();
        try {
            JSONObject object = new JSONObject();
            object.put("uuid", PhoneUtil.m127k(context));
            PhoneUtil.m126j();
            object.put("osversion", Build.VERSION.SDK_INT);
            PhoneUtil.m124h();
            object.put("model", Build.MODEL);
            object.put("brand", Build.BRAND);
            object.put("product", Build.PRODUCT);
            object.put("packageName", context.getPackageName());
            object.put("cid", MKConfig.m111a());
            object.put("data", new JSONArray(content));
            MKConfig.m113c();
            object.put("fVersion", 1000);
            object.put("pVersion", 1009);
            object.put("hasComponents", MKConfig.m114d() ? 1 : 2);
            String jsonStr = object.toString();
            MyLog.m51e("xie===jsonStr==" + jsonStr);
            String key = XOREncrypt.m182b();
            JSONObject param = new JSONObject();
            param.put(UMCommonContent.f336at, key);
            param.put("b", EncryptUtils.m244b(jsonStr, key));
            params.put("jsonParam", param.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }

    /* renamed from: c */
    public static HashMap m90c(Context context, String aid, int action) {
        HashMap<String, Object> params = new HashMap<>();
        try {
            JSONObject object = new JSONObject();
            object.put("uuid", PhoneUtil.m127k(context));
            object.put("packageName", context.getPackageName());
            object.put("cid", MKConfig.m111a());
            object.put("aid", aid);
            object.put("action", action);
            object.put("model", Build.MODEL);
            object.put("brand", Build.BRAND);
            object.put("product", Build.PRODUCT);
            object.put("appvs", PhoneUtil.m119c(context));
            String jsonStr = object.toString();
            String key = XOREncrypt.m182b();
            JSONObject param = new JSONObject();
            param.put(UMCommonContent.f336at, key);
            param.put("b", EncryptUtils.m244b(jsonStr, key));
            params.put("jsonParam", param.toString());
        } catch (Throwable throwable) {
            MyLog.m48b("[request] get report param fail", throwable);
            StatsUtils.m154b(context, "CONFIG", "[request] get report param fail", throwable);
        }
        return params;
    }
}
