package com.ydh.redsheep.rmi.client;

import com.ydh.redsheep.rmi.pojo.User;
import com.ydh.redsheep.rmi.server.service.HelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        //1.从注册表中获取远程对象 , 强转
        HelloService service = (HelloService) Naming.lookup("//127.0.0.1:8888/rmiserver");

        //2.准备参数
        User user = new User("laowang",18);

        //3.调用远程方法sayHello
        String message = service.sayHello(user);
        System.out.println(message);
    }
}
