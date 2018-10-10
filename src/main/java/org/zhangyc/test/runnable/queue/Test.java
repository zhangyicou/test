package org.zhangyc.test.runnable.queue;

/**
 * Created by zhang on 2018/9/26.
 */
public class Test {
    public static void main(String[] args) {
        Thread consumer = new Thread(new ConsumerTest());
        consumer.start();

        Thread producer = new Thread(new RunnableTest());
        producer.start();
    }
}
