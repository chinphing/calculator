package org.xing.utils;

import org.junit.Test;
import org.xing.logger.impl.EventLogger;

/**
 * Created by Administrator on 2017/2/21 0021.
 */

public class LoggerTest {
    @Test
    public void testEvent() {
        EventLogger eventLogger = new EventLogger("1", "1.3.4", "http://xingxing.zysoft.com/evaluation.php");

        eventLogger.onEvent( NumberUtil.format(3.15, 8), "零点一四五", "0.145", 0);
        eventLogger.onEvent( "start");

        try {
            Thread.currentThread().sleep(10000);
        }catch (Exception ex) {

        }
    }
}
