package com.test.zx.common.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler {

	private Object proxied;

	public DynamicProxyHandler(Object proxied) {
		this.proxied = proxied;
	}
	
	/**
	 * @param proxy 所代理的真实对象
	 * @param method 所要调用真实对象的某个方法的Method对象
	 * @param 调用真实对象某个方法时接受的参数
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("*** proxy: " + proxy.getClass() + ".method: " + method + ", args: " + args);
		if (null != args) {
			for (Object arg : args) {
				System.out.println("    " + arg);
			}
		}
		return method.invoke(proxied, args);
	}

}
