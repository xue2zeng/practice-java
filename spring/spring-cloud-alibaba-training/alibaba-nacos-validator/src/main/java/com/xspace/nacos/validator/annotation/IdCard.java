package com.xspace.nacos.validator.annotation;

import com.xspace.nacos.validator.annotation.validator.IdCardValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdCardValidator.class)
public @interface IdCard {

  // 提示信息
  String message() default "身份证号码不合法";

  // 分组
  Class<?>[] groups() default {};

  // 针对于Bean
  Class<? extends Payload>[] payload() default {};

}
