package com.ydh.redsheep.network.tcp.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// 创建客户端端口
		Socket s = new Socket("10.80.7.107",9999);
		// 获取socket中的输出流
		OutputStream os = s.getOutputStream();
		os.write("tcp is come".getBytes());
		s.close();
	}
}
