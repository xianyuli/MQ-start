package com.xianyuli.activemq.topic;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Topic;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@EnableScheduling
public class TopicProducer {

    @Autowired
    private Topic topic;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Scheduled(fixedDelay = 10000)
    public void send() {
        ActiveMQMapMessage activeMQMapMessage = new ActiveMQMapMessage();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        try {
            activeMQMapMessage.setString("topic-info","topic ===== "+time+"  群主：谁在线？");

            jmsMessagingTemplate.convertAndSend(topic,activeMQMapMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }
}
