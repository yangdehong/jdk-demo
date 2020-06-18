package com.ydh.redsheep.thread.pool.countdownlatch;

import com.ydh.redsheep.thread.pool.mypoll.MyExecutors;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @description: 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 * 用给定的计数 初始化 countdownlatch。由于调用了 countDown() 方法，所以在当前计数到达零之前，await 方法会一直受阻塞。
 * 之后，会释放所有等待的线程，await 的所有后续调用都将立即返回。这种现象只出现一次——计数无法被重置。
 * 如果需要重置计数，请考虑使用 cyclicbarrier。
 * countdownlatch 是一个通用同步工具，它有很多用途。将计数 1 初始化的 countdownlatch 用作一个简单的开/关锁存器，
 * 或入口：在通过调用 countDown() 的线程打开入口前，所有调用 await 的线程都一直在入口处等待。
 * 用 N 初始化的 countdownlatch 可以使一个线程在 N 个线程完成某项操作之前一直等待，或者使其在某项操作完成 N 次之前一直等待。
 * countdownlatch 的一个有用特性是，它不要求调用 countDown 方法的线程等到计数到达零时才继续，
 * 而在所有线程都能通过之前，它只是阻止任何线程继续通过一个 await。
 * @author: yangdehong
 * @version: 2018/3/11.
 */
public class CountDownLatchTest {

    public static final int MAX = 10;

    public static void main(String[] args) throws InterruptedException {

        // 开始的倒数锁
//        final countdownlatch begin = new countdownlatch(1);
        // 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(10);
        // 10名选手
        final ExecutorService exec = MyExecutors.newFixedThreadPool(10, "countdownlatch");

        for (int index = 0; index < MAX; index++) {
            final int no = index + 1;
            exec.execute(() -> {
                try {
//                    begin.await();//一直阻塞
                    Thread.sleep(new Random().nextInt(100));
                    System.out.println("No." + no + " arrived");
                } catch (InterruptedException e) {
                } finally {
                    end.countDown();
                }
            });
        }
        System.out.println("Game Start");
//        begin.countDown();
        end.await();
        System.out.println("Game Over");
        exec.shutdown();
    }

}
