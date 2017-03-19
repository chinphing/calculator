package org.xing.android;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.Vector;

/**
 * Created by Administrator on 2017/3/18 0018.
 */

public class PermissionChecker {
    public final static int REQUEST_CODE_ALL = 1;
    public static void requestPermission(Activity activity, String[] permissions) {
        Vector<String> lackedPermissions  =  new Vector<String>();
        for(String per : permissions) {
            if(ContextCompat.checkSelfPermission(activity, per) == PackageManager.PERMISSION_DENIED) {
                lackedPermissions.add(per);
            }
        }

        if(lackedPermissions.size() > 0) {
            String[] tempPermissions = new String[lackedPermissions.size()];
            for(int i=0;i<lackedPermissions.size();i++) {
                tempPermissions[i] = lackedPermissions.get(i);
            }
            ActivityCompat.requestPermissions(activity, tempPermissions, REQUEST_CODE_ALL);
        }
    }
}
