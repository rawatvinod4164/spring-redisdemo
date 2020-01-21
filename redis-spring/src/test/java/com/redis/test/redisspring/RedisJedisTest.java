package com.redis.test.redisspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: vinod.rawat
 * Date: 20/01/20
 */
@SpringBootTest
public class RedisJedisTest {
    private static final String listKey = "lKey";
    private static final String mapKey = "mKey";
    private static final String setKey = "sKey";
    private static final String zsetKey = "zsKey";

    @Autowired
    @Qualifier("redisTemplateCluster")
//            @Qualifier("redisTemplate")
            RedisTemplate redisTemplate;

    @Test
    public void testList() {
        ListOperations listOperations = redisTemplate.opsForList();
        for (int i = 0; i < 10; i++) {
            listOperations.leftPush(listKey, i + "");
            listOperations.rightPush(listKey, i + "");
        }
        System.out.println(listOperations.range(listKey, 0, 100));
    }

    @Test
    public void testMap() {
        HashOperations hashOperations = redisTemplate.opsForHash();
        for (int i = 0; i < 10; i++) {
            hashOperations.put(mapKey, mapKey + i, i + "");
        }
        System.out.println(hashOperations.entries(mapKey));
        hashOperations.delete(mapKey, mapKey + 3);
        System.out.println(hashOperations.entries(mapKey));
        System.out.println(hashOperations.multiGet(mapKey, Arrays.asList(mapKey + 3, mapKey + 1, mapKey + 2)));

    }

    @Test
    public void testSet() {
        SetOperations setOperations = redisTemplate.opsForSet();
        for (int i = 0; i < 10; i++) {
            setOperations.add(setKey, i / 2 + "");
        }
        System.out.println(setOperations.members(setKey));
    }

    @Test
    public void testZSet() {
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        for (int i = 0; i < 10; i++) {
            zSetOperations.add(zsetKey, i * 100 + "", i * 10);
        }
//        System.out.println(zSetOperations.rangeByScoreWithScores(zsetKey,0,100));
        zSetOperations.rangeByScoreWithScores(zsetKey, 0, 100).stream();
    }


}
