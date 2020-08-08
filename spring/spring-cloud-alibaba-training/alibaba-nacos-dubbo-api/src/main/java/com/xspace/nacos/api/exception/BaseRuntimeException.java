package com.xspace.nacos.api.exception;

import com.xspace.nacos.api.util.ExceptionUtils;
import org.springframework.core.NestedRuntimeException;

import java.util.Date;

public class BaseRuntimeException extends NestedRuntimeException implements BaseException {
  private Integer code;
  private Date time;
  private String[] args;
  private String className;
  private String methodName;
  private String[] parameters;
  private boolean handled;
  private String i18NMessage;

  public BaseRuntimeException(Integer code, String defaultMessage, Object[] args) {
    super(defaultMessage);
    this.code = code;
    this.args = ExceptionUtils.convertArgsToString(args);
  }

  public BaseRuntimeException(Integer code, String defaultMessage, Throwable cause, Object[] args) {
    super(defaultMessage, cause);
    this.code = code;
    this.args = ExceptionUtils.convertArgsToString(args);
  }

  public BaseRuntimeException(String defaultMessage, Throwable cause) {
    super(defaultMessage, cause);
  }

  public BaseRuntimeException(String defaultMessage) {
    super(defaultMessage);
  }

  @Override
  public Integer getCode() {
    return this.code;
  }

  @Override
  public Date getTime() {
    return this.time;
  }

  @Override
  public void setTime(Date time) {
    this.time = time;
  }

  @Override
  public String getClassName() {
    return this.className;
  }

  @Override
  public void setClassName(String className) {
    this.className = className;
  }

  @Override
  public String getMethodName() {
    return this.methodName;
  }

  @Override
  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  @Override
  public String[] getParameters() {
    return this.parameters;
  }

  @Override
  public void setParameters(String[] parameters) {
    this.parameters = parameters;
  }

  @Override
  public void setHandled(boolean handled) {
    this.handled = handled;
  }

  @Override
  public boolean isHandled() {
    return this.handled;
  }

  @Override
  public void setI18NMessage(String i18nMessage) {
    this.i18NMessage = i18NMessage;
  }

  @Override
  public String getI18NMessage() {
    return this.i18NMessage;
  }

  @Override
  public String[] getArgs() {
    return this.args;
  }
}
