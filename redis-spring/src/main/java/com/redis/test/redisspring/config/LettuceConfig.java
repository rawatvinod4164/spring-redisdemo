package com.redis.test.redisspring.config;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class LettuceConfig {
    @Autowired
    ClusterRedisConfig clusterRedisConfig;
    private StatefulRedisClusterConnection<String ,String> connection;
    private RedisClusterClient redisClusterClient;
    @Bean
    public StatefulRedisClusterConnection<String, String> getRedisClusterConnection(){
//        List<RedisURI> redisURIS = clusterRedisConfig.getNodesList()
//                .stream()
//                .map(RedisURI::create)
//                .collect(Collectors.toList());
        //ip of any of the redis server on cluster
        redisClusterClient = RedisClusterClient.create("redis://localhost:30001");
        connection = redisClusterClient.connect();
        connection.setReadFrom(ReadFrom.REPLICA_PREFERRED);
        return connection;
    }
    @PreDestroy
    public void killConnection(){
        connection.close();
        redisClusterClient.shutdown();

    }

}