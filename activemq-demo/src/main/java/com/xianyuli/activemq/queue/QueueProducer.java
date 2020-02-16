package com.xianyuli.activemq.queue;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Queue;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@EnableScheduling
public class QueueProducer {

    @Autowired
    private Queue queue;

    /**
     *  也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
     */
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Scheduled(fixedDelay=3000)
    public void send() {
        ActiveMQMapMessage activeMQMapMessage = new ActiveMQMapMessage();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        try {
            activeMQMapMessage.setString("queue-info","queue ===== "+time+" 在吗？");
            jmsMessagingTemplate.convertAndSend(queue,activeMQMapMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
