package com.ydh.redsheep.thread.pool.mypoll;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: yangdehong
 * @version: 2018/1/19.
 */
public class MyExecutors {

    public static ExecutorService newFixedThreadPool(int workerNum, String poolName) {
        return java.util.concurrent.Executors.newFixedThreadPool(workerNum, new MyThreadFactory(poolName));
    }

    public static ExecutorService newFixedThreadPool(int workerNum, int maxNum, String poolName) {

        return new java.util.concurrent.ThreadPoolExecutor(workerNum, maxNum,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new MyThreadFactory(poolName));
    }

    public static ScheduledExecutorService newScheduledThreadPool(int workerNum, String poolName) {
        return java.util.concurrent.Executors.newScheduledThreadPool(workerNum, new MyThreadFactory(poolName));
    }

    public static ExecutorService newCachedThreadPool(String poolName) {
        return java.util.concurrent.Executors.newCachedThreadPool(new MyThreadFactory(poolName));
    }

    public static ExecutorService newSingleThreadExecutor(String poolName) {
        return java.util.concurrent.Executors.newSingleThreadExecutor(new MyThreadFactory(poolName));
    }

}
