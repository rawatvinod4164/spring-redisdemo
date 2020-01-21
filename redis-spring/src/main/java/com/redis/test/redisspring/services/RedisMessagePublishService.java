package com.redis.test.redisspring.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.test.redisspring.model.PublishModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: vinod.rawat
 * Date: 20/01/20
 */
@Component
public class RedisMessagePublishService implements MessagePublisher<PublishModel> {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    @Qualifier("test")
    ChannelTopic channelTopic;
    @Autowired
    ObjectMapper mapper;

    @Override
    public void publish(PublishModel message) {
        try {
            redisTemplate.convertAndSend(channelTopic.getTopic(), mapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}

