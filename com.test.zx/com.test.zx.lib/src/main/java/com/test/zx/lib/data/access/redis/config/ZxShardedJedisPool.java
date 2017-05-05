package com.test.zx.lib.data.access.redis.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.test.zx.lib.property.PropertiesLoader;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

public class ZxShardedJedisPool implements InitializingBean, DisposableBean, FactoryBean<ShardedJedisPool> {

	Logger logger = Logger.getLogger(getClass());
	private static final String REDIS_INSTANCE = "redis.session.instance";
	private ShardedJedisPool shardedJedisPool;
	private JedisPoolConfig jedisPoolConfig;
	private PropertiesLoader propertiesLoader;
	private String prefix;
	/*@Value("${redis.instance.host}")
	private String masterHost;
	@Value("${redis.instance.port}")
	private String masterPort;*/

	public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
		this.jedisPoolConfig = jedisPoolConfig;
	}
	public PropertiesLoader getPropertiesLoader() {
		return propertiesLoader;
	}
	public void setPropertiesLoader(PropertiesLoader propertiesLoader) {
		this.propertiesLoader = propertiesLoader;
	}
	public String getPrefix() {
		if (StringUtils.isEmpty(this.prefix))
			this.prefix = REDIS_INSTANCE;
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void destroy() throws Exception {
		try {
			this.shardedJedisPool.destroy();
		} catch (Exception e) {
			this.logger.error(e);
		}
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.propertiesLoader.getProperties());
		String instance_str = this.propertiesLoader.getProperties().getProperty(getPrefix());
		Assert.notNull(instance_str);

		int instance = Integer.valueOf(instance_str).intValue();
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		for (int i = 1; i <= instance; i++) {
			String masterHost = this.propertiesLoader.getProperties().getProperty(getPrefix() + i + ".host");
			String masterPort = this.propertiesLoader.getProperties().getProperty(getPrefix() + i + ".port");
			JedisShardInfo jedisShardInfo = new JedisShardInfo(masterHost, Integer.valueOf(masterPort).intValue(),6000);
			String password = this.propertiesLoader.getProperties().getProperty(getPrefix() + i + ".password");
			if (!StringUtils.isEmpty(password)) {
				jedisShardInfo.setPassword(password);
			}
			shards.add(jedisShardInfo);
		}
		/*List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo jedisShardInfo = new JedisShardInfo(masterHost, Integer.valueOf(masterPort).intValue(),
				6000);
		shards.add(jedisShardInfo);*/
		this.shardedJedisPool = new ShardedJedisPool(this.jedisPoolConfig, shards);
	}

	public ShardedJedisPool getObject() throws Exception {
		return this.shardedJedisPool;
	}

	public Class<?> getObjectType() {
		return ShardedJedisPool.class;
	}

	public boolean isSingleton() {
		return true;
	}
}
