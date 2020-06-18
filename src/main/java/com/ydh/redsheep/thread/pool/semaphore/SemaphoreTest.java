package com.ydh.redsheep.thread.pool.semaphore;

import com.ydh.redsheep.thread.pool.mypoll.MyExecutors;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

/**
 * @description: 一个计数信号量。
 * 从概念上讲，信号量维护了一个许可集合。如有必要，在许可可用前会阻塞每一个 acquire()，然后再获取该许可。
 * 每个 release() 添加一个许可，从而可能释放一个正在阻塞的获取者。
 * 但是，不使用实际的许可对象，semaphore 只对可用许可的号码进行计数，并采取相应的行动。
 * semaphore 通常用于限制可以访问某些资源（物理或逻辑的）的线程数目。
 *
 * 例如，下面的类使用信号量控制对内容池的访问：
 * 这里是一个实际的情况，大家排队上厕所，厕所只有两个位置，来了10个人需要排队。
 * @author: yangdehong
 * @version: 2018/3/11.
 */
public class SemaphoreTest {

    public static final int PERMITS = 2;
    public static final int MAX = 10;

    public static void main(String[] args) {
        new SemaphoreTest().testCachePool();
    }

    private void testCachePool() {
        ExecutorService cachedThreadPool = MyExecutors.newCachedThreadPool("semaphore");
        Semaphore position = new Semaphore(PERMITS);
        for (int i = 0; i < MAX; i++) {
            final int index = i;
            cachedThreadPool.execute(() -> {
                try {
                    if (position.availablePermits() > 0) {
                        System.out.println("顾客[" + index + "]进入厕所，有空位");
                    } else {
                        System.out.println("顾客[" + index + "]进入厕所，没空位，排队");
                    }
                    // 从信号量中获取一个允许机会，没有的话会阻塞
                    position.acquire();
                    System.out.println("顾客[" + index + "]获得坑位");
                    Thread.sleep(new Random().nextInt(10000));
                    System.out.println("顾客[" + index + "]使用完毕");
                    // 释放允许，将占有的信号量归还
                    position.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        cachedThreadPool.shutdown();
        position.acquireUninterruptibly(PERMITS);
        System.out.println("使用完毕，需要清扫了");
        position.release(PERMITS);
    }


}
