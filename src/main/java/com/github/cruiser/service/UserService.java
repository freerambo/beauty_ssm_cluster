package com.github.cruiser.service;

import java.util.List;

import com.github.cruiser.entity.User;

public interface UserService {

	List<User> getUserList(int offset, int limit);
	 
}
