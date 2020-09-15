package com.ydh.redsheep.io.copy;

import com.ydh.redsheep.copy.model.CopyObject;
import com.ydh.redsheep.copy.model.ParentCopyObject;

/**
* 浅拷贝
* @author : yangdehong
* @date : 2019/4/2 21:16
*/
public class CopyMain {

    public static void main(String[] args) {
//        shallowCopy();
        deepCopy();
    }

    public static void deepCopy() {
        CopyObject copyObject = new CopyObject();
        ParentCopyObject parentCopyObject = new ParentCopyObject();
        parentCopyObject.setCopyObject(copyObject);

        ParentCopyObject parentCopyObject2 = DeepCopyUtils.copyObject(parentCopyObject);

        parentCopyObject2.getCopyObject().setAge(12);
        parentCopyObject2.setEmail("123@qq.com");


        System.out.println(parentCopyObject);
        System.out.println(parentCopyObject2);
    }

    public static void shallowCopy() {
        CopyObject copyObject = new CopyObject();
        ParentCopyObject parentCopyObject = new ParentCopyObject();
        parentCopyObject.setCopyObject(copyObject);

        ParentCopyObject parentCopyObject2 = (ParentCopyObject) parentCopyObject.clone();

        parentCopyObject2.getCopyObject().setAge(12);
        parentCopyObject2.setEmail("123@qq.com");


        System.out.println(parentCopyObject);
        System.out.println(parentCopyObject2);
    }

}
