package com.ydh.redsheep.thread.call;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable+Future
 */
public class CallThreadFutureTest {

    public static void main(String[] args) {
        //创建线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        //创建Callable对象任务
        CallThread task = new CallThread();
        //提交任务并获取执行结果，
        // execute没有返回值，submit有返回值。可以提交Callable或者Runnable的实现类
        Future<Integer> future = executor.submit(task);
        try {
            System.out.println("主线程在执行任务");
            // 这里会阻塞，知道获取到结果
            Integer result = future.get();
            System.out.println("===="+result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            executor.shutdown();
        }
        System.out.println("所有任务执行完毕");
    }

}
