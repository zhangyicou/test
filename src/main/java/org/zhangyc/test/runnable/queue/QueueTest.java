package org.zhangyc.test.runnable.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zhang on 2018/9/26.
 */
public class QueueTest {
    private static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);

    public static void add(String str){
        try {
            blockingQueue.put(str);
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
    }

    public static String get(){
        try {
            return blockingQueue.take();
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }

        return "-----";
    }
}
