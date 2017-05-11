package org.xing.calc.filter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by xing on 2017/5/10.
 */

public class ExprFilterChain implements ExprFilter {
    private LinkedList<ExprFilter> filters;
    public ExprFilterChain() {
        filters = new LinkedList<>();
    }
    public ExprFilterChain(String allowedChars, HashMap<Character, String> pinyin) {
        filters = new LinkedList<>();
        filters.add(new CorrectionExprFilter());
        filters.add(new PinyinExprFilter(allowedChars, pinyin));
    }

    public void addExprFilter(ExprFilter filter) {
        filters.add(filter);
    }

    public String call(String expr) {
        String temp = expr;
        for(ExprFilter filter : filters) {
            temp = filter.call(temp);
        }
        return temp;
    }
}
