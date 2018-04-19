package cn.edu.nefu.embedded.media.domain.entity;

import lombok.Data;

/**
 * 媒体文件实体类
 * created by banshui on 2018/4/19
 */
@Data
public class MediaFileInfo {

  /**
   * 自增id
   */
  private Long id;
  /**
   * 文件名
   */
  private String fileName;
  /**
   * 文件的md5值（文件名+上传时间毫秒+上传用户）
   */
  private String md5Val;
  /**
   * 文件大小
   */
  private Long size;
  /**
   * 文件类型
   */
  private Integer type;
  /**
   * 上传作者
   */
  private Long userId;
  /**
   * 相对路径（存储在服务器中的路径）
   */
  private String path;
  /**
   * 额外的信息
   */
  private String extraInfo;
  private Long updated;
  private Long created;
}
