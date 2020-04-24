package com.ydh.redsheep.network.tcp.trans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TransClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		// 创建客户端端口
		Socket s = new Socket("192.168.1.105",9999);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 给服务端
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);// 可以包装BufferedWriter 传入
		// 返回服务端的信息
		BufferedReader br2 = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		String line = null;
		
		while((line=br.readLine())!=null){
			
//			bw.write(line);
//			bw.newLine();// 相当回车
//			bw.flush();
			pw.println(line);
			
			if("over".equals(line)){
				break;
			}
			
			String str = br2.readLine();
			System.out.println("server"+str);
		}
		
		br.close();
		s.close();
	}
}
