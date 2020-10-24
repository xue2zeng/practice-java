package com.xspace.micro.common;

import com.xspace.micro.common.converter.UserMapping;
import com.xspace.micro.common.model.po.User;
import com.xspace.micro.common.model.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

@Slf4j
@SpringBootTest
class AlibabaNacosMicroCommonApplicationTests {

  @Resource
  private UserMapping userMapping;

  @Test
  public void tetDomain2DTO() {
    User user = new User()
            .setId(1L)
            .setUsername("xue.zeng")
            .setSex(1)
            .setPassword("123456")
            .setCreateTime(LocalDateTime.now())
            .setBirthday(LocalDate.of(1999, 9, 27))
            .setConfig("[{\"field1\":\"Test Field1\",\"field2\":500}]");
    UserVo userVo = userMapping.sourceToTarget(user);
    log.info("User: {}", user);
    //        User: User(id=1, username=zhangsan, password=abc123, sex=1, birthday=1999-09-27,
    //        createTime=2020-01-17T17:46:20.316, config=[{"field1":"Test Field1","field2":500}])
    log.info("UserVo: {}", userVo);
    //        UserVo: UserVo(id=1, username=zhangsan, gender=1, birthday=1999-09-27, createTime=2020-01-17 17:46:20,
    //        config=[UserVo.UserConfig(field1=Test Field1, field2=500)])
  }

  @Test
  public void testDTO2Domain() {
    UserVo.UserConfig userConfig = new UserVo.UserConfig();
    userConfig.setField1("Test Field1");
    userConfig.setField2(500);

    UserVo userVo = new UserVo()
            .setId(1L)
            .setUsername("xue.zeng")
            .setGender(2)
            .setCreateTime("2020-01-18 15:32:54")
            .setBirthday(LocalDate.of(1999, 9, 27))
            .setConfig(Collections.singletonList(userConfig));
    User user = userMapping.targetToSource(userVo);
    log.info("UserVo: {}", userVo);
    //        UserVo: UserVo(id=1, username=zhangsan, gender=2, birthday=1999-09-27, createTime=2020-01-18 15:32:54,
    //        config=[UserVo.UserConfig(field1=Test Field1, field2=500)])
    log.info("User: {}", user);
    //        User: User(id=1, username=zhangsan, password=null, sex=2, birthday=1999-09-27,
    //        createTime=2020-01-18T15:32:54, config=[{"field1":"Test Field1","field2":500}])
  }

}
