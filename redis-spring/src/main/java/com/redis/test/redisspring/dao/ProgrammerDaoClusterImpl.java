package com.redis.test.redisspring.dao;

import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

@Repository
public class ProgrammerDaoClusterImpl implements ProgrammerRepository {
    @Autowired
    StatefulRedisClusterConnection<String, String> redisClusterConnection;
    @Override
    public void setProgrammerAsString(String idKey, String programmer) {
        redisClusterConnection.sync().set(idKey,programmer);
    }

    @Override
    public String getProgrammer(String idKey) {
        return redisClusterConnection.sync().get(idKey);
    }
}
