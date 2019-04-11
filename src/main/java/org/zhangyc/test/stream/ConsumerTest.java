package org.zhangyc.test.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {
    public static void main(String[] args) {
        ConsumerTest test = new ConsumerTest();
        List<String> list = new ArrayList<String>();
        for(int i = 1; i < 11; i++) {
            test.add(list::add, String.valueOf(i));
        }
        list.stream().forEach(a -> System.out.println(a));
    }

    public void add(Consumer<String> consumer, String str){
        consumer.accept(str);
    }
}
