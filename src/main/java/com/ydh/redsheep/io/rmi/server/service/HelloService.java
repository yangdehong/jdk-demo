package com.ydh.redsheep.io.rmi.server.service;


import com.ydh.redsheep.io.rmi.pojo.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloService extends Remote {

    // 1.定义一个sayHello方法
    String sayHello(User user) throws RemoteException;
}
