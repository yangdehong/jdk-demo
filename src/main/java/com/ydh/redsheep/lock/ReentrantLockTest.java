package com.ydh.redsheep.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: yangdehong
 * @version: 2018/1/5.
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        //同时启动5个线程，去进行i++计算，看看实际结果
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                new ReentrantLockTest().print();
            }).start();
        }
    }

    private void print(){
        // 新建多线程锁
        Lock lock = new ReentrantLock();
        lock.lock();
        // 尝试获取锁
        if(lock.tryLock()) {
            // 当通过这个方法去获取锁时，如果线程正在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态
//            zk.lockInterruptibly();
            try{
                System.out.println("==========");
                //同时启动1000个线程，去进行i++计算，看看实际结果
                for (int i = 0; i < 100; i++) {
                    System.out.println(i);
                }
            }catch(Exception ex){

            }finally{
                lock.unlock();
            }
        }
    }

}