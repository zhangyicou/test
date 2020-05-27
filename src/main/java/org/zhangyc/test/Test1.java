package org.zhangyc.test;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @Author: yichu.zhang
 * @Date: 2019-11-15 10:51
 */
public class Test1 {
    public static void main(String[] args) {
        int RINGBUFFER_SIZE = 4;
        Integer[] SYMBOL = {0, 1, 2, 4, 15, 20, 301, 302, 310, 86, 110, 111};

        int LENGTH = 128;

        System.out.println(1 << 15);
        Long[] USER_IDS = new Long[LENGTH];
        Long user_id = 1L;
        IntStream.range(0, LENGTH).forEach(index -> {
            USER_IDS[index] = user_id + index;
        });
//        IntStream.range(0, LENGTH).forEach(index -> {
////           // System.out.println("取模 index = "+index+" / "+(LENGTH / RINGBUFFER_SIZE)+" = " + (index % (LENGTH / RINGBUFFER_SIZE)));
////            System.out.println("取模 index = "+index+" % "+RINGBUFFER_SIZE+" 一维index = " + (index % RINGBUFFER_SIZE) + " 二维index = " + (index % (LENGTH / RINGBUFFER_SIZE)));
////        });

        Arrays.asList(USER_IDS).forEach(userId -> {
            Arrays.asList(SYMBOL).forEach(symbol -> {
                long first1 = calFirstModulo(symbol, userId, SYMBOL.length, LENGTH);
                System.out.println("userId = " + userId + ", symbol = " + symbol + ", first1 = " + first1);

                //System.out.println("userId = " + userId + ", symbol = " + symbol + ", first1 = " + first1  + ", first2 = " + calSecondModulo(symbol, userId, LENGTH, first1 ));
            });
        });
    }

    private static long calFirstModulo(Integer symbol, Long userId, int symbolSize, int first_length){
        return (userId.hashCode() + symbol * symbolSize) % first_length;
    }

    private static long calSecondModulo(Integer symbol, Long userId, int second_length, long first_index){
        return (userId + symbol.hashCode()) * first_index % Integer.hashCode(second_length);
    }
}
