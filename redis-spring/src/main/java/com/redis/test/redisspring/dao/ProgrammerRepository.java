package com.redis.test.redisspring.dao;

import org.springframework.stereotype.Repository;

public interface ProgrammerRepository {
    void setProgrammerAsString(String idKey, String programmer);
    String getProgrammer(String idKey);
}
