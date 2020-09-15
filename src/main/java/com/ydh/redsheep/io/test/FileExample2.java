package com.ydh.redsheep.io.test;

import java.io.*;

/**
 * 字符流
 * 
 * @author adminitrator
 * 
 */
public class FileExample2 {

	public static void main(String[] args) throws IOException {
		// print();
//		fileConcatenate();
		streamTokenizer();
	}

	public static long streamTokenizer(){
		String fileName = "C:\\Users\\adminitrator\\Desktop\\1.txt";  
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(fileName);
			// 创建分析给定字符流的标记生成器
			StreamTokenizer st = new StreamTokenizer(new BufferedReader(fileReader));

			// ordinaryChar方法指定字符参数在此标记生成器中是“普通”字符。
			// 下面指定单引号、双引号和注释符号是普通字符
			st.ordinaryChar('\'');
			st.ordinaryChar('\"');
			st.ordinaryChar('/');

			String s;
			int numberSum = 0;
			int wordSum = 0;
			int symbolSum = 0;
			int total = 0;
			// nextToken方法读取下一个Token.
			// TT_EOF指示已读到流末尾的常量。
			while (st.nextToken() != StreamTokenizer.TT_EOF) {
				// 在调用 nextToken 方法之后，ttype字段将包含刚读取的标记的类型
				switch (st.ttype) {
				// TT_EOL指示已读到行末尾的常量。
				case StreamTokenizer.TT_EOL:
					break;
				// TT_NUMBER指示已读到一个数字标记的常量
				case StreamTokenizer.TT_NUMBER:
					// 如果当前标记是一个数字，nval字段将包含该数字的值
					s = String.valueOf((st.nval));
					System.out.println("数字有：" + s);
					numberSum++;
					break;
				// TT_WORD指示已读到一个文字标记的常量
				case StreamTokenizer.TT_WORD:
					// 如果当前标记是一个文字标记，sval字段包含一个给出该文字标记的字符的字符串
					s = st.sval;
					System.out.println("单词有： " + s);
					wordSum++;
					break;
				default:
					// 如果以上3中类型都不是，则为英文的标点符号
					s = String.valueOf((char) st.ttype);
					System.out.println("标点有： " + s);
					symbolSum++;
				}
			}
			System.out.println("数字有 " + numberSum + "个");
			System.out.println("单词有 " + wordSum + "个");
			System.out.println("标点符号有： " + symbolSum + "个");
			total = symbolSum + numberSum + wordSum;
			System.out.println("Total = " + total);
			return total;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	@SuppressWarnings("resource")
	public static void fileConcatenate() throws IOException {
		String str;
		String[] fileName = { "C:\\Users\\adminitrator\\Desktop\\1.txt" };
		// 构建对该文件您的输入流
		BufferedWriter writer = new BufferedWriter(new FileWriter(
				"C:\\Users\\adminitrator\\Desktop\\4.txt"));
		for (String name : fileName) {
			BufferedReader reader = new BufferedReader(new FileReader(name));

			while ((str = reader.readLine()) != null) {
				writer.write(str);
				writer.newLine();
			}
		}
	}

	public static void print() {
		char[] buffer = new char[512]; // 一次取出的字节数大小,缓冲区大小
		int numberRead = 0;
		FileReader reader = null; // 读取字符文件的流
		PrintWriter writer = null; // 写字符到控制台的流

		try {
			reader = new FileReader("C:\\Users\\adminitrator\\Desktop\\1.txt");
			writer = new PrintWriter(System.out); // PrintWriter可以输出字符到文件，也可以输出到控制台
			while ((numberRead = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, numberRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			writer.close(); // 这个不用抛异常
		}
	}

}
