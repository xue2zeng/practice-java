package com.xspace.nacos.api.annotation;

import com.xspace.nacos.api.dto.ErrorResult;
import com.xspace.nacos.api.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class ResponseResultAdvice implements ResponseBodyAdvice<Object> {
  // 标记名称
  private static final String RESPONSE_RESULT_ANN = "RESPONSE_RESULT_ANN";

  @Override
  public boolean supports(MethodParameter methodParameter, Class clazz) {
    ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = sra.getRequest();
    ResponseResult result = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
    return Objects.isNull(result) ? false : true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                Class clazz, ServerHttpRequest serverHttpRequest,
                                ServerHttpResponse serverHttpResponse) {
    log.info("Enter the format processing of response body");

    //如果返回的数据是Result、Byte、String类型则不进行封装
    if (body instanceof Result || body instanceof Byte || body instanceof String) {
      return body;
    }

    if (body instanceof ErrorResult) {
      log.info("Enter the format processing of exception response body");
      ErrorResult errorResult = (ErrorResult) body;
      return Result.failure(errorResult.getCode(), errorResult.getMessage(), errorResult.getError(), errorResult.getUrl());
    }
    return Result.success(body);
  }
}
