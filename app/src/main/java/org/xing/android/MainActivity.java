package org.xing.android;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.baidu.speech.VoiceRecognitionService;
import com.umeng.analytics.MobclickAgent;

import org.xing.calc.Calculator;
import org.xing.logger.AsyncLog;
import org.xing.logger.Log;
import org.xing.utils.DeviceUtil;
import org.xing.utils.NumberUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements RecognitionListener{

    private boolean isListening;
    private SpeechRecognizer speechRecognizer;

    private String uniqueId;
    private Calculator calculator;
    private Log evalLog;

    private EditText inputText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

        isListening = false;
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(
                this, new ComponentName(this, VoiceRecognitionService.class));
        speechRecognizer.setRecognitionListener(this);

        uniqueId = DeviceUtil.getUniqueId(this);
        calculator = Calculator.createDefault();
        evalLog = AsyncLog.createAsyncHttpLog(this.getString(R.string.recordUrl));

        Button beginButton = (Button) this.findViewById(R.id.begin);
        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListening(true);
                inputText.setText(R.string.tips);
            }
        });

        Button endButton = (Button) this.findViewById(R.id.end);
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopListening();
                inputText.setText("已暂停");
            }
        });

        inputText = (EditText) this.findViewById(R.id.input);

        recordDynamic = (ProgressBar) this.findViewById(R.id.recordDynamic);
        stateButton = (Button) this.findViewById(R.id.stateButton);

        historyData = new LinkedList<String>();
        historyList = (WebView) this.findViewById(R.id.historylist);
        historyList.setBackgroundColor(0);
        historyList.getSettings().setJavaScriptEnabled(true);
        historyList.getSettings().setAppCacheEnabled(true);
        historyList.loadUrl("file:///android_asset/history.html");

        startListening(true);
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

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }

    public synchronized void startListening(boolean resetInputCount) {
        if(isListening) return;
        speechRecognizer.startListening(new Intent());
        isListening = true;
        if(resetInputCount) {
            noInputCount = 0;
        }
    }

    public synchronized void stopListening() {
        speechRecognizer.cancel();
        isListening = false;
        stateButton.setBackgroundResource(R.mipmap.input_sleep);
    }

    public void onReadyForSpeech(Bundle params){
        stateButton.setBackgroundResource(R.mipmap.input_ready);
    }

    public void onBeginningOfSpeech(){

    }

    public void onRmsChanged(float rmsdB)    {
        recordDynamic.setProgress((int)rmsdB);
    }

    public void onBufferReceived(byte[] buffer){

    }

    public void onEndOfSpeech(){
        stateButton.setBackgroundResource(R.mipmap.input_sleep);
        isListening = false;
    }

    public void onError(int error){
        stopListening();

        StringBuilder sb = new StringBuilder();
        switch (error) {
            case SpeechRecognizer.ERROR_AUDIO:
                sb.append("录音设备未授权");
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                noInputCount ++;
                if(noInputCount >= maxNoInputCount) {
                    sb.append("暂停工作，点击'开始'按钮重新工作");
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
                sb.append("未能识别");
                MobclickAgent.onEvent(this, "failMatch");
                startListening(true);
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                sb.append("引擎忙");
                MobclickAgent.onEvent(this, "busy");
                break;
            case SpeechRecognizer.ERROR_SERVER:
                sb.append("未能识别");
                startListening(true);
                MobclickAgent.onEvent(this, "failServer");
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                sb.append("网络连接连接超时");
                MobclickAgent.onEvent(this, "errorNetworkTimeout");
                break;
        }
        if(sb.length() > 0) {
            inputText.setText(sb.toString());
        }
    }

    private String buildExpr(Bundle results) {
        ArrayList<String> nbest = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        StringBuilder expr = new StringBuilder();
        for(String w : nbest) {
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
        params.put("readExpr", readExpr == null? "null":readExpr);
        params.put("type", 0);
        evalLog.recordEvaluation(params);
    }

    private void showResult(String expr, Double evalResult, String readExpr) {
        //界面上显示结果
        if(!Double.isNaN(evalResult)) {
            String text = NumberUtil.format(evalResult, 8);

            String item = readExpr + "=" + text;
            historyData.add(0, item);
            historyList.loadUrl("javascript:addItem('"+item+"')");
            /*
            historyList.setAdapter(new ArrayAdapter<String>(
                    this, R.layout.list_text_view, historyData));
            */
            //inputText.setText(expr+"->"+readExpr+"="+text);
            inputText.setText(text);
        } else {
            inputText.setText("遗憾：'"+expr+"'无效表达式！");
        }

    }

    public void onResults(Bundle results){
        String expr = buildExpr(results);

        //结算结果
        String readExpr = null;
        Double evalResult = calculator.eval(expr.toString());
        if(evalResult != null && (!evalResult.isNaN())) {
            readExpr = calculator.getReadExpr();
        }

        this.recordEvaluation(uniqueId, evalResult.toString(),
                expr.toString(), readExpr, 0);

        showResult(expr.toString(), evalResult, readExpr);

        startListening(true);
    }

    public void onPartialResults(Bundle partialResults){

    }

    public void onEvent(int eventType, Bundle params){

    }
}
