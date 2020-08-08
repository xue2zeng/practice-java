package com.xspace.nacos.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {
  public static ObjectMapper objectMapper = new ObjectMapper();

  public static ObjectMapper getInstance() {
    return objectMapper;
  }
}
