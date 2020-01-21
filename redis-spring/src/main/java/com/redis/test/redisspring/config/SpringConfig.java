//package com.redis.test.redisspring.config;
//import com.redis.test.redisspring.services.RedisSubscriber;
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisPassword;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
//import org.springframework.data.redis.serializer.GenericToStringSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class SpringConfig {
//    @Value("${spring.redis.host}")
//    private String hostname;
//    @Value("${spring.redis.password}")
//    private String password;
//    @Value("${spring.redis.port}")
//    private int port;
//    @Value("${spring.redis.jedis.pool.max-active}")
//    private int maxTotal;
//    @Value("${spring.redis.jedis.pool.max-idle}")
//    private int maxIdle;
//    @Value("${spring.redis.jedis.pool.min-idle}")
//    private int minIdle;
//
//    public JedisClientConfiguration getJedisClientConfig(){
//        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jedisPoolingClientConfigurationBuilder =
//                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
//        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
//        genericObjectPoolConfig.setMaxIdle(maxIdle);
//        genericObjectPoolConfig.setMaxTotal(maxTotal);
//        genericObjectPoolConfig.setMinIdle(minIdle);
//        return jedisPoolingClientConfigurationBuilder.poolConfig(genericObjectPoolConfig).build();
//    }
//    @Bean("jedisConnectionFactory")
//    public JedisConnectionFactory getJedisConnnectionnFactory(){
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(hostname);
//        if(!(password==null||password=="")){
//            redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
//        }
//        redisStandaloneConfiguration.setPort(port);
//        return new JedisConnectionFactory(redisStandaloneConfiguration, getJedisClientConfig());
//    }
//    @Bean("redisTemplate")
//    public RedisTemplate redisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory){
//        RedisTemplate<String ,Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory);
//        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        return redisTemplate;
//    }
//
//    @Bean("test")
//    public ChannelTopic getTestChannelTopic(){
//        return new ChannelTopic("test");
//    }
//    @Bean("rest")
//    public ChannelTopic getRestChannelTopic(){
//        return new ChannelTopic("rest");
//    }
//    @Autowired
//    private RedisSubscriber redisSubscriber;
//    @Bean
//    MessageListenerAdapter messageListenerAdapter() {
//        return new MessageListenerAdapter(redisSubscriber);
//    }
//
//    @Bean
//    RedisMessageListenerContainer redisContainer() {
//        RedisMessageListenerContainer container
//                = new RedisMessageListenerContainer();
//        container.setConnectionFactory(getJedisConnnectionnFactory());
//        container.addMessageListener(messageListenerAdapter(), getTestChannelTopic());
//        return container;
//    }
//}
