package org.xing.logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class AsyncLog implements Log {

    private Log syncLog;
    private ExecutorService executorService;

    public AsyncLog(Log log) {
        this.syncLog = log;
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public boolean recordEvaluation(final String userId, final String result,
                                    final String inputExpr,final String readExpr) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                syncLog.recordEvaluation(userId, result, inputExpr, readExpr);
            }
        });
        return true;
    }

    public static AsyncLog createAsyncHttpLog(String url) {
        return new AsyncLog(new HttpLog(url));
    }
}
