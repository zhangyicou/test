package org.zhangyc.test.stream;

import java.util.HashMap;

/**
 * @Author: yichu.zhang
 * @Date: 2019-08-22 17:17
 */
public class AllMatchTest {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 5);
        map.put("b", 5);
        map.put("c", 5);
        map.put("d", 5);
        map.put("e", 5);

        final HashMap<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("a", 5);
        map1.put("b", 5);
        map1.put("c", 5);
        map1.put("d", 4);
        map1.put("e", 5);

        boolean result = map.entrySet().stream().allMatch(entry -> map1.get(entry.getKey()) >= entry.getValue());
        System.out.println("result = " + result);
    }
}
