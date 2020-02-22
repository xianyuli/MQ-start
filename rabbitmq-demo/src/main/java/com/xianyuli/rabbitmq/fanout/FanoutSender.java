package com.xianyuli.rabbitmq.fanout;

import com.xianyuli.rabbitmq.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 5000L)
    public void send() {
        rabbitTemplate.convertAndSend(RabbitmqConfig.FANOUT_EXCHANGE, "","bindingkey='',queue=");
    }

}
