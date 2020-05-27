package org.zhangyc.test.stream;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: yichu.zhang
 * @Date: 2020-05-19 11:51
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(list1.stream().allMatch(index-> list1.indexOf(index) >= 0) && list1.size() == list2.size());

        List<Integer> list3 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list4 = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(list3.stream().allMatch(index-> list4.indexOf(index) >= 0) && list3.size() == list4.size());

        List<Integer> list5 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> list6 = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(list5.stream().allMatch(index-> list6.indexOf(index) >= 0) && list5.size() == list6.size());

        List<Integer> list7 = new ArrayList<>();
        list7.add(1);
        list7.add(2);
        list7.add(3);
        list7.add(4);
        list7.add(5);
        list7.add(6);
        list7.add(7);
        list7.add(8);
        list7.add(9);
        list7.add(10);
        System.out.println(new ListTest().getSwitchList(list7));
    }

    private List<Integer> getSwitchList(List<Integer> list){
        if(CollectionUtils.isEmpty(list)){
            return list;
        }

        Iterator<Integer> symbolItor = list.iterator();
        while (symbolItor.hasNext()){
            Integer symbol = symbolItor.next();
            if(symbol % 2 == 0){
                symbolItor.remove();
            }
        }

        return list;
    }
}
