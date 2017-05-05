package com.test.zx.lib.data.access.redis.operation;

import redis.clients.jedis.ShardedJedis;

import java.io.UnsupportedEncodingException;

/**
 * 封装的一个用于操作redis的抽象类，通过其中的call方法，减少了对jedis连接池的获取和释放操作
 * @see {@link AbstractCacheOperation#call(CallRedisOperation)}
 *
 * @param <T>
 */
public abstract class CallRedisOperation<T> {
        public abstract T callback(ShardedJedis resource) throws UnsupportedEncodingException;
}
