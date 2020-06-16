package com.xspace.cache.service;

import com.alicp.jetcache.anno.Cached;
import com.xspace.cache.model.User;

import static com.alicp.jetcache.anno.CacheType.REMOTE;

public interface UserService {
  @Cached(name="UserService.selectById:", key = "#userId", expire = 60, cacheType = REMOTE)
  User selectById(Long userId);
}
