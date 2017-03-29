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
        tips.add("试一下：'帮助'");
        tips.add("试一下：'升级'");
        tips.add("试一下：'主题'");
        tips.add("试一下：'三分之一'");
        tips.add("试一下：'加一千'");
        tips.add("试一下：'删除'");
        tips.add("试一下：'对数三'");
        tips.add("试一下：'三对数五'");
        tips.add("试一下：'清空'");
        tips.add("试一下：'二乘括号二加一括号'");
        tips.add("试一下：'正弦零点二'");
        tips.add("试一下：'正弦三分之派'");
        tips.add("试一下：'正弦三十度'");
        tips.add("试一下：'根号五'");
        tips.add("试一下：'三根号五'");
        tips.add("试一下：'三的平方'");
        tips.add("试一下：'三的四次方'");
        tips.add("试一下：'百度'");
        tips.add("试一下：'讯飞'");
        tips.add("试一下：'引擎'");
        tips.add("试一下：'退出'");
        tips.add("提示：分享三次可彻底删除广告");
        return tips;
    }
}
