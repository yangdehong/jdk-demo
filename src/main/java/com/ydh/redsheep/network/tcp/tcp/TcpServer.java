package com.ydh.redsheep.network.tcp.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

	public static void main(String[] args) throws IOException {
		// 新建一个服务，赋予端口
		ServerSocket ss = new ServerSocket(9999);
		// 获取socket
		Socket s = ss.accept();
		
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip+"......");
		
		// 获取读取的流
		InputStream is = s.getInputStream();
		
		byte[] b = new byte[1024];
		int len = is.read(b);
		System.out.println(new String(b, 0, len));
		
		s.close();// 关闭客户端
		ss.close();
	}
}
