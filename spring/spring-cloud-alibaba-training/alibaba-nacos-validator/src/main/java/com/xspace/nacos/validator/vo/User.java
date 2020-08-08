package com.xspace.nacos.validator.vo;

import com.xspace.nacos.validator.annotation.IdCard;
import com.xspace.nacos.validator.group.Create;
import com.xspace.nacos.validator.group.Update;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class User implements Serializable {

  private static final long serialVersionUID = 7809158721338632766L;
  @NotBlank(message = "用户名不能为空", groups = Create.class)
  @NotNull(message = "用户名不能为空", groups = Create.class)
  private String userName;
  @NotBlank(message = "手机号不能为空")
  @NotNull(message = "手机不能为空")
  @Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$"
          , message = "手机号格式错误")
  @Size(min = 11, max = 11, message = "手机号只能为11位")
  private String phone;
  @NotNull(message = "性别不能为空")
  private Integer sex;
  @NotNull(message = "性别不能为空")
  @Min(value = 6, message = "年龄不能小于{min}岁")
  @Max(value = 100, message = "手机号只能为{max}位")
  private Integer age;
  @NotBlank(message = "邮箱不能为空", groups = Update.class)
  @NotNull(message = "邮箱不能为空", groups = Update.class)
  @Email(message = "邮箱格式错误", groups = Update.class)
  private String email;

  @NotNull(message = "身份证号不能为空")
  @IdCard(message = "身份证不合法")
  private String IdCardNumber;

  @NotNull(groups = {Create.class, Update.class})
  @Valid
  private Job job;

  @Data
  public static class Job {

    @Min(value = 1, groups = Update.class)
    private Long jobId;

    @NotNull(groups = {Create.class, Update.class})
    @Size(min = 2, max = 10, groups = {Create.class, Update.class})
    private String jobName;

    @NotNull(groups = {Create.class, Update.class})
    @Size(min = 2, max = 10, groups = {Create.class, Update.class})
    private String position;
  }
}
