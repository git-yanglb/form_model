package com.wy.model.service;

import com.wy.model.entity.User;

public interface UserService {

	public User selectByPrimaryKey(Integer userId);
	
	public User selectByName(String name);

}
