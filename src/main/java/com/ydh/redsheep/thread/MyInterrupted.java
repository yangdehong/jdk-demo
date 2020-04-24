package com.ydh.redsheep.thread;

/**
 * @description:
 * @author: yangdehong
 * @version: 2017/11/6.
 */
public class MyInterrupted {

    public static void main(String[] args) {
        Thread t = new Thread(new MyInterruptedThread());
        t.start();
        System.out.println("is start.......");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        System.out.println("start interrupt..." + System.currentTimeMillis());
        t.interrupt();
        System.out.println("end interrupt ..." + System.currentTimeMillis());
    }
}

class MyInterruptedThread implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("我没有种中断");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("我已经结束了..." + System.currentTimeMillis());
    }
}
