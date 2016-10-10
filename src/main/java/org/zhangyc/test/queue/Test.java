package org.zhangyc.test.queue;

/**
 * Created by user on 16/7/5.
 */
public class Test {
    public static void main(String[] args){

        Producter producter1 = new Producter();
        new Thread(producter1).start();
        System.out.println("---------------");
        Runnable consumer = new Consumer();
        new Thread(consumer).start();

        Producter producter2 = new Producter();
        new Thread(producter2).start();
        Producter producter3 = new Producter();
        new Thread(producter3).start();
//        Producter producter4 = new Producter();
//        producter4.run();
//        Producter producter5 = new Producter();
//        producter5.run();
//        Producter producter6 = new Producter();
//        producter6.run();
    }
}
