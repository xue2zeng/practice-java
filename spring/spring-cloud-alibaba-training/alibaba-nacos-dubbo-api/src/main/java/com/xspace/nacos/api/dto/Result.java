package com.xspace.nacos.api.dto;

import com.xspace.nacos.api.enumerate.MessageEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
  private static final long serialVersionUID = 7694032958760634992L;

  private Integer code;
  private String message;
  private T data;
  private String url;

  public Result(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public Result(Integer code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public static <T> Result<T> success() {
    return new Result<T>(MessageEnum.SUCCESS.getCode(), MessageEnum.SUCCESS.getMessage());
  }

  public static <T> Result<T> success(T data) {
    Result result = success();
    result.setData(data);
    return result;
  }

  public static <T> Result<T> failure(MessageEnum messageEnum) {
    return new Result<T>(messageEnum.getCode(), messageEnum.getMessage());
  }

  public static <T> Result<T> failure(MessageEnum messageEnum, T data) {
    Result<T> result = failure(messageEnum);
    result.setData(data);
    return result;
  }

  public static <T> Result<T> failure(Integer code, String message, T data) {
    Result<T> result = new Result<T>(code, message);
    result.setData(data);
    return result;
  }

  public static <T> Result<T> failure(Integer code, String message, T data, String url) {
    Result<T> result = failure(code, message, data);
    result.setUrl(url);
    return result;
  }
}
