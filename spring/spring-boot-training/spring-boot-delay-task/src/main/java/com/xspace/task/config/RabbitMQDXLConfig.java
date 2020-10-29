package com.xspace.task.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 延迟任务：TTL+DLX实现
 */
@Configuration
public class RabbitMQDXLConfig {
  public final static String QUEUE_NAME = "delay_queue";
  public final static String DELAY_NAME = "delay_key";
  public final static String EXCHANGE_NAME = "delay_exchange";

  /**
   * 延时队列交换机
   * 注意这里的交换机类型：CustomExchange
   *
   * @return
   */
  @Bean
  public CustomExchange delayExchange() {
    Map<String, Object> args = new HashMap<>();
    args.put("x-delayed-type", "direct");
    // 属性参数 交换机名称 交换机类型 是否持久化 是否自动删除 配置参数
    // 参数二为类型：必须是x-delayed-message
    return new CustomExchange(EXCHANGE_NAME, "x-delayed-message", true, false, args);
  }


  /**
   * 延时队列
   *
   * @return
   */
  @Bean
  public Queue delayQueue() {
    //属性参数 队列名称 是否持久化
    return new Queue(QUEUE_NAME, true);
  }

  /**
   * 给延时队列绑定交换机
   *
   * @return
   */
  @Bean
  public Binding cfgDelayBinding() {
    return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_NAME).noargs();
  }

}
