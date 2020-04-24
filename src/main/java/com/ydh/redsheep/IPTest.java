package com.ydh.redsheep;

import java.util.HashSet;
import java.util.Set;

/**
* ip二进制回文
* @author : yangdehong
* @date : 2020-04-24 18:26
*/
public class IPTest {

    public static void main(String[] args) throws Exception {
        Integer begin = 1;
        Integer end = 256;

        for (int i=begin; i<end; i++) {
            for (int j=begin; j<end; j++) {
                int a = i;
                int b = j;
                int c = reverse(j);
                int d = reverse(i);

                Set<Integer> set = new HashSet<>();
                isEveryNumber(set, a);
                isEveryNumber(set, b);
                isEveryNumber(set, c);
                isEveryNumber(set, d);
                if (set.size() == 10) {
                    System.out.println("源码：[("+a+","+b+"),("+c+","+d+")]");
                    System.out.println("二进制：[("+getRealVal(a)+","+getRealVal(b)
                            +"),("+getRealVal(c)+","+getRealVal(d)+")]");
                }
            }
        }

    }

    private static String getRealVal(int value) {
       return String.format("%08d", Long.parseLong(Integer.toBinaryString(value)));
    }

    private static void isEveryNumber(Set<Integer> set, Integer value){

        int v1 = get1(value);
        set.add(v1);
        int v10 = get10(value);
        set.add(v10);
        int v100 = get100(value);
        if (v100>0) {
            set.add(v100);
        }

    }

    private static Integer get1(Integer value) {
        return value%10;
    }

    private static Integer get10(Integer value) {
        return (value/10)%10;
    }

    private static Integer get100(Integer value) {
        return value/100;
    }

    /**
     * 二进制倒置
     * @param n
     * @return
     */
    private static int reverse(Integer n) {
        int sum=0;
        for (int i=0; i<8; i++) {
            int num=n>>i;
            num &=1;
            num<<=(7-i);
            sum|=num;
        }
        return sum;
    }

}
