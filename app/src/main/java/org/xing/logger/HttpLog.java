package org.xing.logger;

import org.xing.utils.HttpUtil;

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
    public boolean log(Map<String, Object> params) {
        HttpUtil.doPost(this.url, params, "utf-8");

        return true;
    }
}
