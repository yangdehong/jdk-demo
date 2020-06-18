package com.ydh.redsheep.steam;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
*
* @author : yangdehong
* @date : 2020-01-02 19:52
*/
public class FunctionTest {

    public static void main(String[] args) {
//        consumerTest();
//        functionTest();
//        predicateTest();
        supplierTest();
    }

    /**
     * 无参数，返回一个结果
     */
    public static void supplierTest() {
        Supplier s = () -> "娃哈哈";
        System.out.println(s.get());
    }

    /**
     * 一个输入参数，返回一个布尔值结果
     */
    public static void predicateTest() {
        Predicate<String> p = o -> o.equals("steam");
        Predicate<String> g = o -> o.startsWith("t");

        System.out.println(p.test("steam"));

        /**
         * negate: 用于对原来的Predicate做取反处理；
         * 如当调用p.steam("steam")为True时，调用p.negate().steam("steam")就会是False；
         */
        System.out.println(p.negate().test("steam"));

        /**
         * and: 针对同一输入值，多个Predicate均返回True时返回True，否则返回False；
         */
        System.out.println(p.and(g).test("steam"));

        /**
         * or: 针对同一输入值，多个Predicate只要有一个返回True则返回True，否则返回False
         */
        System.out.println(p.or(g).test("ta"));
    }

    /**
     * 接受一个输入参数T，返回一个结果R
     */
    public static void functionTest() {
        Function<Integer, Integer> f = s -> ++s;
        Function<Integer, Integer> g = s -> s * 2;

        System.out.println(f.apply(2));

        /**
         * 下面表示在执行f时，先执行g，并且执行f时使用g的输出当作输入。
         * 相当于以下代码：
         * Integer a = g.apply(2);
         * System.out.println(f.apply(a));
         */
        System.out.println(f.compose(g).apply(2));
        /**
         * 表示执行f后再执行g的Apply；
         * 相当于以下代码
         * Integer a = f.apply(2);
         * System.out.println(g.apply(a));
         */
        System.out.println(f.andThen(g).apply(2));
        /**
         * identity方法会返回一个不进行任何处理的Function，即输出与输入值相等；
         */
        System.out.println(Function.identity().apply("a"));
    }

    /**
     * 接受一个输入参数并且无返回的操作
     */
    public static void consumerTest() {
        Consumer f = System.out::println;
        Consumer f2 = n -> System.out.println(n + "-F2");

        f.accept("steam");
        f.accept("=========");

        //执行完F后再执行F2的Accept方法
        f.andThen(f2).accept("steam");
        f.accept("=========");

        //连续执行F的Accept方法
        f.andThen(f).andThen(f2).andThen(f).accept("test1");
    }


}
