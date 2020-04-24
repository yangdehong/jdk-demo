package com.ydh.redsheep.thread.create;

import com.ydh.redsheep.thread.MyThread2;

/**
*
* @author : yangdehong
* @date : 2019/2/13 10:59
*/
public class CreateThread2 {
	public static void main(String[] args) {
		MyThread2 m1 = new MyThread2("Window 1");
		MyThread2 m2 = new MyThread2("Window 2");
		MyThread2 m3 = new MyThread2("Window 3");
		Thread t1 = new Thread(m1);
		Thread t2 = new Thread(m2);
		Thread t3 = new Thread(m3);
		t1.start();
		t2.start();
		t3.start();
	}
}
