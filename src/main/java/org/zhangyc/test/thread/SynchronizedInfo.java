package org.zhangyc.test.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: yichu.zhang
 * @Date: 2019-08-15 19:00
 */
public class SynchronizedInfo {
    public void cal(final Long userId, final int index){
        synchronized (userId){
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("userId = " + userId + "; index = " + index + "; time = " + System.currentTimeMillis());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
