package org.zhangyc.test.atomic;

import java.util.concurrent.atomic.AtomicLong;

public class LongTest {
    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong(1);
        for(int i = 0; i < 10; i++){
            System.out.println(atomicLong.get());
            atomicLong.addAndGet(2);
        }
    }
}
