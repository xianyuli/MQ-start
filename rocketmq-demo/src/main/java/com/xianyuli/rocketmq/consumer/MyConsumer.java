package com.xianyuli.rocketmq.consumer;

import com.xianyuli.rocketmq.config.ConsumerConfig;
import com.xianyuli.rocketmq.config.consumer.BaseConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class MyConsumer extends BaseConsumer implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LoggerFactory.getLogger(MyConsumer.class);

    @Autowired
    private ConsumerConfig consumerConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            super.listener("rocket_topic", "tag1", consumerConfig.getGroupName(), consumerConfig.getNamesrvAddr());
        } catch (MQClientException e) {
            log.error("消费者监听器启动失败", e);
        }
    }


    @Override
    public ConsumeConcurrentlyStatus dealBody(List<MessageExt> msgs) {
        int num = 1;
        log.info("进入MyConsumer");
        msgs.forEach(msg -> {
            log.info("第" + num + "次消息");
            String msgStr = new String(msg.getBody(), StandardCharsets.UTF_8);
            System.out.println(msgStr);
        });
        log.info("消费完成");
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
