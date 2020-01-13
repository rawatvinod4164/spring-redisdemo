package com.redis.test.redisspring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: vinod.rawat
 * Date: 13/01/20
 */
@Service
public class ProgrammerDao {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    public void setProgrammerAsString(String idKey, String programmer){
        redisTemplate.opsForValue().set(idKey, programmer);
    }
    public String getProgrammer(String idKey){
        return redisTemplate.opsForValue().get(idKey);
    }
}
