package com.ydh.redsheep.io.rmi.server;

import com.ydh.redsheep.io.rmi.server.service.HelloService;
import com.ydh.redsheep.io.rmi.server.service.impl.HelloServiceImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        //1.创建HelloService实例
        HelloService service = new HelloServiceImpl();

        //2.获取注册表
        LocateRegistry.createRegistry(8888);

        //3.对象的绑定
        //bind方法的参数1:   rmi://ip地址:端口/服务名   参数2:绑定的对象
        Naming.bind("//127.0.0.1:8888/rmiserver",service);
    }
}
