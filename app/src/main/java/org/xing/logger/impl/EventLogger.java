package org.xing.logger.impl;

import android.os.Build;

import org.xing.logger.AsyncLog;
import org.xing.logger.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/21 0021.
 */

public class EventLogger {
    private String userId;
    private String version;
    private Log eventLogger;

    public EventLogger(String userId, String version, String url) {
        this.userId = userId;
        this.version = version;
        eventLogger = AsyncLog.createAsyncHttpLog(url);
    }

    /*
    常规计数事件
     */
    public void onEvent(String event) {
        Map<String, Object> params = new HashMap<>();
        params.put("_eventType", 1);
        params.put("version", version);
        params.put("userId", userId);
        params.put("model", Build.VERSION.SDK_INT + " " + Build.BRAND + " " + Build.MODEL);
        params.put("event", event);
        eventLogger.log(params);
    }

    /*
    计算事件
     */
    public void onEvent(String result, String inputExpr, String readExpr, int type) {
        Map<String, Object> params = new HashMap<>();
        params.put("_eventType", 2);
        params.put("userId", userId);
        params.put("version", version);
        params.put("result", result);
        params.put("inputExpr", inputExpr);
        params.put("readExpr", readExpr == null ? "null" : readExpr);
        params.put("type", type);
        eventLogger.log(params);
    }
}
