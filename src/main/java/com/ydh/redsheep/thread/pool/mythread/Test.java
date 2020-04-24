package com.ydh.redsheep.thread.pool.mythread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @author: yangdehong
 * @version: 2018/1/19.
 */
public class Test {

    public static final int MAX = 10;

    public static void main(String[] args) {
        ExecutorService pool = MyExecutors.newFixedThreadPool(2, "volat");
        for (int i=0; i<MAX; i++) {
            final int index = i;
            pool.execute(() -> {
                try {
                    System.out.println(index);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            // 当前排队线程数
            int queueSize = ((ThreadPoolExecutor) pool).getQueue().size();
            // 当前活动线程数
            int activeCount = ((ThreadPoolExecutor) pool).getActiveCount();
            // 执行完成线程数
            long completedTaskCount = ((ThreadPoolExecutor) pool).getCompletedTaskCount();
            // 总线程数（排队线程数 + 活动线程数 +  执行完成线程数）
            long taskCount = ((ThreadPoolExecutor) pool).getTaskCount();
            System.out.println("=================");
            System.out.println("====="+queueSize);
            System.out.println("====="+activeCount);
            System.out.println("====="+completedTaskCount);
            System.out.println("====="+taskCount);
            i = 100;
        }
        pool.shutdown();
    }

}
