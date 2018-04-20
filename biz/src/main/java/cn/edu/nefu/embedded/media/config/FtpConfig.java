package cn.edu.nefu.embedded.media.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * Ftp 配置管理类
 * created by banshui on 2018/4/20
 */
@Data
public class FtpConfig {
  /**
   * 获取ip地址
   */
  @Value("${FTP_ADDRESS}")
  private String FTP_ADDRESS;

  /**
   * 端口号
   */
  @Value("${FTP_PORT}")
  private String FTP_PORT;

  /**
   * 用户名
   */
  @Value("${FTP_USERNAME}")
  private String FTP_USERNAME;

  /**
   * 密码
   */
  @Value("${FTP_PASSWORD}")
  private String FTP_PASSWORD;

  /**基本路径
   */
  @Value("${FTP_BASE_PATH}")
  private String FTP_BASE_PATH;

  /**
   * 下载地址地基础url
   */
  @Value("${IMAGE_BASE_URL}")
  private String IMAGE_BASE_URL;
}
