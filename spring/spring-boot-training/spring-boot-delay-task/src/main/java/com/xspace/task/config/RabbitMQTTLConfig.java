package com.xspace.task.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class RabbitMQTTLConfig {
  public final static String DELAY_QUEUE_NAME = "delay_queue";
  public final static String RECEIVE_QUEUE_NAME = "receive_queue";
  public final static String RECEIVE_NAME = "receive_key";
  public final static String DELAY_NAME = "delay_key";
  public final static String DELAY_EXCHANGE_NAME = "delay_exchange";
  public final static String RECEIVE__EXCHANGE_NAME = "receive_exchange";
  /**
   * 死信交换机
   *
   * @return
   */
  @Bean
  public DirectExchange userOrderDelayExchange() {
    return new DirectExchange(DELAY_EXCHANGE_NAME);
  }

  /**
   * 死信队列
   *
   * @return
   */
  @Bean
  public Queue userOrderDelayQueue() {
    Map<String, Object> map = new HashMap<>(16);
    map.put("x-dead-letter-exchange", RECEIVE__EXCHANGE_NAME);
    map.put("x-dead-letter-routing-key", RECEIVE_NAME);
    return new Queue(DELAY_QUEUE_NAME, true, false, false, map);
  }

  /**
   * 给死信队列绑定交换机
   *
   * @return
   */
  @Bean
  public Binding userOrderDelayBinding() {
    return BindingBuilder.bind(userOrderDelayQueue()).to(userOrderDelayExchange()).with(DELAY_NAME);
  }

  /**
   * 死信接收交换机
   *
   * @return
   */
  @Bean
  public DirectExchange userOrderReceiveExchange() {
    return new DirectExchange(RECEIVE__EXCHANGE_NAME);
  }

  /**
   * 死信接收队列
   *
   * @return
   */
  @Bean
  public Queue userOrderReceiveQueue() {
    return new Queue(RECEIVE_QUEUE_NAME);
  }

  /**
   * 死信交换机绑定消费队列
   *
   * @return
   */
  @Bean
  public Binding userOrderReceiveBinding() {
    return BindingBuilder.bind(userOrderReceiveQueue()).to(userOrderReceiveExchange()).with(RECEIVE_NAME);
  }

}
