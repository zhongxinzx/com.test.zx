package com.test.zx.common.proxy;

public class RealObject implements Interface {

	@Override
	public void doSomething() {
		System.out.println("doSomething");
	}

	@Override
	public void doSomethinElse(String arg) {
		System.out.println("something else " + arg);
	}

}
