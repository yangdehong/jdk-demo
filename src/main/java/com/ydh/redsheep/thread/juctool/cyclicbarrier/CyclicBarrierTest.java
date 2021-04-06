package com.ydh.redsheep.thread.juctool.cyclicbarrier;

import com.ydh.redsheep.thread.pool.mypoll.MyExecutors;

import java.time.LocalDateTime;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;

/**
 * @description: 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。
 * 在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 cyclicbarrier 很有用。
 * 因为该 barrier 在释放等待线程后可以重用，所以称它为循环的 barrier。
 * cyclicbarrier 支持一个可选的 Runnable 命令，在一组线程中的最后一个线程到达之后（但在释放所有线程之前），
 * 该命令只在每个屏障点运行一次。若在继续所有参与线程之前更新共享状态，此屏障操作很有用。
 * <p>
 * 下面是一个在并行分解设计中使用 barrier 的例子，很经典的旅行团例子：
 * @author: yangdehong
 * @version: 2018/3/11.
 */
public class CyclicBarrierTest {

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
        private CyclicBarrier barrier;
        private String tourName;

        public Tour(CyclicBarrier barrier, String tourName, int[] times) {
            this.times = times;
            this.tourName = tourName;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(times[0] * 1000);
                System.out.println(LocalDateTime.now() + tourName + " Reached Shenzhen");
                barrier.await();
                Thread.sleep(times[1] * 1000);
                System.out.println(LocalDateTime.now() + tourName + " Reached Guangzhou");
                barrier.await();
//                Thread.sleep(times[2] * 1000);
//                System.out.println(LocalDateTime.now() + tourName + " Reached Shaoguan");
//                barrier.await();
//                Thread.sleep(times[3] * 1000);
//                System.out.println(LocalDateTime.now() + tourName + " Reached Changsha");
//                barrier.await();
//                Thread.sleep(times[4] * 1000);
//                System.out.println(LocalDateTime.now() + tourName + " Reached Wuhan");
//                barrier.await();
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
        }
    }

    public static void main(String[] args) {
        // 三个旅行团
        CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService exec = MyExecutors.newFixedThreadPool(3, "cyclicbarrier");
        exec.submit(new Tour(barrier, "WalkTour", timeWalk));
        exec.submit(new Tour(barrier, "SelfTour", timeSelf));
        //当我们把下面的这段代码注释后，会发现，程序阻塞了，无法继续运行下去。
        exec.submit(new Tour(barrier, "BusTour", timeBus));
        exec.shutdown();
    }

}
