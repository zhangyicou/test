package org.zhangyc.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
    private static volatile Integer i = 1;
    //private static int i = 1;
    public static void main(String[] args) {
        Thread a = new A(i);
        Thread b = new A(i);
        a.start();
        b.start();
        try {
            TimeUnit.SECONDS.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(i);
        try{
            Callable b1 = new B(i);
            Callable b2 = new B(i);
            System.out.println(b1.call());
            System.out.println(b2.call());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class A extends Thread{
    private Integer i;
    public A(Integer i){
        this.i = i;
    }
    @Override
    public void run() {
        for (int j = 0; j < 100; j++) {
            i++;
        }
    }
}

class B implements Callable<Integer>{

    private Integer i;
    public B(Integer i){
        this.i = i;
    }

    @Override
    public Integer call() throws Exception {
        for (int j = 0; j < 100; j++) {
            this.i++;
        }
        return i;
    }
}
