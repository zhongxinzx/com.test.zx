package com.test.zx.lib.data.access.redis.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.UnsupportedEncodingException;

/**
 * 缓存操作抽象类
 */
public abstract class AbstractCacheOperation {
        private static final byte[] EMPTY_DATA = new byte[0];

        protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractCacheOperation.class);

        public <T> T call(CallRedisOperation<T> callRedis) {
                ShardedJedis resource = getShardedJedisPool().getResource();
                try {
                        return callRedis.callback(resource);
                } catch (UnsupportedEncodingException e) {
                        LOGGER.error("callRedis error:", e);

                } finally {
                        getShardedJedisPool().returnResource(resource);
                }
                return null;
        }

        protected byte[] safeWrapKey(String key) {
                if (key != null) {
                        try {
                                return key.getBytes("UTF-8");
                        } catch (UnsupportedEncodingException e) {
                                LOGGER.error("safeWrapKey error:", e);
                        }
                        return EMPTY_DATA;
                } else {
                        return EMPTY_DATA;
                }
        }

        protected String safeConvertKey(byte[] key) {
                if (key != null) {
                        try {
                                return new String(key, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                                LOGGER.error("safeConvertKey error:", e);
                        }
                }
                return "";
        }

        public abstract ShardedJedisPool getShardedJedisPool();
}
