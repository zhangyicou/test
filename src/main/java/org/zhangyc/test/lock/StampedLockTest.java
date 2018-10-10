package org.zhangyc.test.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * Created by zhang on 2018/9/21.
 */
public class StampedLockTest {
    private double x, y;//内部定义表示坐标点
    private final StampedLock s1 = new StampedLock();//定义了StampedLock锁,

    private void move(double dataX, double dataY){
        long stamp = s1.writeLock();//这里的含义和distanceFormOrigin方法中 s1.readLock()是类似的
        try{
            x += dataX;
            y += dataY;
        }finally {
            s1.unlockWrite(stamp);//退出临界区,释放写锁
        }
    }

    private double distanceFormOrigin(){
        //试图尝试一次乐观读 返回一个类似于时间戳的邮戳整数stamp 这个stamp就可以作为这一个所获取的凭证
        long stamp = s1.tryOptimisticRead();

        return stamp;
    }

    public static void main(String[] args) {

    }
}
