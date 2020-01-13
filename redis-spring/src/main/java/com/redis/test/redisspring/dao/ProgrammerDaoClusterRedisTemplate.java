package com.redis.test.redisspring.dao;

import com.redis.test.redisspring.model.Programmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: vinod.rawat
 * Date: 10/01/20
 */
@Service
public class ProgrammerDaoClusterRedisTemplate {
    @Autowired
    RedisTemplate redisTemplate;

    public void setProgrammer(String key, Programmer programmer) {
        redisTemplate.opsForValue().set(key, programmer);
    }
    public Programmer getProgrammer(String key) {
        return (Programmer) redisTemplate.opsForValue().get(key);
    }
}
