package org.zhangyc.test.extendsstatic;

/**
 * Created by user on 16/8/27.
 */
public class Child extends Parent {
    private static void println(String key){
        System.out.println(map.get(key));
    }

    public static void main(String[] args){
        println("1");
    }
}
