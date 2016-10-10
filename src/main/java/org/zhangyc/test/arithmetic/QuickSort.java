package org.zhangyc.test.arithmetic;

import java.util.Random;

/**
 * Created by user on 16/7/7.
 */
public class QuickSort {
    public static void main(String args[]){
        int[] sort = new int[10];

        Random random = new Random(47);
        for(int i=0; i < sort.length; i++){
            sort[i] = random.nextInt(100);
        }

        for(int i=0; i < sort.length; i++){
            System.out.println("sort["+i+"]:"+sort[i]);
        }
    }

    private int[] sort(int[] ints){
        int index = ints.length/2;
        int A = ints[index];




        return ints;
    }
}
