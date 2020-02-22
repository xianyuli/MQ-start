package com.xianyuli.rabbitmq.topic;

import com.xianyuli.rabbitmq.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {

    @RabbitListener(queues = RabbitmqConfig.TOPIC_QUEUE1)
    public void receiverTopicQueue1(String message) {
        System.out.println(RabbitmqConfig.TOPIC_QUEUE1 + "收到：" + message + RabbitmqConfig.TOPIC_QUEUE1);
    }

    @RabbitListener(queues = RabbitmqConfig.TOPIC_QUEUE2)
    public void receiverTopicQueue2(String message) {
        System.out.println(RabbitmqConfig.TOPIC_QUEUE2 + "收到：" + message + RabbitmqConfig.TOPIC_QUEUE2);
    }

}
