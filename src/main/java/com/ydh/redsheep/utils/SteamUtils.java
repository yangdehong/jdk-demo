package com.ydh.redsheep.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

@Slf4j
public class SteamUtils {

    public static <T> void handleItem(Callable<T> callable, BiConsumer<Integer, T> biConsumer) {
        AtomicInteger index = new AtomicInteger(0);
        do {
            try {
                T number = callable.call();
                biConsumer.accept(index.getAndIncrement(), number);
                log.info("一共" + number + "页");
            } catch (Exception e) {
                log.error("按条处理出错分页", e);
            }
        } while (index.get() <= 10);
    }

}
