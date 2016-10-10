package org.zhangyc.test.countdown;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by user on 16/7/6.
 */
public class Entity {
    private static Entity entity = new Entity();

    public static Entity getInstance(){
        return entity;
    }

    private Map<String, HashSet<Object>> map = new HashMap<String, HashSet<Object>>();

    public void put(String key, HashSet<Object> set){
        map.put(key, set);
    }

    public HashSet get(String key){
        return map.get(key);
    }
}
