package org.xing.android;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ProgressBar;

import com.baidu.speech.VoiceRecognitionService;
import com.umeng.analytics.MobclickAgent;

import org.w3c.dom.Text;
import org.xing.calc.Calculator;
import org.xing.logger.AsyncLog;
import org.xing.logger.Log;
import org.xing.update.UpdateManager;
import org.xing.utils.DeviceUtil;
import org.xing.utils.NumberUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements RecognitionListener {

    private boolean isListening;
    private SpeechRecognizer speechRecognizer;

    private String uniqueId;
    private Calculator calculator;
    private Log evalLog;

    private EditText inputText;
    private TextView msgText;
    private ProgressBar recordDynamic;
    private Button stateButton;

    /*
    历史记录
     */
    private WebView historyList;
    private LinkedList<String> historyData;

    /*
    空闲状态计数，达到maxNoInputCount暂时停止工作
     */
    private int noInputCount = 0;
    private int maxNoInputCount = 5;

    protected void showHelp() {
        /*
        historyList.loadUrl("javascript:hideAllList(false);");
        msgText.setText("");
        MobclickAgent.onEvent(this, "help");
        */
        Intent intent =new Intent(MainActivity.this, SimpleHelpActivity.class);
        intent.putExtra("url", "file:///android_asset/help.html");

        startActivity(intent);
    }

    protected void showTips(int delaySecond) {
        //延迟1s弹出对话框，大致实在用户说了一句话之后弹出
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("小技巧");
                builder.setMessage("  说'帮助'、'怎么用'，查看使用教程。\n  支持括号、分数、π、三角函数、对数函数。\n  立即查看教程？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showHelp();
                    }
                });
                builder.setNegativeButton("知道", null);
                builder.show();
            }
        }, delaySecond*1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppConfig.loadConfig(this);

        if(AppConfig.getCheckUpdate()) {
            UpdateManager.checkUrl = this.getString(R.string.updateCheckUrl);
            UpdateManager.update(this);
        }

        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

        isListening = false;
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(
                this, new ComponentName(this, VoiceRecognitionService.class));
        speechRecognizer.setRecognitionListener(this);

        uniqueId = DeviceUtil.getUniqueId(this);
        calculator = Calculator.createDefault(getResources().openRawResource(R.raw.token));
        evalLog = AsyncLog.createAsyncHttpLog(this.getString(R.string.recordUrl));

        Button stateBtn = (Button) this.findViewById(R.id.stateButton);
        stateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(MainActivity.this, "statusClick");
                if(isListening) {
                    stopListening();
                    msgText.setText("已暂停");
                } else {
                    startListening(true);
                    msgText.setText("");
                }
            }
        });

        inputText = (EditText) this.findViewById(R.id.input);
        msgText = (TextView) this.findViewById(R.id.msg);

        recordDynamic = (ProgressBar) this.findViewById(R.id.recordDynamic);
        stateButton = (Button) this.findViewById(R.id.stateButton);

        historyData = new LinkedList<String>();
        historyList = (WebView) this.findViewById(R.id.historylist);
        historyList.setBackgroundColor(0);
        historyList.getSettings().setJavaScriptEnabled(true);
        historyList.getSettings().setAppCacheEnabled(true);

        historyList.loadUrl("javascript:var isFirstStart="+AppConfig.getIsFirstStart()+";");
        historyList.loadUrl("file:///android_asset/history.html");

        startListening(true);
    }


    @Override
    public void onResume() {
        super.onResume();
        startListening(true);
        MobclickAgent.onResume(this);
        msgText.setText("");
    }

    @Override
    public void onPause() {
        super.onPause();
        stopListening();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public synchronized void startListening(boolean resetInputCount) {
        if (isListening) return;
        speechRecognizer.startListening(new Intent());
        isListening = true;
        if (resetInputCount) {
            noInputCount = 0;
        }
    }

    public synchronized void stopListening() {
        speechRecognizer.cancel();
        isListening = false;
        stateButton.setBackgroundResource(R.mipmap.input_sleep);
    }

    public void onReadyForSpeech(Bundle params) {
        stateButton.setBackgroundResource(R.mipmap.input_ready);
    }

    public void onBeginningOfSpeech() {

    }

    public void onRmsChanged(float rmsdB) {
        recordDynamic.setProgress((int) rmsdB);
    }

    public void onBufferReceived(byte[] buffer) {

    }

    public void onEndOfSpeech() {
        stateButton.setBackgroundResource(R.mipmap.input_sleep);
        msgText.setText("正在识别...");
    }

    public void onError(int error) {
        stopListening();

        StringBuilder sb = new StringBuilder();
        switch (error) {
            case SpeechRecognizer.ERROR_AUDIO:
                sb.append("录音设备未授权");
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                noInputCount++;
                if (noInputCount >= maxNoInputCount) {
                    sb.append("已暂停");
                } else {
                    startListening(false);
                }
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                sb.append("客户端错误");
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                sb.append("权限不足");
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                sb.append("请检查网络连接");
                MobclickAgent.onEvent(this, "errorNetwork");
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                sb.append("未识别");
                MobclickAgent.onEvent(this, "failMatch");
                startListening(true);
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                sb.append("引擎忙");
                MobclickAgent.onEvent(this, "busy");
                break;
            case SpeechRecognizer.ERROR_SERVER:
                sb.append("未识别");
                startListening(true);
                MobclickAgent.onEvent(this, "failServer");
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                sb.append("网络连接连接超时");
                MobclickAgent.onEvent(this, "errorNetworkTimeout");
                break;
        }
        if (sb.length() > 0) {
            msgText.setText(sb.toString());
        }
    }

    private String buildExpr(Bundle results) {
        ArrayList<String> nbest = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        StringBuilder expr = new StringBuilder();
        for (String w : nbest) {
            expr.append(w);
        }
        return expr.toString();
    }

    private void recordEvaluation(String userId, String result,
                                  String inputExpr, String readExpr, int type) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("result", result);
        params.put("inputExpr", inputExpr);
        params.put("readExpr", readExpr == null ? "null" : readExpr);
        params.put("type", 0);
        evalLog.recordEvaluation(params);
    }

    private void showResult(String expr, Double evalResult, String readExpr) {
        //界面上显示结果
        if (!Double.isNaN(evalResult)) {
            String text = NumberUtil.format(evalResult, 8);

            String item = readExpr + "=" + text;

            historyData.add(0, item);
            historyList.loadUrl("javascript:addItem('" + item + "')");
            inputText.setText(text);
            msgText.setText("");
        } else {
            msgText.setText("未识别");
        }

    }

    public void onResults(Bundle results) {

        String expr = buildExpr(results);
        if(expr.contains("帮助") || expr.contains("示例")
                || expr.contains("说明") || expr.contains("怎么用") ) {
            showHelp();
        } else {
            //结算结果
            String readExpr = null;
            Double evalResult = calculator.eval(expr.toString());
            if (evalResult != null && (!evalResult.isNaN())) {
                readExpr = calculator.getReadExpr();
            }

            this.recordEvaluation(uniqueId, NumberUtil.format(evalResult, 8),
                    expr.toString(), readExpr, 0);

            showResult(expr.toString(), evalResult, readExpr);

            if(AppConfig.getIsFirstStart()) {
                showTips(1);
                AppConfig.setIsFirstStart(false);
            }
        }

        isListening = false;
        startListening(true);
    }

    public void onPartialResults(Bundle partialResults) {

    }

    public void onEvent(int eventType, Bundle params) {

    }
}
