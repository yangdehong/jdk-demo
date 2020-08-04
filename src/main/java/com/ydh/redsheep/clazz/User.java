package com.ydh.redsheep.clazz;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
*
* @author : yangdehong
* @date : 2020-06-24 20:11
*/
@Data
@Slf4j
public class User implements Serializable {

    private Long id;

    private String name;

    {
        System.out.println("普通代码块");
    }

    static {
        System.out.println("静态代码块");
    }

}
