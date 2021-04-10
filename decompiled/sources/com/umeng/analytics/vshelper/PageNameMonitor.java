package com.umeng.analytics.vshelper;

public class PageNameMonitor implements PageLifeCycle {
    private static String currentActivity = null;
    private static String currentCustomPage = null;
    private static Object lock = new Object();

    private PageNameMonitor() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.analytics.vshelper.PageNameMonitor$a */
    public class C0133a {

        /* renamed from: a */
        private static final PageNameMonitor f1022a = new PageNameMonitor();

        private C0133a() {
        }
    }

    public static PageNameMonitor getInstance() {
        return C0133a.f1022a;
    }

    @Override // com.umeng.analytics.vshelper.PageLifeCycle
    public void activityResume(String str) {
        synchronized (lock) {
            currentActivity = str;
        }
    }

    @Override // com.umeng.analytics.vshelper.PageLifeCycle
    public void activityPause(String str) {
        synchronized (lock) {
            currentActivity = null;
        }
    }

    @Override // com.umeng.analytics.vshelper.PageLifeCycle
    public void customPageBegin(String str) {
        synchronized (lock) {
            currentCustomPage = str;
        }
    }

    @Override // com.umeng.analytics.vshelper.PageLifeCycle
    public void customPageEnd(String str) {
        synchronized (lock) {
            currentCustomPage = null;
        }
    }

    public String getCurrenPageName() {
        synchronized (lock) {
            String str = currentCustomPage;
            if (str != null) {
                return str;
            }
            String str2 = currentActivity;
            if (str2 != null) {
                return str2;
            }
            return null;
        }
    }
}
