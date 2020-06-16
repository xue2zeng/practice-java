package com.xspace.cache.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.CreateCache;
import com.xspace.cache.mapper.UserMapper;
import com.xspace.cache.model.User;
import com.xspace.cache.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
  @Resource
  UserMapper userMapper;

  // 定义一个User对象的二级缓存(本地+远程)
  @CreateCache(name = "xspace.user", localExpire = 3600, localLimit = 100, expire = 7200, cacheType = CacheType.BOTH)
  private Cache<Long, User> userCache;

  @Override
  public User selectById(long id) {
    User user = userCache.get(id);
    if (Objects.isNull(user)) {
      user = userMapper.selectById(id);
      userCache.put(id, user);
    }
    log.info("user:[{}]", user);
    return user;
  }
}
