package com.example.redispubsub.service;

import com.example.redispubsub.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class MessagePublishingService extends RedisAbstractProducer {

    public MessagePublishingService() {
        super("testTopic");
    }

    public CompletableFuture<?> receiveMessage(MessageModel message) {
        publish(message);
        return CompletableFuture.completedFuture(message);
    }
}
