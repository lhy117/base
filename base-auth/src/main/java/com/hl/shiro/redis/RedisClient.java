package com.hl.shiro.redis;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.crazycake.shiro.IRedisManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 自定义的shiro redis client
 * @author lhy
 * @date 2018年3月26日 下午4:54:48
 */
public class RedisClient implements IRedisManager{

	@Resource
	private RedisTemplate<byte[], byte[]> redis;
	
	@Override
	public byte[] get(byte[] key) {
		return redis.opsForValue().get(key);
	}
	
	@Override
	public byte[] set(byte[] key, byte[] value, int expire) {
		redis.opsForValue().set(key, value);
		redis.expire(key, expire, TimeUnit.MILLISECONDS);
		return redis.opsForValue().get(key);
	}

	@Override
	public void del(byte[] key) {
		redis.delete(key);
	}

	@Override
	public Long dbSize(byte[] pattern) {
		return new Long(keys(pattern).size());
	}

	@Override
	public Set<byte[]> keys(byte[] pattern) {
		return redis.keys(pattern);
	}
}
