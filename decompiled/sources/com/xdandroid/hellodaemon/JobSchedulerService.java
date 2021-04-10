package com.xdandroid.hellodaemon;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;

@TargetApi(21)
public class JobSchedulerService extends JobService {
    public boolean onStartJob(JobParameters params) {
        if (!C0253b.f1700d) {
            return false;
        }
        C0253b.m1817c(C0253b.f1698b);
        return false;
    }

    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
