package com.ydh.redsheep.spi;

public class DogHelloService  implements HelloService {
    @Override
    public String sayHello() {
        return "wang wang";
    }
}
