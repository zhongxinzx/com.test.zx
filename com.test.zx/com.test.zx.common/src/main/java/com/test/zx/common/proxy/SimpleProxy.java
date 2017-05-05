package com.test.zx.common.proxy;

public class SimpleProxy implements Interface {
	
	private Interface proxied;

	public SimpleProxy(Interface proxied) {
		this.proxied = proxied;
	}
	
	@Override
	public void doSomething() {
		System.out.println("Simple proxy do something");
		proxied.doSomething();
	}

	@Override
	public void doSomethinElse(String arg) {
		System.out.println("Simple proxy do something else " + arg);
		proxied.doSomethinElse(arg);
	}

}
