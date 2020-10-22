package com.ydh.redsheep.thread.call;

import java.util.concurrent.Callable;

public class CallThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(10000);
        return 999;
    }

}
