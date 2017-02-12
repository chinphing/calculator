package org.xing.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.umeng.analytics.MobclickAgent;

import java.io.ByteArrayInputStream;

public class SimpleHelpActivity extends AppCompatActivity {
    private WebView helpWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_help);


        Button closeButton =(Button)findViewById(R.id.help_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        helpWeb = (WebView) this.findViewById(R.id.simple_help);
        helpWeb.setBackgroundColor(0);
        helpWeb.getSettings().setJavaScriptEnabled(true);
        helpWeb.getSettings().setAppCacheEnabled(true);

        helpWeb.setWebViewClient(new WebViewClient() {
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
                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(in);
                    return new WebResourceResponse("text/plain", "utf-8", new ByteArrayInputStream("启动外部应用...".getBytes()));
                }
            }
        });

        String url = getIntent().getStringExtra("url");
        helpWeb.loadUrl(url);
    }


    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
