package org.zhangyc.test.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: yichu.zhang
 * @Date: 2019-08-13 17:32
 */
public class RemoveIfTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
        list.removeIf(e -> e % 2 == 0);
        list.stream().forEach(e -> System.out.println(e));
    }
}
