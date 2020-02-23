package com.xianyuli.rocketmq.config.listener;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
public class MyTransactionListenner implements TransactionListener {
    private static final Logger log = LoggerFactory.getLogger(MyTransactionListenner.class);

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        log.info(new String(message.getBody(), StandardCharsets.UTF_8));
        return LocalTransactionState.COMMIT_MESSAGE;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
