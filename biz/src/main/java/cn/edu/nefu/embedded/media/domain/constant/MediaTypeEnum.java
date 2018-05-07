package cn.edu.nefu.embedded.media.domain.constant;

/**
 * 媒体类型枚举
 * created by banshui on 2018/4/20
 */
public enum MediaTypeEnum {
  /**
   * 图片类型
   */
  IMAGE(111),
  /**
   * 视频类型
   */
  VEDIO(222),
  /**
   * 文件类型（除了图片，视频的文件）
   */
  FILE(333);


  private int value;

  MediaTypeEnum(int value){
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
