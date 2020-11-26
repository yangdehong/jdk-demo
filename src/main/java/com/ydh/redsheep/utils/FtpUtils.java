package com.ydh.redsheep.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
*
* @author : yangdehong
* @date : 2020/11/16 上午11:13
*/
@Slf4j
public class FtpUtils {

    // ftp服务器地址
    public static String hostname = "121.196.34.167";
    // ftp服务器端口号默认为21
    public static Integer port = 21;
    // ftp登录账号
    public static String username = "ydh";
    // ftp登录密码
    public static String password = "Yiwise.AI112233";

    public static FTPClient ftpClient = null;

    /**
     * 初始化ftp服务器
     */
    public static void initFtpClient() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            log.debug("ftp服务器={}:{}......connecting", hostname, port);
            ftpClient.connect(hostname, port); // 连接ftp服务器
            ftpClient.login(username, password); // 登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); // 是否成功登录服务器
            boolean positiveCompletion = FTPReply.isPositiveCompletion(replyCode);
            if (positiveCompletion) {
                log.debug("ftp服务器连接={}:{}......success", hostname, port);
            } else {
                log.error("ftp服务器连接={}:{}......failed", hostname, port);
            }
        } catch (IOException e) {
            log.error("ftp连接出错", e);
        }
    }

    /**
     * 上传文件
     *
     * @param pathname       ftp服务保存地址
     * @param fileName       上传到ftp的文件名
     * @param originFileName 待上传文件的名称（绝对地址） *
     * @return
     */
    public static boolean uploadFile(String pathname, String fileName, String originFileName) {
        initFtpClient();
        InputStream inputStream = null;
        try {
            log.debug("开始上传文件");
            File file = new File(originFileName);
            inputStream = new FileInputStream(file);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            createDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            // 每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据
            ftpClient.enterLocalPassiveMode();
            // 观察是否真的上传成功
            boolean storeFlag = ftpClient.storeFile(fileName, inputStream);
            log.debug("storeFlag=={}", storeFlag);
            inputStream.close();
            ftpClient.logout();
            log.debug("上传文件成功");
        } catch (Exception e) {
            log.error("上传文件失败", e);
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 上传文件
     *
     * @param pathname    ftp服务保存地址
     * @param fileName    上传到ftp的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public static boolean uploadFile(String pathname, String fileName, InputStream inputStream) {
        try {
            log.debug("开始上传文件");
            initFtpClient();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            createDirecroty(pathname);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            log.debug("上传文件成功");
        } catch (Exception e) {
            log.error("上传文件失败", e);
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * * 下载文件 *
     *
     * @param pathname  FTP服务器文件目录 *
     * @param filename  文件名称 *
     * @param localPath 下载后的文件路径 *
     * @return
     */
    public static boolean downloadFile(String pathname, String filename, String localPath) {
        initFtpClient();
        boolean flag = false;
        OutputStream os = null;
        try {
            log.debug("开始下载文件");
            // 切换FTP目录
            boolean changeFlag = ftpClient.changeWorkingDirectory(pathname);
            log.debug("changeFlag=={}", changeFlag);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setRemoteVerificationEnabled(false);
            FTPFile ftpFile = ftpClient.mdtmFile(filename);
            File localFile = new File(localPath + "/" + ftpFile.getName());
            os = new FileOutputStream(localFile);
            ftpClient.retrieveFile(ftpFile.getName(), os);
            os.close();
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
           log.error("下载文件出错", e);
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * * 删除文件 *
     *
     * @param pathname FTP服务器保存目录 *
     * @param filename 要删除的文件名称 *
     * @return
     */
    public boolean deleteFile(String pathname, String filename) {
        boolean flag = false;
        try {
            log.debug("开始删除文件");
            initFtpClient();
            // 切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
            log.debug("删除文件成功");
        } catch (Exception e) {
            log.error("删除文件失败", e);
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
     * @param remote
     * @return
     * @throws IOException
     */
    public static boolean createDirecroty(String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(path)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    changeWorkingDirectory(subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    /**
     * 改变目录路径
     * @param directory
     * @return
     */
    public static boolean changeWorkingDirectory(String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                System.out.println("进入文件夹" + directory + " 成功！");

            } else {
                System.out.println("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    /**
     * 判断ftp服务器文件是否存在
     * @param path
     * @return
     * @throws IOException
     */
    public static boolean existFile(String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 创建目录
     * @param dir
     * @return
     */
    public static boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                System.out.println("创建文件夹" + dir + " 成功！");
            } else {
                System.out.println("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) {
        // 文件路径写为用户建立时 指定的目录
//        FtpUtils.uploadFile("home/ftpFile", "book.xml", "/etl/etldata/inter/ZNWH/delta/20201110");
        FtpUtils.downloadFile("/etl/etldata/inter/ZNWH/delta/20201120/", "CST_CONTACT_INFO_20201120_ADD.dat", "/tmp/ftp");
        //ftp.deleteFile("/home/ftpFile", "123.png");
        System.out.println("ok");
    }


}
