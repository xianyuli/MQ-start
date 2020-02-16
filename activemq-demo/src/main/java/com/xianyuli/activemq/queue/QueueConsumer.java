package com.xianyuli.activemq.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

@Component
public class QueueConsumer {
    /**
     * 使用JmsListener配置消费者监听的队列，其中Message是接收到的消息
     */
    @JmsListener(destination = "active.queue",containerFactory = "queueListenerContainerFactory")
    public void receiveQueue(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        try {
            String info = mapMessage.getString("queue-info");
            System.out.println(info+"不在！");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}
