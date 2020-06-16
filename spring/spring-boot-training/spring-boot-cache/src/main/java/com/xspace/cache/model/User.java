package com.xspace.cache.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
  private static final long serialVersionUID = -6563747627860446527L;
  private long userId;
  private String userName;
}
