package com.umeng.analytics.vshelper;

/* renamed from: com.umeng.analytics.vshelper.a */
public interface PageLifeCycle {
    void activityPause(String str);

    void activityResume(String str);

    void customPageBegin(String str);

    void customPageEnd(String str);
}
