package org.zhangyc.test.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by user on 16/6/16.
 */
public class ThreadPool {
    /**
     * 初始化数量
     */
    private int initSize=Runtime.getRuntime().availableProcessors();
    /**
     * 当前正在使用的数量
     */
    private volatile int coreSize=0;

    /**
     * 当前正在使用的数量
     */
    private volatile int useSize=0;

    /**
     * 最大数量
     */
    private volatile int maxSize = coreSize * 4;

    private BlockingQueue<Thread> myPool = null;

//    private ConcurrentLinkedQueue<Thread> pool = new ConcurrentLinkedQueue<Thread>();


    public ThreadPool(int initSize, int maxSize){
        this.initSize = initSize;
        this.maxSize = maxSize;
        init();
    }

    private void init(){
        myPool = new ArrayBlockingQueue<Thread>(this.initSize);
        for(int i = 0; i < this.initSize; i++){
            this.myPool.add(new Thread());
            this.coreSize++;
        }
    }

    public Thread getThread() throws InterruptedException {
        System.out.println("coreSize:" + this.coreSize + ";  maxSize:" + this.maxSize+ ";  useSize:" + this.useSize + "; size:" + this.myPool.size());
        if (this.myPool.isEmpty()) {
            if (this.coreSize < this.maxSize && useSize < this.maxSize) {
                this.myPool.add(new Thread());
                this.coreSize++;
            }
        }

        this.useSize++;
        return this.myPool.take();
    }

    public void returnT(Thread t){
        if(t != null) {
            this.myPool.add(t);
            this.useSize--;
        }
    }

    public void shutdown(){
        for(int i=0; i < this.myPool.size(); i++){
            Thread t = this.myPool.remove();
            t = null;
            this.coreSize--;
        }
    }
}
