package com.ydh.redsheep.thread;

/**
* 启动的线程类
* @author : yangdehong
* @date : 2019/2/13 10:56
*/
public class MyThread extends Thread {
    /**
     * 每个线程都拥有100张票
     */
    private int ticket = 100;

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (ticket > 0) {
            System.out.println(ticket-- + " is saled by "
                    + Thread.currentThread().getName());
        }
    }
}
