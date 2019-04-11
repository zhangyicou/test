package org.zhangyc.test.list;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedMapTest {
    public static void main(String[] args) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for(int i = 1; i <= 10; i++){
            map.put(i, i);
        }

        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> entry = iterator.next();
            if(entry.getKey() % 2 == 0){
                iterator.remove();
            }
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
