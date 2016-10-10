package org.zhangyc.test.queue;

/**
 * Created by user on 16/7/5.
 */
public class Producter implements Runnable{
    private static long counter = 1;
    public void run() {
        while(true) {
            try{
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            long index = counter++;
            Entity entity = new Entity();
            entity.setId(index);
            QueueProvider.put(entity);
        }
    }
}
