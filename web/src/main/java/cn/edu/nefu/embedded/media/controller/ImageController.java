package cn.edu.nefu.embedded.media.controller;

import cn.edu.nefu.embedded.media.domain.constant.MediaTypeEnum;
import cn.edu.nefu.embedded.media.response.MediaUploadResult;
import cn.edu.nefu.embedded.media.util.FtpUtil;
import cn.edu.nefu.embedded.media.util.ImageUtil;
import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
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
    String ip = request.getRemoteAddr();
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
    mediaUploadResult.setSize(uploadFile.getSize());
    if(! isLegal){
      mediaUploadResult.setMsg("非法的图片格式");
      return mediaUploadResult;
    }
    // 新的文件名（文件原始名-当前时间毫秒-ip-userId）
    String md5Val = DigestUtils.md5Hex(uploadFile.getOriginalFilename() + "-" + System.currentTimeMillis() + "-" + ip + "-" + "userId");
    String fileName = md5Val + "." + StringUtils.substringAfterLast(uploadFile.getOriginalFilename(), ".");
    try {
      // 验证是否为图片
      BufferedImage image = ImageIO.read(uploadFile.getInputStream());
      mediaUploadResult.setWidth(image.getWidth() + "");
      mediaUploadResult.setHeight(image.getHeight() + "");
      String url = FtpUtil.upload(fileName, getFilePath(), uploadFile.getInputStream());
      if(url != null) {
        mediaUploadResult.setMsg("SUCCESS");
        mediaUploadResult.setUrls(Lists.newArrayList(ImageUtil.getAbsolutePath(url)));
        return mediaUploadResult;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    mediaUploadResult.setMsg("上传失败！");
    return mediaUploadResult;
  }

  private String getFilePath() {
    Date nowDate = new Date();
    return MediaTypeEnum.IMAGE.getValue() + "/" + new DateTime(nowDate).toString("yyyy") + "/" + new DateTime(nowDate).toString("MM") + "/"
        + new DateTime(nowDate).toString("dd");
  }
}
