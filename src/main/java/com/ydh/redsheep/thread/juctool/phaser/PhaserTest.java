package com.ydh.redsheep.thread.juctool.phaser;

import com.ydh.redsheep.thread.pool.mypoll.MyExecutors;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Phaser;

/**
* 代替CountDownLatch
* @author : yangdehong
* @date : 2021/2/28 12:15
*/
public class PhaserTest {

    public static final int MAX = 10;

    public static void main(String[] args) throws InterruptedException {

        final Phaser phaser = new Phaser(10);
        // 10名选手
        final ExecutorService exec = MyExecutors.newFixedThreadPool(10, "countdownlatch");

        for (int index = 0; index < MAX; index++) {
            final int no = index + 1;
            exec.execute(() -> {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                    System.out.println("No." + no + " arrived");
                } catch (InterruptedException e) {
                } finally {
                    phaser.arrive();
                }
            });
        }
        System.out.println("Game Start");
        phaser.awaitAdvance(phaser.getPhase());
        System.out.println("Game Over");
        exec.shutdown();
    }

}
