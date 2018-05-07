package cn.edu.nefu.embedded.media.util;

import cn.edu.nefu.embedded.media.config.FtpConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil {

  private static FTPClient ftpClient = null;

  private static FtpConfig ftpConfig = new FtpConfig();

  /**
   * ftp上传文件方法 title:pictureUpload
   * @param newName 文件新名称--防止重名 例如："1.jpg"
   * @param savePath 文件保存路径。注：最后访问路径是 ftpConfig.getFTP_ADDRESS()+"/images"+picSavePath
   * @param inputStream 要上传的文件（图片）
   * @return 若上传成功，返回文件的访问路径，若上传失败，返回null
   */
  public static String upload(String newName,
      String savePath, InputStream inputStream) {
    boolean flag = uploadFile(savePath, newName,
        inputStream);
    if (!flag) {
      return null;
    }
    //picHttpPath = ftpConfig.getFTP_ADDRESS()+"/images"+picSavePath+"/"+picNewName;
    return ftpConfig.getFTP_BASE_PATH() + savePath + "/" + newName;
  }


  /**
   * Description: 向FTP服务器上传文件
   *
   * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
   * @param filename 上传到FTP服务器上的文件名
   * @param input 输入流
   * @return 成功返回true，否则返回false
   */
  private static boolean uploadFile(
      String filePath, String filename, InputStream input) {
    try {
      FTPClient ftp = getFTPClient();
      int reply = ftp.getReplyCode();
      if (!FTPReply.isPositiveCompletion(reply)) {
        ftp.disconnect();
        return false;
      }
      //切换到上传目录
      if (!ftp.changeWorkingDirectory(ftpConfig.getFTP_BASE_PATH() + filePath)) {
        //如果目录不存在创建目录
        String[] dirs = filePath.split("/");
        String tempPath = ftpConfig.getFTP_BASE_PATH();
        for (String dir : dirs) {
          if (null == dir || "".equals(dir)) {
            continue;
          }
          tempPath += "/" + dir;
          if (!ftp.changeWorkingDirectory(tempPath)) {
            if (!ftp.makeDirectory(tempPath)) {
              return false;
            } else {
              ftp.changeWorkingDirectory(tempPath);
            }
          }
        }
      }
      //设置上传文件的类型为二进制类型
      ftp.setFileType(FTP.BINARY_FILE_TYPE);
      ftp.enterLocalPassiveMode();//这个设置允许被动连接--访问远程ftp时需要
      //上传文件
      if (!ftp.storeFile(filename, input)) {
        return false;
      }
      input.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

  /**
   * Description: 从FTP服务器下载文件
   *
   * @param host FTP服务器hostname
   * @param port FTP服务器端口
   * @param username FTP登录账号
   * @param password FTP登录密码
   * @param remotePath FTP服务器上的相对路径
   * @param fileName 要下载的文件名
   * @param localPath 下载后保存到本地的路径
   */
  public static boolean downloadFile(String host, int port, String username, String password,
      String remotePath,
      String fileName, String localPath) {
    try {
      FTPClient ftp = getFTPClient();
      int reply = ftp.getReplyCode();
      if (!FTPReply.isPositiveCompletion(reply)) {
        ftp.disconnect();
        return false;
      }
      ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
      FTPFile[] fs = ftp.listFiles();
      for (FTPFile ff : fs) {
        if (ff.getName().equals(fileName)) {
          File localFile = new File(localPath + "/" + ff.getName());
          OutputStream is = new FileOutputStream(localFile);
          ftp.retrieveFile(ff.getName(), is);
          is.close();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

  /**
   * 获取ftp客户端
   *
   * @return 已连接的客户端
   * @throws IOException 异常
   */
  private static FTPClient getFTPClient() throws IOException {
    if (ftpClient == null) {
      ftpClient = new FTPClient();
      ftpClient
          .connect(ftpConfig.getFTP_ADDRESS(), Integer.valueOf(ftpConfig.getFTP_PORT()));// 连接FTP服务器
      // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
      ftpClient.login(ftpConfig.getFTP_USERNAME(), ftpConfig.getFTP_PASSWORD());// 登录
    }
    return ftpClient;
  }
}