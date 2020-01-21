package com.redis.test.redisspring;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: vinod.rawat
 * Date: 17/01/20
 */
@SpringBootTest
public class ReddisonLockTest implements Serializable {
    @Autowired
    RedissonClient redissonClient;
    private static final String key = "RKey1";

    @Test
    public void Test() throws InterruptedException {

        RBucket<T> rBucket = redissonClient.getBucket(key);
        rBucket.set(new T());
        System.out.println(redissonClient.getBucket(key).get());
    }

    class T implements Serializable {
        int x = 10;
        String y = "string";

        @Override
        public String toString() {
            return "T{" +
                    "x=" + x +
                    ", y='" + y + '\'' +
                    '}';
        }
    }

    @Test
    public void Test2() {
        RLock lock = redissonClient.getFairLock(key + "_lock");
        lock.lock();
        System.out.println(redissonClient.getBucket(key).get());
        lock.unlock();
    }

}
