package com.xianyuli.rocketmq.config.producer;

import com.xianyuli.rocketmq.config.ProducerConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefualtProducer {
    private static final Logger log = LoggerFactory.getLogger(DefualtProducer.class);

    @Autowired
    private ProducerConfig producerConfig;

    /**
     * 创建普通消息发送者实例
     *
     * @return
     * @throws MQClientException
     */
    @Bean
    public DefaultMQProducer defaultMQProducer() throws MQClientException {
        log.info("开始创建defaultMQProducer=======================");
        log.info("producerConfig: {}", producerConfig.toString());
        DefaultMQProducer producer = new DefaultMQProducer(producerConfig.getGroupName());
        producer.setNamesrvAddr(producerConfig.getNamesrvAddr());
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.start();
        log.info("rocketmq DefaultMQProducer server创建成功=======================");
        return producer;
    }


}
