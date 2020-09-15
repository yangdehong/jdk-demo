package com.ydh.redsheep.io.copy.model;

import java.io.Serializable;

/**
* 拷贝的对象
* @author : yangdehong
* @date : 2019/4/2 22:21
*/
public class ParentCopyObject implements Cloneable, Serializable {

    private CopyObject copyObject;

    private String email;

    private Integer high;

    public CopyObject getCopyObject() {
        return copyObject;
    }

    public void setCopyObject(CopyObject copyObject) {
        this.copyObject = copyObject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return "ParentCopyObject{" +
                "copyObject=" + copyObject +
                ", email='" + email + '\'' +
                ", high=" + high +
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
