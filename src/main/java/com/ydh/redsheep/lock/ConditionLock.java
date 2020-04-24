package com.ydh.redsheep.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
* 条件锁
* @author : yangdehong
* @date : 2019-10-28 14:02
*/
public class ConditionLock {

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("111111");
                condition.await();
                System.out.println("222222");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 通知结束等待
        System.out.println("333333");
        lock.lock();
        condition.signal();
        lock.unlock();
        System.out.println("444444");


    }

}
