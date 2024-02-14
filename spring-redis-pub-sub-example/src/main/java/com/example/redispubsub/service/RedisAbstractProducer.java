package com.example.redispubsub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public abstract class RedisAbstractProducer extends RedisAbstractLowLevelProducer{
    private String topic;

    public RedisAbstractProducer(String topic) {
        this.topic = topic;
    }

    public void publish(Object message) {
        publish(message, topic);
    }
}
