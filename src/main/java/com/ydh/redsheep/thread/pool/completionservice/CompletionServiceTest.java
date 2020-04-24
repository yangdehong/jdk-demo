package com.ydh.redsheep.thread.pool.completionservice;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @description: 将生产新的异步任务与使用已完成任务的结果分离开来的服务。
 * 生产者 submit 执行的任务。使用者 take 已完成的任务，并按照完成这些任务的顺序处理它们的结果。
 * 例如，completionservice 可以用来管理异步 IO ，执行读操作的任务作为程序或系统的一部分提交，
 * 然后，当完成读操作时，会在程序的不同部分执行其他操作，执行操作的顺序可能与所请求的顺序不同。
 * 通常，completionservice 依赖于一个单独的 Executor 来实际执行任务，在这种情况下，completionservice 只管理一个内部完成队列。
 * ExecutorCompletionService 类提供了此方法的唯一实现。
 * @author: yangdehong
 * @version: 2018/3/11.
 */
public class CompletionServiceTest {

    public static final int MAX = 10;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        // 构建完成服务
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);
        //构建10个生成者
        for (int i = 0; i < MAX; i++) {
            completionService.submit(new Producer());
        }
        //利用主线程作为一个消费者
        for (int i = 0; i < MAX; i++) {
            try {
                int result = completionService.take().get();
                System.out.println(Thread.currentThread().getName() + " Comsumer:" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        executor.shutdown();
    }

    /**
    * callable类型的生产者
    * @author : yangdehong
    * @date : 2019/2/13 10:55
    */
    static class Producer implements Callable<Integer> {
        @Override
        public Integer call() {
            int ran = 0;
            try {
                //休眠一段时间
                ran = new Random().nextInt(1000);
                Thread.sleep(ran);
                System.out.println(Thread.currentThread().getName() + " Produce:" + ran);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return ran;
        }

    }

//    public static void main(String[] args) throws Exception {
//        ExecutorService service = Executors.newCachedThreadPool();
//        completionservice<String> completion = new ExecutorCompletionService<>(service);
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            completion.submit(()->{
//                Integer time = (int) (Math.random() * 10000);
//                try {
//                    System.out.println(index + " start");
//                    Thread.sleep(time);
//                    System.out.println(index + " end");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return index + ":" + time;
//            });
//        }
//        for (int i = 0; i < 10; i++) {
//            System.out.println(completion.take().get());
//        }
//        service.shutdown();
//    }

}
