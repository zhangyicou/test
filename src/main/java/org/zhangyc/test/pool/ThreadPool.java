package org.zhangyc.test.pool;

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
    private int coreSize=0;
    /**
     * 最大数量
     */
    private int maxSize = coreSize * 4;

    private ConcurrentLinkedQueue<Thread> pool = new ConcurrentLinkedQueue<Thread>();


    public ThreadPool(int initSize, int maxSize){
        this.initSize = initSize;
        this.maxSize = maxSize;
        init();
    }

    private void init(){
        for(int i = 0; i < this.initSize; i++){
            this.pool.add(new Thread());
            System.out.println("i:" + i);
        }
    }

    public Thread getThread(){
        System.out.println("coreSize:" + this.coreSize + " :  maxSize:" + this.maxSize + ":" + this.pool.size());
        if(this.pool.isEmpty()){
            if(this.coreSize < this.maxSize){
                this.pool.add(new Thread());
            }else{
                try{
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        Thread t = this.pool.poll();
        if(t != null){
            this.coreSize++;
        }
        System.out.println("coreSize:"+this.coreSize + " :  maxSize:"+ this.maxSize);
        System.out.println("-------------------------------------");
        return t;
    }

    public void returnT(Thread t){
        if(t != null) {
            this.pool.add(t);
            this.coreSize--;
        }
    }

    public void shutdown(){
        for(int i=0; i < this.pool.size(); i++){
            Thread t = this.pool.remove();
            t = null;
            this.coreSize--;
        }
    }
}
