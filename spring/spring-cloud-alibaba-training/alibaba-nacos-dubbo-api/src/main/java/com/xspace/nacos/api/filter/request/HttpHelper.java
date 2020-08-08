package com.xspace.nacos.api.filter.request;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
public class HttpHelper {
  public static String getBodyString(HttpServletRequest request) throws IOException {
    StringBuilder sb = new StringBuilder();
    InputStream inputStream = null;
    BufferedReader reader = null;
    try {
      inputStream = request.getInputStream();
      reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
      String line = "";
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      log.error("The reader request failure.", e);
    } finally {
      if (Objects.nonNull(inputStream)) {
        try {
          inputStream.close();
        } catch (IOException e) {
          log.error("The input stream close failure.", e);
        }
      }
      if (Objects.nonNull(reader)) {
        try {
          reader.close();
        } catch (IOException e) {
          log.error("The stream stream close failure.", e);
        }
      }
    }
    return sb.toString();
  }
}
