package com.spring.app.service;


import java.util.List;

import com.spring.app.entity.User;
import com.spring.app.model.UserModel;

public interface IUserService {

	User getUserById(Long Id);
	List<User> getAll();
	User getByName(String name);
	User createNewAccount(UserModel user);
	User getByUserName(String userName);
	
}
