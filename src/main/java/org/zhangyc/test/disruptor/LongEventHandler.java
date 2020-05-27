package org.zhangyc.test.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Created by zhangyicou on 2019/1/19.
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    public LongEventHandler(){

    }

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(System.currentTimeMillis());
    }
}