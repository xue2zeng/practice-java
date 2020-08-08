package com.xspace.nacos.api.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * The Class SpringApplicationContextUtils.
 */
@Component
public class SpringApplicationContextUtils<T> implements ApplicationContextAware {
  /** The Constant self. */
  private static final SpringApplicationContextUtils self = new SpringApplicationContextUtils();

  /** The application context. */
  private ApplicationContext applicationContext;

  /**
   * 服务器启动，Spring容器初始化时，当加载了当前类为bean组件后， 将会调用下面方法注入ApplicationContext实例.
   *
   * @param applicationContext the new application context
   */
  @Override
  public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
    self.applicationContext = applicationContext;
  }

  /**
   * Gets the application context.
   *
   * @return the application context
   */
  public static ApplicationContext getApplicationContext() {
    return self.applicationContext;
  }

  /**
   * 外部调用这个getBean方法就可以手动获取到bean 用bean组件的name来获取bean.
   *
   * @param <T>      the generic type
   * @param beanName the bean name
   * @return the bean
   */
  @SuppressWarnings("unchecked")
  public static <T> T getBean(String beanName) {
    return (T) self.applicationContext.getBean(beanName);
  }

  @SuppressWarnings("unchecked")
  public static <T> T getBean(Class<T> clazz) throws BeansException {
    return (T) self.applicationContext.getBean(clazz);
  }
}
