package com.umeng.commonsdk.internal.utils;

import android.os.Build;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/* renamed from: com.umeng.commonsdk.internal.utils.e */
public class ExecShell {

    /* renamed from: com.umeng.commonsdk.internal.utils.e$a */
    /* compiled from: ExecShell */
    public enum EnumC0173a {
        check_su_binary(new String[]{"/system/xbin/which", "su"});
        

        /* renamed from: b */
        String[] f1291b;

        private EnumC0173a(String[] strArr) {
            this.f1291b = strArr;
        }
    }

    /* renamed from: a */
    public ArrayList mo691a(EnumC0173a aVar) {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT > 28) {
            return arrayList;
        }
        try {
            Process exec = Runtime.getRuntime().exec(aVar.f1291b);
            new BufferedWriter(new OutputStreamWriter(exec.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    arrayList.add(readLine);
                } catch (Exception e) {
                }
            }
            return arrayList;
        } catch (Exception e2) {
            return null;
        }
    }
}
