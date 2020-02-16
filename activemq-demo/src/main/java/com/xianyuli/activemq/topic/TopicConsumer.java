package com.xianyuli.activemq.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

@Component
public class TopicConsumer {
    /**
     * # 消息类型 默认值为false，false：queue true：topic，topic开启就不能接受queue，可以通过自定义connectionFactory
     * jms:
     *   pub-sub-domain: true
     *
     *
     * @param message
     */

    @JmsListener(destination = "active.topic",containerFactory = "topicListenerContainerFactory")
    public void receiveTopic1(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        try {
            String string = mapMessage.getString("topic-info");
            System.out.println(string+"1号:我在");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "active.topic",containerFactory = "topicListenerContainerFactory")
    public void receiveTopic2(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        try {
            String string = mapMessage.getString("topic-info");
            System.out.println(string+"2号:我在");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
