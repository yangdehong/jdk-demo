package com.ydh.redsheep.thread.create;

import com.ydh.redsheep.thread.MyThread;

/**
*
* @author : yangdehong
* @date : 2019/2/13 10:59
*/
public class CreateThread {
    public static void main(String[] args) {
        MyThread m1 = new MyThread("Window 1");
        MyThread m2 = new MyThread("Window 2");
        MyThread m3 = new MyThread("Window 3");
        m1.start();
        m2.start();
        m3.start();
    }
}
