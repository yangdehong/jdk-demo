package com.ydh.redsheep.thread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
* runAsync异步的方式指派工作，但是没有返回值
* @author : yangdehong
* @date : 2021/2/28 22:04
*/
public class CompletableFutureDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture =
                CompletableFuture.runAsync(() -> {
                    try {
                        Thread.sleep(2000);
                        System.out.println("任务执行完成");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        // 阻塞，等待任务执行完成
        voidCompletableFuture.get();
        System.out.println("程序运行结束");
    }
}
