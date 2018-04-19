package cn.edu.nefu.embedded.media;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 媒体中心程序主类
 * created by banshui on 2018/4/19
 */
@SpringBootApplication
@MapperScan("cn.edu.nefu.embedded.media.dal")
public class EmbeddedMediaApplication {
  public static void main(String[] args) {
    SpringApplication.run(EmbeddedMediaApplication.class, args);
  }
}
