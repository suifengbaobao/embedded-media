package cn.edu.nefu.embedded.media.service;

import cn.edu.nefu.embedded.media.domain.entity.MediaFileInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 图片处理类 created by banshui on 2018/4/20
 */
public interface MediaFileService {

  /**
   * 插入一条记录
   * @param mediaFileInfo 文件信息
   * @return true-插入成功 false-插入失败
   */
  Boolean insert(MediaFileInfo mediaFileInfo);

  /**
   * 更新一个文件信息（md5值不允许更新)
   * @param mediaFileInfo 文件信息
   * @return true-更新成功 false-更新失败
   */
  public Boolean update(MediaFileInfo mediaFileInfo);

  /**
   * 删除一条文件信息（软删除）
   * @param id 文件唯一id
   * @return true-s删除成功 false-删除失败
   */
  public Boolean deleteById(Long id);

  /**
   * 查询一条记录根据文件id
   * @param id 文件id
   * @return 文件信息
   */
  MediaFileInfo queryById(Long id);

  /**
   * 根据文件id批量查询文件信息
   * @param ids id集合
   * @return 文件信息集合
   */
  List<MediaFileInfo> queryByIds(List<Long> ids);

  /**
   * 根据文件类型查询文件信息
   * @param type 媒体类型
   * @Link MediaTypeEnum
   * @return 文件集合
   */
  List<MediaFileInfo> queryByType(Integer type);

  /**
   * 根据用户id查询文件信息
   * @param userId 用户id
   * @return 文件信息集合
   */
  List<MediaFileInfo> queryByUserId(Long userId);

  /**
   * 根据md5值查询文件信息
   * @param md5 md5值
   * @return 文件信息
   */
  MediaFileInfo queryByMd5(String md5);
}
