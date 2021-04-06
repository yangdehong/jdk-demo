package com.ydh.redsheep.thread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureDemo7 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        thenCompose();
        thenCombine();
    }

    /**
     * 如果希望返回值是一个非嵌套的CompletableFuture，可以使用thenCompose:
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello world";
            }
        }).thenCompose(new Function<String, CompletionStage<Integer>>() {
            @Override
            public CompletionStage<Integer> apply(String s) {
                return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        return s.length();
                    }
                });
            }
        });
        Integer integer = future.get();
        System.out.println(integer);
    }

    /**
     * 2个输入参数，1个返回值;BiFunction是在签名两个CompletableFuture执行完成之后再次返回结果
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello";
            }
        }).thenCombine(CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "word";
            }
        }), new BiFunction<String, String, Integer>() {
            @Override
            public Integer apply(String s, String s2) {
                return s.length() + s2.length();
            }
        });
        Integer result = future.get();
        System.out.println(result);
    }

}
