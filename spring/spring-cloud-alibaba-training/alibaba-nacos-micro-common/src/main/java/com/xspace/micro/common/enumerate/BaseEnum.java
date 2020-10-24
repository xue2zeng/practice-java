package com.xspace.micro.common.enumerate;

/**
 * 枚举接口
 *
 * @param <T>
 */
public interface BaseEnum<E extends Enum<E>, T> {
  T getValue();
}
