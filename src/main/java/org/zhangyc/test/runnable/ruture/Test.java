package org.zhangyc.test.runnable.ruture;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by user on 16/7/18.
 */
public class Test {
    public static void main(String[] args){
        Executors.newSingleThreadExecutor().execute(new TestRunnable());
        System.out.println("----------------------------------------------");
        Future future = Executors.newSingleThreadExecutor().submit(new TestRunnable());
        try {
            future.get();
        }catch (Throwable t){
            t.printStackTrace();
        }
    }
}
