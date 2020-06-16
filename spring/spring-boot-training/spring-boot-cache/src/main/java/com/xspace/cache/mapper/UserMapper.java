package com.xspace.cache.mapper;

import com.xspace.cache.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper {
    public User selectById(Long userId);
}