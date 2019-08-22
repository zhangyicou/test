package org.zhangyc.test.map;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: yichu.zhang
 * @Date: 2019-08-22 11:49
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();

        String key_prefix = "key";
        for(int i = 0; i < 1000; ){
            String str = key_prefix+"_"+i;
            linkedHashMap.put(str, str);
            i +=2;
        }

        Iterator<Map.Entry<String, String>> iterator = linkedHashMap.entrySet().iterator();
        while (iterator.hasNext()){
            System.out.println("key = "+iterator.next().getKey());
        }

        System.out.println("---------------");
        for(int i = 1; i < 1000;){
            String str = key_prefix+"_"+i;
            linkedHashMap.put(str, str);
            i +=2;
        }
        Iterator<Map.Entry<String, String>> iterator1 = linkedHashMap.entrySet().iterator();
        while (iterator1.hasNext()){
            System.out.println("key = "+iterator1.next().getKey());
        }

        System.out.println(linkedHashMap.keySet().stream().count());
        System.out.println(linkedHashMap.keySet().stream().anyMatch(key -> key.equalsIgnoreCase("key_997")));

        System.out.println(0xff);
    }
}
