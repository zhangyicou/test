package org.zhangyc.test;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by user on 16/7/11.
 */
public class Test {
    public static void main(String args[]){
        int[] products = new int[]{
            596, 199, 264, 465, 646, 404, 354, 364,
            250, 301, 974, 409, 345, 418, 366, 440,
            876, 828, 843, 730, 706, 895, 971, 758,
            759, 747, 851, 755, 771, 866, 756, 806
        };

        int sum = 0;
        for(int i = 0; i < products.length; i++){
            sum += products[i];
        }
        System.out.println("sum:"+sum);


        double aa = 1.1;
        int bb = 1;
        System.out.println("result:"+(aa > bb));

        boolean[] sent = new boolean[products.length];
        int i = 0;
        for(int a : products){
            sent[i] = true;
            if(a < 500){
                i++;
                continue;
            }
            i++;
        }

        for(boolean b : sent) {
            System.out.println("b:" + b);
        }

        long limiter = 1;
        do {
            //商品库限流了
            //limiter = RateLimiterUtil.tryAcquire(second, 10);
            limiter++;
            if (limiter > 10) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }while (limiter > 10);

        System.out.println("limiter:" + limiter);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        System.out.println("get:" + atomicBoolean.get());

        Float price1 = 1840.64f;
        Float price2 = 1840.647f;
        System.out.println(Math.abs(price1.floatValue() - price2.floatValue()) < 1);

        System.out.println(Long.valueOf(""));
    }
}
