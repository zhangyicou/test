package org.zhangyc.test.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * Created by zhangyicou on 2019/1/19.
 */
public class LongEventTranslator implements EventTranslatorOneArg<LongEvent, Long> {
    @Override
    public void translateTo(LongEvent longEvent, long sequence, Long arg0) {
        longEvent.setValue(arg0);
    }
}
