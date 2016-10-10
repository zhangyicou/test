package org.zhangyc.test.thread.schedulePool;

import com.sun.javaws.exceptions.ExitException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by user on 16/8/11.
 */
public class MyScheduleThread implements Runnable{
    private int value = 0;
    private int index;
    public MyScheduleThread(int i){
        this.index = i;
    }
    protected final List<Integer> checkList = new ArrayList<Integer>();

    public void run() {
        System.out.println(this.index+":"+this.value++);
        for(int i = 1; i < 10; i++){
            checkList.add(i);
        }
        print();
        if(this.value == 10){
            throw new RuntimeException("RuntimeException....");
        }
    }

    public void print(){
        System.out.println("size:"+checkList.size());
        for(int v : checkList){
            System.out.println("v:"+v);
        }
        checkList.clear();
    }
}
