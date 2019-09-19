package org.zhangyc.test.pool;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yichu.zhang
 * @Date: 2019-08-22 20:05
 */
public class ThreadPoolTaskSchedulerTest {
    private final long DELAY = 200L;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
    public static void main(String[] args) {
        ThreadPoolTaskSchedulerTest test = new ThreadPoolTaskSchedulerTest();
        test.test();
    }

    private void test(){
        List<Integer> list = new ArrayList<Integer>();
        list.addAll(Arrays.asList(1,2,3,4,5));

        //list.forEach(v -> scheduler.scheduleWithFixedDelay(() -> print(v), 0, DELAY, TimeUnit.MILLISECONDS));

        //scheduler.scheduleWithFixedDelay(() -> print(v), 0, DELAY, TimeUnit.MILLISECONDS));
    }

    private void print(int value){
        try {
            for(int i = 0 ; i < 5; i++) {
                TimeUnit.MILLISECONDS.sleep(2);
                System.out.println("value = " + i + "_"+value);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void print(List<Integer> list){
        try {
            for(int i = 0 ; i < 5; i++) {
                TimeUnit.MILLISECONDS.sleep(2);
                System.out.println("value = " + i + "_");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
