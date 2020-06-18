package com.ydh.redsheep;

import java.util.Iterator;
import java.util.TreeSet;

public class Test {

    public static void main(String[] args) throws Exception {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        treeSet.add(5);
        treeSet.add(5);
        treeSet.add(4);
        treeSet.add(3);
        treeSet.add(2);
        treeSet.add(6);
        treeSet.add(7);

        Iterator<Integer> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }



}
