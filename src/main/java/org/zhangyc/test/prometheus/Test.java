package org.zhangyc.test.prometheus;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: yichu.zhang
 * @Date: 2019-09-24 18:07
 */
public class Test {
    private ConcurrentMap<Integer, AtomicLong> map = new ConcurrentHashMap<>();


    public static void main(String[] args) {
        Test test = new Test();
        test.init();

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.scheduleAtFixedRate(() -> test.update(), 1000, 1000, TimeUnit.MILLISECONDS);
    }

    private void init(){
        for(int i = 1; i <= 5; i++){
            map.put(i, new AtomicLong());
        }
    }

    private void update(){
        this.map.values().stream().forEach(atomicLong -> atomicLong.incrementAndGet());
    }
}
