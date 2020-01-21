package com.redis.test.redisspring.controller;

import com.redis.test.redisspring.model.PublishModel;
import com.redis.test.redisspring.services.RedisMessagePublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: vinod.rawat
 * Date: 20/01/20
 */
@RestController
@RequestMapping("/publish")
public class PublisherController {
    @Autowired
    RedisMessagePublishService redisMessagePublishService;

    @PostMapping
    public void pulish(@RequestBody PublishModel model) {
        System.out.println("Recieved : " + model);
        redisMessagePublishService.publish(model);
    }
}
