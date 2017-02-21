package org.xing.calc;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Administrator on 2017/2/21 0021.
 */

public class Tips {
    private Random random;
    private Vector<String> tipsStr;

    public Tips() {
        random = new Random();
        tipsStr = new Vector<>();
    }

    public void add(String tip) {
        tipsStr.add(tip);
    }

    public String get(int index) {
        if(index >= 0 && index < tipsStr.size())
            return tipsStr.get(index);
        else
             return null;
    }

    public String randomGet() {
        if(tipsStr.size() > 0) {
            return tipsStr.get(
                    random.nextInt(tipsStr.size())
            );
        }
        return null;
    }

    public static Tips createSimpleTips() {
        Tips tips = new Tips();
        tips.add("帮助");
        tips.add("升级");
        tips.add("三分之一");
        tips.add("加一千");
        tips.add("删除");
        tips.add("对数三");
        tips.add("三对数五");
        tips.add("清空");
        tips.add("二乘括号二加一括号");
        tips.add("正弦零点二");
        tips.add("正弦三分之派");
        tips.add("正弦三十度");
        tips.add("根号五");
        tips.add("三根号五");
        tips.add("三的平方");
        tips.add("三的四次方");
        return tips;
    }
}
