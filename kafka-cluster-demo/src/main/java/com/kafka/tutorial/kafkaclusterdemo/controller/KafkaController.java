package com.kafka.tutorial.kafkaclusterdemo.controller;

import com.kafka.tutorial.kafkaclusterdemo.Consumer;
import com.kafka.tutorial.kafkaclusterdemo.Producer;
import com.kafka.tutorial.kafkaclusterdemo.model.ProducerObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class KafkaController {
    @Autowired
    Producer producer;
    @Autowired
    Consumer consumer;
    @PostMapping("/produce")
    public void produce(@RequestBody ProducerObject producerObject){
        producer.produce(producerObject);
    }
}
