package com.xspace.nacos.api.config;

import com.xspace.nacos.api.filter.ResponseResultInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

  @Bean
  public ResponseResultInterceptor getResponseResultInterceptor() {
    return new ResponseResultInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(getResponseResultInterceptor())    //指定拦截器类
            .addPathPatterns("/**");        //指定该类拦截的url
  }
}
