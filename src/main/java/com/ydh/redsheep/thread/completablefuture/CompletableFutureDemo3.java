package com.ydh.redsheep.thread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
* runAsync的基础上加了一个返回值
* @author : yangdehong
* @date : 2021/2/28 22:05
*/
public class CompletableFutureDemo3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "这是结果";
            }
        });
        String result = future.get();
        System.out.println("任务执行结果:" + result);
    }

}
