package cn.edu.nefu.embedded.media.util;

/**
 * 图片路径处理工具
 * created by banshui on 2018/5/9
 */
public class ImageUtil {
  private static final String MEDIA_DOMAIN = "http://www.static.embedded-nefu";

  /**
   * 获取图片的绝对路径
   * @param path 相对路径
   * @return 绝对路径
   */
  public static String getAbsolutePath(String path){
    return MEDIA_DOMAIN + path;
  }
}
