package org.zhangyc.test.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by zhangyicou on 2019/1/19.
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
