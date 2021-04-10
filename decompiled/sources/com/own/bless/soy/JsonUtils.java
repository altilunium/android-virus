package com.own.bless.soy;

import org.json.JSONObject;

/* renamed from: com.own.bless.soy.z */
public class JsonUtils {
    /* renamed from: a */
    public static String m268a(BaseModel model) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("aid", model.f67a);
            jsonObject.put("link", model.f68b);
            jsonObject.put("openType", model.f69c);
            jsonObject.put("imgUrl", model.f70d);
            jsonObject.put("closeCycle", model.f71e);
            jsonObject.put("clickCycle", model.f72f);
            jsonObject.put("jsContent", model.f73g);
            if (model instanceof LnkModel) {
                LnkModel lnkModel = (LnkModel) model;
                jsonObject.put("icon", lnkModel.f75h);
                jsonObject.put("name", lnkModel.f76i);
                jsonObject.put("count", lnkModel.f77j);
            } else if (model instanceof NoticeModel) {
                NoticeModel noticeModel = (NoticeModel) model;
                jsonObject.put("title", noticeModel.f80h);
                jsonObject.put("desc", noticeModel.f81i);
                jsonObject.put("icon", noticeModel.f82j);
                jsonObject.put("bigIcon", noticeModel.f83k);
                jsonObject.put("snco", noticeModel.f84l);
            } else if (model instanceof WinModel) {
                WinModel winModel = (WinModel) model;
                jsonObject.put("isFull", winModel.f100h);
                jsonObject.put("icon", winModel.f101i);
                jsonObject.put("inside", winModel.f102j);
                jsonObject.put("isdirect", winModel.f103k);
            }
            return jsonObject.toString();
        } catch (Throwable throwable) {
            MyLog.m48b("object to string fail", throwable);
            return "";
        }
    }

    /* renamed from: c */
    public static WinModel m270c(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            WinModel model = new WinModel();
            model.f67a = jsonObject.optString("aid", "");
            model.f68b = jsonObject.optString("link", "");
            model.f69c = jsonObject.optInt("openType", 0);
            model.f70d = jsonObject.optString("imgUrl", "");
            model.f71e = jsonObject.optInt("closeCycle", 0);
            model.f72f = jsonObject.optInt("clickCycle", 0);
            model.f73g = jsonObject.optString("jsContent", "");
            model.f100h = jsonObject.optInt("isFull", 0);
            model.f101i = jsonObject.optString("icon", "");
            model.f102j = jsonObject.optInt("inside", 0);
            model.f103k = jsonObject.optInt("isdirect", 0);
            return model;
        } catch (Throwable throwable) {
            MyLog.m48b("string to win model fail", throwable);
            return null;
        }
    }

    /* renamed from: b */
    public static NoticeModel m269b(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            NoticeModel model = new NoticeModel();
            model.f67a = jsonObject.optString("aid", "");
            model.f68b = jsonObject.optString("link", "");
            model.f69c = jsonObject.optInt("openType", 0);
            model.f70d = jsonObject.optString("imgUrl", "");
            model.f71e = jsonObject.optInt("closeCycle", 0);
            model.f72f = jsonObject.optInt("clickCycle", 0);
            model.f73g = jsonObject.optString("jsContent", "");
            model.f80h = jsonObject.optString("title", "");
            model.f81i = jsonObject.optString("desc", "");
            model.f82j = jsonObject.optString("icon", "");
            model.f83k = jsonObject.optString("bigIcon", "");
            model.f84l = jsonObject.optInt("snco", 0);
            return model;
        } catch (Throwable throwable) {
            MyLog.m48b("string to notice model fail", throwable);
            return null;
        }
    }
}
