package cn.edu.nefu.embedded.media;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;

/**
 * 媒体中心程序主类
 * created by banshui on 2018/4/19
 */
@SpringBootApplication
@MapperScan("cn.edu.nefu.embedded.media.dal")
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class EmbeddedMediaApplication {
  public static void main(String[] args) {
    SpringApplication.run(EmbeddedMediaApplication.class, args);
  }
}
