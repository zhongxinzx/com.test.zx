package com.test.zx.security.shiro.service;

import java.util.Set;

import com.test.zx.security.shiro.vo.User;

public interface UserService {
	Set<String> findUserRoles(String userName);
	
	Set<String> findPermissonsByUserName(String userName);
	
	User findByUserName(String userName);
}
