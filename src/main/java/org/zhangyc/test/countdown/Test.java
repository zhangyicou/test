package org.zhangyc.test.countdown;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by user on 16/7/6.
 */
public class Test {
    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(10);
        Executor executor = Executors.newFixedThreadPool(10);

        String taskId = UUID.randomUUID().toString();
        for(int i = 0; i < 10; i++){
            executor.execute(new MyThread(latch, i, taskId));
        }
        try {
            latch.await();
        }catch (Throwable t){
            t.printStackTrace();
        }
        System.out.println("-------------");
        for(Object obj : Entity.getInstance().get(taskId)){
            System.out.println(obj);
        }
    }
}
