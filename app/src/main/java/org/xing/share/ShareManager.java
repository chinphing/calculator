package org.xing.share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.xing.android.MainActivity;
import org.xing.android.R;
import org.xing.logger.impl.EventLogger;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/19 0019.
 */

public class ShareManager {
    private Activity activity;

    /*
    微信相关配置
     */
    private String weixinAppid;
    private IWXAPI iwxapi;

    /*
    QQ配置
     */
    private String qqAppid;
    private Tencent mTencent;

    private EventLogger eventLogger;

    public ShareManager(Activity activity) {
        eventLogger = MainActivity.eventLogger;

        this.activity = activity;

        weixinAppid = "wxf3bb46c3047a46dd";
        iwxapi = WXAPIFactory.createWXAPI(activity, weixinAppid, true);
        iwxapi.registerApp(weixinAppid);

        qqAppid = "1105840771";
        mTencent = Tencent.createInstance(qqAppid, activity.getApplicationContext());
    }

    public void shareToWeixin(int flag) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = activity.getString(R.string.shareUrl);

        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = activity.getString(R.string.shareDesc);
        msg.description = activity.getString(R.string.shareDesc);

        //这里替换一张自己工程里的图片资源
        Bitmap thumb = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.share_icon);
        msg.setThumbImage(thumb);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag == 0? SendMessageToWX.Req.WXSceneSession:SendMessageToWX.Req.WXSceneTimeline;
        eventLogger.onEvent(flag == 0? "weixinShare":"pengyouquanShare");
        iwxapi.sendReq(req);
    }

    public void shareToQQ(int flag) {
        final Bundle params = new Bundle();
        if(flag == 0) {
            params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
            params.putString(QQShare.SHARE_TO_QQ_TITLE, activity.getString(R.string.shareDesc));
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY, activity.getString(R.string.shareDesc));
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, activity.getString(R.string.shareUrl));
            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, activity.getString(R.string.shareImage));
            params.putString(QQShare.SHARE_TO_QQ_APP_NAME, activity.getString(R.string.app_name));
        } else {
            params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT );
            params.putString(QzoneShare.SHARE_TO_QQ_TITLE, activity.getString(R.string.shareDesc));
            params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, activity.getString(R.string.shareDesc));
            params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, activity.getString(R.string.shareUrl));
            ArrayList<String> imageList = new ArrayList<>();
            imageList.add(activity.getString(R.string.shareImage));
            params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageList);
        }

        IUiListener defaultUiListener = new IUiListener() {
            public void onComplete(Object var1){}
            public  void onError(UiError var1){}
            public void onCancel() {}
        };

        if(flag == 0) {
            eventLogger.onEvent("qqShare");
            mTencent.shareToQQ(activity, params, defaultUiListener);
        } else {
            eventLogger.onEvent("qzoneShare");
            mTencent.shareToQzone(activity, params, defaultUiListener);
        }
    }

}
