package org.zhangyc.test.stream;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectoresTest {
    public static void main(String[] args) {
        // 将Stream转换成List或Set
        Stream<String> stream = Stream.of("I", "love", "you", "too");

        List<String> stringList = stream.collect(Collectors.toList()); // (1)
        System.out.println(JSON.toJSONString(stringList));//["I","love","you","you","too"]

        //返回的是一个String，不是List<String>

        String string1 = stringList.stream().collect(Collectors.joining());
        System.out.println(string1);//Iloveyouyoutoo

        String string2 = stringList.stream().collect(Collectors.joining(" "));
        System.out.println(string2);//I love you you too

        String string3 = stringList.stream().collect(Collectors.joining(" ", "{", "}"));
        System.out.println(string3);//{I,love,you,you,too}

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("C");
        Set<String> stringSet2 = list.stream().collect(Collectors.toSet());
        System.out.println(JSON.toJSONString(stringSet2));//["A","B","C"]

        //------------------------------------------------------------------------
        Subject subject1 = new Subject(1, "语文", 91.0);
        Subject subject2 = new Subject(2, "数学", 94.0);
        Subject subject3 = new Subject(3, "英语", 92.0);
        Subject subject4 = new Subject(4, "语文", 90.0);
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(subject1);
        subjectList.add(subject2);
        subjectList.add(subject3);
        subjectList.add(subject4);

        Map<Integer, String> map1 = subjectList.stream().collect(Collectors.toMap(Subject::getId, Subject::getName));
        System.out.println(JSON.toJSONString(map1));//{1:"语文",2:"数学",3:"英语",4:"语文"}

        Map<Integer, Subject> map2 = subjectList.stream().collect(Collectors.toMap(Subject::getId, Function.identity()));
        System.out.println(JSON.toJSONString(map2));//{1:{"id":1,"name":"语文","score":91},2:{"id":2,"name":"数学","score":94},3:{"id":3,"name":"英语","score":92},4:{"id":4,"name":"语文","score":90}}

        Map<String, Double> map3 = subjectList.stream().collect(Collectors.toMap(Subject::getName, Subject::getScore, (oldValue, newValue) -> newValue));
        System.out.println(JSON.toJSONString(map3));//{"数学":94,"语文":90,"英语":92}

        Map<Integer, Subject> map4 = subjectList.stream().collect(Collectors.toMap(Subject::getId, Function.identity(), (oldValue, newValue) -> newValue));
        System.out.println(JSON.toJSONString(map4));//{1:{"id":1,"name":"语文","score":91},2:{"id":2,"name":"数学","score":94},3:{"id":3,"name":"英语","score":92},4:{"id":4,"name":"语文","score":90}}

        Map<Integer, Subject> map5 = subjectList.stream().collect(Collectors.toMap(Subject::getId, Function.identity()));
        System.out.println("map5="+JSON.toJSONString(map5));

        //这里的key必须是boolean型
        //按照某个条件是否成立分为两组，value是list
        Map<Boolean, List<Subject>> stringListMap1 = subjectList.stream().collect(Collectors.partitioningBy(s -> s.getName().equals("语文")));
        System.out.println(JSON.toJSONString(stringListMap1));
        //{false:[{"grade":94.0,"id":2,"name":"数学"},{"grade":92.0,"id":3,"name":"英语"}],true:[{"grade":91.0,"id":1,"name":"语文"},{"grade":90.0,"id":4,"name":"语文"}]}

        //按照某个条件分为多组，value是list
        Map<String, List<Subject>> stringListMap2 = subjectList.stream().collect(Collectors.groupingBy(Subject::getName));//这里只能是Subject::getName，如果写成subject.getName()则报错
        System.out.println(JSON.toJSONString(stringListMap2));
        //{"数学":[{"grade":94.0,"id":2,"name":"数学"}],"语文":[{"grade":91.0,"id":1,"name":"语文"},{"grade":90.0,"id":4,"name":"语文"}],"英语":[{"grade":92.0,"id":3,"name":"英语"}]}

        //使用parallelStream是多管道处理，效率比stream高很多
        Map<String, List<Subject>> stringListMap5 = subjectList.parallelStream().collect(Collectors.groupingBy(Subject::getName));//这里只能是Subject::getName，如果写成subject.getName()则报错
        System.out.println(JSON.toJSONString(stringListMap5));
        //{"数学":[{"grade":94.0,"id":2,"name":"数学"}],"语文":[{"grade":91.0,"id":1,"name":"语文"},{"grade":90.0,"id":4,"name":"语文"}],"英语":[{"grade":92.0,"id":3,"name":"英语"}]}

        //按某个条件统计，每种key存在多少个总数
        Map<String, Long> stringListMap3 = subjectList.stream().collect(Collectors.groupingBy(Subject::getName, Collectors.counting()));
        System.out.println(JSON.toJSONString(stringListMap3));
        //{"数学":1,"语文":2,"英语":1}

        //按某个条件统计，key的某个属性的列表
        Map<String, List<Double>> stringListMap4 = subjectList.stream().collect(Collectors.groupingBy(Subject::getName,
                Collectors.mapping(Subject::getScore, Collectors.toList())));
        System.out.println(JSON.toJSONString(stringListMap4));
        //{"数学":[94.0],"语文":[91.0,90.0],"英语":[92.0]}


        //------------------------------------------------------------------------
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        //串行输出
        numbers.stream().forEach(System.out::print);//123456789

        System.out.println();

        //并行输出
        numbers.parallelStream().forEach(System.out::print);//658973421

        System.out.println();

        //并行有序输出
        numbers.parallelStream().forEachOrdered(System.out::print);//123456789
    }
}
