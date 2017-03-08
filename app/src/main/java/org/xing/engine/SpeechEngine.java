package org.xing.engine;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public interface SpeechEngine {
    void setSpeechListener(SpeechListener listener);
    void startListening();
    void stopListening();
    void cancel();
    void destroy();
}
