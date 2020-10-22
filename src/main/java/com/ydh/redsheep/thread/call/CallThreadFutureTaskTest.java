package com.ydh.redsheep.thread.call;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Callable+FutureTask
 */
public class CallThreadFutureTaskTest {

    public static void main(String[] args) {
        //第一种方式
        ExecutorService executor = Executors.newCachedThreadPool();
        CallThread task = new CallThread();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        executor.submit(futureTask);
        executor.shutdown();

        //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
//        Task task = new Task();
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
//        Thread thread = new Thread(futureTask);
//        thread.start();
        try {
            System.out.println("主线程在执行任务");
            // 这里会阻塞，知道获取到结果
            Integer result = futureTask.get();
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
