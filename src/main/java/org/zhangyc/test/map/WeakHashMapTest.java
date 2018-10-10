package org.zhangyc.test.map;

import java.util.WeakHashMap;

/**
 * Created by zhang on 2018/9/21.
 */
public class WeakHashMapTest {
    public static void main(String[] args){
        WeakHashMap<String, String> weakHashMap1 = new WeakHashMap<String, String>(1000);
        WeakHashMap<String, String> weakHashMap2 = new WeakHashMap<String, String>(1000);
        int i = 1;
        while (true){
            System.out.println("weakHashMap1.size="+weakHashMap1.size() + "; weakHashMap2.size="+weakHashMap2.size());
            weakHashMap1.put(new String(String.valueOf(i)), new String(String.valueOf(i)));
            weakHashMap2.put(String.valueOf(i), String.valueOf(i));
            i++;
        }
    }
}
