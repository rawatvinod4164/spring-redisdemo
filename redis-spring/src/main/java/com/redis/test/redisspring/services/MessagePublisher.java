package com.redis.test.redisspring.services;

/**
 * Created by IntelliJ IDEA.
 * User: vinod.rawat
 * Date: 20/01/20
 */
public interface MessagePublisher<T> {
    void publish(T message);

}
