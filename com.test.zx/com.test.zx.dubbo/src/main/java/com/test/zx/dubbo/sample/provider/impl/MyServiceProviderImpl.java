package com.test.zx.dubbo.sample.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.zx.dubbo.sample.provider.MyServiceProvider;
import com.test.zx.service.api.MyService;

@Service("myServiceProvider")
public class MyServiceProviderImpl implements MyServiceProvider {

	@Autowired
	private MyService myService;
	
	@Override
	public void saveStringToRedis(String key, String value) {
		myService.saveStringToRedis(key, value);
	}

}
