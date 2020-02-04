package com.kafka.tutorial.kafkaclusterdemo;

import com.kafka.tutorial.kafkaclusterdemo.model.ProducerObject;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    final
    KafkaTemplate<String ,String > producer;

    public Producer(@Qualifier(value = "highThroughputKafkaProducer") KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    public void produce(ProducerObject producerObject) {
        //create record
        ProducerRecord<String,String > record;
        // sending to kafka producer there is callback if something happens
        for (int i=0 ;i<20;i++) {
            record= new ProducerRecord<>(producerObject.getTopic(), //topic name
                    producerObject.getKey()+""+i //key
                    ,producerObject.getValue()+""+System.currentTimeMillis() //value
            );

            logger.info(record.toString());
            ListenableFuture<SendResult<String, String>> future = producer.send(record.topic(), record.key(),record.value());
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onSuccess(final SendResult<String, String> message) {
                    logger.info("sent message= " + message + " with offset= " + message.getRecordMetadata().offset());
                }

                @Override
                public void onFailure(final Throwable throwable) {
                    logger.error("unable to send message ");
                }
            });
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
