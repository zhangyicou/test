package org.zhangyc.test.list.thread;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user on 16/7/18.
 */
public class TestRunable implements Runnable {
    private List<Integer> list;
    private AtomicInteger taskCountr;
    public TestRunable(List<Integer> l, AtomicInteger taskCountr){
        this.list = l;
        this.taskCountr = taskCountr;
    }
    public void run() {
        for(int i : this.list){
            System.out.println(i);
        }
        taskCountr.incrementAndGet();
    }
}
