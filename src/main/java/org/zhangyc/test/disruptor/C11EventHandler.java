package org.zhangyc.test.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created by zhangyicou on 2019/1/19.
 */

public class C11EventHandler implements EventHandler<LongEvent>, WorkHandler<LongEvent> {
    ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        threadLocal.set(longEvent.getValue());
        threadLocal.set(threadLocal.get() + 10);
        System.out.println(System.currentTimeMillis()+": c1-1-1 consumer finished.number=" + threadLocal.get() + "; sequence="+sequence);
        //longEvent.setValue(threadLocal.get());
    }

    @Override
    public void onEvent(LongEvent longEvent) throws Exception {
        longEvent.setValue(longEvent.getValue() + 10);
    }
}