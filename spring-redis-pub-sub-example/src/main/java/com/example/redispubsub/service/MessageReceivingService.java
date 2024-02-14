package com.example.redispubsub.service;

import com.example.redispubsub.model.MessageModel;
import org.springframework.stereotype.Service;

@Service
public class MessageReceivingService extends RedisAbstractReceiver {

    public MessageReceivingService() {
        super("testTopic");
    }

    @Override
    public void handleMessage(Object message) {
        MessageModel myMessage = (MessageModel) message;
        Integer id = myMessage.getId();
        try {
            Thread.sleep(1000*id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Message: " + id);
    }
}
