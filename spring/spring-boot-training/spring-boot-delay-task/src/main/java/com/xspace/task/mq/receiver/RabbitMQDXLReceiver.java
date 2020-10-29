package com.xspace.task.mq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class RabbitMQDXLReceiver {

    //监听消息队列
    @RabbitListener(queues = "delay_queue")
    @RabbitHandler
    public void consumeMessage(String message) {
        log.info("延迟队列在 [{}] 时间, 延迟后收到消息: {}" , LocalDateTime.now(), message);
    }
}