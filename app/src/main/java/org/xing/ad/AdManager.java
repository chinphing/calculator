package org.xing.ad;

import android.app.Activity;
import android.os.Handler;
import android.view.ViewGroup;

import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;
import com.umeng.analytics.MobclickAgent;

import org.xing.android.MainActivity;
import org.xing.logger.impl.EventLogger;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xing on 2017/2/14.
 */

public class AdManager {

    private Activity mContext;
    private String gdtAppId;
    private String gdtBannerPosID;
    private ViewGroup adContainer;
    private BannerView bv;

    private Handler handler;
    private Runnable runnable;

    private EventLogger eventLogger;
    /*
    自动显示广告机制
     */
    private Random random;
    private float showProb;
    private long lastShowTimestamp;
    private static long showInterval = 60 * 1000;

    public AdManager(Activity context,
                    String appid, String bannerPosID,
                     ViewGroup bannerContainer) {
        this.mContext = context;
        this.gdtAppId = appid;
        this.gdtBannerPosID = bannerPosID;
        this.adContainer = bannerContainer;

        handler = null;
        runnable = null;

        eventLogger = MainActivity.eventLogger;

        random = new Random();
        showProb = 0.25f;
        lastShowTimestamp = System.currentTimeMillis();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long currentTimestamp = System.currentTimeMillis();
                if(currentTimestamp - lastShowTimestamp > showInterval) {
                    if(random.nextFloat() < showProb) {
                        mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {showAd(30);
                                }
                            });
                    } else {
                        lastShowTimestamp = currentTimestamp;
                    }
                }
            }
        }, 5 * 1000, 5* 1000);
    }

    public void showAd(int delaySeconds) {
        showProb = 0.25f;
        lastShowTimestamp = System.currentTimeMillis();

        if(bv == null) {
            this.bv = new BannerView(mContext, ADSize.BANNER,
                    this.gdtAppId, this.gdtBannerPosID);
            this.bv.setRefresh(30);
            this.bv.setShowClose(true);
            this.bv.setADListener(new AbstractBannerADListener() {
                @Override
                public void onNoAD(int arg0) {
                    MobclickAgent.onEvent(mContext, "bannerNoAD");
                    eventLogger.onEvent("bannerNoAD");
                }

                @Override
                public void onADReceiv() {
                    MobclickAgent.onEvent(mContext, "bannerReceived");
                    eventLogger.onEvent("bannerReceived");
                }

                @Override
                public void onADExposure() {
                    bv.setPadding(0, 0, 0, 10);
                    MobclickAgent.onEvent(mContext, "bannerExposure");
                    eventLogger.onEvent("bannerExposure");
                }

                @Override
                public void onADClosed() {
                    closeAd();
                }
            });
            adContainer.addView(bv);
            this.bv.loadAD();
        }

        /*
        清理上一次自动关闭的状态，以免影响这一次的展示
         */
        if(handler != null) {
            if(runnable != null) {
                handler.removeCallbacks(runnable);
                runnable = null;
            }
            handler = null;
        }

        /*
        自动关闭
         */
        if(delaySeconds > 0) {
            postCloseAd(delaySeconds);
        }
    }

    public void closeAd() {
        if (bv != null) {
            adContainer.removeView(bv);
            bv.destroy();
            bv = null;
        }
    }

    public void postCloseAd(int delaySeconds) {
        if(bv != null && delaySeconds > 0) {
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    closeAd();
                }
            };
            handler.postDelayed(runnable, delaySeconds * 1000);
        }else {
            closeAd();
        }
    }
}
