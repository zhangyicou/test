package org.zhangyc.test.doubles;


import org.zhangyc.test.thread.PrecisionUtil;

import java.math.BigDecimal;

public class DoublesTest {
    public static void main(String[] args) {
        System.out.println(0.1 + 0.2);

        java.text.NumberFormat  formater  =  java.text.DecimalFormat.getInstance();
        formater.setMaximumFractionDigits(8);
        formater.setMinimumFractionDigits(0);
        System.out.println(formater.format(0));

        System.out.println("--a-" + formater.format(6.7E-4));
        String a = formater.format(-1.9999999999997797E-4);
        System.out.println("--a- " + a);
        System.out.println("--a- " +new BigDecimal(a));


        System.out.println("--b-" + formater.format(6.7E-4));
        String b = formater.format(0E-8);
        System.out.println("--b-" + b);
        System.out.println("--b-" + new BigDecimal(b).doubleValue());

        System.out.println("--c-" + formater.format(6.7E-4));
        String c = formater.format(0.00000000);
        System.out.println("--c-" + c);
        System.out.println("--c-" + new BigDecimal(c).doubleValue());

        System.out.println("--d-" + formater.format(6.7E-4));
        String d = formater.format(0.12345678);
        System.out.println("--d-" + d);
        System.out.println("--d-" + new BigDecimal(d).doubleValue());

        System.out.println("--e-" + formater.format(6.7E-4));
        String e = PrecisionUtil.getInstance().formatNumber(0.00000000, 8);
        System.out.println("--e-" + e);
        System.out.println("--e-" + new BigDecimal(e).doubleValue());

        double f1 = -7.043999999997091E-5;
        System.out.println("--f-" + formater.format(f1));
        String f = PrecisionUtil.getInstance().formatNumber(f1, 8);
        System.out.println("--f-" + f);
        BigDecimal bigDecimal = new BigDecimal(f);
        System.out.println("--f-" + bigDecimal.doubleValue());
        System.out.println(f1 == bigDecimal.doubleValue());
        BigDecimal bigDecimal2 = new BigDecimal(100.56);
        System.out.println(bigDecimal2.toPlainString());



    }
}
