package cn.edu.nefu.embedded.media.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * mvc 配置文件
 * created by banshui on 2018/4/20
 */
@Configuration
public class ServletContextConfig {
  private static final Long MAX_UPLOAD_SIZE = 1000 * 1000 * 500L;// 最大支持上传500MB的文件

  @Bean
  public CommonsMultipartResolver commonsMultipartResolver(){
    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
    commonsMultipartResolver.setDefaultEncoding("UTF-8");
    commonsMultipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
    commonsMultipartResolver.setMaxInMemorySize(40960);
    return commonsMultipartResolver;
  }
}
