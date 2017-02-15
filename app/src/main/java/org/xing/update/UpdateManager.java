package org.xing.update;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.xing.android.AppConfig;
import org.xing.android.R;
import org.xing.utils.NumberUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

import okhttp3.Call;

/**
 * app更新
 * Created by sangbo on 16-5-19.
 */
public class UpdateManager {


    private static int mAppVersionCode = 0;
    private static Context mContext;
    private static ProgressDialog mAlertDialog;
    private static boolean mIsEnforceCheck = false;
    public static String checkUrl = "";
    public static UpdateEntity mUpdateEntity;

    public static void postUpdate(final Context context, int delaySecond) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(AppConfig.getCheckUpdate()) {
                    mIsEnforceCheck = false;
                    UpdateManager.update(context);
                }
            }
        }, delaySecond*1000);
    }

    public static void update(Context context){
        update(context,mIsEnforceCheck);
    }

    public static void update(Context context, final boolean isEnforceCheck){
        mContext = context;
        mIsEnforceCheck = isEnforceCheck;
        mAppVersionCode = AppConfig.getVersionCode();

        checkUrl = mContext.getString(R.string.updateCheckUrl);
        if(TextUtils.isEmpty(checkUrl)){
            Toast.makeText(mContext, "url不能为空，请设置url", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpUtils.get().url(checkUrl).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                if(mIsEnforceCheck)
                    Toast.makeText(mContext, "更新失败，请检查网络", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {

                loadOnlineData(response);

            }
        });
    }

    private static void loadOnlineData(String json) {
        try {
            UpdateEntity updateEntity = new UpdateEntity(json);
            if(updateEntity == null){
                if(mIsEnforceCheck)
                    Toast.makeText(mContext, "网络信息获取失败，请重试", Toast.LENGTH_SHORT).show();
                return;
            }
            mUpdateEntity = updateEntity;

            if(mAppVersionCode < mUpdateEntity.versionCode){
                //启动更新
                AlertUpdate();
            } else if(mIsEnforceCheck){
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("更新");
                builder.setMessage("已是最新版本："+AppConfig.getVersionName());
                builder.setPositiveButton("确定", null);
                builder.show();
            }
        } catch (JSONException e) {
            MobclickAgent.reportError(mContext, e);
        }

    }


    private static void AlertUpdate(){

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("发现新版本");
        builder.setMessage("当前版本：" + AppConfig.getVersionName() + "\n"
                + "新版本：" + mUpdateEntity.versionName + "\n"
                + "大小：" + NumberUtil.getPrintSize(mUpdateEntity.fileSize) + "\n"
                + mUpdateEntity.updateLog + "\n");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MobclickAgent.onEvent(mContext, "updateConfirm");
                updateApp();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MobclickAgent.onEvent(mContext, "updateCancel");
                //选择取消之后，不再检查更新
                AppConfig.setCheckUpdate(false);
            }
        });
        builder.show();
    }

    private static void updateApp() {
        updateApp(false);
    }

    private static void updateApp(boolean isEnforceDown) {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = AppConfig.getPackageName() + mUpdateEntity.versionName +".apk";

        if(!isEnforceDown){
            File file = new File(filePath+"/"+fileName);
            if(file.exists()){
                install(file);
                return;
            }
        }

        mAlertDialog = new ProgressDialog(mContext);
        mAlertDialog.setTitle("更新("+mUpdateEntity.versionName+")");
        mAlertDialog.setMessage("正在下载最新版本...");
        mAlertDialog.setCancelable(false);
        mAlertDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mAlertDialog.setProgress(0);
        mAlertDialog.setMax(100);
        mAlertDialog.setIndeterminate(false);
        mAlertDialog.setProgressNumberFormat("");
        mAlertDialog.show();

        OkHttpUtils.get().url(mUpdateEntity.downUrl).build().execute(
                new FileCallBack(
                        filePath,
                        fileName) {
                    @Override
                    public void inProgress(float progress, long total) {
                        int downloadSize = (int)(progress * total);
                        if(downloadSize > mAlertDialog.getProgress() + 100 * 1024) {
                            mAlertDialog.setProgress((int)(100*progress));
                            mAlertDialog.setProgressNumberFormat(
                                    String.format("%s/%s",
                                    NumberUtil.getPrintSize(downloadSize),
                                    NumberUtil.getPrintSize(total))
                            );
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        //下载失败，是否重试
                        resterAlert();
                    }

                    @Override
                    public void onResponse(File file) {
                        //下载成功，开始安装
                        install(file);
                    }

                    @Override
                    public void onAfter() {
                        mAlertDialog.dismiss();
                    }
                });
    }


    public static void install(File file) {

        if(!checkMD5(file)){
            md5Alert();
            return;
        }

        MobclickAgent.onEvent(mContext, "install");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    private static void md5Alert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示");
        builder.setMessage("\n安装文件不完整，是否重新下载\n");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateApp(true);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    private static void resterAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示");
        builder.setMessage("\n文件下载失败，是否重试？\n");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateApp();
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    private static boolean checkMD5(File file) {
        String md5Value;
        try {
            md5Value = getMd5ByFile(file);
        } catch (FileNotFoundException e) {
            md5Value = "-1";
        }
        Log.d("md5:",md5Value);
        return md5Value.equals(mUpdateEntity.md5);
    }

    public static String getMd5ByFile(File file) throws FileNotFoundException {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}
