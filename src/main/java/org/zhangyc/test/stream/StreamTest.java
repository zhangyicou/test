package org.zhangyc.test.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oker on 2019/1/18.
 */
public class StreamTest {
    public static void main(String[] args) {
        List<A> list = new ArrayList<A>();
        for(int i = 0; i < 2000; i++){
            A a = new A();
            a.setId(i);
            a.setName("name".concat(String.valueOf(i)));
            list.add(a);
        }

        long a1 = System.currentTimeMillis();
        list.stream().forEach(a -> System.out.print(a.getId()));
        System.out.println("streat.time="+(System.currentTimeMillis() - a1));

        long a2 = System.currentTimeMillis();
        for(A a : list){
            System.out.print(a.getId());
        }
        System.out.println("for.time="+(System.currentTimeMillis() - a2));
    }


}

class A{
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
