package org.zhangyc.test.queue;

/**
 * Created by user on 16/7/5.
 */
public class Consumer implements Runnable {

    public void run() {
        while (true) {
            Entity entity = QueueProvider.take();
            if(entity != null) {
                System.out.println("entity:" + entity.getId());
            }else{
                System.out.println("entity is null......");
            }
//            try{
//                Thread.sleep(5);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
        }
    }
}
