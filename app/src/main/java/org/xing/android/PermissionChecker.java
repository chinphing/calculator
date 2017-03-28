package org.xing.android;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

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

    public static void requestAudioPermission(final Activity activity) {
        final String per = Manifest.permission.RECORD_AUDIO;

        int state = ContextCompat.checkSelfPermission(activity, per);
        if(state != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.shouldShowRequestPermissionRationale(activity, per)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("授权提醒");
            builder.setMessage("计算器需要使用录音设备输入数字，请授权，否则无法使用。");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(activity, new String[] {per}, REQUEST_CODE_ALL);
                }
            });
            builder.show();
        } else {
            startSetting(activity , "\t计算器需要使用录音设备(麦克风)输入数字，请打开设置界面授权，否则无法使用。\n" +
                                    "参考步骤：'权限设置'->'录音'->'星星声控计算器'->'允许'。");
        }
    }

    public static void startSetting(final Activity activity, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("应用未授权");
        builder.setMessage(msg);
        builder.setPositiveButton("打开", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    activity.startActivity(intent);
                }catch (Exception ex) {
                    Toast.makeText(activity,
                            "抱歉，无法自动跳转到设置页面，请手动操作。",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("忽略", null);
        builder.show();
    }
}
