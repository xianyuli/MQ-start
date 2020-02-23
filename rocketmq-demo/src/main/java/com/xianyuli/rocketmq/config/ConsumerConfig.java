package com.xianyuli.rocketmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rocketmq.consumer")
public class ConsumerConfig {
    private String groupName;

    private String namesrvAddr;

    private String transactionGroupName;

    public String getTransactionGroupName() {
        return transactionGroupName;
    }

    public void setTransactionGroupName(String transactionGroupName) {
        this.transactionGroupName = transactionGroupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }
}
