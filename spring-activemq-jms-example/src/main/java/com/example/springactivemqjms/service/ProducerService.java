package com.example.springactivemqjms.service;

import com.example.springactivemqjms.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendToQueue(String sender, MessageModel message) {
        jmsTemplate.convertAndSend(
                "test.queue",
                message,
                m -> {
                    m.setStringProperty("sender", sender);
                    return m;
                }
        );
    }
}
