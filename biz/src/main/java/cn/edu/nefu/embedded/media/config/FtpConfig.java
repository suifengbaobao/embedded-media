package cn.edu.nefu.embedded.media.config;

/**
 * Ftp 配置管理类
 * created by banshui on 2018/4/20
 */
public class FtpConfig {
  /**
   * 获取ip地址
   */
  public static String FTP_ADDRESS = "138.128.212.149";

  /**
   * 端口号
   */
  public static int FTP_PORT = 21;

  /**
   * 用户名
   */
  public static String FTP_USERNAME = "vsftpd";

  /**
   * 密码
   */
  public static String FTP_PASSWORD = "123456";

  /**基本路径
   */
  public static String FTP_BASE_PATH = "/media/embedded";
}
