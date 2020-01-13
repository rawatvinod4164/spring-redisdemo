package com.redis.test.redisspring.dao;

import org.springframework.stereotype.Repository;
@Repository
public class ProgrammerDaoImpl implements ProgrammerRepository {

//    @Autowired
//    RedisTemplate<String, Object> redisTemplate;
    @Override
    public void setProgrammerAsString(String idKey, String programmer) {
//        redisTemplate.opsForValue().set(idKey,programmer);
//        redisTemplate.expire(idKey,100, TimeUnit.SECONDS);
    }

    @Override
    public String getProgrammer(String idKey) {
//        return (String) redisTemplate.opsForValue().get(idKey);
        return null;
    }
}
