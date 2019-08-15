package org.zhangyc.test.doubles;


import java.math.BigDecimal;

public class DoublesTest {
    public static void main(String[] args) {
        System.out.println(0.1 + 0.2);

        java.text.NumberFormat  formater  =  java.text.DecimalFormat.getInstance();
        formater.setMaximumFractionDigits(4);
        formater.setMinimumFractionDigits(4);
        System.out.println(formater.format(0));

        System.out.println(formater.format(6.7E-4));

        BigDecimal bigDecimal = new BigDecimal(100.56);
        System.out.println(bigDecimal.toPlainString());
    }
}
