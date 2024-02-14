package com.example.redispubsub.controller;

import com.example.redispubsub.model.MessageModel;
import com.example.redispubsub.service.MessagePublishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MessageReceiverController {
    @Autowired
    private MessagePublishingService messageReceiverService;

    @PostMapping(value = "/sendMessage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<?>> receiveMessage(@RequestBody MessageModel messageModel) {
        return ResponseEntity.accepted().body(Mono.fromFuture(messageReceiverService.receiveMessage(messageModel)));
    }
}
