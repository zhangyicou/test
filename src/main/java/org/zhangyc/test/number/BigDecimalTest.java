package org.zhangyc.test.number;

import java.math.BigDecimal;

/**
 * Created by oker on 2019/1/17.
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal open = new BigDecimal(10);
        BigDecimal close = new BigDecimal(30);
        final BigDecimal changePercent = close.subtract(open).divide(open);

        System.out.println(changePercent);

        BigDecimal other = new BigDecimal(-5);
        System.out.println(close.subtract(open.subtract(other)));
    }
}
