package com.test.zx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.zx.lib.data.access.redis.operation.KeyCacheOperation;
import com.test.zx.service.api.MyService;

@Service("myService")
public class MyServiceImpl implements MyService {

	@Autowired
	private KeyCacheOperation keyCacheOperation;
	
	@Override
	public void saveStringToRedis(String key, String value) {
		keyCacheOperation.set(key, value);
	}

}
