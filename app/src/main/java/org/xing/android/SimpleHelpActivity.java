package org.xing.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

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

        String url = getIntent().getStringExtra("url");
        helpWeb.loadUrl(url);
    }
}
