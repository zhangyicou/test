package org.zhangyc.test.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yichu.zhang
 * @Date: 2019-09-17 11:01
 */
public class Test {
    public static void main(String[] args) {
        ReceiveDbData data = new ReceiveDbData();
        List<Integer> orderEventList = new ArrayList<>();
        List<Long> commandIdList = new ArrayList<>();
        List<Long> sequenceSyncList = new ArrayList<>();

        data.setCommandIdList(commandIdList);
        data.setOrderEventList(orderEventList);
        data.setSequenceSyncList(sequenceSyncList);

        for(int i = 0; i < 1000; i++){
            orderEventList.add(i);
            commandIdList.add(10000L+i);
            sequenceSyncList.add(i * 1L);
        }

        data.getOrderEventList().removeIf(item -> item%2 == 0);

        data.getOrderEventList().forEach(System.out::print);
        System.out.println();
        System.out.println(data.getOrderEventList().size());
        data.getCommandIdList().forEach(System.out::print);
        System.out.println();
        System.out.println(data.getCommandIdList().size());
        data.getSequenceSyncList().forEach(System.out::print);
        System.out.println();
        System.out.println(data.getSequenceSyncList().size());
    }
}
