package org.xing.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.util.UUID;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class DeviceUtil {
    public static String getUniqueId(Activity act) {
        TelephonyManager tm = (TelephonyManager) act.getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

        String tmDevice, tmSerial, tmPhone, androidId;

        tmDevice = Build.BRAND;
        tmSerial = Build.SERIAL;
        androidId = Build.MODEL;

        try {
            //部分机型这里会抛出异常
            tmDevice += tm.getDeviceId();
            tmSerial += tm.getSimSerialNumber();
            androidId += android.provider.Settings.Secure.getString(
                    act.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();

        return uniqueId;
    }
}
