package com.kafka.tutorial.kafkaclusterdemo.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;

@Configuration
public class ProducerConfiguration {
    //create kafka-properties
    @Autowired
    private KafkaProperties kafkaProperties;
    //create kafka producer
    private KafkaProducer<String, String> safeKafkaProducer;
    private KafkaProducer<String, String> highThroughputKafkaProducer;

    @Bean(name = "kafkaProducer")
    public KafkaTemplate<String, String> kafkaTemplate(){
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties()));
    }
    @Bean(name = "safeKafkaProducer")
    public KafkaTemplate<String, String> getSafeKafkaProducer(){
        //use different properties for other map as well something is miising
        Map<String, String> propertiesMap =  kafkaProperties.getProperties();
        propertiesMap.put(ProducerConfig.RETRIES_CONFIG, String.valueOf(Integer.MAX_VALUE));
        propertiesMap.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");
        propertiesMap.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties()));

    }
    @Bean(name = "highThroughputKafkaProducer")
    public KafkaTemplate<String, String> getHighThroughputKafkaProducer(){
        Map<String, String> propertiesMap =  kafkaProperties.getProperties();
        propertiesMap.put(ProducerConfig.RETRIES_CONFIG, String.valueOf(Integer.MAX_VALUE));
        propertiesMap.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");
        propertiesMap.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");

        propertiesMap.put(ProducerConfig.LINGER_MS_CONFIG,"20");
//        propertiesMap.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");
        propertiesMap.put(ProducerConfig.BATCH_SIZE_CONFIG,Integer.toString(32*1024));

        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties()));
    }
}
