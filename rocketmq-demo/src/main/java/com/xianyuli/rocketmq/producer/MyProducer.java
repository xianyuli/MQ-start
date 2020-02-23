package com.xianyuli.rocketmq.producer;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MyProducer {
    private static final Logger log = LoggerFactory.getLogger(MyProducer.class);


    @Autowired
    private DefaultMQProducer defaultMQProducer;

    //@Scheduled(fixedDelay = 500L)
    public void send() throws RemotingException, MQClientException, InterruptedException {
        long time = System.currentTimeMillis();
        Message message = new Message("rocket_topic", "tag1", String.valueOf(time), "rocketmq测试消息".getBytes(StandardCharsets.UTF_8));
        defaultMQProducer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("传输成功");
                log.info(JSON.toJSONString(sendResult));
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("传输成功");
            }
        });

    }

}
