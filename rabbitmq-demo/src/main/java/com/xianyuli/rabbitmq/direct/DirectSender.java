package com.xianyuli.rabbitmq.direct;

import com.xianyuli.rabbitmq.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DirectSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 此处为routingkey与bindingkey匹配，DIRECT_EXCHANGE将消息发送到对应的queue
     */
    //@Scheduled(fixedDelay = 1000L)
    public void send1() {
        rabbitTemplate.convertAndSend(RabbitmqConfig.DIRECT_EXCHANGE, "bindingkey1", "bindingkey=bindingkey1，queue=");
    }

    //@Scheduled(fixedDelay = 5000L)
    public void send2() {
        rabbitTemplate.convertAndSend(RabbitmqConfig.DIRECT_EXCHANGE, "bindingkey2", "bindingkey=bindingkey2，queue=");
    }

}
