package org.xing.android;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.baidu.speech.VoiceRecognitionService;
import com.umeng.analytics.MobclickAgent;

import org.xing.Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements RecognitionListener{

    private boolean isListening;

    private SpeechRecognizer speechRecognizer;

    private Calculator calculator;

    EditText inputText;

    private ProgressBar recordDynamic;

    private Button stateButton;

    private ListView historyList;
    private LinkedList<String> historyData;

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

        calculator = Calculator.createDefault();

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
        historyList = (ListView) this.findViewById(R.id.historylist);

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

    public void onResults(Bundle results){
        ArrayList<String> nbest = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        StringBuilder expr = new StringBuilder();
        for(String w : nbest) {
            expr.append(w);
        }

        //结算结果
        Double evalResult = calculator.eval(expr.toString());
        String readExpr = null;
        if(evalResult != null && (!evalResult.isNaN())) {
            readExpr = calculator.getReadExpr();
        }

        //百度统计代码
        HashMap<String, String> info = new HashMap<>();
        info.put("result", evalResult.toString());
        info.put("expr", expr.toString());
        info.put("readExpr", readExpr == null? "null":readExpr);
        MobclickAgent.onEvent(this, "result", info);

        //界面上显示结果
        if(!Double.isNaN(evalResult)) {
            String text = String.format("%.8f", evalResult);
            text = text.replaceAll("(\\.)?0*$", "");

            historyData.add(0, readExpr + "=" + text);
            historyList.setAdapter(new ArrayAdapter<String>(
                    this, R.layout.list_text_view, historyData));
            //inputText.setText(expr+"->"+readExpr+"="+text);
            inputText.setText(text);
        } else {
            inputText.setText("遗憾：'"+expr+"'无效表达式！");
        }

        startListening(true);
    }

    public void onPartialResults(Bundle partialResults){

    }

    public void onEvent(int eventType, Bundle params){

    }
}
