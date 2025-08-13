package com.cloud.psynea.service.impl.consumer;

import com.cloud.psynea.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserService userService;

    @KafkaListener(topics = {"updateStatus"},groupId = "group-1")
    public void listen(String data){

        userService.updateUser(data);

    }
}
