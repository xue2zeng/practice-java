package com.xspace.cache.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserServiceTest {

  @Resource
  UserService userService;

  @Test
  public void testSelectById() {
    userService.selectById(1L);
  }
}
