package com.ydh.redsheep.thread.pool.mypoll;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: yangdehong
 * @version: 2018/1/19.
 */
public class ThreadPoolExecutorTest {

    public static final int MAX = 10;

    public static void main(String[] args) {
        new ThreadPoolExecutorTest().testCachePool();
        new ThreadPoolExecutorTest().testFixedPool();
        new ThreadPoolExecutorTest().testScheduledPool();
        new ThreadPoolExecutorTest().testSinglePool();

    }

    /**
     * 创建一个定长线程池，支持定时及周期性任务执行。
     */
    private void testSinglePool() {
        ExecutorService singleThreadExecutor = MyExecutors.newSingleThreadExecutor("CachePool");
        for (int i = 0; i < MAX; i++) {
            final int index = i;
            singleThreadExecutor.execute(() -> {
                try {
                    System.out.println(index);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 创建一个定长线程池，支持定时及周期性任务执行。
     */
    private void testScheduledPool() {
        // 定时执行
        ScheduledExecutorService scheduledThreadPool = MyExecutors.newScheduledThreadPool(5, "CachePool");
        scheduledThreadPool.schedule(() ->
                        System.out.println("delay 3 seconds"),
                3, TimeUnit.SECONDS);
        // 循环执行
        scheduledThreadPool.scheduleAtFixedRate(() ->
                        System.out.println("delay 1 seconds, and excute every 3 seconds"),
                1, 3, TimeUnit.SECONDS);
    }

    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     * 因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
     */
    private void testFixedPool() {
        ExecutorService fixedThreadPool = MyExecutors.newFixedThreadPool(3, "CachePool");
        for (int i = 0; i < MAX; i++) {
            final int index = i;
            fixedThreadPool.execute(() -> {
                try {
                    System.out.println(index);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        fixedThreadPool.shutdown();
    }

    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
     */
    private void testCachePool() {
        ExecutorService cachedThreadPool = MyExecutors.newCachedThreadPool("CachePool");
        for (int i = 0; i < MAX; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(() -> System.out.println(index)
            );
        }
    }

}
