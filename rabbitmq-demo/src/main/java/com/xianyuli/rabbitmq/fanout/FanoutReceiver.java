package com.xianyuli.rabbitmq.fanout;

import com.xianyuli.rabbitmq.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiver {

    @RabbitListener(queues = RabbitmqConfig.FANOUT_QUEUE1)
    public void receiverFanoutQueue1(String message) {
        System.out.println(RabbitmqConfig.FANOUT_QUEUE1 + "收到：" + message + RabbitmqConfig.FANOUT_QUEUE1);
    }
    @RabbitListener(queues = RabbitmqConfig.FANOUT_QUEUE2)
    public void receiverFanoutQueue2(String message) {
        System.out.println(RabbitmqConfig.FANOUT_QUEUE1 + "收到：" + message + RabbitmqConfig.FANOUT_QUEUE2);
    }
}
