package org.xing.engine;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

import com.baidu.speech.VoiceRecognitionService;

import org.xing.utils.Logger;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public class BaiduSpeechEngine implements SpeechEngine{
    private Context ctx;
    private SpeechListener listener;
    private SpeechRecognizer speechRecognizer;
    private InternalRecognitionListener _listener;

    public BaiduSpeechEngine(Context ctx) {
        this.ctx = ctx;
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(
                ctx, new ComponentName(ctx, VoiceRecognitionService.class));
    }

    public void setSpeechListener(SpeechListener listener){
        this.listener = listener;
        this._listener = new InternalRecognitionListener(listener);
        speechRecognizer.setRecognitionListener(_listener);
    }

    public void startListening() {
        speechRecognizer.startListening(new Intent());
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

    private static class InternalRecognitionListener implements RecognitionListener {
        private SpeechListener listener;
        public InternalRecognitionListener(SpeechListener listener) {
            this.listener = listener;
        }

        public void onResults(Bundle results) {
            ArrayList<String> nbest = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            StringBuilder expr = new StringBuilder();
            for (String w : nbest) {
                expr.append(w);
            }

            listener.onResults(expr.toString());
        }

        public void onReadyForSpeech(Bundle bundle) {
            listener.onReadyForSpeech();
        }

        //会话发生错误回调接口
        public void onError(int error) {
            listener.onError(error);
        }

        //开始录音
        public void onBeginningOfSpeech() {
            listener.onBeginningOfSpeech();
        }

        //音量值
        public void onRmsChanged(float rms){
            listener.onRmsChanged(rms/20);
        }

        //结束录音
        public void onEndOfSpeech() {
            listener.onEndOfSpeech();
        }


        public void onPartialResults(Bundle partialResults) {

        }

        public void onEvent(int eventType, Bundle params) {
            Logger.info("xing", String.valueOf(eventType)+": "+params.toString());
        }

        public void onBufferReceived(byte[] buffer) {

        }
    };
}
