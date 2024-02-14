package com.example.redispubsub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisAbstractLowLevelProducer {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void publish(Object message, String topic) {
        redisTemplate.convertAndSend(topic, message);
    }
}
