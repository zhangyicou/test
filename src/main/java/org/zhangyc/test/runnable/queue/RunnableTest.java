package org.zhangyc.test.runnable.queue;

import java.util.Queue;

/**
 * Created by zhang on 2018/9/26.
 */
public class RunnableTest implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 10000000; i++){
            System.out.println("i="+i);
            QueueTest.add(String.valueOf(i));
        }
    }
}
