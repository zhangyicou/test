package org.zhangyc.test.random;

import java.util.Random;

/**
 * Created by user on 16/9/19.
 */
public class Test {
    public static void main(String[] args){
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            System.out.println(random.nextInt(2));
        }
    }
}
