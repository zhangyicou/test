package org.zhangyc.test.semaphore;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 16/7/22.
 */
public class RateLimiter {
    private static ConcurrentHashMap<String, Semaphore> map = new ConcurrentHashMap<String, Semaphore>();

    private static RateLimiter limiter = new RateLimiter();
    private static Semaphore semaphore = null;


    public static synchronized boolean tryAcquire(long second, int number){
        boolean acquire = true;
        String cecondStr = String.valueOf(second);
        if(!map.containsKey(cecondStr)){
            semaphore = new Semaphore(number);
            map.put(cecondStr, semaphore);
            remove(String.valueOf(second-20));
        }

        semaphore = map.get(cecondStr);

        try {
            acquire = semaphore.tryAcquire(300, TimeUnit.MILLISECONDS);
            System.out.println(second + "..."+acquire);
        }catch (Throwable t){
            t.printStackTrace();
            acquire = false;
        }

        return acquire;
    }


    private static void remove(String secondStr){
        map.remove(secondStr);
    }
}
