package com.redis.test.redisspring;

import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

@SpringBootTest
class RedisSpringApplicationTests {
	@Autowired
	@Qualifier("redisTemplate")
//			@Qualifier("redisClusterTemplate")
			RedisTemplate template;
	private final String key = "tx-key";

	@After
	public void deleteCounter() {
		template.delete(key);
	}

	@Test
	void contextLoads() {
		final int valueSetInBetween = 23;
		final int valueSetWithinSession = 42;
		template.execute(new SessionCallback<Object>() {
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				operations.watch(key);
				setKeyByOtherBySession(valueSetWithinSession);
				operations.multi();
				operations.opsForValue().set(key, valueSetInBetween);
				operations.exec();
				return null;
			}
		});
		int value = (int) template.boundValueOps(key).get();
		Assert.assertEquals(valueSetInBetween, value);
	}

	private final void setKeyByOtherBySession(int value) {
		template.boundValueOps(key).set(value);
	}

}
