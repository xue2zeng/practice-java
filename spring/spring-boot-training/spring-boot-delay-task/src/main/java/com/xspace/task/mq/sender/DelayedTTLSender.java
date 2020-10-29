package com.xspace.task.mq.sender;

import com.xspace.task.config.RabbitMQDXLConfig;
import com.xspace.task.config.RabbitMQTTLConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class DelayedTTLSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendDelay(String msg, Integer time) {

        log.info("发送时间： [{}]" , LocalDateTime.now(), msg);

        rabbitTemplate.convertAndSend(RabbitMQTTLConfig.DELAY_EXCHANGE_NAME, RabbitMQTTLConfig.DELAY_NAME, msg,
                new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //message.getMessageProperties().setHeader("x-delay", time);
                message.getMessageProperties().setExpiration(time.toString());
                return message;
            }
        });
    }
}