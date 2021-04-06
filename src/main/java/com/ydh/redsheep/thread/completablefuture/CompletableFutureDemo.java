package com.ydh.redsheep.thread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
* 异步回调，同Future
* @author : yangdehong
* @date : 2021/2/28 22:00
*/
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future.complete("hello world");
        }).start();
        System.out.println("获取结果中。。。");
        String result = future.get();
        System.out.println("获取的结果:" + result);
    }
}