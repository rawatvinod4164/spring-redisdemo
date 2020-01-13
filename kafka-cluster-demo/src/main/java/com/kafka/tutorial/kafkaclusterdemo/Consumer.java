package com.kafka.tutorial.kafkaclusterdemo;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private static final int consumerThreads = 100;
    private ExecutorService executorService;
    @Autowired
    private KafkaConsumer<String, String> kafkaConsumer;

    @PostConstruct
    public void init() {
        executorService = new ThreadPoolExecutor(consumerThreads, consumerThreads, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @KafkaListener(id="consumer_1", topics = "MultipleBrokerTopic", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer1(String payload) {
        logger.info("Consumer 1 : received payload='{}' ", payload);
    }

    @KafkaListener(id="consumer_2", topics = "MultipleBrokerTopic", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer2(String payload) {
        logger.info("Consumer 2 : received payload='{}' ", payload);
    }
    @KafkaListener(id="consumer_3", topics = "MultipleBrokerTopic", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer3(String payload) {
        logger.info("Consumer 3 : received payload='{}' ", payload);
    }
}
