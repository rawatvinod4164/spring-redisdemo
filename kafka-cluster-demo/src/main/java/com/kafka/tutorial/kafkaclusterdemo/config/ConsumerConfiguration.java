package com.kafka.tutorial.kafkaclusterdemo.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfiguration {//should seperate base config for consumer
    @Autowired
    KafkaProperties kafkaProperties;
    private KafkaConsumer<String, String> kafkaConsumer;

    @Bean
    public KafkaConsumer<String, String> getKafkaConsumer() {
        kafkaProperties.getProperties().put(ConsumerConfig.GROUP_ID_CONFIG,"test-consumer-group");
        kafkaProperties.getProperties().put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
        kafkaProperties.getProperties().put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
        kafkaConsumer = new KafkaConsumer<>(kafkaProperties.buildConsumerProperties());
        return kafkaConsumer;
    }
}
