package org.zhangyc.test.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: yichu.zhang
 * @Date: 2020-02-24 12:48
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String, List<Integer>> map = new ConcurrentHashMap<>();
        List list = map.getOrDefault("aa", new ArrayList<>());
        System.out.println("list.size = " + list.size());
        list.add(1);
        list.add(2);
        System.out.println("list.size = " + list.size());

        System.out.println("list.size = " + map.get("aa").size());
    }
}
