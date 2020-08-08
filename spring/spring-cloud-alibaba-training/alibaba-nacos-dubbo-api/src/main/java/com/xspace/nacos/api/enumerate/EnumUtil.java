package com.xspace.nacos.api.enumerate;

import java.util.Objects;
import java.util.Optional;

/**
 * 枚举工具类
 */
public class EnumUtil {
  private EnumUtil(){}

  public static  <T extends IEnum> Optional<T> getIEnum(Class<T> targetType, String source) {
    for (T enumObj : targetType.getEnumConstants()) {
      if (Objects.equals(source, enumObj.getValue().toString())) {
        return Optional.of(enumObj);
      }
    }
    return Optional.empty();
  }
}
