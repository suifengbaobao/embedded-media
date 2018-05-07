package cn.edu.nefu.embedded.media.dal;

import cn.edu.nefu.embedded.media.domain.entity.MediaFileInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 媒体文件映射类 created by banshui on 2018/4/20
 */
@Repository
public interface MediaFileMapper {

  long insert(MediaFileInfo mediaFileInfo);

  long update(MediaFileInfo mediaFileInfo);

  long deleteById(@Param("id") Long id);

  MediaFileInfo queryById(@Param("id") Long id);

  List<MediaFileInfo> queryByIds(@Param("ids") List<Long> ids);

  List<MediaFileInfo> queryByType(@Param("type") Integer type);

  List<MediaFileInfo> queryByUserId(@Param("userId") Long userId);

  MediaFileInfo queryByMd5(@Param("md5") String md5);
}
