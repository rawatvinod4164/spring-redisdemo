package com.redis.test.redisspring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;
@Repository
public class ProgrammerDaoImpl implements ProgrammerRepository {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Override
    public void setProgrammerAsString(String idKey, String programmer) {
        redisTemplate.opsForValue().set(idKey,programmer);
        redisTemplate.expire(idKey,100, TimeUnit.SECONDS);
    }

    @Override
    public String getProgrammer(String idKey) {
        return (String) redisTemplate.opsForValue().get(idKey);
    }
}
