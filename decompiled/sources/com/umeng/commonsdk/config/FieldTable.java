package com.umeng.commonsdk.config;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.umeng.commonsdk.config.d */
public class FieldTable {

    /* renamed from: a */
    public static final long f1062a = 1000;

    /* renamed from: b */
    public static final String f1063b = "_LAST_FIELD";

    /* renamed from: c */
    public static final String f1064c = "3758096383";

    /* renamed from: d */
    public static final String f1065d = "2147483647";

    /* renamed from: e */
    public static final String f1066e = "262143";

    /* renamed from: f */
    public static final String f1067f = "2047";

    /* renamed from: g */
    public static final int f1068g = 64;

    /* renamed from: h */
    public static String[] f1069h = new String[35];

    /* renamed from: i */
    public static String[] f1070i = new String[32];

    /* renamed from: j */
    public static String[] f1071j = new String[19];

    /* renamed from: k */
    public static String[] f1072k = new String[12];

    /* renamed from: l */
    private static Map f1073l = new HashMap();

    /* renamed from: com.umeng.commonsdk.config.d$a */
    /* compiled from: FieldTable */
    public enum EnumC0147a {
        header_utoken,
        header_cpu,
        header_mccmnc,
        header_sub_os_name,
        header_sub_os_version,
        header_device_type,
        header_device_id_imei,
        header_device_id_mac,
        header_device_id_android_id,
        header_device_id_serialNo,
        header_bulid,
        header_os_version,
        header_resolution,
        header_mc,
        header_timezone,
        header_local_info,
        header_carrier,
        header_access,
        header_tracking_imei,
        header_tracking_android_id,
        header_tracking_utdid,
        header_tracking_idmd5,
        header_tracking_idfa,
        header_tracking_mac,
        header_tracking_serial,
        header_tracking_uuid,
        header_tracking_uop,
        header_tracking_oldumid,
        header_tracking_newumid,
        header_ekv_send_on_exit,
        header_device_oaid,
        header_local_ip,
        header_foreground_count,
        header_first_resume,
        _LAST_FIELD
    }

    /* renamed from: com.umeng.commonsdk.config.d$b */
    /* compiled from: FieldTable */
    public enum EnumC0148b {
        inner_rs,
        inner_by,
        inner_gp,
        inner_to,
        inner_mo,
        inner_ca,
        inner_fl,
        inner_gdf_r,
        inner_thm_z,
        inner_dsk_s,
        inner_sd,
        inner_build,
        inner_sr,
        inner_scr,
        inner_sinfo,
        inner_winfo,
        inner_input,
        inner_bt,
        inner_mem,
        inner_cpu,
        inner_rom,
        inner_bstn,
        inner_cam,
        inner_appls,
        inner_lbs,
        internal_run_server,
        internal_imsi,
        internal_meid,
        tp_tp,
        inner_imei2,
        inner_iccid,
        _LAST_FIELD
    }

    /* renamed from: com.umeng.commonsdk.config.d$c */
    /* compiled from: FieldTable */
    public enum EnumC0149c {
        push_cpu,
        push_imei,
        push_mac,
        push_android_id,
        push_serialNo,
        push_settings_android_id,
        push_network_access_mode,
        push_on_line,
        push_time_zone,
        push_locale_info,
        push_resolution,
        push_operator,
        push_utdid,
        push_service_work,
        push_service_name,
        push_intent_exist,
        push_data_data,
        push_notification_enabled,
        _LAST_FIELD
    }

    /* renamed from: com.umeng.commonsdk.config.d$d */
    /* compiled from: FieldTable */
    public enum EnumC0150d {
        share_device_id,
        share_imsi,
        share_iccid,
        share_sn,
        share_net_accmode,
        share_android_id,
        share_wifi_mac,
        share_sd_size,
        share_ssid,
        share_resolution,
        share_conn_type,
        _LAST_FIELD
    }

    static {
        EnumC0147a.values();
        EnumC0148b.values();
        EnumC0149c.values();
        EnumC0150d.values();
        String[] strArr = f1069h;
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            while (true) {
                EnumC0147a.values();
                if (i2 >= 35) {
                    break;
                }
                f1069h[i2] = EnumC0147a.values()[i2].toString();
                i2++;
            }
            Map map = f1073l;
            if (map != null) {
                map.put(EnumC0147a.class.getName(), f1069h);
            }
        }
        String[] strArr2 = f1070i;
        if (strArr2 != null && strArr2.length > 0) {
            int i3 = 0;
            while (true) {
                EnumC0148b.values();
                if (i3 >= 32) {
                    break;
                }
                f1070i[i3] = EnumC0148b.values()[i3].toString();
                i3++;
            }
            Map map2 = f1073l;
            if (map2 != null) {
                map2.put(EnumC0148b.class.getName(), f1070i);
            }
        }
        String[] strArr3 = f1071j;
        if (strArr3 != null && strArr3.length > 0) {
            int i4 = 0;
            while (true) {
                EnumC0149c.values();
                if (i4 >= 19) {
                    break;
                }
                f1071j[i4] = EnumC0149c.values()[i4].toString();
                i4++;
            }
            Map map3 = f1073l;
            if (map3 != null) {
                map3.put(EnumC0149c.class.getName(), f1071j);
            }
        }
        String[] strArr4 = f1072k;
        if (strArr4 != null && strArr4.length > 0) {
            while (true) {
                EnumC0150d.values();
                if (i >= 12) {
                    break;
                }
                f1072k[i] = EnumC0150d.values()[i].toString();
                i++;
            }
            Map map4 = f1073l;
            if (map4 != null) {
                map4.put(EnumC0150d.class.getName(), f1072k);
            }
        }
    }

    /* renamed from: a */
    public static boolean m1130a(String str) {
        if (str == null || str.length() <= 0 || f1063b.equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public static String[] m1131b(String str) {
        if (str == null || str.length() <= 0 || !f1073l.containsKey(str)) {
            return null;
        }
        return (String[]) f1073l.get(str);
    }
}
