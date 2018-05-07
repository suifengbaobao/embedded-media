package cn.edu.nefu.embedded.media.service.impl;

import cn.edu.nefu.embedded.media.dal.MediaFileMapper;
import cn.edu.nefu.embedded.media.domain.entity.MediaFileInfo;
import cn.edu.nefu.embedded.media.service.MediaFileService;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by banshui on 2018/5/7
 */
@Service
public class MediaFileServiceImpl implements MediaFileService {
  private final MediaFileMapper mediaFileMapper;

  @Autowired
  public MediaFileServiceImpl(MediaFileMapper mediaFileMapper) {
    this.mediaFileMapper = mediaFileMapper;
  }

  public Boolean insert(MediaFileInfo mediaFileInfo){
    if(mediaFileInfo == null || mediaFileInfo.getMd5Val() == null){
      return Boolean.FALSE;
    }
    long count = mediaFileMapper.insert(mediaFileInfo);
    return count == 1;
  }

  public Boolean update(MediaFileInfo mediaFileInfo){
    if(mediaFileInfo == null || mediaFileInfo.getId() == null){
      return Boolean.FALSE;
    }
    long count = mediaFileMapper.update(mediaFileInfo);
    return count == 1;
  }

  public Boolean deleteById(Long id){
    if(id == null){
      return Boolean.FALSE;
    }
    long count = mediaFileMapper.deleteById(id);
    return count == 1;
  }

  @Override
  public MediaFileInfo queryById(Long id) {
    if(id == null) {
      return null;
    }
    return mediaFileMapper.queryById(id);
  }

  @Override
  public List<MediaFileInfo> queryByIds(List<Long> ids) {
    if(CollectionUtils.isEmpty(ids)) {
      return null;
    }
    return mediaFileMapper.queryByIds(ids);
  }

  @Override
  public List<MediaFileInfo> queryByType(Integer type) {
    return mediaFileMapper.queryByType(type);
  }

  @Override
  public List<MediaFileInfo> queryByUserId(Long userId) {
    if(userId == null) {
      return null;
    }
    return mediaFileMapper.queryByUserId(userId);
  }

  @Override
  public MediaFileInfo queryByMd5(String md5) {
    if(StringUtils.isBlank(md5)) {
      return null;
    }
    return mediaFileMapper.queryByMd5(md5);
  }
}
