package com.xspace.task.mq.receiver;

import com.rabbitmq.client.Channel;
import com.xspace.task.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Slf4j
public class MQReceiver {
    @RabbitListener(queues = RabbitMQConfig.DELAY_QUEUE)
    @RabbitHandler
    public void onDelayMessage(Message msg, Channel channel) throws IOException {
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, true);
        log.info("延迟队列在 [{}] 时间, 延迟后收到消息: {}" , LocalDateTime.now(), new String(msg.getBody()));
    }
}