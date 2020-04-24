package com.ydh.redsheep.network.tcp.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer2 {

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
		
		// 获取socket中的输出流
		OutputStream os = s.getOutputStream();
		os.write("收到了".getBytes());
		
		s.close();// 关闭客户端
		ss.close();
	}
}
