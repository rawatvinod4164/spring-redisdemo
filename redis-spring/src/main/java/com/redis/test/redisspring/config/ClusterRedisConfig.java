package com.redis.test.redisspring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Configuration
public class ClusterRedisConfig {
    @Value("${spring.redis.cluster.nodes}")
    private List<String> nodes;
    @Bean("lettuceConnectionFactory")
    public RedisConnectionFactory getLettuceConnectionnFactory(){
        RedisClusterConfiguration rc = new RedisClusterConfiguration(nodes);
        rc.setMaxRedirects(10);
        RedisConnectionFactory rcf = new LettuceConnectionFactory(rc);
        return rcf;
    }
    @Bean("redisTemplateCluster")
    public RedisTemplate<String, ?> getRedisTemplate(@Qualifier("lettuceConnectionFactory") RedisConnectionFactory connectionFactory){
        RedisTemplate<String, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}

