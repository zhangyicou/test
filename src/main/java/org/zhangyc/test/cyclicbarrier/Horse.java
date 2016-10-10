package org.zhangyc.test.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by user on 16/7/15.
 */
public class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random random = new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    public synchronized int getStrides(){return strides;}

    public void run() {
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    strides += random.nextInt(3);
                    System.out.println(id + ":"+getStrides());
                }

                barrier.await();
            }

        }catch (Throwable t){
            t.printStackTrace();
        }
    }

    public String toString(){
        return "Horse "+ id + "";
    }

    public String tracks(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < getStrides(); i++){
            sb.append("*");
        }
        sb.append(id);

        return sb.toString();
    }
}
