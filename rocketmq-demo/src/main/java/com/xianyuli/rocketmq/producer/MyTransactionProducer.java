package com.xianyuli.rocketmq.producer;

import com.alibaba.fastjson.JSON;
import com.xianyuli.rocketmq.config.listener.MyTransactionListenner;
import com.xianyuli.rocketmq.config.producer.TransactionProducer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MyTransactionProducer {
    private static final Logger log = LoggerFactory.getLogger(MyTransactionProducer.class);


    @Autowired
    private TransactionMQProducer transactionMQProducer;

    @Autowired
    private MyTransactionListenner myTransactionListenner;

    @Scheduled(fixedDelay = 500L)
    public void send() throws RemotingException, MQClientException, InterruptedException {
        long time = System.currentTimeMillis();
        Message message = new Message("t_rocket_topic", "tag1", String.valueOf(time), "rocketmq测试消息".getBytes(StandardCharsets.UTF_8));
        transactionMQProducer.setTransactionListener(myTransactionListenner);
        transactionMQProducer.setSendMsgTimeout(10000);

        transactionMQProducer.sendMessageInTransaction(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("传输成功");
                log.info(JSON.toJSONString(sendResult));
            }
            @Override
            public void onException(Throwable e) {
                log.info("传输失败", e);
            }
        });

    }

}
