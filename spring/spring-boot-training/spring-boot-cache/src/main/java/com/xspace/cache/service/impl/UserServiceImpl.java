package com.xspace.cache.service.impl;

import com.xspace.cache.mapper.UserMapper;
import com.xspace.cache.model.User;
import com.xspace.cache.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
  @Resource
  UserMapper userMapper;

  @Override
  public User selectById(Long userId) {
    return userMapper.selectById(userId);
  }
}
