package org.zhangyc.test.countdown;

import java.util.HashSet;
import java.util.concurrent.CountDownLatch;

/**
 * Created by user on 16/7/6.
 */
public class MyThread implements Runnable {

    private CountDownLatch cdLatch = null;
    private int i;
    private String taskId;
    public MyThread(CountDownLatch latch, int seconds, String taskId){
        this.cdLatch = latch;
        this.i = seconds;
        this.taskId = taskId;
    }

    public void run() {
        try {
            for(int i=0; i < 500; i++) {
                Thread.sleep(1000 * this.i);
                Object obj = new Object();
                HashSet<Object> set = Entity.getInstance().get(this.taskId);
                if (set != null) {
                    set.add(obj);
                } else {
                    set = new HashSet<Object>();
                    set.add(obj);
                    Entity.getInstance().put(this.taskId, set);
                }

                System.out.println("id:" + this.i+"."+i);
            }
            System.out.println("cdLatch.count:" + this.cdLatch.getCount());
            this.cdLatch.countDown();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
