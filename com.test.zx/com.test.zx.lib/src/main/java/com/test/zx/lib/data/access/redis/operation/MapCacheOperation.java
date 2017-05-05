package com.test.zx.lib.data.access.redis.operation;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

/**
 */
public class MapCacheOperation extends AbstractCacheOperation {

	@Autowired
	private ShardedJedisPool shardedJedisPool;

	public MapCacheOperation(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	public Long put(final String key, final String filed, final byte[] value) {
		return call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.hset(safeWrapKey(key), safeWrapKey(filed), value);
			}
		});
	}

	public Long put(final String key, final String filed, final String value) {
		return call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.hset(key, filed, value);
			}
		});
	}

	public Long incr(final String key, final String filed) {
		return incrBy(key, filed, 1);

	}

	public Long decr(final String key, final String filed) {
		return incrBy(key, filed, -1);
	}

	public Long incrBy(final String key, final String filed, final int value) {
		return call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.hincrBy(key, filed, value);
				// return resource.hset(safeWrapKey(key), safeWrapKey(filed),
				// value);
			}
		});
	}

	public byte[] get(final String key, final String filed) {
		return call(new CallRedisOperation<byte[]>() {
			@Override
			public byte[] callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.hget(safeWrapKey(key), safeWrapKey(filed));
			}
		});
	}

	public byte[] get(final String key, final byte[] filed) {
		return call(new CallRedisOperation<byte[]>() {
			@Override
			public byte[] callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.hget(safeWrapKey(key), filed);
			}
		});
	}

	public Long size(final String key) {
		return call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.hlen(safeWrapKey(key));
			}
		});
	}

	public Set<byte[]> keys(final String key) {
		return call(new CallRedisOperation<Set<byte[]>>() {
			@Override
			public Set<byte[]> callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.hkeys(safeWrapKey(key));
			}
		});
	}

	public Long del(final String key, final String... fileds) {
		byte[][] filedsBytes = new byte[fileds.length][];
		for (int i = 0; i < filedsBytes.length; i++) {
			filedsBytes[i] = safeWrapKey(fileds[i]);
		}
		return del(key, filedsBytes);
	}

	public Long del(final String key, final byte[]... fileds) {
		return call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.hdel(safeWrapKey(key), fileds);
			}
		});
	}

	public Map<byte[], byte[]> getAll(final String key) {
		return call(new CallRedisOperation<Map<byte[], byte[]>>() {
			@Override
			public Map<byte[], byte[]> callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.hgetAll(safeWrapKey(key));
			}
		});
	}

	public Map<String, String> getAllStr(final String key) {
		return call(new CallRedisOperation<Map<String, String>>() {
			@Override
			public Map<String, String> callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.hgetAll(key);
			}
		});
	}

	@Override
	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}
}
