package com.xspace.micro.common.converter;

import com.alibaba.fastjson.JSON;
import com.xspace.micro.common.config.BaseMapping;
import com.xspace.micro.common.model.po.User;
import com.xspace.micro.common.model.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring" /*, uses = JsonObjectMapping.class*/, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapping extends BaseMapping<User, UserVo> {

  @Mapping(target = "gender", source = "sex")
  @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
  @Override
  UserVo sourceToTarget(User user);

  @Mapping(target = "sex", source = "gender")
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
  @Override
  User targetToSource(UserVo user);

  default List<UserVo.UserConfig> strConfigToListUserConfig(String config) {
    return JSON.parseArray(config, UserVo.UserConfig.class);
  }

  default String listUserConfigToStrConfig(List<UserVo.UserConfig> list) {
    return JSON.toJSONString(list);
  }
}