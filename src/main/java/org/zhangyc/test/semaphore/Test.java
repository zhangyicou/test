package org.zhangyc.test.semaphore;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by user on 16/7/22.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(new Date());
        System.out.println(str.substring(0,10));
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        RateLimiter limiter = RateLimiter.create(1);
        for(int i = 1; i < 1000000; i++){
            executorService.execute(new RunTest(i, limiter));
        }
        executorService.shutdownNow();
    }
}
