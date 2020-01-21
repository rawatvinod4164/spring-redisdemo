package com.redis.test.redisspring.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.test.redisspring.model.PublishModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: vinod.rawat
 * Date: 20/01/20
 */
@Component
public class RedisSubscriber implements MessageListener {
    @Autowired
    ObjectMapper mapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            PublishModel publishModel = mapper.readValue(message.toString(), PublishModel.class);
            System.out.println(publishModel);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("Message received: " + new String(message.getBody()));
    }
}
