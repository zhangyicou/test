package org.zhangyc.test.disruptor;

/**
 * Created by zhangyicou on 2019/1/19.
 */
public class LongEvent {
    private long value;

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
