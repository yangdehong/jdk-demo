package com.ydh.redsheep.pojocopy.model;

import java.io.Serializable;

/**
 * 拷贝的对象
 *
 * @author : yangdehong
 * @date : 2019/4/2 22:20
 */
public class CopyObject implements Cloneable, Serializable {

    private Integer age;

    private String name;

    private Long money;

    public CopyObject() {
        this.age = 11;
        this.name = "央财";
        this.money = 99L;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "CopyObject{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    // 重写Object类的clone方法
    @Override
    public Object clone() {
        Object obj = null;
        //调用Object类的clone方法，返回一个Object实例
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
