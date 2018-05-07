package cn.edu.nefu.embedded.media.response;

import lombok.Data;

/**
 * 文件上传结果
 * created by banshui on 2018/5/7
 */
@Data
public class MediaUploadResult {

  /**
   * 上传结果是否成功
   */
  private boolean success;
  /**
   * 相对路径
   */
  private String url;
  /**
   * 媒体类型专用 宽度
   */
  private String width;
  /**
   * 媒体类型专用 高度
   */
  private String height;
  /**
   * 文件大小，单位字节
   */
  private long size;
}
