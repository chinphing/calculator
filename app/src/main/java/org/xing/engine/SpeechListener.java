package org.xing.engine;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public interface SpeechListener{

    void onReadyForSpeech();

    void onBeginningOfSpeech();

    void onRmsChanged(float rmsdB);

    void onEndOfSpeech();

    void onError(int error);

    void onResults(String expr);
}
