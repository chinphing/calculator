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

    public static String getPrintSize(long size) {
        float printSize = (float)size / 1024/ 1024;
        return String.format("%.1fMB", printSize);
    }

    public static String toFraction(Double number) {
        if(number == null || number.isNaN() || number.isInfinite()) {
            return null;
        }
        StringBuilder frac = new StringBuilder();

        return frac.toString();
    }
}
