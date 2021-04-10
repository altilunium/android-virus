package com.umeng.commonsdk.vchannel;

import android.content.Context;
import com.umeng.commonsdk.service.UMGlobalContext;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.umeng.commonsdk.vchannel.b */
public class Event {

    /* renamed from: a */
    private String f1671a = "_$unknown";

    /* renamed from: b */
    private String f1672b;

    /* renamed from: c */
    private long f1673c = 0;

    /* renamed from: d */
    private long f1674d = 0;

    /* renamed from: e */
    private String f1675e = Constant.f1670j;

    /* renamed from: f */
    private Map f1676f;

    /* renamed from: a */
    public String mo1016a() {
        return this.f1671a;
    }

    /* renamed from: a */
    public void mo1018a(String str) {
        this.f1671a = str;
    }

    /* renamed from: b */
    public long mo1020b() {
        return this.f1673c;
    }

    /* renamed from: a */
    public void mo1017a(long j) {
        this.f1673c = j;
    }

    /* renamed from: c */
    public Map mo1021c() {
        return this.f1676f;
    }

    /* renamed from: a */
    public void mo1019a(Map map) {
        this.f1676f = map;
    }

    public Event(Context context) {
        this.f1672b = UMGlobalContext.getInstance(context).getProcessName(context);
        this.f1676f = null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append("id:" + this.f1671a + ",");
        sb.append("pn:" + this.f1672b + ",");
        sb.append("ts:" + this.f1673c + ",");
        Map map = this.f1676f;
        if (map != null && map.size() > 0) {
            for (String str : this.f1676f.keySet()) {
                Object obj = this.f1676f.get(str);
                sb.append(obj == null ? str + ": null" + "," : str + ": " + obj.toString() + ",");
            }
        }
        sb.append("ds:" + this.f1674d + "]");
        return sb.toString();
    }

    /* renamed from: d */
    public JSONObject mo1022d() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.f1671a);
            jSONObject.put("pn", this.f1672b);
            jSONObject.put("ds", this.f1674d);
            jSONObject.put("ts", this.f1673c);
            Map map = this.f1676f;
            if (map != null && map.size() > 0) {
                for (String str : this.f1676f.keySet()) {
                    jSONObject.put(str, this.f1676f.get(str));
                }
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(this.f1675e, jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("ekv", jSONArray2);
            return jSONObject3;
        } catch (Throwable th) {
            return null;
        }
    }
}
