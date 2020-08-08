package com.xspace.nacos.validator.annotation.validator;

import com.google.common.collect.Lists;
import lombok.experimental.Delegate;

import javax.validation.Valid;
import java.util.List;

/**
 * @Delegate注解受lombok版本限制，1.18.6以上版本可支持。如果校验不通过，会抛出NotReadablePropertyException，同样可以使用统一异常进行处理。
 * @param <E>
 */
public class ValidationList<E> implements List<E> {

  @Delegate // @Delegate是lombok注解
  @Valid // 一定要加@Valid注解
  public List<E> list = Lists.newArrayList();

  // 一定要记得重写toString方法
  @Override
  public String toString() {
    return list.toString();
  }
}
