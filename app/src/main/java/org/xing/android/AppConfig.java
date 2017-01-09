package org.xing.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public class AppConfig {
    private static String packageName;
    private static int versionCode;
    private static String versionName;
    private static boolean isFirstStart;
    private static boolean checkUpdate;
    private static Context mContext;
    private static SharedPreferences prefs;

    public static void loadConfig(Context mContext) {

        isFirstStart = false;
        checkUpdate = false;

        mContext = mContext;
        packageName = mContext.getApplicationContext().getPackageName();
        prefs = PreferenceManager.getDefaultSharedPreferences(mContext);

        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(packageName, 0);
            versionCode = info.versionCode;
            versionName = info.versionName;

            int lastVersionCode = prefs.getInt("versionCode", 0);
            if (versionCode > lastVersionCode) {
                isFirstStart = true;
                prefs.edit().putInt("versionCode", versionCode).commit();

                //第一次启动，检查版本更新的字段重设
                prefs.edit().putBoolean("checkUpdate", true).commit();
                checkUpdate = true;
            } else {
                checkUpdate = prefs.getBoolean("checkUpdate", true);
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getPackageName() {
        return packageName;
    }

    public static int getVersionCode() {
        return versionCode;
    }
    public static String getVersionName() {
        return versionName;
    }
    public static boolean getIsFirstStart() {
        return isFirstStart;
    }
    public static void setIsFirstStart(boolean b) {
        isFirstStart = b;
    }
    public static boolean getCheckUpdate() {
        return checkUpdate;
    }
    public static void setCheckUpdate(boolean b) {
        checkUpdate = b;
        prefs.edit().putBoolean("checkUpdate", b).commit();
    }
}
