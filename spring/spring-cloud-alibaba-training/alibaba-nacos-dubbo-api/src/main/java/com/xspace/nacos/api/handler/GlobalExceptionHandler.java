package com.xspace.nacos.api.handler;

import com.xspace.nacos.api.dto.ErrorResult;
import com.xspace.nacos.api.enumerate.MessageEnum;
import com.xspace.nacos.api.exception.UserAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice // 等同于@ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

  @ExceptionHandler(UserAuthenticationException.class)
  public ErrorResult userExceptionHandler(HttpServletRequest request, UserAuthenticationException e) {
    log.error("user unauthorized", e);
    return new ErrorResult(MessageEnum.UNAUTHORIZED.getCode(), MessageEnum.UNAUTHORIZED.getMessage(), "user error",
            request.getRequestURI());
  }
}
