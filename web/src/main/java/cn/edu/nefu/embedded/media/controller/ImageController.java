package cn.edu.nefu.embedded.media.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图片上传控制器
 * created by banshui on 2018/4/20
 */
@RestController
@RequestMapping("/media/image")
public class ImageController {
  @RequestMapping(value = "put", method = RequestMethod.POST)
  public Object imagePut(){

    return null;
  }
}
