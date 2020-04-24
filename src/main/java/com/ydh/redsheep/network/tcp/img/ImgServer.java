package com.ydh.redsheep.network.tcp.img;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ImgServer {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// 新建一个服务，赋予端口
		ServerSocket ss = new ServerSocket(9999);

		while(true){
			// 获取socket
			Socket s = ss.accept();
			
			new Thread(new PicThread(s)).start();
		}
		
//		ss.close();
	}
}

class PicThread implements Runnable {

	private Socket s;

	public PicThread(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {

		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + "......");

		try {
			InputStream is = s.getInputStream();

			FileOutputStream fos = new FileOutputStream(
					"C:\\Users\\Public\\Pictures\\Sample Pictures\\321.jpg");

			byte[] b = new byte[1024];
			int len = 0;

			while ((len = is.read(b)) != -1) {

				fos.write(b, 0, len);

			}

			OutputStream os = s.getOutputStream();
			os.write("成功".getBytes());

			fos.close();
			s.close();// 关闭客户端
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
