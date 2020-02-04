package com.redis.test.redisspring.config;

import com.redis.test.redisspring.services.RedisSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

@Configuration
public class ClusterRedisConfig {
    @Value("${spring.redis.cluster.nodes}")
    private List<String> nodes;

    @Bean("lettuceConnectionFactory")
    public RedisConnectionFactory getLettuceConnectionnFactory() {
        RedisClusterConfiguration rc = new RedisClusterConfiguration(nodes);
        RedisConnectionFactory rcf = new LettuceConnectionFactory(rc);
        return rcf;
    }

    @Bean("redisTemplateCluster")
    public RedisTemplate<String, Object> getRedisTemplate(@Qualifier("lettuceConnectionFactory") RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean("test")
    public ChannelTopic getTestChannelTopic() {
        return new ChannelTopic("test");
    }

    @Bean("rest")
    public ChannelTopic getRestChannelTopic() {
        return new ChannelTopic("rest");
    }

    @Autowired
    private RedisSubscriber redisSubscriber;

    @Bean
    MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(redisSubscriber);
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        container.setConnectionFactory(getLettuceConnectionnFactory());
        container.addMessageListener(messageListenerAdapter(), getTestChannelTopic());
        return container;
    }
}

