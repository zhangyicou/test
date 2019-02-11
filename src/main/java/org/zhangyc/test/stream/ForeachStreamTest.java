package org.zhangyc.test.stream;

import com.google.common.primitives.Ints;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by user on 16/7/11.
 */
public class ForeachStreamTest {
    public static void main(String args[]){
        int[] products = new int[]{
            596, 199, 264, 465, 646, 404, 354, 364,
            250, 301, 974, 409, 345, 418, 366, 440,
            876, 828, 843, 730, 706, 895, 971, 758,
            759, 747, 851, 755, 771, 866, 756, 806
        };
        System.out.println("-------------------------------------");
        List<Integer> intList = Ints.asList(products);
        System.out.println(intList);

        intList.stream().forEach(a -> {
            if(a  < 400){
                return;
            }
            System.out.print(a + ", ");
        });
        System.out.println("-------------------------------------");
        for(int a = 0; a < intList.size(); a++){
            int value = intList.get(a);
            if(value  < 400){
                return;
            }
            System.out.print(value + ", ");
        }
    }
}
