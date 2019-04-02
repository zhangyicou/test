package org.zhangyc.test.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created by zhangyicou on 2019/1/19.
 */

public class C21EventHandler implements EventHandler<LongEvent>, WorkHandler<LongEvent> {
    ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("C21EventHandler.longEvent="+longEvent.getValue());
        threadLocal.set(longEvent.getValue());
        threadLocal.set(threadLocal.get() + 20);
        System.out.println(System.currentTimeMillis()+": c2-1-1 consumer finished.number=" + threadLocal.get() + "; sequence="+sequence);
        //longEvent.setValue(threadLocal.get());
    }

    @Override
    public void onEvent(LongEvent longEvent) throws Exception {
        long number = longEvent.getValue();
        number += 20;
        System.out.println(System.currentTimeMillis()+": c2-1-2 consumer finished.number=" + number);
    }
}
