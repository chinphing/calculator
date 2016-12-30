package org.xing.logger;

import org.xing.utils.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class HttpLog implements Log {
    private String url;
    public HttpLog(String url) {
        this.url = url;
    }


    @Override
    public boolean recordEvaluation(String userId, String result, String inputExpr, String readExpr) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", userId);
        params.put("result", result);
        params.put("inputExpr", inputExpr);
        params.put("readExpr", readExpr);

        HttpUtil.doPost(this.url, params, "utf-8");

        return true;
    }
}
