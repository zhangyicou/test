package org.zhangyc.test.delayqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 16/7/16.
 */
public class DelayedTask implements Runnable, Delayed {
    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;
    protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

    public DelayedTask(int delayInMilliseconds){
        delta = delayInMilliseconds;
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        sequence.add(this);
    }

    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    public int compareTo(Delayed delayed) {
        DelayedTask that = (DelayedTask)delayed;
        if(trigger < that.trigger) return -1;
        if(trigger > that.trigger) return 1;
        return 0;
    }

    public void run(){System.out.println(this);}

    public String toString(){
        return String.format("[%1$-4d]", delta) + "Task "+id;
    }

    public String summary(){
        return "("+id+":"+delta+")";
    }

    public static class EndSentinel extends DelayedTask{
        private ExecutorService exec;

        public EndSentinel(int delay, ExecutorService e){
            super(delay);
            exec = e;
        }

        public void run(){
            for(DelayedTask pt : sequence){
                System.out.println(pt.summary() + "");
            }

            System.out.println();

            System.out.println(this + "Calling shutdownNow()");
            exec.shutdownNow();
        }
    }

    class DelayedTaskConsumer implements Runnable{
        private DelayQueue<DelayedTask> queue;
        public DelayedTaskConsumer(DelayQueue<DelayedTask> q){
            this.queue = q;
        }

        public void run(){
            try{
                while (!Thread.interrupted()){
                    queue.take().run();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("Finished DelayedTaskConsumer....");
        }
    }
}
