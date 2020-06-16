package com.xspace.cache.mapper;

import com.xspace.cache.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User selectById(Long userId);
}