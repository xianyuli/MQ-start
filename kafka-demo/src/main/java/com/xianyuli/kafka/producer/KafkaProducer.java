package com.xianyuli.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final String MY_TOPIC = "TOPIC_KAFKA";

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Scheduled(fixedDelay = 5000L)
    public void produce(){
        kafkaTemplate.send(MY_TOPIC,"测试kafka消息");
    }

}
