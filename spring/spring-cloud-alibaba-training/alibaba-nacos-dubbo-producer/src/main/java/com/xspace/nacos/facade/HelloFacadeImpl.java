package com.xspace.nacos.facade;

import com.xspace.nacos.api.facade.HelloFacade;
import org.apache.dubbo.config.annotation.Service;

@Service(interfaceClass = HelloFacade.class)
public class HelloFacadeImpl implements HelloFacade {
  @Override
  public String hello(String name) {
    return "hello " + name;
  }
}
