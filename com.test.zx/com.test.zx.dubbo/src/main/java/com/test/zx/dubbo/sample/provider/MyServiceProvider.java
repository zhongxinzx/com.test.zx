package com.test.zx.dubbo.sample.provider;

public interface MyServiceProvider {
	void saveStringToRedis(String key, String value);
}
