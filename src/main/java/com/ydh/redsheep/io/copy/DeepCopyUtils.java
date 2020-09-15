package com.ydh.redsheep.io.copy;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
* 深拷贝
* @author : yangdehong
* @date : 2019/4/2 23:00
*/
public class DeepCopyUtils {
    @SuppressWarnings("unchecked")
    public static <T> T copyObject(T src) {
        T dest;
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);

            dest = (T) in.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            dest = null;
        }
        return dest;
    }


}
