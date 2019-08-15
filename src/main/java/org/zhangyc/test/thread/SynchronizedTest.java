package org.zhangyc.test.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yichu.zhang
 * @Date: 2019-08-15 18:59
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        SynchronizedInfo synchronizedInfo = new SynchronizedInfo();

        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize, corePoolSize, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000));

        for(int i = 1; i < 10000; i++){
            final Long userId = i % 2L;
            final int index = i;
            poolExecutor.submit(() -> synchronizedInfo.cal(userId, index));
        }

    }
}
