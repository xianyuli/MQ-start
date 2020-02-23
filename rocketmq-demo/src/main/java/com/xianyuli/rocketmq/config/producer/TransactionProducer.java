package com.xianyuli.rocketmq.config.producer;

import com.xianyuli.rocketmq.config.ProducerConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class TransactionProducer {
    private static final Logger log = LoggerFactory.getLogger(TransactionProducer.class);

    @Autowired
    private ProducerConfig producerConfig;

    @Bean
    public TransactionMQProducer transactionMQProducer() throws MQClientException {
        log.info("开始创建TransactionMQProducer=======================");
        log.info("producerConfig: {}", producerConfig.toString());
        TransactionMQProducer producer = new TransactionMQProducer(producerConfig.getTransactionGroupName());
        producer.setNamesrvAddr(producerConfig.getNamesrvAddr());
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2000), thread -> {
                    Thread t = new Thread();
                    t.setName("client-transaction-msg-check-thread");
                    return t;
                });
        producer.setExecutorService(executorService);
        producer.start();
        log.info("rocketmq TransactionMQProducer server创建成功=======================");
        return producer;
    }

}
