package com.xspace.nacos.api.exception;

public class IdempotentRuntimeException extends BaseRuntimeException{
  public IdempotentRuntimeException(String msg) {
    super(msg);
  }

  public IdempotentRuntimeException(Integer code, String defaultMsg, Object[] args) {
    super(code, defaultMsg, args);
  }

  public IdempotentRuntimeException(Integer code, String msg) {
    super(code, msg, new Object[0]);
  }

  public IdempotentRuntimeException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public IdempotentRuntimeException(Integer code, String msg, Throwable cause) {
    super(code, msg, cause, new Object[0]);
  }

  @Override
  public Throwable fillInStackTrace() {
    return this;
  }
}
