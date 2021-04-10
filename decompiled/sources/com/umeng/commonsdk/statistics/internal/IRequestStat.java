package com.umeng.commonsdk.statistics.internal;

/* renamed from: com.umeng.commonsdk.statistics.internal.b */
public interface IRequestStat {
    void onRequestEnd();

    void onRequestFailed();

    void onRequestStart();

    void onRequestSucceed(boolean z);
}
