package org.zhangyc.test.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.*;

public class Test {
    private String str = "abc";

    public static void main(String[] args) {
        final CyclicBarrier barrier = new CyclicBarrier(2);
        ExecutorService service = Executors.newCachedThreadPool();
        Test test = new Test();
        for(int i = 0; i < 2; i++){
            Runnable runnable  = new Runnable() {

                @Override
                public void run() {

                    try {
                        Thread.sleep(new Random().nextInt(1000));
                        System.out.println("线程 " + Thread.currentThread().getName() + "即将到达目的地 1,当前已有" + (barrier.getNumberWaiting() + 1) + "个到达," + (barrier.getNumberWaiting() == 2 ? "继续走啊" : "") );
                        barrier.await();
                        Thread.sleep(new Random().nextInt(1000));
                        System.out.println("线程 " + Thread.currentThread().getName() + "即将到达目的地 2,当前已有" + (barrier.getNumberWaiting() + 1) + "个到达," + (barrier.getNumberWaiting() == 2 ? "继续走啊" : "") );
                        barrier.await();
                        Thread.sleep(new Random().nextInt(1000));
                        System.out.println("线程 " + Thread.currentThread().getName() + "即将到达目的地 3,当前已有" + (barrier.getNumberWaiting() + 1) + "个到达," + (barrier.getNumberWaiting() == 2 ? "继续走啊" : "") );
                        barrier.await();
                        test.test();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
    }

    public void test(){
        //String str = UUID.randomUUID().toString();
        //String str = "a".concat("b").concat("c");//
        String str = "abc";
        synchronized (str){
            System.out.println("str="+str + "; "+Thread.currentThread().getName()+"; "+System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}