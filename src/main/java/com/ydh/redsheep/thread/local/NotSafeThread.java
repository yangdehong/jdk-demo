package com.ydh.redsheep.thread.local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NotSafeThread implements Runnable {

    public static ThreadLocal<Number> value = new ThreadLocal<Number>() {};

    public static Number number = new Number();

    private int i;

    public NotSafeThread(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        //每个线程计数加一
        number.setNum(i);
        //将其存储到ThreadLocal中
        value.set(number);//不同线程，保存的是同一个number对象的引用，而不是每一个线程保存一个num对象。因此当number对象的值被其他的线程修改时，会导致当前线程中保存的值，也会发生变化
        //延时2秒
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        //输出num值
        System.out.println(value.get().getNum());
    }

    public static void main(String[] args) throws Exception {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            newCachedThreadPool.execute(new NotSafeThread(i));
        }
    }

}
