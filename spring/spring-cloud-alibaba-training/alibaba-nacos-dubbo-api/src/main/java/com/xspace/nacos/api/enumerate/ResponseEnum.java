package com.xspace.nacos.api.enumerate;

public enum ResponseEnum implements IEnum<Integer>{
  /*** 通用部分 100 - 599***/
  SUCCESS(200, "成功请求"),
  REDIRECT(301, "重定向"),
  BAD_REQUEST(400, "错误的请求"),
  UNAUTHORIZED(401, "未认证（签名错误）"),
  NOT_FOUND(404, "资源未找到"),
  SERVER_ERROR(500, "服务器错误"),

  /*** 这里可以根据不同模块用不同的区级分开错误码，例如:
   *  <p>1000～1999 区间表示用户模块错误
   *  <p>2000～2999 区间表示订单模块错误
   *  <p>3000～3999 区间表示商品模块错误
   * ***/
  ;
  private Integer code;
  private String message;

  ResponseEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public Integer getValue() {
    return this.code;
  }
}
