package com.example.springactivemqjms.controller;

import com.example.springactivemqjms.model.MessageModel;
import com.example.springactivemqjms.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageReceiverController {

    @Autowired
    private ProducerService producerService;

    @PostMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> receiveMessage(@RequestHeader("Sender") String sender, @RequestBody MessageModel message) {
        producerService.sendToQueue(sender, message);
        return ResponseEntity
                .accepted()
                .build();
    }
}
