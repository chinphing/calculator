package org.xing.calc.filter;

import java.util.LinkedList;

/**
 * Created by xing on 2017/5/10.
 */

public class ExprFilterChain implements ExprFilter {
    private LinkedList<ExprFilter> filters;
    public ExprFilterChain() {
        filters = new LinkedList<>();
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
