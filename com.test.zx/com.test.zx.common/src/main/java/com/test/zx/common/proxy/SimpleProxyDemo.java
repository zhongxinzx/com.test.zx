package com.test.zx.common.proxy;

public class SimpleProxyDemo {

	public static void consumer(Interface iface) {
		iface.doSomething();
		iface.doSomethinElse("bonobo");
	}
	
	public static void main(String[] args) {
		consumer(new RealObject());
		consumer(new SimpleProxy(new RealObject()));
	}

}
