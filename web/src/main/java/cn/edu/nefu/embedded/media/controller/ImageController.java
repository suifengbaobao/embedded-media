package cn.edu.nefu.embedded.media.controller;

import cn.edu.nefu.embedded.media.response.MediaUploadResult;
import java.io.File;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传控制器
 * created by banshui on 2018/4/20
 */
@RestController
@RequestMapping("/media/image")
public class ImageController {
  // 允许上传的格式
  private static final String[] IMAGE_TYPE = new String[] { ".bmp", ".jpg", ".jpeg", ".gif", ".png" };

  @RequestMapping(value = "put", method = RequestMethod.POST)
  public Object imagePut(@RequestParam("uploadFile") MultipartFile uploadFile , HttpServletRequest request, HttpServletResponse response){
    // 校验图片格式
    boolean isLegal = false;
    for (String type : IMAGE_TYPE) {
      if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
        isLegal = true;
        break;
      }
    }
    // 封装Result对象，并且将文件的byte数组放置到result对象中
    MediaUploadResult mediaUploadResult = new MediaUploadResult();
    // 状态
    mediaUploadResult.setSuccess(isLegal);
    // 文件新路径
    String filePath = getFilePath(uploadFile.getOriginalFilename());

    return null;
  }

  private String getFilePath(String sourceFileName) {
    Date nowDate = new Date();
    // yyyy/MM/dd
    String fileFolder = File.separator + new DateTime(nowDate).toString("yyyy") + File.separator + new DateTime(nowDate).toString("MM") + File.separator
        + new DateTime(nowDate).toString("dd");
    File file = new File(fileFolder);
    if (!file.isDirectory()) {
      // 如果目录不存在，则创建目录
      file.mkdirs();
    }
    return fileFolder;
  }
}
