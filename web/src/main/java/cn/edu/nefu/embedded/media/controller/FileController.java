package cn.edu.nefu.embedded.media.controller;

import cn.edu.nefu.embedded.media.domain.constant.MediaTypeEnum;
import cn.edu.nefu.embedded.media.domain.entity.MediaFileInfo;
import cn.edu.nefu.embedded.media.response.MediaUploadResult;
import cn.edu.nefu.embedded.media.service.MediaFileService;
import cn.edu.nefu.embedded.media.util.FtpUtil;
import cn.edu.nefu.embedded.media.util.ImageUtil;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制器 created by banshui on 2018/5/11
 */
@RestController
@RequestMapping("/media/file")
public class FileController {

  // 允许上传的格式
  private final MediaFileService mediaFileService;

  @Autowired
  public FileController(MediaFileService mediaFileService) {
    this.mediaFileService = mediaFileService;
  }

  @RequestMapping(value = "upload", method = RequestMethod.POST)
  public Object imagePut(@RequestParam("uploadFile") MultipartFile[] uploadFile,
      HttpServletRequest request) {
    // 返回值
    List<MediaUploadResult> files = new LinkedList<>();
    if (uploadFile == null || uploadFile.length == 0) {
      return new MediaUploadResult().error("并没有上传文件啊");
    }
    try {
      for (MultipartFile file : uploadFile) {
        MediaUploadResult mediaUploadResult = new MediaUploadResult();
        // 封装MediaUploadResult对象，并且将文件的byte数组放置到result对象中
        String ip = request.getRemoteAddr();
        // 新的文件名（文件原始名-当前时间毫秒-ip-userId）
        String md5Val = DigestUtils.md5Hex(
            file.getOriginalFilename() + "-" + System.currentTimeMillis() + "-" + ip + "-"
                + "userId");
        String fileName =
            md5Val + "." + StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        String url = FtpUtil.upload(fileName, getFilePath(), file.getInputStream());
        if (url != null) {
          mediaUploadResult.setMsg("SUCCESS");
          mediaUploadResult.setUrl(ImageUtil.getAbsolutePath(url));
          mediaUploadResult.setSuccess(true);
          mediaUploadResult.setSize(file.getSize());
          // 保存上传文件的信息
          MediaFileInfo mediaFileInfo = new MediaFileInfo();
          mediaFileInfo.setMd5Val(md5Val);
          mediaFileInfo.setSize(file.getSize());
          mediaFileInfo.setOriginalFileName(file.getOriginalFilename());
          mediaFileInfo.setNewFileName(fileName);
          mediaFileInfo.setPath(url);
          mediaFileInfo.setType(MediaTypeEnum.FILE.getCode());
          mediaFileService.insert(mediaFileInfo);
        }
        files.add(mediaUploadResult);
      }
      return files;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new MediaUploadResult().error("上传失败");
  }

  /**
   * 获取上传文件的路径（时间年月日）
   *
   * @return 文件路径
   */
  private String getFilePath() {
    Date nowDate = new Date();
    return MediaTypeEnum.IMAGE.getValue() + "/" + new DateTime(nowDate).toString("yyyy") + "/"
        + new DateTime(nowDate).toString("MM") + "/"
        + new DateTime(nowDate).toString("dd");
  }
}
