package org.zhangyc.test.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created by zhangyicou on 2019/1/19.
 */

public class C11EventHandler implements EventHandler<LongEvent>, WorkHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        long number = longEvent.getValue();
        number += 10;
        System.out.println(System.currentTimeMillis()+": c1-1-1 consumer finished.number=" + number);
    }

    @Override
    public void onEvent(LongEvent longEvent) throws Exception {
        long number = longEvent.getValue();
        number += 10;
        System.out.println(System.currentTimeMillis()+": c1-1-2 consumer finished.number=" + number);
    }
}
