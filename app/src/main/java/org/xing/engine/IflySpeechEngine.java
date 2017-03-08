package org.xing.engine;

import android.content.Context;
import android.os.Bundle;

import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;

import org.xing.android.MainActivity;
import org.xing.logger.impl.EventLogger;
import org.xing.utils.JsonParser;

import static com.iflytek.cloud.ErrorCode.ERROR_AUDIO_RECORD;
import static com.iflytek.cloud.ErrorCode.ERROR_ENGINE_BUSY;
import static com.iflytek.cloud.ErrorCode.ERROR_NETWORK_TIMEOUT;
import static com.iflytek.cloud.ErrorCode.ERROR_NO_MATCH;
import static com.iflytek.cloud.ErrorCode.ERROR_NO_NETWORK;
import static com.iflytek.cloud.ErrorCode.ERROR_PERMISSION_DENIED;
import static com.iflytek.cloud.ErrorCode.MSP_ERROR_NO_DATA;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public class IflySpeechEngine implements SpeechEngine{
    private Context ctx;
    private SpeechListener listener;
    private SpeechRecognizer speechRecognizer;
    private InternalRecognizerListener _listener;

    private static EventLogger eventLogger;

    public IflySpeechEngine(Context ctx) {
        this.ctx = ctx;
        SpeechUtility.createUtility(ctx, SpeechConstant.APPID +"=585290e7");

        speechRecognizer= SpeechRecognizer.createRecognizer(ctx, null);
        speechRecognizer.setParameter(SpeechConstant.DOMAIN, "iat");          //{短信和日常用语：iat (默认) 视频：video 地图：poi 音乐：music
        speechRecognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        speechRecognizer.setParameter(SpeechConstant.ACCENT, "mandarin");
        speechRecognizer.setParameter(SpeechConstant.VAD_BOS, "10000");         //十秒超时
        speechRecognizer.setParameter(SpeechConstant.ASR_PTT, "0");             //不带标点

        eventLogger = MainActivity.eventLogger;
    }

    public void setSpeechListener(SpeechListener listener){
        this.listener = listener;
        _listener = new InternalRecognizerListener(listener);
    }

    public void startListening() {
        speechRecognizer.startListening(_listener);
        listener.onReadyForSpeech();
    }

    public void stopListening() {
        speechRecognizer.stopListening();
    }
    public void cancel() {
        speechRecognizer.cancel();
    }
    public void destroy() {
        speechRecognizer.destroy();
    }

    private static class InternalRecognizerListener implements RecognizerListener{
        private SpeechListener listener;
        public InternalRecognizerListener(SpeechListener listener) {
            this.listener = listener;
        }
        public void onResult(RecognizerResult results, boolean isLast) {
            String expr = JsonParser.parseIatResult(results.getResultString());
            listener.onResults(expr);
        }

        //会话发生错误回调接口
        public void onError(SpeechError error) {
            switch (error.getErrorCode()) {
                case ERROR_AUDIO_RECORD:
                    listener.onError(android.speech.SpeechRecognizer.ERROR_AUDIO);
                    break;
                case ERROR_ENGINE_BUSY:
                    listener.onError(android.speech.SpeechRecognizer.ERROR_RECOGNIZER_BUSY);
                    break;
                case ERROR_PERMISSION_DENIED:
                    listener.onError(android.speech.SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS);
                    break;
                case ERROR_NO_NETWORK:
                    listener.onError(android.speech.SpeechRecognizer.ERROR_NETWORK);
                    break;
                case 	ERROR_NETWORK_TIMEOUT:
                    listener.onError(android.speech.SpeechRecognizer.ERROR_NETWORK_TIMEOUT);
                    break;
                case 	ERROR_NO_MATCH:
                    listener.onError(android.speech.SpeechRecognizer.ERROR_NO_MATCH);
                    break;
                case MSP_ERROR_NO_DATA:
                    listener.onError(android.speech.SpeechRecognizer.ERROR_SPEECH_TIMEOUT);
                    break;
                default:
                    eventLogger.onEvent("ifly-error-"+error.getErrorCode()+": "+error.getErrorDescription());
                    listener.onError(android.speech.SpeechRecognizer.ERROR_CLIENT);
                    break;
            }
        }

        //开始录音
        public void onBeginOfSpeech() {
            listener.onBeginningOfSpeech();
        }

        //音量值0~30
        public void onVolumeChanged(int volume, byte[] data){
            listener.onRmsChanged((float)volume * 10);
        }

        //结束录音
        public void onEndOfSpeech() {
            listener.onEndOfSpeech();
        }

        //扩展用接口
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {

        }
    };
}
