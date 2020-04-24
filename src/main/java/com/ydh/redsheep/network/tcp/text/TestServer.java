package com.ydh.redsheep.network.tcp.text;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
	public static void main(String[] args) throws IOException {
		// 新建一个服务，赋予端口
		ServerSocket ss = new ServerSocket(9999);
		// 获取socket		
		Socket s = ss.accept();
		
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip+"......");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\adminitrator\\Desktop\\ss\\server.txt"), true);
		
		String line;
		
		while((line=br.readLine())!=null){
			pw.println(line);
		}
		
		PrintWriter pw2 = new PrintWriter(s.getOutputStream(), true);
		pw2.println("成功");
		
		pw.close();
		s.close();// 关闭客户端
		ss.close();
	}
}
