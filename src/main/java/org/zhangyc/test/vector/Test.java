package org.zhangyc.test.vector;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user on 16/3/28.
 */
public class Test {
    public static void main(String[] args){
        Vector<Integer> vector = new Vector<Integer>(10);
        AtomicInteger totalTask = new AtomicInteger();
        Thread task = null;
        for(int h = 0; h < 100; h++){
            task = new Task(vector, totalTask);
            task.start();
        }

        do{
            try {
                Thread.sleep(500);
            }catch (Exception e){
                e.printStackTrace();
            }
        }while (totalTask.get() < 100 * 1000);

        System.out.println(vector.size());
        System.out.println(totalTask.get());
    }

}

class Task extends Thread{
    private Vector<Integer> vector;
    private AtomicInteger totalTask;
    public Task(Vector<Integer> vector, AtomicInteger totalTask){
        this.vector = vector;
        this.totalTask = totalTask;
    }
    @Override
    public void run() {
        for(int i = 0; i < 1000; i++){
            this.vector.add(i);
            totalTask.addAndGet(1);
        }
    }
}
