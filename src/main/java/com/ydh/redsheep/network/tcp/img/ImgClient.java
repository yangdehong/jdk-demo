package com.ydh.redsheep.network.tcp.img;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ImgClient {
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		// 创建客户端端口
		Socket s = new Socket("192.168.1.105", 9999);

		FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Pictures\\Sample Pictures\\123.jpg");
		// 给服务端
		OutputStream os = s.getOutputStream();
																	
		byte[] b = new byte[1024];
		int len = 0;

		while ((len = fis.read(b)) != -1) {

			os.write(b,0,len);

		}
		
		s.shutdownOutput();// 关闭输出流

		InputStream is = s.getInputStream();
		byte[] b2 = new byte[1024];
		int num = is.read(b2);
		System.out.println(new String(b2,0,num));

		fis.close();
		s.close();
	}
}
