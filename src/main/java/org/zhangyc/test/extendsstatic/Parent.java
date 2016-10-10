package org.zhangyc.test.extendsstatic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 16/8/27.
 */
public class Parent {
    protected static Map<String, String> map = new HashMap<String, String>();

    static {
        System.out.println("-----------");
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
    }
}
