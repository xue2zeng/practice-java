package com.xspace.nacos.api.exception;

import java.util.Date;

public interface BaseException {
  Integer getCode();

  String[] getArgs();

  void setTime(Date var1);

  Date getTime();

  void setClassName(String var1);

  String getClassName();

  void setMethodName(String var1);

  String getMethodName();

  void setParameters(String[] var1);

  String[] getParameters();

  void setHandled(boolean var1);

  boolean isHandled();

  String getMessage();

  void setI18NMessage(String var1);

  String getI18NMessage();
}
