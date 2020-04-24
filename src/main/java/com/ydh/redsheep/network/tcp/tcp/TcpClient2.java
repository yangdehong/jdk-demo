package com.ydh.redsheep.network.tcp.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient2 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// 创建客户端端口
		Socket s = new Socket("10.80.7.107",9999);
		// 获取socket中的输出流
		OutputStream os = s.getOutputStream();
		os.write("服务端 你好".getBytes());
		
		// 获取读取的流
		InputStream is = s.getInputStream();
		
		byte[] b = new byte[1024];
		int len = is.read(b);
		System.out.println(new String(b, 0, len));
		
		s.close();
	}
}
