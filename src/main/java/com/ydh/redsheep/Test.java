package com.ydh.redsheep;

import com.ydh.redsheep.utils.FtpUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Test {

    public static void main(String[] args) throws Exception {
        // 文件路径写为用户建立时 指定的目录
//        ftp.uploadFile("home/ftpFile", "book.xml", "F:/Test/book.xml");
        boolean flag = FtpUtils.downloadFile("/etl/etldata/inter/ZNWH/delta/20201110", "CST_CONTACT_INFO_20201110_ADD.dat", "/Users/yangdehong/Downloads");
        //ftp.deleteFile("/home/ftpFile", "123.png");
        System.out.println(flag);

        String path = "/Users/yangdehong/Downloads/CST_CONTACT_INFO_20201110_ADD.dat";
        readBatFile(path);
    }

    public static void readBatFile(String path) throws Exception {
        File file = new File(path);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
        BufferedReader br = new BufferedReader(isr);//输入流
        String str;
        while ((str = br.readLine()) != null) {//按行读取
            String[] arr = str.split("\u001B");
            for (String s : arr) {
                System.out.println(s);
            }
        }
        br.close();//关闭流
    }

}
