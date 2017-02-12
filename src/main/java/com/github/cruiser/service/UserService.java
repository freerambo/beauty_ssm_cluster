package com.github.cruiser.service;

import com.github.cruiser.entity.User;

import java.util.List;

public interface UserService {

	List<User> getUserList(int offset, int limit);
	 
}
