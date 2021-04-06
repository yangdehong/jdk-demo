package com.ydh.redsheep.thread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * 对于 Future，在提交任务之后，只能调用 get()等结果返回;但对于 CompletableFuture，可以在
 * 结果上面再加一个callback，当得到结果之后，再接着执行callback。thenRun、thenAccept和thenApply
 *
 * @author : yangdehong
 * @date : 2021/2/28 22:08
 */
public class CompletableFutureDemo4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        thenRun();
//        thenAccept();
        thenApply();
    }

    /**
     * thenRun后面跟的是一个无参数、无返回值的方法，即Runnable，所以最终的返回值是 CompletableFuture<Void>类型。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {

                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "这是结果";
        }).thenRun(() -> {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务执行结束之后执行的语句");
        });
        // 阻塞等待任务执行完成
        voidCompletableFuture.get();
        System.out.println("任务执行结束");
    }

    /**
     * thenAccept后面跟的是一个有参数、无返回值的方法，称为Consumer，返回值也是 CompletableFuture<Void>类型。顾名思义，只进不出，
     * 所以称为Consumer;前面的 Supplier，是无参数，有返回值，只出不进，和Consumer刚好相反。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() ->
        {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("返回中间结果");
            return "这是中间结果";
        }).thenAccept((param) -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务执行后获得前面的中间结果:" + param);
        });
        // 阻塞等待任务执行完成
        future.get();
        System.out.println("任务执行完成");
    }

    /**
     * thenApply 后面跟的是一个有参数、有返回值的方法，称为Function。返回值是 CompletableFuture<String>类型。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()
                -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("返回中间结果");
            return "abcdefg";
        }).thenApply(new Function<String, Integer>() {
            @Override
            public Integer apply(String middle) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("获取中间结果，再次计算返回");
                return middle.length();
            }
        });
        Integer integer = future.get();
        System.out.println("最终的结果为:" + integer);
    }

}
