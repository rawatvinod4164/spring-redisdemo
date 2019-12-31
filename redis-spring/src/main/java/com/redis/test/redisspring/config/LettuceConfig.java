package com.redis.test.redisspring.config;

import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class LettuceConfig {
    @Autowired
    ClusterRedisConfig clusterRedisConfig;
    @Bean
    public StatefulRedisClusterConnection<String, String> getRedisClusterConnection(){
//        List<RedisURI> redisURIS = clusterRedisConfig.getNodesList()
//                .stream()
//                .map(RedisURI::create)
//                .collect(Collectors.toList());
        //ip of any of the redis server on cluster
        RedisClusterClient redisClusterClient = RedisClusterClient.create("redis://localhost:30001");
        return redisClusterClient.connect();
    }
}