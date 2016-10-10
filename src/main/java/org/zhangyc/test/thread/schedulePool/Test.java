package org.zhangyc.test.thread.schedulePool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 16/8/11.
 */
public class Test {
    public static void main(String[] args){
        ScheduledExecutorService scheduleService = Executors.newScheduledThreadPool(5);
        Runnable schedule = null;

        int i = 1;
        while(i < 20){
            schedule = new MyScheduleThread(i);
            //scheduleService.schedule(schedule, 10, TimeUnit.SECONDS);
            scheduleService.scheduleAtFixedRate(schedule, 2, 1, TimeUnit.SECONDS);
            i++;
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }
        }
    }
}
