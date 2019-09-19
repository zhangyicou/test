package org.zhangyc.test.lock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yichu.zhang
 * @Date: 2019-09-18 19:04
 */
public class ReentrantLockTest {
    public static void main(String[] args) throws Exception {
        EventLinkedHashMapQueue queue = new EventLinkedHashMapQueue(1);
        for(int i = 0; i < 3; i++){
            System.out.println(queue.add(i, new ArrayList<Event>(Arrays.asList(Event.builder().id(i).name("name-"+i).time(System.currentTimeMillis()).build())), 1, TimeUnit.SECONDS));
            System.out.println(queue.get(1, TimeUnit.SECONDS).toString());
        }
    }
}
