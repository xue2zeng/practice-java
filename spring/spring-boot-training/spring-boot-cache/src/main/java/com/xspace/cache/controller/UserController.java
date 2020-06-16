package com.xspace.cache.controller;


import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.xspace.cache.model.User;
import com.xspace.cache.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@RestController
public class UserController {

  @Resource
  UserService userService;


  @GetMapping(value = "/getUser")
  public String getUserInfo(@RequestParam long id) {

      User user = userService.selectById(id);

    return "success";
  }
}
