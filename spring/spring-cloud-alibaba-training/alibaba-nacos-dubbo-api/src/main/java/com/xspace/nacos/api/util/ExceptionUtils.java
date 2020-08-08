package com.xspace.nacos.api.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
public class ExceptionUtils extends org.apache.commons.lang3.exception.ExceptionUtils {
  public ExceptionUtils() {
  }

  public static String[] convertArgsToString(Object[] args) {
    String[] argsStrs = new String[args.length];

    for (int i = 0; i < args.length; ++i) {
      argsStrs[i] = String.valueOf(args[i]);
    }

    return argsStrs;
  }

  public static String toString(Throwable e) {
    return toString("", e);
  }

  public static String toString(String msg, Throwable e) {
    StringWriter w = new StringWriter();
    w.write(msg);
    PrintWriter p = new PrintWriter(w);
    p.println();

    String var4;
    try {
      e.printStackTrace(p);
      var4 = w.toString();
    } finally {
      p.close();
      try {
        w.close();
      } catch (IOException ioException) {
        log.error("The string writer is close failure.", ioException);
      }
    }

    return var4;
  }

}
