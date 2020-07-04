package com.xspace.nacos;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AlibabaNacosSentinelRateLimitingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosSentinelRateLimitingApplication.class, args);
    }

    // 注解支持的配置bean
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    @RestController
    static class TestController {

        @GetMapping("/sentinel")
        public String hello() {
            return "hi, xue.zeng";
        }
    }
}
