package com.xspace.nacos.service;

import com.xspace.nacos.api.HelloService;
import org.apache.dubbo.config.annotation.Service;

@Service(interfaceClass = HelloService.class)
public class HelloServiceImpl implements HelloService {
  @Override
  public String hello(String name) {
    return "hello " + name;
  }
}
