package org.zhangyc.test.semaphore;

/**
 * Created by user on 16/7/22.
 */
public class RunTest implements Runnable {
    private int i;
    public RunTest(int i){
        this.i = i;
    }
    public void run() {
        while(true) {
            long second = System.currentTimeMillis() / 1000;

            boolean limiter = RateLimiter.tryAcquire(second, 10);
            if(!limiter){
                continue;
            }
            System.out.println(this.i + ":" +second + " after..."+limiter);
        }
    }
}
