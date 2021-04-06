package com.ydh.redsheep.thread.juctool.phaser;

import com.ydh.redsheep.thread.juctool.cyclicbarrier.CyclicBarrierTest;
import com.ydh.redsheep.thread.pool.mypoll.MyExecutors;

import java.time.LocalDateTime;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Phaser;

/**
* 代替CyclicBarrier
* @author : yangdehong
* @date : 2021/2/28 12:15
*/
public class PhaserTest2 {

    /**
     * 徒步需要的时间: Shenzhen, Guangzhou, Shaoguan, Changsha, Wuhan
     */
    private static int[] timeWalk = {5, 8, 15, 15, 10};
    /**
     * 自驾游
     */
    private static int[] timeSelf = {1, 3, 4, 4, 5};
    /**
     * 旅游大巴
     */
    private static int[] timeBus = {2, 4, 6, 6, 7};

    static class Tour implements Runnable {
        private int[] times;
        private Phaser barrier;
        private String tourName;

        public Tour(Phaser barrier, String tourName, int[] times) {
            this.times = times;
            this.tourName = tourName;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(times[0] * 1000);
                System.out.println(LocalDateTime.now() + tourName + " Reached Shenzhen");
                barrier.arrive();
                Thread.sleep(times[1] * 1000);
                System.out.println(LocalDateTime.now() + tourName + " Reached Guangzhou");
                barrier.arrive();
//                Thread.sleep(times[2] * 1000);
//                System.out.println(LocalDateTime.now() + tourName + " Reached Shaoguan");
//                barrier.await();
//                Thread.sleep(times[3] * 1000);
//                System.out.println(LocalDateTime.now() + tourName + " Reached Changsha");
//                barrier.await();
//                Thread.sleep(times[4] * 1000);
//                System.out.println(LocalDateTime.now() + tourName + " Reached Wuhan");
//                barrier.await();
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        // 三个旅行团
        Phaser barrier = new Phaser(3);
        ExecutorService exec = MyExecutors.newFixedThreadPool(3, "cyclicbarrier");
        exec.submit(new Tour(barrier, "WalkTour", timeWalk));
        exec.submit(new Tour(barrier, "SelfTour", timeSelf));
        //当我们把下面的这段代码注释后，会发现，程序阻塞了，无法继续运行下去。
        exec.submit(new Tour(barrier, "BusTour", timeBus));
        exec.shutdown();
    }

}
