package org.xing.utils;

/**
 * Created by Administrator on 2016/12/30 0030.
 */

public class NumberUtil {
    public static String format(double num, int maxFloat) {
        String text = String.format("%."+maxFloat+"f", num);
        text = text.replaceAll("(\\.)?0*$", "");
        return text;
    }
}
