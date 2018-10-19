package com.wy.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wy.model.entity.User;
import com.wy.model.mapper.UserMapper;
import com.wy.model.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	@Cacheable(value = "user-key")
	public User selectByPrimaryKey(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	@Cacheable(value = "user-key")
	public User selectByName(String name) {
		return userMapper.selectByName(name);
	}

}
