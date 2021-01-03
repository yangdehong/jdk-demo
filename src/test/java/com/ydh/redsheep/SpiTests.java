package com.ydh.redsheep;

import com.ydh.redsheep.spi.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ServiceLoader;

@SpringBootTest
class SpiTests {

    @Test
    void test() {
        final ServiceLoader<HelloService> helloServices  = ServiceLoader.load(HelloService.class);
        for (HelloService helloService : helloServices){
            System.out.println(helloService.getClass().getName() + ":" + helloService.sayHello());
        }

    }

}
