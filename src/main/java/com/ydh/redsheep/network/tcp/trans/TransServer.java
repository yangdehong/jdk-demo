package com.ydh.redsheep.network.tcp.trans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TransServer {
	public static void main(String[] args) throws IOException {
		// 新建一个服务，赋予端口
		ServerSocket ss = new ServerSocket(9999);
		// 获取socket
		Socket s = ss.accept();
		
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip+"......");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
		
		String line = null;
		
		while((line=br.readLine())!=null){
//			bw.write(line.toUpperCase());
//			bw.newLine();// 相当回车
//			bw.flush();
			pw.println(line.toUpperCase());
		}
		
		s.close();// 关闭客户端
		ss.close();
	}
}
