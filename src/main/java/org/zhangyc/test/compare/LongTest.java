package org.zhangyc.test.compare;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: yichu.zhang
 * @Date: 2019-12-23 19:45
 */
public class LongTest {
    public static void main(String[] args) {
        List<Long> list = Arrays.asList(1000L, 23L, 45L, 300L, 126L, 56L, 3L, 89L, 1234L, 3L);
        Collections.sort(list, new Comparator<Long>() {
            /**
             * 升序
             * @param o1
             * @param o2
             * @return
             */
            @Override
            public int compare(Long o1, Long o2) {
                return o1.compareTo(o2);


            }
        });
        list.forEach(index-> System.out.println(index));
    }
}
