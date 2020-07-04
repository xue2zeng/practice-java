package com.xspace.nacos;

import com.xspace.nacos.api.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class AlibabaNacosDubboConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(AlibabaNacosDubboConsumerApplication.class, args);
  }

  @Slf4j
  @RestController
  static class TestController {

    @Reference
    HelloService helloService;

    @GetMapping("/test")
    public String test() {
      return helloService.hello("xue.zeng");
    }
  }
}
