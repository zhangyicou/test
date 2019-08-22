package org.zhangyc.test.semaphore;

/**
 * Created by user on 16/7/22.
 */
public class RunTest implements Runnable {
    private int i;
    private com.google.common.util.concurrent.RateLimiter rateLimiter;
    public RunTest(int i,
                   com.google.common.util.concurrent.RateLimiter rateLimiter){
        this.i = i;
        this.rateLimiter = rateLimiter;
        if(this.i % 1000 == 0){
            this.rateLimiter.setRate(this.rateLimiter.getRate() + 1);
        }
        if(this.rateLimiter.getRate() > 20 && this.i > 10000){
            this.rateLimiter.setRate(this.rateLimiter.getRate() - 1);
        }

    }
    public void run() {
        while(true) {
            boolean limiter = this.rateLimiter.tryAcquire();
            if(!limiter){
                continue;
            }
            System.out.println(this.i + " after..."+this.rateLimiter.getRate());
        }
    }
}
