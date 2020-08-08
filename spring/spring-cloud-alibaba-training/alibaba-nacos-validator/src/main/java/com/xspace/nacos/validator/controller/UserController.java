package com.xspace.nacos.validator.controller;

import com.google.common.collect.Lists;
import com.xspace.nacos.api.annotation.ResponseResult;
import com.xspace.nacos.validator.annotation.validator.ValidationList;
import com.xspace.nacos.validator.group.Create;
import com.xspace.nacos.validator.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@ResponseResult
@RestController
@RequestMapping("/user")
public class UserController {

  @PostMapping(value = "/add")
  public User userRegister(@RequestBody @Validated(Create.class) User user) {
    return user;
  }

  @PostMapping("/list/save")
  public List<User> saveList(@RequestBody @Validated(Create.class) ValidationList<User> userList) {
    return Lists.newArrayList(userList);
  }

  @Autowired
  private javax.validation.Validator globalValidator;

  // 编程式校验
  @PostMapping("/saveWithCodingValidate")
  public User saveWithCodingValidate(@RequestBody User user) {
    Set<ConstraintViolation<User>> validate = globalValidator.validate(user, Create.class);
    // 如果校验通过，validate为空；否则，validate包含未校验通过项
    if (validate.isEmpty()) {
      // 校验通过，才会执行业务逻辑处理

    } else {
      for (ConstraintViolation<User> userDTOConstraintViolation : validate) {
        // 校验失败，做其它逻辑
        System.out.println(userDTOConstraintViolation);
      }
    }
    return user;
  }
}
