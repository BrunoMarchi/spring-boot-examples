package com.example.springactivemqjms.service;

import com.example.springactivemqjms.model.MessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

@Slf4j
@Service
public class ConsumerService {

    @JmsListener(destination = "test.queue")//, containerFactory = "defaultJmsListenerContainerFactory")
    public void receiver(MessageModel message, @Header("sender") String sender) throws JMSException {
        log.info("Message: " + message);
        log.info("Sender: " + sender);
    }
}
