package org.xing.android;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import org.xing.ad.AdManager;
import org.xing.calc.Calculator;
import org.xing.calc.Tips;
import org.xing.engine.BaiduSpeechEngine;
import org.xing.engine.IflySpeechEngine;
import org.xing.engine.SpeechEngine;
import org.xing.engine.SpeechListener;
import org.xing.logger.impl.EventLogger;
import org.xing.share.ShareManager;
import org.xing.theme.Theme;
import org.xing.theme.ThemeChangeListener;
import org.xing.theme.ThemeManager;
import org.xing.update.UpdateManager;
import org.xing.utils.DeviceUtil;
import org.xing.utils.NumberUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements SpeechListener, ThemeChangeListener {

    private boolean isListening;
    private SpeechEngine speechEngine;

    private Calculator calculator;

    public static EventLogger eventLogger;
    private Tips tips;

    private EditText inputText;
    private TextView msgText;
    private ProgressBar recordDynamic;
    private Button stateButton;
    private Button startButton;
    private WebView historyList;

    /*
	命令
	 */
    private Stack<Double> historyResult;
    private Map<String, Integer> cmdName;

    /*
    腾讯联盟广告
     */
    private AdManager adManager;

    /*
    主题
     */
    private Theme currentTheme;
    private ThemeManager themeManager;

    /*
    分享
     */
    private LinearLayout shareLayout;
    private ShareManager shareManager;

    /*
    空闲状态计数，达到maxNoInputCount暂时停止工作
     */
    private int noInputCount = 0;
    private int maxNoInputCount = 5;

    protected void showHelp() {
        MobclickAgent.onEvent(this, "help");
        eventLogger.onEvent("help");

        Intent intent =new Intent(MainActivity.this, SimpleHelpActivity.class);
        intent.putExtra("url", getString(R.string.helpUrl));
        startActivity(intent);
    }

    /*
    新版本功能提示
    延迟1s弹出对话框，大致实是在用户说了一句话之后弹出
     */
    protected void showTips(int delaySecond) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("小技巧");
                builder.setMessage("  说'引擎'，切换到讯飞识别引擎，计算速度更快。" +
                        "立即切换？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        stopListening();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                changeSpeechEngine(null);
                                startListening(true);
                            }
                        }, 1000);
                    }
                });
                builder.setNegativeButton("知道", null);
                builder.show();
            }
        }, delaySecond*1000);
    }

    private void initSpeechRecognizer() {
        if(speechEngine != null) {
            stopListening();
            speechEngine.destroy();
        }

        isListening = false;

        String preferedEngine = AppConfig.getPreferedEngine();
        eventLogger.onEvent("engine-"+preferedEngine);

        if(preferedEngine.equals("ifly")) {
            //速度快，准确性差一点
            speechEngine = new IflySpeechEngine(this);
        } else {
            //准确性高，速度稍慢
            speechEngine = new BaiduSpeechEngine(this);
        }
        speechEngine.setSpeechListener(this);
    }

    private void changeSpeechEngine(String engine) {
        eventLogger.onEvent("changeEngine");
        String preferedEngine = AppConfig.getPreferedEngine();
        if(preferedEngine.equals("ifly") && (engine == null || engine.equals("baidu"))){
            AppConfig.setPreferedEngine("baidu");
            Toast.makeText(this, "切换到百度语音引擎（更准确）\n" +
                    "说'引擎'或'讯飞'可以切换", Toast.LENGTH_LONG).show();
            initSpeechRecognizer();
        }else if(preferedEngine.equals("baidu") && (engine == null || engine.equals("ifly"))){
            AppConfig.setPreferedEngine("ifly");
            Toast.makeText(this, "切换到科大讯飞引擎（更快速）\n" +
                    "说'引擎'或'百度'可以切换", Toast.LENGTH_LONG).show();
            initSpeechRecognizer();
        } else {
            if(preferedEngine.equals("baidu")) {
                Toast.makeText(this, "已经是百度语音引擎，无需重新设置\n" +
                        "说'引擎'或'讯飞'可以切换", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "已经是科大讯飞引擎，无需重新设置\n" +
                        "说'引擎'或'百度'可以切换", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void initUserView() {
        stateButton = (Button) this.findViewById(R.id.stateButton);
        stateButton = (Button) this.findViewById(R.id.stateButton);
        stateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(MainActivity.this, "statusClick");
                eventLogger.onEvent("statusClick");
                Toast toast = Toast.makeText(MainActivity.this,
                        "图标为绿色可以开始输入\n灰色表示已暂停或者正在识别。",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        });
        startButton = (Button) this.findViewById(R.id.ctrl_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(MainActivity.this, "startClick");
                eventLogger.onEvent("startClick");
                if(isListening) {
                    stopListening();
                    startButton.setBackgroundResource(R.mipmap.start);
                    msgText.setText("已暂停");
                    adManager.showAd(0);
                } else {
                    startListening(true);
                    startButton.setBackgroundResource(R.mipmap.stop);
                    msgText.setText("");
                    adManager.postCloseAd(30);
                }
            }
        });

        this.findViewById(R.id.help_mark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHelp();
            }
        });

        inputText = (EditText) this.findViewById(R.id.input);
        inputText.setKeyListener(null);
        msgText = (TextView) this.findViewById(R.id.msg);

        recordDynamic = (ProgressBar) this.findViewById(R.id.recordDynamic);
    }

    private void initCalculator() {
        calculator = Calculator.createDefault(getResources().openRawResource(R.raw.token));

        historyList = (WebView) this.findViewById(R.id.historylist);
        historyList.setBackgroundColor(0);
        historyList.getSettings().setJavaScriptEnabled(true);
        historyList.getSettings().setAppCacheEnabled(true);
        historyList.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);

                if(currentTheme != null) {
                    String jsCode = "javascript:setTextColor('" + currentTheme.getStyle("historyColor") + "')";
                    historyList.loadUrl(jsCode);
                }
            }
        });

        historyList.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector detector = null;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(detector == null) {
                    detector = new GestureDetector(MainActivity.this,
                            new GestureDetector.SimpleOnGestureListener() {
                                @Override
                                public boolean onDoubleTap(MotionEvent e) {
                                    themeManager.randomTheme();
                                    return super.onDoubleTap(e);
                                }
                            }
                    );
                }
                detector.onTouchEvent(event);

                return false;
            }
        });

        historyList.loadUrl("javascript:var isFirstStart="+AppConfig.getIsFirstStart()+";");
        historyList.loadUrl("file:///android_asset/history.html");

        historyResult = new Stack<>();
    }

    private void initCommand() {
        cmdName = new HashMap<String, Integer>();
        cmdName.put("清屏", 1);
        cmdName.put("清空", 1);
        cmdName.put("清除", 1);

        cmdName.put("撤销", 2);
        cmdName.put("取消", 2);
        cmdName.put("倒退", 2);
        cmdName.put("后退", 2);
        cmdName.put("删除", 2);

        cmdName.put("帮助", 3);
        cmdName.put("示例", 3);
        cmdName.put("说明", 3);

        cmdName.put("升级", 4);
        cmdName.put("版本", 4);

        cmdName.put("主题", 5);

        cmdName.put("引擎", 6);
        cmdName.put("百度", 6);
        cmdName.put("讯飞", 6);
    }

    private void initAd() {
        adManager = new AdManager(this,
                this.getString(R.string.gdtAppid),
                this.getString(R.string.gdtBannerPosID),
                (ViewGroup) this.findViewById(R.id.bannerContainer));
    }

    private void initTheme() {
        themeManager = new ThemeManager(this);
        themeManager.addThemeChangeListener(this);
        themeManager.applyTheme(AppConfig.getThemeId());
    }

    private void initShare() {
        shareManager = new ShareManager(this);

        shareLayout = (LinearLayout) findViewById(R.id.share_board);
        this.findViewById(R.id.share_mark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareLayout.setVisibility(View.VISIBLE);
            }
        });
        this.findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareLayout.setVisibility(View.GONE);
            }
        });
        this.findViewById(R.id.share_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareManager.shareToWeixin(0);
            }
        });
        this.findViewById(R.id.share_pengyouquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareManager.shareToWeixin(1);
            }
        });
        this.findViewById(R.id.share_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareManager.shareToQQ(0);
            }
        });
        this.findViewById(R.id.share_kongjian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareManager.shareToQQ(1);
            }
        });
    }

    private void initPermission() {
        PermissionChecker.requestPermission(this, new String[] {
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        AppConfig.loadConfig(this);

        String uniqueId = DeviceUtil.getUniqueId(this);
        eventLogger = new EventLogger(uniqueId, AppConfig.getVersionName(),
                this.getString(R.string.recordUrl));
        eventLogger.onEvent("start");

        tips = Tips.createSimpleTips();

        //10s后检查更新
        UpdateManager.postUpdate(this, 10);

        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

        initUserView();
        initCalculator();
        initCommand();
        initAd();
        initTheme();
        initShare();

        initPermission();
        initSpeechRecognizer();
    }


    @Override
    public void onResume() {
        super.onResume();

        startListening(true);
        startButton.setBackgroundResource(R.mipmap.stop);
        msgText.setText(tips.randomGet());

        MobclickAgent.onResume(this);
        adManager.onResume();
        adManager.postCloseAd(30);
    }

    @Override
    public void onPause() {
        super.onPause();

        stopListening();
        startButton.setBackgroundResource(R.mipmap.start);

        MobclickAgent.onPause(this);
        adManager.onPause();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public synchronized void startListening(boolean resetInputCount) {
        if (isListening) return;
        speechEngine.startListening();
        isListening = true;
        lastRmsdB = 0;
        if (resetInputCount) {
            noInputCount = 0;
        }
    }

    public  void stopListening() {
        if(isListening) {
            speechEngine.cancel();
        }
        listeningStopped();
    }

    public synchronized void listeningStopped() {
        isListening = false;
        stateButton.setBackgroundResource(R.mipmap.input_sleep);
    }

    public void onReadyForSpeech() {
        stateButton.setBackgroundResource(R.mipmap.input_ready);
    }

    public void onBeginningOfSpeech() {

    }

    private float lastRmsdB=0;
    public void onRmsChanged(float rmsdB) {
        lastRmsdB = (rmsdB*3+lastRmsdB*7) /10;
        recordDynamic.setProgress((int) lastRmsdB);
    }

    public void onEndOfSpeech() {
        stateButton.setBackgroundResource(R.mipmap.input_sleep);
        msgText.setText("正在识别...");
    }

    public void onError(int error) {
        listeningStopped();

        StringBuilder sb = new StringBuilder();
        switch (error) {
            case SpeechRecognizer.ERROR_AUDIO:
                sb.append("录音设备未授权");
                startButton.setBackgroundResource(R.mipmap.start);
                eventLogger.onEvent("errorAudio");
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                noInputCount++;
                if (noInputCount >= maxNoInputCount) {
                    sb.append("已暂停");
                    startButton.setBackgroundResource(R.mipmap.start);
                } else {
                    startListening(false);
                }
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                sb.append("客户端错误");
                startButton.setBackgroundResource(R.mipmap.start);
                eventLogger.onEvent("errorClient");
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                sb.append("录音设备未授权");
                startButton.setBackgroundResource(R.mipmap.start);
                eventLogger.onEvent("errorPermissions");
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                sb.append("请检查网络连接");
                MobclickAgent.onEvent(this, "errorNetwork");
                eventLogger.onEvent("errorNetwork");
                startButton.setBackgroundResource(R.mipmap.start);
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                sb.append("未识别");
                MobclickAgent.onEvent(this, "failMatch");
                eventLogger.onEvent("failMatch");
                startListening(true);
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                sb.append("引擎忙");
                MobclickAgent.onEvent(this, "busy");
                eventLogger.onEvent("busy");
                startButton.setBackgroundResource(R.mipmap.start);
                break;
            case SpeechRecognizer.ERROR_SERVER:
                sb.append("未识别");
                startListening(true);
                MobclickAgent.onEvent(this, "failServer");
                eventLogger.onEvent("failServer");
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                sb.append("网络连接连接超时");
                MobclickAgent.onEvent(this, "errorNetworkTimeout");
                eventLogger.onEvent("errorNetworkTimeout");
                startButton.setBackgroundResource(R.mipmap.start);
                break;
        }

        msgText.setText(sb.toString());
    }

    private String buildExpr(Bundle results) {
        ArrayList<String> nbest = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        StringBuilder expr = new StringBuilder();
        for (String w : nbest) {
            expr.append(w);
        }
        return expr.toString();
    }

    private void showResult(String expr, Double evalResult, String readExpr) {
        //界面上显示结果
        if (!Double.isNaN(evalResult)) {
            historyResult.push(evalResult);
            String text = NumberUtil.format(evalResult, 8);

            String item = readExpr + "=" + text;

            historyList.loadUrl("javascript:addItem('" + item + "')");
            inputText.setText(text);
            msgText.setText("");
        } else {
            String errMsg = calculator.getErrMsg();
            if(errMsg != null) {
                msgText.setText("未识别，'"+errMsg+"'表达错误");
            } else {
                msgText.setText("未识别，'"+expr+"'表达错误");
            }
        }

    }

    public boolean handleCommand(String expr) {
        String cmd = calculator.execFilter(expr);
        if(cmd != null && cmd.length() > 0
                && cmdName.containsKey(cmd)) {
            int type = cmdName.get(cmd);
            this.eventLogger.onEvent("NaN", expr.toString(), "null", type);
            switch (type) {
                case 1:
                    historyResult.clear();
                    historyList.loadUrl("javascript:clearHistory()");
                    calculator.setLastResult(0);
                    inputText.setText("0");
                    break;
                case 2:
                    if(historyResult.size() > 0) {
                        historyResult.pop();
                        historyList.loadUrl("javascript:historyPop()");
                        if(historyResult.size() > 0) {
                            Double lastResult = historyResult.peek();
                            calculator.setLastResult(lastResult);
                            inputText.setText(NumberUtil.format(lastResult, 8));
                        } else {
                            calculator.setLastResult(0);
                            inputText.setText("0");
                        }
                    }
                    break;
                case 3:
                    showHelp();
                    break;
                case 4:
                    UpdateManager.update(this, true);
                    break;
                case 5:
                    themeManager.randomTheme();
                    break;
                case 6:
                    if(cmd.equals("引擎")) {
                        changeSpeechEngine(null);
                    }else if(cmd.equals("百度")){
                        changeSpeechEngine("baidu");
                    }else {
                        changeSpeechEngine("ifly");
                    }
                    break;
                default:
                    break;
            }
            msgText.setText("");
            return true;
        }

        return false;
    }

    public void onResults(String expr) {
        if(expr == null || expr.length() == 0) return;

        if(!handleCommand(expr)) {
            //结算结果
            String readExpr = null;
            Double evalResult = calculator.eval(expr.toString());
            if (evalResult != null && (!evalResult.isNaN())) {
                readExpr = calculator.getReadExpr();
            }

            this.eventLogger.onEvent(NumberUtil.format(evalResult, 8),
                    expr.toString(), readExpr, 0);

            showResult(expr.toString(), evalResult, readExpr);

            if(AppConfig.getIsFirstStart()) {
                showTips(10);
                AppConfig.setIsFirstStart(false);
            }
        }

        isListening = false;
        startListening(true);
    }

    public boolean isAlive() {
        return !this.isFinishing();
    }

    public void onThemeChange(Theme theme) {
        if(theme == null) return;

        eventLogger.onEvent("theme-"+theme.getId());
        try {
            currentTheme = theme;
            AppConfig.setThemeId(theme.getId());

            int color;
            List<String> backgroundImages = theme.getImagePaths();
            LinearLayout layout = (LinearLayout) this.findViewById(R.id.backgroundLayout);
            if(backgroundImages != null && backgroundImages.size() > 0) {
                InputStream is = this.getAssets().open(backgroundImages.get(0));
                Drawable background = Drawable.createFromStream(is, "theme");
                layout.setBackgroundDrawable(background);
                is.close();
            } else {
                layout.setBackgroundDrawable(null);
                color =  Color.parseColor(theme.getStyle("background"));
                layout.setBackgroundColor(color);
            }

            String jsCode = "javascript:setTextColor('"+currentTheme.getStyle("historyColor")+"')";
            historyList.loadUrl(jsCode);

            color = Color.parseColor(theme.getStyle("resultColor"));
            inputText.setTextColor(color);
            color =  Color.parseColor(theme.getStyle("msgColor"));
            msgText.setTextColor(color);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
