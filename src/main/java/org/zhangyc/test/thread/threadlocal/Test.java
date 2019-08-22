package org.zhangyc.test.thread.threadlocal;

/**
 * @Author: yichu.zhang
 * @Date: 2019-08-22 16:14
 */
public class Test {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };

        for(int i = 0; i < 3; i++){
            MyThread t = new MyThread(i, threadLocal);
            t.start();
        }
    }
}
