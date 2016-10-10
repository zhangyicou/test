package org.zhangyc.test.list.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user on 16/7/18.
 */
public class Test {
    public static void main(String[] args){
        int length = 100;
        int split = 5;
        int size = length / split;
        if((length % split) > 0){
            size++;
        }

        TestRunable tr = null;
        ExecutorService tableService =  Executors.newFixedThreadPool(size);

        List<Integer> list = new ArrayList<Integer>();
        AtomicInteger taskCountr = new AtomicInteger();
        int taskNumber = 0;
        for(int i = 0; i < length; i++){
            list.add(i);
            if(list.size() > split){
                tr = new TestRunable(list, taskCountr);
                tableService.execute(tr);

                list = new ArrayList<Integer>();
                taskNumber++;
            }
        }

        if(!list.isEmpty()){
            tr = new TestRunable(list, taskCountr);
            tableService.execute(tr);
        }



        while(taskNumber > taskCountr.get()){
            Thread.yield();
        }

        System.out.println("-----------");
    }
}
