package com.xspace.nacos.validator.handler;

import com.google.common.collect.Lists;
import com.xspace.nacos.api.dto.ErrorResult;
import com.xspace.nacos.api.dto.Result;
import com.xspace.nacos.api.enumerate.ResponseEnum;
import com.xspace.nacos.validator.vo.ArgumentInvalid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Slf4j
@RestControllerAdvice // 等同于@ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

  /**
   * 方法参数校验
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Result handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
    List<ArgumentInvalid> argumentsInvalids = Lists.newArrayList();
    for (FieldError error : e.getBindingResult().getFieldErrors()) {
      ArgumentInvalid bean = new ArgumentInvalid();
      bean.setField(error.getField());
      bean.setMessage(error.getDefaultMessage());
      argumentsInvalids.add(bean);
    }

    log.error(e.getMessage(), e);
    return new ErrorResult(ResponseEnum.BAD_REQUEST.getCode(), ResponseEnum.BAD_REQUEST.getMessage(),
            argumentsInvalids,
            request.getRequestURI());
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public Result handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException e) {
    log.error(e.getMessage(), e);
    return new ErrorResult(ResponseEnum.BAD_REQUEST.getCode(), ResponseEnum.BAD_REQUEST.getMessage(),
            e.getMessage(),
            request.getRequestURI());
  }
}