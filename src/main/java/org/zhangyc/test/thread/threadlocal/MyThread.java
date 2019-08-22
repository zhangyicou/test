package org.zhangyc.test.thread.threadlocal;

/**
 * @Author: yichu.zhang
 * @Date: 2019-08-22 16:15
 */
public class MyThread extends Thread{
    private ThreadLocal<Integer> threadLocal;
    private int index;

    public MyThread(int i, ThreadLocal<Integer> threadLocal){
        this.index = i;
        this.threadLocal = threadLocal;
    }
    @Override
    public void run() {
        for(int i = 0; i < 3; i++) {
            System.out.println("index = " + this.index
                    + "; id = " + this.getId()
                    + "; name = " + this.getName()
                    + "; value = " + this.getValue());
        }
    }

    private Integer getValue(){
        this.threadLocal.set(this.threadLocal.get() + 1);

        return this.threadLocal.get();
    }

}
