package com.test.zx.lib.data.access.redis.operation;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.UnsupportedEncodingException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

/**
 */
public class SetCacheOperation extends AbstractCacheOperation {
	@Autowired
	private ShardedJedisPool shardedJedisPool;

	public SetCacheOperation(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	@Override
	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public Long add(final String key, final String member) {
		return call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {

				return resource.sadd(key, member);
			}
		});
	}

	public Long rem(final String key, final String member) {
		return call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.srem(key, member);
			}
		});
	}

	public Set<String> members(final String key) {
		return call(new CallRedisOperation<Set<String>>() {
			@Override
			public Set<String> callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.smembers(key);
			}
		});
	}

	public Boolean isMember(final String key, final String member) {
		return call(new CallRedisOperation<Boolean>() {
			@Override
			public Boolean callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.sismember(key, member);
			}
		});
	}

}
