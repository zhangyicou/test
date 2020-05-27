package org.zhangyc.test.thread;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yichu.zhang
 * @Date: 2020-05-19 16:34
 */
public class ScheduledThreadPoolExecutorTest {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutorTest test = new ScheduledThreadPoolExecutorTest();
        //池中只有一个线程
        ScheduledThreadPoolExecutor schedulePool = new ScheduledThreadPoolExecutor(1);
        //作为一个周期任务提交,period 为1000ms,任务执行时间为2000ms
        schedulePool.scheduleAtFixedRate(()->test.hay(), 1, 1, TimeUnit.SECONDS);
    }

    private void hay(){
        System.out.println(".................................................");
    }
}
