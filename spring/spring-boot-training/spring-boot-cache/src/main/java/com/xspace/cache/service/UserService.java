package com.xspace.cache.service;

import com.alicp.jetcache.anno.Cached;
import com.xspace.cache.model.User;

import static com.alicp.jetcache.anno.CacheType.REMOTE;

public interface UserService {

  @Cached(name="cache.user", key = "#id", expire = 3600, cacheType = REMOTE)
  User selectById(long id);
}
