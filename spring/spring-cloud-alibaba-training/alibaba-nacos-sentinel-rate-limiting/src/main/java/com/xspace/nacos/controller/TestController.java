package com.xspace.nacos.controller;

import com.xspace.nacos.sentinel.CurrentLimitingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
public class TestController {
  @Resource
  CurrentLimitingService currentLimitingService;

  @GetMapping("/limit")
  public String hello() {
    currentLimitingService.doSomething("hello " + LocalDateTime.now());
    return "hello, xue.zeng";
  }

  @GetMapping("/breaker")
  public String hello2() {
    currentLimitingService.doSomething2("hello " + LocalDateTime.now());
    return "hello, xue.zeng";
  }
}
