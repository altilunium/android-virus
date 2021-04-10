package com.umeng.tunnel;

import android.content.Context;
import android.util.Log;
import java.util.Map;

public class UMChannelAgent {
    private static final String TAG = "UMChannelAgent";
    private static final String UMENG_VCHANNEL = "com.umeng.commonsdk.vchannel.Sender";
    private static boolean vChannelReady;

    static {
        vChannelReady = false;
        try {
            Class.forName(UMENG_VCHANNEL);
            vChannelReady = true;
        } catch (Throwable th) {
        }
    }

    private static void reflectOnEvent(Context context, String str, Map map) {
        try {
            Class.forName(UMENG_VCHANNEL).getMethod("onEvent", Context.class, String.class, Map.class).invoke(null, context, str, map);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "--->>> Can not find class com.umeng.commonsdk.vchannel.sender .");
        } catch (NoSuchMethodException e2) {
            Log.e(TAG, "--->>> Can not find method onEvent .");
        } catch (SecurityException e3) {
            Log.e(TAG, "--->>> Security exception is thrown when we find onEvent method !");
        } catch (Exception e4) {
            Log.e(TAG, "--->>> Exception is thrown when onEvent method is called !");
        }
    }

    private static void reflectSetCustomHeader(Map map) {
        try {
            Class.forName(UMENG_VCHANNEL).getMethod("setCustomHeader", Map.class).invoke(null, map);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "--->>> Can not find class com.umeng.commonsdk.vchannel.sender .");
        } catch (NoSuchMethodException e2) {
            Log.e(TAG, "--->>> Can not find method setCustomHeader .");
        } catch (SecurityException e3) {
            Log.e(TAG, "--->>> Security exception is thrown when we find setCustomHeader method !");
        } catch (Exception e4) {
            Log.e(TAG, "--->>> Exception is thrown when setCustomHeader method is called !");
        }
    }

    public static boolean init() {
        if (!vChannelReady) {
            Log.e(TAG, "--->>> Umeng tunnel module depends on common library, please integrate common first.");
        }
        return vChannelReady;
    }

    public static void setCustomHeader(Map map) {
        reflectSetCustomHeader(map);
    }

    public static void onDebugEvent(Context context, String str, Map map) {
        reflectOnEvent(context, str, map);
    }
}
