package com.ydh.redsheep;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        String str = "0001";
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(str);
        if (m.find()) {
            System.out.println(m.group());
            System.out.println(m.start());
            System.out.println(m.end());
            String[] split = str.split(m.group());
            System.out.println(split.length);
        }

    }


}
