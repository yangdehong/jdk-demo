package com.ydh.redsheep.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock是为了优化可重入读写锁性能的一个锁实现工具，jdk8开始引入。相比于普通的ReentranReadWriteLock主要多了一种乐观读的功能，
 * 在API上增加了stamp的入参和返回值，且不支持重入
 *
 * @author : yangdehong
 * @date : 2020/11/30 13:52
 */
public class StampedLockTest {

    static ExecutorService service = Executors.newFixedThreadPool(10);
    static StampedLock lock = new StampedLock();
    static long milli = 5000;
    static int count = 0;

    public static void main(String[] args) {
        /**
         * 对于悲观读和悲观写的方法与ReentranReadWriteLock读写锁效果一样
         * 写被读锁阻塞了5秒
         */
//        long a = System.currentTimeMillis();
//        readLock();
//        long b = writeLock();
//        System.out.println(b - a); // 5067

        /**
         * 写没有被读锁阻塞，乐观读最终走了悲观读读取了最新值
         */
        long l1 = System.currentTimeMillis();
        optimisticRead();
        long l2 = writeLock();
        System.out.println(l2-l1); // 553

    }

    /**
     * 悲观写
     *
     * @return
     */
    private static long writeLock() {
        long stamp = lock.writeLock(); //获取排他写锁
        count += 1;
        lock.unlockWrite(stamp); //释放写锁
        System.out.println("数据写入完成");
        return System.currentTimeMillis();
    }

    /**
     * 悲观读
     */
    private static void readLock() {//普通的读锁
        service.submit(() -> {
            int currentCount = 0;
            long stamp = lock.readLock(); //获取排他读锁
            try {
                currentCount = count; //获取变量值
                try {
                    TimeUnit.MILLISECONDS.sleep(milli);//模拟读取需要花费20秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlockRead(stamp); //释放读锁
            }
            System.out.println("readLock==" + currentCount); //显示最新的变量值
        });
        try {
            TimeUnit.MILLISECONDS.sleep(500);//要等一等读锁先获得
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 乐观读
     */
    private static void optimisticRead() {
        service.submit(() -> {
            long stamp = lock.tryOptimisticRead(); //尝试获取乐观读锁
            int currentCount = count; //获取变量值
            try {
                TimeUnit.MILLISECONDS.sleep(milli);//模拟读取需要花费20秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!lock.validate(stamp)) { //判断count是否进入写模式
                stamp = lock.readLock(); //已经进入写模式，没办法只能老老实实的获取读锁
                try {
                    currentCount = count; //成功获取到读锁，并重新获取最新的变量值
                } finally {
                    lock.unlockRead(stamp); //释放读锁
                }
            }
            //走到这里，说明count还没有被写，那么可以不用加读锁，减少了读锁的开销
            System.out.println("optimisticRead==" + currentCount); //显示最新的变量值
        });
        try {
            TimeUnit.MILLISECONDS.sleep(500);//要等一等读锁先获得
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
