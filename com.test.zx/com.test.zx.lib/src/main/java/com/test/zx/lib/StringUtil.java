package com.test.zx.lib;

public class StringUtil {
	public static boolean isEmpty(String str) {
		return (null == str || "".equals(str.trim()));
	}
}
