package org.xing.logger;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public interface Log {
    boolean recordEvaluation(String userId, String result, String inputExpr, String readExpr);
}
