package com.xspace.nacos.api.dto;

import java.io.Serializable;

public class ErrorResult<T> extends Result implements Serializable {
  private static final long serialVersionUID = 7804951234365280536L;

  private T error;

  public ErrorResult(Integer code, String message, T error) {
    super(code, message, null);
    this.error = error;
  }

  public ErrorResult(Integer code, String message, T error, String url) {
    super(code, message, null);
    this.error = error;
    super.setUrl(url);
  }

  public T getError() {
    return error;
  }

  public void setError(T error) {
    this.error = error;
  }
}
