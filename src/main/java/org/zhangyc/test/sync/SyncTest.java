package org.zhangyc.test.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SyncTest {
    private final static ExecutorService service = Executors.newFixedThreadPool(8);
    String aaa = "a";

    public static void main(String[] args) {
        A a = new A();
        List<Future<Boolean>> list = new ArrayList<>();
        for(int i = 0; i < 10000; i++){
            Callable<Boolean> t = new Th(a);
            list.add(service.submit(t));
        }

        for(Future<Boolean> f : list){
            try {
                f.get();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(a.get());
    }


}

class A{
    private int a;

    public Integer get(){
        return a;
    }

    public void set(){
        synchronized (this) {
            this.a = a+1;
        }
    }
}

class Th implements Callable<Boolean> {
    private A a;

    public Th(A a){
        this.a = a;
    }
    @Override
    public Boolean call() {
        a.set();
        System.out.println("a = " + a.get());
        return true;
    }
}
