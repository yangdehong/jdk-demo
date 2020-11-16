package com.ydh.redsheep.steam;

import com.ydh.redsheep.utils.SteamUtils;

import java.util.Random;

public class HandleTest {

    public static void main(String[] args) {
        new HandleTest().test();
    }

    private void test() {
        SteamUtils.handleItem(HandleTest::getNumber, (i, number)->{
            System.out.println(i + "=====" + number);
        });
    }

    private static Integer getNumber() {
        return new Random().nextInt();
    }


}
