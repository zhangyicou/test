package org.zhangyc.test.semaphore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 16/7/22.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(new Date());
        System.out.println(str.substring(0,10));
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i = 1; i < 10; i++){
            executorService.execute(new RunTest(i));
        }
        executorService.shutdownNow();
    }
}
