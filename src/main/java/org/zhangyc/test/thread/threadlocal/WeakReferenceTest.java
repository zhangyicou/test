package org.zhangyc.test.thread.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yichu.zhang
 * @Date: 2019-08-22 18:50
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        ThreadLocal<Map<String, Long>> threadLocal = new ThreadLocal<Map<String, Long>>(){
            @Override
            protected Map<String, Long> initialValue() {
                return new HashMap<String, Long>();
            }
        };

        for(long i = 0; i <= 10_000_000_000_000_000L; i++){
            threadLocal.get().put(String.valueOf(i), i);
        }

        for(int i = 0; i < 10_000_000_000_000_000L; i++){
            long value_1 = threadLocal.get().get(String.valueOf(i));
            long value_2 = threadLocal.get().get(String.valueOf(i+1));
            if(value_1 + 1 != value_2){
                System.out.println(value_1 + " - " + value_2);
            }
        }
    }
}
