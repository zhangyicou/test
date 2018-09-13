package org.zhangyc.test.base;

import java.math.BigDecimal;

/**
 * Created by user on 16/7/18.
 */
public class Test {
    public static void main(String[] str){
        Worker worker = new Worker(1l, "name.1", 20,
                1, "Elong", 200d);
        Farmer farmer = new Farmer(2l, "name.2", 21,
                300d, 400d);
        Person person = new Person(3l, "name.3", 22);

        Test t = new Test();

        t.work(worker);

        t.work(farmer);

        t.work(person);

//        BigDecimal number = new BigDecimal(2.11d);
//        for(int i=0; i < 10; i++){
//            number = number.multiply(new BigDecimal(2.11d));
//            System.out.println(number.toString());
//            System.out.println(number.setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
//        }

//        long value1 = 1808622;
//        long value2 = 1529128;
//        long value3 = 2319769;
//        long value4 = 1771505;
//        long value5 = 1869031;
//        long value6 = 1961485;
//        long value7 = 1716582;
//        long value8 = 1790138;
//        System.out.println(value1+value2+value3+value4+value5+value6+value7+value8);
//
//        BigDecimal bigDecimal = new BigDecimal(15);
//        BigDecimal divisor = new BigDecimal(100);
////        bigDecimal = bigDecimal.divide(divisor);
//        System.out.println(bigDecimal.toString());
//
//        BigDecimal eight = new BigDecimal(85);
//        System.out.println(bigDecimal.add(divisor).add(eight));
//
//        Double price  =new Double(0.0);
//        if(price.doubleValue() == 0){
//            System.out.println("--------");
//        }else{
//            System.out.println("---!-----");
//        }
    }


    public void work(Person person){
        if(person instanceof Worker){
            System.out.println(person);
        }else if(person instanceof Farmer){
            System.out.println(person);
        }else{
            System.out.println(person);
        }
    }
}
