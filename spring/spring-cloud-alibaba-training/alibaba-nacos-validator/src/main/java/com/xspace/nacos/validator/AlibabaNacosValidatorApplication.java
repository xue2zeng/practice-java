package com.xspace.nacos.validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AlibabaNacosValidatorApplication {

  public static void main(String[] args) {
    SpringApplication.run(AlibabaNacosValidatorApplication.class, args);
  }
}
