package org.zhangyc.test.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user on 16/8/9.
 */
public class Test {
    public static void main(String[] args){
        AtomicInteger atomicInteger = new AtomicInteger();
        ExecutorService es = Executors.newSingleThreadExecutor();
        MyFuture mf = new MyFuture(atomicInteger);
        Future future = es.submit(mf);
        try {
            future.get();
            String result = "";
            result = atomicInteger.get() > 0 ? "SUCCESS" : "NONE";
            System.out.println(result);
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
    }
}
