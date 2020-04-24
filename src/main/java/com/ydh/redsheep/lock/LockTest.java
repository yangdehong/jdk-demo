package com.ydh.redsheep.lock;

/**
 * @Auther: yangdehong
 * @Date: 2018/8/7 13:32
 * @Description:
 */
public class LockTest {

    public static LockTest lockTest = new LockTest();

    public static void main(String[] args) {
        Thread threadA = new Thread(new A());
        threadA.start();
        Thread threadB = new Thread(new B());
        threadB.start();
        Thread threadC = new Thread(new C());
        threadC.start();
    }

    public static synchronized void a(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("aaa");
    }

    public synchronized void b(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("bbb");
    }

    public synchronized void c(){
        System.out.println("ccc");
    }

}

class A implements Runnable {

    @Override
    public void run() {
        new LockTest().lockTest.a();
    }
}

class B implements Runnable {

    @Override
    public void run() {
        new LockTest().lockTest.b();
    }
}

class C implements Runnable {

    @Override
    public void run() {
        new LockTest().lockTest.c();
    }
}
