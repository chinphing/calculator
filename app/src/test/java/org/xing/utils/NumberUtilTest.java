package org.xing.utils;

import org.junit.Test;

/**
 * Created by Administrator on 2017/1/9 0009.
 */
public class NumberUtilTest {
    @Test
    public void getPrintSize() throws Exception {
        int[] size = new int[] {10100000};
        for(int i=0;i<size.length;i++) {
            System.out.println(NumberUtil.getPrintSize(size[i]));
        }
    }

}