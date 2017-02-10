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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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
    private Button startButton;
    private WebView historyList;

    /*
	命令
	 */
    private Stack<Double> historyResult;
    private Map<String, Integer> cmdName;


    /*
    空闲状态计数，达到maxNoInputCount暂时停止工作
     */
    private int noInputCount = 0;
    private int maxNoInputCount = 5;

    protected void showHelp() {
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
                builder.setMessage("  说'帮助'、'说明'，查看使用教程。\n  支持括号、分数、π、三角函数、对数函数。\n  立即查看教程？");
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

        stateButton = (Button) this.findViewById(R.id.stateButton);
        stateButton = (Button) this.findViewById(R.id.stateButton);
        stateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(MainActivity.this, "statusClick");
                if(isListening) {
                    stopListening();
                    startButton.setBackgroundResource(R.mipmap.start);
                    msgText.setText("已暂停");
                } else {
                    startListening(true);
                    startButton.setBackgroundResource(R.mipmap.stop);
                    msgText.setText("");
                }
            }
        });
        startButton = (Button) this.findViewById(R.id.ctrl_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(MainActivity.this, "startClick");
                if(isListening) {
                    stopListening();
                    startButton.setBackgroundResource(R.mipmap.start);
                    msgText.setText("已暂停");
                } else {
                    startListening(true);
                    startButton.setBackgroundResource(R.mipmap.stop);
                    msgText.setText("");
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
        msgText = (TextView) this.findViewById(R.id.msg);

        recordDynamic = (ProgressBar) this.findViewById(R.id.recordDynamic);

        historyList = (WebView) this.findViewById(R.id.historylist);
        historyList.setBackgroundColor(0);
        historyList.getSettings().setJavaScriptEnabled(true);
        historyList.getSettings().setAppCacheEnabled(true);

        historyList.loadUrl("javascript:var isFirstStart="+AppConfig.getIsFirstStart()+";");
        historyList.loadUrl("file:///android_asset/history.html");

        startListening(true);
        startButton.setBackgroundResource(R.mipmap.stop);

        historyResult = new Stack<>();
        cmdName = new HashMap<String, Integer>();
        cmdName.put("清屏", 1);
        cmdName.put("清空", 1);
        cmdName.put("清除", 1);

        cmdName.put("撤销", 2);
        cmdName.put("倒退", 2);
        cmdName.put("删除", 2);

        cmdName.put("帮助", 3);
        cmdName.put("示例", 3);
        cmdName.put("说明", 3);
    }


    @Override
    public void onResume() {
        super.onResume();
        startListening(true);
        startButton.setBackgroundResource(R.mipmap.stop);
        MobclickAgent.onResume(this);
        msgText.setText("");
    }

    @Override
    public void onPause() {
        super.onPause();
        stopListening();
        startButton.setBackgroundResource(R.mipmap.start);
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
        lastRmsdB = 0;
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

    private float lastRmsdB=0;
    public void onRmsChanged(float rmsdB) {
        lastRmsdB = (rmsdB*3+lastRmsdB*7) /10;
        recordDynamic.setProgress((int) lastRmsdB);
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
                startButton.setBackgroundResource(R.mipmap.start);
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
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                sb.append("权限不足");
                startButton.setBackgroundResource(R.mipmap.start);
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                sb.append("请检查网络连接");
                MobclickAgent.onEvent(this, "errorNetwork");
                startButton.setBackgroundResource(R.mipmap.start);
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                sb.append("未识别");
                MobclickAgent.onEvent(this, "failMatch");
                startListening(true);
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                sb.append("引擎忙");
                MobclickAgent.onEvent(this, "busy");
                startButton.setBackgroundResource(R.mipmap.start);
                break;
            case SpeechRecognizer.ERROR_SERVER:
                sb.append("未识别");
                startListening(true);
                MobclickAgent.onEvent(this, "failServer");
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                sb.append("网络连接连接超时");
                MobclickAgent.onEvent(this, "errorNetworkTimeout");
                startButton.setBackgroundResource(R.mipmap.start);
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
        params.put("type", type);
        evalLog.recordEvaluation(params);
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
                msgText.setText("未识别");
            }
        }

    }

    public boolean handleCommand(String expr) {
        String cmd = calculator.execFilter(expr);
        if(cmd != null && cmd.length() > 0
                && cmdName.containsKey(cmd)) {
            int type = cmdName.get(cmd);
            this.recordEvaluation(uniqueId, "NaN", expr.toString(), "null", type);
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
                default:
                    break;
            }
            msgText.setText("");
            return true;
        }

        return false;
    }

    public void onResults(Bundle results) {

        String expr = buildExpr(results);
        if(!handleCommand(expr)) {
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
