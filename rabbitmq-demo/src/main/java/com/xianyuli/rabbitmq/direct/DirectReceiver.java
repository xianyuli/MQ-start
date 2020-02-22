package com.xianyuli.rabbitmq.direct;

import com.xianyuli.rabbitmq.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {

    @RabbitListener(queues = RabbitmqConfig.DIRECT_QUEUE1)
    public void receiverDirectQueue1(String message) {
        System.out.println(RabbitmqConfig.DIRECT_QUEUE1 + "收到：" + message + RabbitmqConfig.DIRECT_QUEUE1);
    }

    @RabbitListener(queues = RabbitmqConfig.DIRECT_QUEUE2)
    public void receiverDirectQueue2(String message) {
        System.out.println("============" + RabbitmqConfig.DIRECT_QUEUE2 + "============");
        System.out.println(RabbitmqConfig.DIRECT_QUEUE2 + "收到：" + message + RabbitmqConfig.DIRECT_QUEUE2);
        System.out.println("============" + RabbitmqConfig.DIRECT_QUEUE2 + "============");
    }

}
