package com.redis.test.redisspring.config;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class LettuceConfig {
//    @Autowired
//    ClusterRedisConfig clusterRedisConfig;
    private StatefulRedisClusterConnection<String ,String> connection;
    private RedisClusterClient redisClusterClient;

    @Value("${spring.redis.cluster.nodes}")
    private List<String> nodes;
    @Value("${spring.redis.cluster.max-redirects:5}")
    private Integer maxRedirects;
    @Bean
    public StatefulRedisClusterConnection<String, String> getRedisClusterConnection(){
        List<RedisURI> redisURIS = nodes
                .stream()
                .map(host -> host.split(":"))
                .map(host ->RedisURI.create(host[0],Integer.parseInt(host[1])))
                .collect(Collectors.toList());
        //ip of any of the redis server on cluster
//        redisClusterClient = RedisClusterClient.create("redis://localhost:30001");
        redisClusterClient  = RedisClusterClient.create(redisURIS);
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