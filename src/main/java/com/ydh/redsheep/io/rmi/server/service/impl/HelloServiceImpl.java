package com.ydh.redsheep.io.rmi.server.service.impl;

import com.ydh.redsheep.io.rmi.pojo.User;
import com.ydh.redsheep.io.rmi.server.service.HelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    // 手动实现父类的构造方法
    public HelloServiceImpl() throws RemoteException {
        super();
    }

    // 我们自定义的sayHello
    @Override
    public String sayHello(User user) throws RemoteException {
        System.out.println("this is server , say hello to "+user.getUsername());
        return "success";
    }
}
