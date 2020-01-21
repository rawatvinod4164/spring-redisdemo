package com.redis.test.redisspring.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: vinod.rawat
 * Date: 17/01/20
 */
@Component
public class ReddisonConfig {
    @Bean
    public RedissonClient getRedissonClient() throws IOException {
        Config config = Config.fromYAML(ResourceUtils.getFile("classpath:redisson.yaml"));
        config.setCodec(new TypedJsonJacksonCodec(Object.class));
        return Redisson.create(config);
    }
}
