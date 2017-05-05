package com.test.zx.lib.data.access.redis.operation;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

/**
 */
public class KeyCacheOperation extends AbstractCacheOperation {
	@Autowired
	private ShardedJedisPool shardedJedisPool;

	public KeyCacheOperation(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	@Override
	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public Long del(final String key) {
		return call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.del(key);
			}
		});
	}

	public Long del(final byte[] key) {
		return call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.del(key);
			}
		});
	}

	public String set(final String key, final String value) {
		return call(new CallRedisOperation<String>() {
			@Override
			public String callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.set(key, value);
			}
		});
	}

	public String get(final String key) {
		return call(new CallRedisOperation<String>() {
			@Override
			public String callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.get(key);
			}
		});
	}

	public Long expire(final String key, final int seconds) {
		return call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.expire(key, seconds);
			}
		});
	}

	public Long incr(final String key) {
		return incrBy(key, 1);
	}

	public Long incrBy(final String key, final long incr) {
		return call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.incrBy(key, incr);
			}
		});
	}

	public Long decr(final String key) {
		return decrBy(key, 1);
	}

	public Long decrBy(final String key, final long decr) {
		return call(new CallRedisOperation<Long>() {
			@Override
			public Long callback(ShardedJedis resource) throws UnsupportedEncodingException {
				return resource.decrBy(key, decr);
			}
		});
	}

	/**
	 * 效率不是很高，如果想要效率比较高，可以考虑将key，放入一个set，直接从set中取出
	 *
	 * @param keyPattern
	 * @return
	 */
	public Set<byte[]> getKeys(final String keyPattern) {
		return call(new CallRedisOperation<Set<byte[]>>() {
			@Override
			public Set<byte[]> callback(ShardedJedis resource) throws UnsupportedEncodingException {
				Collection<Jedis> allShards = resource.getAllShards();
				Set<byte[]> set = new HashSet<byte[]>();
				for (Jedis allShard : allShards) {
					Set<byte[]> keys = allShard.keys(keyPattern.getBytes("UTF-8"));
					set.addAll(keys);
				}
				return set;
			}
		});
	}

}
