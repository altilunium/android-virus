package com.umeng.commonsdk.config;

import com.umeng.commonsdk.debug.UMRTLog;

/* renamed from: com.umeng.commonsdk.config.g */
public class SensitiveFieldHandler implements IConfigHandler {
    @Override // com.umeng.commonsdk.config.IConfigHandler
    /* renamed from: a */
    public void mo667a(String str, Object obj, String[] strArr) {
        if (str != null && str.length() > 0) {
            try {
                long parseLong = Long.parseLong(str);
                if (parseLong == -1) {
                    UMRTLog.m1142e("Config", "--->>> SensitiveFieldHandler: handleConfigItem: invalid config value.");
                    return;
                }
                UMRTLog.m1143i("Config", "--->>> CollectFieldJudgment: handleConfigItem: item : " + str);
                if (obj != null && (obj instanceof CollectController)) {
                    try {
                        Boolean.valueOf(true);
                        for (int i = 0; i < strArr.length; i++) {
                            String str2 = strArr[i];
                            if (FieldTable.m1130a(str2)) {
                                ((CollectController) obj).mo666a(str2, Boolean.valueOf(BitUtils.m1119a(parseLong, i)));
                            }
                        }
                    } catch (Throwable th) {
                    }
                }
            } catch (Throwable th2) {
                UMRTLog.m1142e("Config", "--->>> SensitiveFieldHandler: handleConfigItem: parseLong exception.");
            }
        }
    }
}
