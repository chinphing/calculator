package org.xing.logger;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public interface Log {
    boolean recordEvaluation(Map<String, Object> params);
}
