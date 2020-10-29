package com.xspace.task.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 延迟队列：rabbitmq_delayed_message_exchange插件实现
 */
@Configuration
public class RabbitMQConfig {
  @Bean
  public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(new Jackson2JsonMessageConverter());
    factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    return factory;
  }

  public static final String DELAY_EXCHANGE = "Ex.DelayExchange";
  public static final String DELAY_QUEUE = "MQ.DelayQueue";
  public static final String DELAY_KEY = "delay.#";

  /**
   * 延时交换机
   *
   * @return TopicExchange
   */
  @Bean
  public TopicExchange delayExchange() {
    Map<String, Object> pros = new HashMap<>(3);
    //设置交换机支持延迟消息推送
    pros.put("x-delayed-message", "topic");
    TopicExchange exchange = new TopicExchange(DELAY_EXCHANGE, true, false, pros);
    //我们在也可以在 Exchange 的声明中可以设置exchange.setDelayed(true)来开启延迟队列
    exchange.setDelayed(true);
    return exchange;
  }

  /**
   * 延时队列
   *
   * @return Queue
   */
  @Bean
  public Queue delayQueue() {
    return new Queue(DELAY_QUEUE, true);
  }

  /**
   * 绑定队列和交换机,以及设定路由规则key
   *
   * @return Binding
   */
  @Bean
  public Binding delayBinding() {
    return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_KEY);
  }
}
