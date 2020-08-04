package com.ydh.redsheep.clazz;

/**
*
* @author : yangdehong
* @date : 2020-06-24 20:10
*/
public class Clazz {


    public static void main(String[] args) {
        String user = "com.ydh.redsheep.clazz.User";
        test(user);
    }

    public static void test(String user) {
        try {
            Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass(user);
            System.out.println(aClass);
            System.out.println("====================");
            Class<?> bClass = Class.forName(user);
            System.out.println(bClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
