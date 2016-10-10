package org.zhangyc.test.list.split;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16/7/14.
 */
public class Test {
    public static void main(String[] str){
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < 10000; i++){
            list.add(i);
        }

        List<List<Integer>> newlist = new ArrayList<List<Integer>>();
        for(int i = 0; i < list.size(); ){
            int last = i+200;
            System.out.println("i:"+i+"; last:"+last);
            newlist.add(list.subList(i, last));
            i += 200;
        }

        int sum = 0;
        for(int i = 0; i < newlist.size(); i++){
            System.out.println(sum+=newlist.get(i).size());
        }

        int i = 0;
        do{
            i++;
        }while(i==2);
        System.out.println(i);
    }
}
