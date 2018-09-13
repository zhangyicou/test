package org.zhangyc.test.pool;

/**
 * Created by user on 16/6/16.
 */
public class Test {
    public static void main(String args[]){
        ThreadPool pool = new ThreadPool(1, 2);
        for(int i = 0; i < 3; i++){
            try {
                Thread t = pool.getThread();
                System.out.println(t);
                if(i == 1){
                    pool.returnT(t);
                }
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }
        }
        System.out.println("-------------------------");
    }
}
