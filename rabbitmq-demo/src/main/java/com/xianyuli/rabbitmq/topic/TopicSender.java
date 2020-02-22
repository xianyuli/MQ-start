package com.xianyuli.rabbitmq.topic;

import com.xianyuli.rabbitmq.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    //@Scheduled(fixedDelay = 5000L)
    public void send() {
        rabbitTemplate.convertAndSend(RabbitmqConfig.TOPIC_EXCHANGE, "topicQueue.msg", "bindingkey=topicQueue.msg,queue=");
        rabbitTemplate.convertAndSend(RabbitmqConfig.TOPIC_EXCHANGE, "topicQueue.abc.1", "bindingkey=topicQueue.abc.1,queue=");


    }


}
