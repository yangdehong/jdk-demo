package com.ydh.redsheep;

import com.ydh.redsheep.bean.Person;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        String name = person.getClass().getName();
        Class<?> aClass = Class.forName(name);
        Class<?>[] classes = new Class[1];
        classes[0] = String.class;
        Method setName = aClass.getMethod("setName", classes);
        System.out.println(setName);
    }

}
