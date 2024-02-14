package com.example.redispubsub.service;

import lombok.Getter;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.RedisSerializer;

public abstract class RedisAbstractReceiver extends MessageListenerAdapter {
    @Getter
    private String topic;

    public RedisAbstractReceiver(String topic) {
        this.topic = topic;
        super.setSerializer(RedisSerializer.java());
    }
    public abstract void handleMessage(Object message);
}