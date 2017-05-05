package com.test.zx.lib.data.access.redis.operation;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 */
public class ListCacheOperation extends AbstractCacheOperation {
	@Autowired
	private ShardedJedisPool shardedJedisPool;

	public ListCacheOperation(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	@Override
	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public Long lPush(final String key, final byte[] data) {
		return call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.lpush(key.getBytes("UTF-8"), data);
			}
		});
	}

	public byte[] rPop(final String key) {
		return call(new CallRedisOperation<byte[]>() {
			@Override
			public byte[] callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.rpop(key.getBytes("UTF-8"));
			}
		});
	}

	public Long rPush(final String key, final byte[] data) {
		return call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.rpush(key.getBytes("UTF-8"), data);
			}
		});
	}

	public long length(final String key) {
		Long call = call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.llen(key.getBytes("UTF-8"));
			}
		});
		return call != null ? call : 0;
	}

	public List<byte[]> getAll(final String key) {
		return call(new CallRedisOperation<List<byte[]>>() {
			@Override
			public List<byte[]> callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.lrange(key.getBytes("UTF-8"), 0, -1);
			}
		});
	}

}
