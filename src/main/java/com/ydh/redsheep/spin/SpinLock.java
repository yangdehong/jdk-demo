package com.ydh.redsheep.spin;

import java.util.concurrent.atomic.AtomicReference;

/**
* 自定义自旋锁
 * 缺点
 * 1、CAS操作需要硬件的配合；
 * 2、保证各个CPU的缓存（L1、L2、L3、跨CPU Socket、主存）的数据一致性，通讯开销很大，在多处理器系统上更严重；
 * 3、没法保证公平性，不保证等待进程/线程按照FIFO顺序获得锁。
* @author : yangdehong
* @date : 2020/11/26 20:24
*/
public class SpinLock {
    /**
     * owner属性持有锁当前拥有者的线程的引用，如果该引用为null，则表示锁未被占用，不为null则被占用。
     */
    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

    public void lock() {
        Thread currentThread = Thread.currentThread();

        // 如果锁未被占用，则设置当前线程为锁的拥有者
        while (!owner.compareAndSet(null, currentThread)) {
        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();

        // 只有锁的拥有者才能释放锁
        owner.compareAndSet(currentThread, null);
    }
}
