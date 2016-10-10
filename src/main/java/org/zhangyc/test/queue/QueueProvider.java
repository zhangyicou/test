package org.zhangyc.test.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by user on 16/7/5.
 */
public class QueueProvider {
    private static BlockingQueue<Entity> queue = new LinkedBlockingQueue<Entity>(10);

    public static boolean put(Entity entity){
        try {
            long start = System.currentTimeMillis();
            queue.put(entity);
            System.out.println("put.time:"+(System.currentTimeMillis()-start));
            return true;
        }catch (Throwable t){
            t.printStackTrace();
        }

        return false;
    }

    public static Entity take(){
        try {
            long start = System.currentTimeMillis();
            Entity entity = queue.take();
            System.out.println("take.time:"+(System.currentTimeMillis()-start));
            return entity;
        }catch (Throwable t){
            t.printStackTrace();
        }
        return null;
    }

}
