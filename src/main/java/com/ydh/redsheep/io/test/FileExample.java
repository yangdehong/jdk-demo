package com.ydh.redsheep.io.test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 字节流
 *
 * @author adminitrator
 */
public class FileExample {
    public static void main(String[] args) throws IOException {

//         createFile();
//         checkLen();
//         fileCopy();
//         objetStream();
//         dataStream();
         pushBackInputStream();
//        sequenceInputStream();
    }

    /**
     * SequenceInputStream:有些情况下，当我们需要从多个输入流中向程序读入数据。
     * 此时，可以使用合并流，将多个输入流合并成一个SequenceInputStream流对象。
     * SequenceInputStream会将与之相连接的流集组合成一个输入流并从第一个输入流开始读取，
     * 直到到达文件末尾，接着从第二个输入流读取，依次类推，直到到达包含的最后一个输入流的文件末尾为止。
     * 合并流的作用是将多个源合并合一个源。其可接收枚举类所封闭的多个字节流对象
     */
    public static void sequenceInputStream() {
        // 创建一个合并流的对象
        SequenceInputStream sis = null;
        // 创建输出流。
        BufferedOutputStream bos = null;
        try {
            // 构建流集合。
            Vector<InputStream> vector = new Vector<InputStream>();
            vector.addElement(new FileInputStream("1.txt"));
            vector.addElement(new FileInputStream("2.txt"));
            vector.addElement(new FileInputStream("2.txt"));
            Enumeration<InputStream> e = vector.elements();

            sis = new SequenceInputStream(e);

            bos = new BufferedOutputStream(new FileOutputStream("3.txt"));
            // 读写数据
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = sis.read(buf)) != -1) {
                bos.write(buf, 0, len);
                bos.flush();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (sis != null) {
                    sis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * PushbackInputStream类继承了FilterInputStream类是iputStream类的修饰者。
     * 提供可以将数据插入到输入流前端的能力(当然也可以做其他操作)。
     * 简而言之PushbackInputStream类的作用就是能够在读取缓冲区的时候提前知道下一个字节是什么，其实质是读取到下一个字符后回退的做法，
     * 这之间可以进行很多操作，这有点向你把读取缓冲区的过程当成一个数组的遍历，遍历到某个字符的时候可以进行的操作，当然，如果要插入，
     * 能够插入的最大字节数是与推回缓冲区的大小相关的，插入字符肯定不能大于缓冲区吧！
     *
     * @throws IOException
     */
    public static void pushBackInputStream() throws IOException {
        String str = "hello,rollenholt";
        PushbackInputStream push = null; // 声明回退流对象
        ByteArrayInputStream bat = null; // 声明字节数组流对象
        bat = new ByteArrayInputStream(str.getBytes());
        push = new PushbackInputStream(bat); // 创建回退流对象，将拆解的字节数组流传入
        int temp = 0;
        while ((temp = push.read()) != -1) { // push.read()逐字节读取存放在temp中，如果读取完成返回-1
            if (temp == ',') { // 判断读取的是否是逗号
                push.unread(temp); // 回到temp的位置
                temp = push.read(); // 接着读取字节
                System.out.print("(回退" + (char) temp + ") "); // 输出回退的字符
            } else {
                System.out.print((char) temp); // 否则输出字符
            }
        }
    }

    public static void dataStream() {
        String path = "3.txt";
        Member[] members = {new Member("Justin", 90), new Member("momor", 95),
                new Member("Bush", 88)};
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(
                    new FileOutputStream(path));
            for (Member member : members) {
                // 写入UTF字符串
                dataOutputStream.writeUTF(member.getName());
                // 写入int数据
                dataOutputStream.writeInt(member.getAge());
            }
            // 所有数据至目的地
            dataOutputStream.flush();
            // 关闭流
            dataOutputStream.close();
            DataInputStream dataInputStream = new DataInputStream(
                    new FileInputStream(path));
            // 读出数据并还原为对象
            for (int i = 0; i < members.length; i++) {
                // 读出UTF字符串
                String name = dataInputStream.readUTF();
                // 读出int数据
                int score = dataInputStream.readInt();
                members[i] = new Member(name, score);
            }
            // 关闭流
            dataInputStream.close();
            // 显示还原后的数据
            for (Member member : members) {
                System.out
                        .printf("%s\t%d%n", member.getName(), member.getAge());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读写对象
     */
    public static void objetStream() {
        ObjectOutputStream objectwriter = null;
        ObjectInputStream objectreader = null;

        try {
            objectwriter = new ObjectOutputStream(new FileOutputStream("3.txt"));
//            objectwriter.writeUTF("UTF-8");
            System.out.println(Charset.defaultCharset());
            objectwriter.writeObject(new Student("中国通", 22));
            objectwriter.writeObject(new Student("tt", 18));
            objectwriter.writeObject(new Student("rr", 17));
            // /////////////////////////////////////////////////////打印控制台
            objectreader = new ObjectInputStream(new FileInputStream("3.txt"));
            for (int i = 0; i < 3; i++) {
                System.out.println(objectreader.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                objectreader.close();
                objectwriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 拷贝文件
     */
    public static void fileCopy() {
        FileInputStream input = null;
        FileOutputStream out = null;
        try {
            input = new FileInputStream("1.txt");
            // 如果文件不存在会自动创建
            out = new FileOutputStream("2.txt");
            // 一次取出的字节数大小,缓冲区大小
            byte[] buffer = new byte[1024];
            int numberRead = 0;
            // numberRead的目的在于防止最后一次读取的字节小于buffer长度，
            while ((numberRead = input.read(buffer)) != -1) {
                // 否则会自动被填充0
                out.write(buffer, 0, numberRead);
            }
            out.write("fdsfds".getBytes());
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 字节长度
     */
    public static void checkLen() {
        // 统计文件字节长度
        int count = 0;
        // 文件输入流
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("1.txt"));
            /*
             * 1.new File()里面的文件地址也可以写成D:\\David\\Java\\java
             * 高级进阶\\files\\tiger.jpg,前一个\是用来对后一个
             * 进行转换的，FileInputStream是有缓冲区的，所以用完之后必须关闭，否则可能导致内存占满，数据丢失。
             */
            // 读取文件字节，并递增指针到下一个字节
            while (inputStream.read() != -1) {
                count++;
            }
            System.out.println("---长度是： " + count + " 字节");
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件处理示例
     */
    public static void createFile() {
        File f = new File("1.txt");
        try {
            // 当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件。
            f.createNewFile();
            // 返回由此抽象路径名表示的文件或目录的名称。
            System.out.println("该分区大小" + f.getTotalSpace() / (1024 * 1024 * 1024) + "G");
            // 创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
            f.mkdirs();
            // 删除此抽象路径名表示的文件或目录
            // f.delete();
            // 返回由此抽象路径名表示的文件或目录的名称。
            System.out.println("文件名  " + f.getName());
            // 返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回null
            System.out.println("文件父目录字符串 " + f.getParent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
