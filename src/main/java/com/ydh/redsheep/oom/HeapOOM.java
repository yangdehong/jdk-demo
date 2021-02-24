package com.ydh.redsheep.oom;

import java.util.ArrayList;
import java.util.List;

/**
* 堆内存溢出
* @author : yangdehong
* @date : 2021/2/24 13:56
*/
public class HeapOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        while (true) {
            list.add("辅导书及偶发节温器欧赔和光荣和个人或过热囧哥今儿起搏金额破不解气");
        }
    }

}
