package org.zhangyc.test.future;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user on 16/8/9.
 */
public class MyFuture implements Runnable {
    private final AtomicInteger counter;
    public MyFuture(AtomicInteger atomicInteger){
        counter = atomicInteger;
    }
    public void run() {
        counter.incrementAndGet();
    }
}
