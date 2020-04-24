package com.ydh.redsheep.test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SteamTest {

    public static void main(String[] args) {
        steamTest();
//        optionalTest();
    }

    public static void optionalTest() {
        Optional o = Optional.of("test");
        System.out.println(o.isPresent());
        System.out.println(o.get());
    }

    public static void steamTest() {
        List<String> list = Arrays.asList("a", "b", "c d", "", "a d");
//        // 获取串行的Stream对象
//        list.stream().forEach(item -> {
//            System.out.println(item);
//        });
//        // 获取并行的Stream对象
//        list.parallelStream().forEach(item -> {
//            System.out.println(item);
//        });
        // limit-指定数量，sorted-排序
        List<String> collect = list.stream().sorted((s1, s2) -> {
            return s1.compareTo(s2);
        }).collect(Collectors.toList());
        System.out.println(collect);
//        // 条件过滤
//        list.stream().filter(item -> item.contains("a")).forEach(System.out::println);
//        // map用于映射每个元素到对应的结果
//        list.stream().map(s -> s + "test").forEach(System.out::println);
//        // flatMap，同样的代码和map比较结果就知道了
//        list.stream().flatMap(s -> Stream.of(s.split(" "))).forEach(System.out::println);

    }

}
