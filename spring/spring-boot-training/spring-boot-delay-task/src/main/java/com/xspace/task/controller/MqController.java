package com.xspace.task.controller;

import com.xspace.task.mq.receiver.RabbitMQDXLReceiver;
import com.xspace.task.mq.sender.DelayedDXLSender;
import com.xspace.task.mq.sender.DelayedTTLSender;
import com.xspace.task.mq.sender.MQSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/mq")
public class MqController {

    @Autowired
    private MQSender mqSender;

    @Autowired
    private DelayedDXLSender delayedDXLSender;

    @Autowired
    private DelayedTTLSender delayedTTLSender;

    @GetMapping(value = "/send/delay")
    public void sendDelay(int delayTime) {
        String msg = "hello delay";
        log.info("发送开始时间: {}, 测试发送delay消息====> {}", LocalDateTime.now(), msg);
        mqSender.sendDelay(msg, delayTime);
    }

    @GetMapping(value = "/send/delay/dxl")
    public void sendDelayDxl(int delayTime) {
        String msg = "hello delay";
        log.info("发送开始时间: {}, 测试发送delay消息====> {}", LocalDateTime.now(), msg);
        delayedDXLSender.sendDelay(msg, delayTime);
    }

    @GetMapping(value = "/send/delay/ttl")
    public void sendDelayTll(int delayTime) {
        String msg = "hello delay";
        log.info("发送开始时间: {}, 测试发送delay消息====> {}", LocalDateTime.now(), msg);
        delayedTTLSender.sendDelay(msg, delayTime);
    }
}