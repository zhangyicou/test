package org.zhangyc.test.runnable.queue;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhang on 2018/9/26.
 */
public class ConsumerTest implements Runnable {
    @Override
    public void run() {
        while (true){
            System.out.println(QueueTest.get());
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }
        }
    }
}
