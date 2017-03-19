package org.xing.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.xing.android.R;

/**
 * Created by Administrator on 2017/3/19 0019.
 */

public class ShareManager {
    private Context context;

    /*
    微信相关配置
     */
    private String weixinAppid;
    private IWXAPI iwxapi;

    public ShareManager(Context context) {
        this.context = context;
        weixinAppid = "wxf3bb46c3047a46dd";
        iwxapi = WXAPIFactory.createWXAPI(context, weixinAppid, true);
        iwxapi.registerApp(weixinAppid);
    }

    public void shareToWeixin(int flag) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = context.getString(R.string.shareUrl);

        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = context.getString(R.string.shareDesc);
        msg.description = context.getString(R.string.shareDesc);

        //这里替换一张自己工程里的图片资源
        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        msg.setThumbImage(thumb);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag == 0? SendMessageToWX.Req.WXSceneSession:SendMessageToWX.Req.WXSceneTimeline;
        iwxapi.sendReq(req);
    }
}
