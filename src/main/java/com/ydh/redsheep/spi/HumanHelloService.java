package com.ydh.redsheep.spi;

public class HumanHelloService   implements HelloService {
    @Override
    public String sayHello() {
        return "hello 你好";
    }
}
