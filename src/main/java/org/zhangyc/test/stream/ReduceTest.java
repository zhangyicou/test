package org.zhangyc.test.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: yichu.zhang
 * @Date: 2019-09-19 15:48
 */
public class ReduceTest {
    public static void main(String[] args) {

        List<Integer> numList = Arrays.asList(1);
        int result = numList.stream().reduce((a,b) -> a + b ).get();
        System.out.println(result);

        numList = Arrays.asList(1);
        result = numList.stream().reduce((a,b) -> {
            System.out.println("a = "+a +", b="+b);
            return a + b;
        } ).get();
        System.out.println(result);

        numList = Arrays.asList(1,2,3,4,5);
        result = numList.stream().reduce((a,b) -> a + b ).get();
        System.out.println(result);

        numList = Arrays.asList(1,2,3,4,5);
        result = numList.stream().reduce((a,b) -> {
            System.out.println("a = "+a +", b="+b);
            return a + b;
        } ).get();
        System.out.println(result);
    }
}
