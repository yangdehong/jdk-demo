package com.ydh.redsheep.network.tcp.text;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		// 创建客户端端口
		Socket s = new Socket("192.168.1.105",9999);
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\adminitrator\\Desktop\\ss\\client.txt"));
		// 给服务端
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);// 可以包装BufferedWriter 传入
		
		String line;
		
		while((line=br.readLine())!=null){
			pw.println(line);
		}
		
		s.shutdownOutput();// 关闭输出流
		
		// 返回服务端的信息
		BufferedReader br2 = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String str = br2.readLine();
		System.out.println(str);
		
		br.close();
		s.close();
	}
}
