package com.xspace.nacos.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CurrentLimitingService {

  // 限流与阻塞处理
  @SentinelResource(value = "doSomething", blockHandler = "exceptionHandler")
  public void doSomething(String str) {
    log.info(str);
  }

  public void exceptionHandler(String str, BlockException e) {
    log.error("block handler: {}", str, e);
  }

  // 熔断与降级处理
  @SentinelResource(value = "doSomething2", fallback = "fallbackHandler")
  public void doSomething2(String str) {
    log.info(str);
    throw new RuntimeException("发生异常");
  }

  public void fallbackHandler(String str) {
    log.error("fall handler: {}", str);
  }
}
