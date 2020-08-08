package com.xspace.nacos.validator.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArgumentInvalid implements Serializable {
  private static final long serialVersionUID = 7881558688890391815L;
  private String field;
  private String message;
}
