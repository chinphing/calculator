package org.xing.android;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import java.io.ByteArrayInputStream;

public class SimpleHelpActivity extends AppCompatActivity {
    private WebView helpWeb;

    private View videoView;
    private FrameLayout videoContainerView;

    LinearLayout toolBar;
    private ProgressBar progressBar;
    private TextView titleText;
    private WebChromeClient chromeClient;

    /**
     * 设置全屏
     */
    private void setFullScreen() {
        // 设置全屏的相关属性，获取当前的屏幕状态，然后设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 全屏下的状态码：1098974464
        // 窗口下的状态吗：1098973440
    }

    /**
     * 退出全屏
     */
    private void quitFullScreen() {
        // 声明当前屏幕状态的参数并获取
        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setAttributes(attrs);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_help);

        videoView = null;
        videoContainerView = (FrameLayout) findViewById(R.id.video);

        toolBar = (LinearLayout) findViewById(R.id.toolBar);
        progressBar = (ProgressBar) findViewById(R.id.webProgress);
        titleText = (TextView) findViewById(R.id.help_title);

        Button closeButton = (Button) findViewById(R.id.help_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        helpWeb = (WebView) this.findViewById(R.id.simple_help);
        helpWeb.setBackgroundColor(0);

        WebSettings settings = helpWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(false);
        settings.setDomStorageEnabled(true);

        chromeClient = new WebChromeClient() {
            private CustomViewCallback callBack;

            // 播放网络视频时全屏会被调用的方法
            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                toolBar.setVisibility(View.INVISIBLE);
                helpWeb.setVisibility(View.INVISIBLE);

                // 如果一个视图已经存在，那么立刻终止并新建一个
                if (videoView != null) {
                    callback.onCustomViewHidden();
                    return;
                }
                videoContainerView.addView(view);
                videoView = view;
                callBack = callback;
                videoContainerView.setVisibility(View.VISIBLE);
                setFullScreen();
            }

            // 视频播放退出全屏会被调用的
            @Override
            public void onHideCustomView() {
                if (videoView == null)// 不是全屏播放状态
                    return;

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                videoView.setVisibility(View.GONE);
                videoContainerView.removeView(videoView);
                videoView = null;
                videoContainerView.setVisibility(View.GONE);
                callBack.onCustomViewHidden();

                toolBar.setVisibility(View.VISIBLE);
                helpWeb.setVisibility(View.VISIBLE);
                quitFullScreen();
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    if (View.INVISIBLE == progressBar.getVisibility()) {
                        progressBar.setVisibility(View.VISIBLE);
                        titleText.setText("页面加载中...");
                    }
                    progressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        };
        helpWeb.setWebChromeClient(chromeClient);

        helpWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                WebView.HitTestResult hitTestResult = view.getHitTestResult();
                if (!TextUtils.isEmpty(url) && hitTestResult == null) {
                    return;
                }
                titleText.setText(view.getTitle());
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (url.startsWith("http") || url.startsWith("https")
                        || url.startsWith("javascript") || url.startsWith("file")) {
                    return super.shouldInterceptRequest(view, url);
                } else {
                    String tips = "启动外部应用...";
                    try {
                        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(in);
                    } catch (ActivityNotFoundException ex) {
                        tips = "启动外部应用失败，请安装最新版应用。";
                    }

                    return new WebResourceResponse("text/plain", "utf-8", new ByteArrayInputStream(tips.getBytes()));
                }
            }
        });

        helpWeb.setOnTouchListener(new View.OnTouchListener() {
            private float x1 = 0;
            private float x2 = 0;
            private float y1 = 0;
            private float y2 = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                //继承了Activity的onTouchEvent方法，直接监听点击事件
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //当手指按下的时候
                    x1 = event.getX();
                    y1 = event.getY();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //当手指离开的时候
                    x2 = event.getX();
                    y2 = event.getY();

                    if (y1 - y2 > 50) {
                        toolBar.setVisibility(View.GONE);
                    } else if (y2 - y1 > 50) {
                        toolBar.setVisibility(View.VISIBLE);
                    }
                }
                return false;
            }
        });

        String url = getIntent().getStringExtra("url");
        helpWeb.loadUrl(url);
    }


    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        helpWeb.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        helpWeb.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && helpWeb.canGoBack()) {
            helpWeb.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (videoView == null) {
            super.onBackPressed();
        } else {
            chromeClient.onHideCustomView();
        }
    }
}
