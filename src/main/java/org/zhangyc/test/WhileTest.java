package org.zhangyc.test;

import java.util.concurrent.TimeUnit;

/**
 * @Author: yichu.zhang
 * @Date: 2019-09-12 22:06
 */
public class WhileTest {
    public static void main(String[] args) {
        while (true){
            System.out.println("1-------------");
            //throw new RuntimeException("---------");
            //return;

            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
