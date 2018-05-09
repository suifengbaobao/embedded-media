package cn.edu.nefu.embedded.media.domain.constant;

/**
 * 媒体类型枚举
 * created by banshui on 2018/4/20
 */
public enum MediaTypeEnum {
  /**
   * 图片类型
   */
  IMAGE(111, "image"),
  /**
   * 视频类型
   */
  VEDIO(222, "video"),
  /**
   * 文件类型（除了图片，视频的文件）
   */
  FILE(333, "file");


  private int code;
  private String value;

  MediaTypeEnum(int code, String value){
    this.code = code;
    this.value = value;
  }

  public int getCode() {
    return code;
  }
  public String getValue() {
    return value;
  }
}
