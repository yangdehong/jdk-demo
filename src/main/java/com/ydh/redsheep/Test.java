package com.ydh.redsheep;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) throws Exception {
        Map<String, Object> extraInfoMap = new HashMap<>();
        extraInfoMap.put("manual", String.valueOf(false));
        extraInfoMap.put("manual2", "false");
        extraInfoMap.put("manual3", "123");

        System.out.println(extraInfoMap);
    }

}
