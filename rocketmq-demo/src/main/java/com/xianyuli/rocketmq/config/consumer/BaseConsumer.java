package com.xianyuli.rocketmq.config.consumer;

import com.xianyuli.rocketmq.config.ConsumerConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public abstract class BaseConsumer {
    private static final Logger log = LoggerFactory.getLogger(BaseConsumer.class);

    public void listener(String topic, String tag, String groupName, String namesrvAddr) throws MQClientException {
        log.info("开启" + topic + ":" + tag + "消费者=======================");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.subscribe(topic, tag);
        //内部类实现监听  DefaultConsumerConfigure.this.dealBody(list)
        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> this.dealBody(list));
        consumer.start();
        log.info("rocketmq启动成功=======================");
    }

    public abstract ConsumeConcurrentlyStatus dealBody(List<MessageExt> msgs);

}
