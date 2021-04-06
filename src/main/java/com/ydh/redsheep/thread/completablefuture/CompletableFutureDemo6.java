package com.ydh.redsheep.thread.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 组合任意多个CompletableFuture
 *
 * @author : yangdehong
 * @date : 2021/2/28 22:29
 */
public class CompletableFutureDemo6 {

    private static final Random RANDOM = new Random();
    private static volatile int result = 0;


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        allOf();
//        anyOf();
    }

    /**
     * allOf的返回值是CompletableFuture<Void>类型，这是因为每个传入的CompletableFuture的返回值都可能不同，
     * 所以组合的结果是无法用某种类型来表示的，索性返回Void类型。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void allOf() throws ExecutionException, InterruptedException {
        CompletableFuture[] futures = new CompletableFuture[10];
        for (int i = 0; i < 10; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 + RANDOM.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    result++;
                }
            });
            futures[i] = future;
        }
        System.out.println(result);
        Integer anyResult = CompletableFuture.allOf(futures).thenApply(new Function<Object, Integer>() {
            @Override
            public Integer apply(Object o) {
                return result;
            }
        }).get();
        System.out.println(anyResult);
    }

    /**
     * anyOf 的含义是只要有任意一个 CompletableFuture 结束，就可以做接下来的事情，而无须像 AllOf那样，等待所有的CompletableFuture结束。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void anyOf() throws ExecutionException, InterruptedException {
        CompletableFuture[] futures = new CompletableFuture[10];
        for (int i = 0; i < 10; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 + RANDOM.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    result++;
                }
            });
            futures[i] = future;
        }
        System.out.println(result);
        Integer anyResult = CompletableFuture.anyOf(futures).thenApply(new Function<Object, Integer>() {
            @Override
            public Integer apply(Object o) {
                return result;
            }
        }).get();
        System.out.println(anyResult);
    }

}
